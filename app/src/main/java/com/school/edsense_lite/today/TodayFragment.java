package com.school.edsense_lite.today;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.messages.MessagesFragment;
import com.school.edsense_lite.model.AssignmentResponseModel;
import com.school.edsense_lite.model.Row;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.utils.Common;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.today.Header.ASSIGNMENT_HEADER;
import static com.school.edsense_lite.today.Header.SCHEDULE_HEADER;
import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_EVENTS;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_NEWS;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT9;
import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

public class TodayFragment extends BaseFragment {
    @BindView(R.id.todayRecyclerview)
    RecyclerView todayRecyclerView;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.date)
    TextView dateTV;

    private ScheduleRecyclerViewAdapter scheduleRecyclerViewAdapter;

    @Inject
    TodayApi todayApi;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance() {
        TodayFragment fragment = new TodayFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        applyFonts();
        setCurrentDate();
        scheduleRecyclerViewAdapter = new ScheduleRecyclerViewAdapter();
        todayRecyclerView.setAdapter(scheduleRecyclerViewAdapter);
        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessagesFragment.mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        String currentDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT9);
        if(!bearerToken.isEmpty()) {

            List<Observable<?>> requests = new ArrayList<>();

            // Make a collection of all requests you need to call at once, there can be any number of requests, not only 3. You can have 2 or 5, or 100.
            requests.add(todayApi.getSchedules(currentDate, bearerToken));
            requests.add(todayApi.getAssignmentsForLoginUser(bearerToken));

            if(Common.isNetworkAvailable(getActivity())) {
                // Zip all requests with the Function, which will receive the results.
                Observable.zip(
                        requests,
                        new Function<Object[], ScheduleAndAssignment>() {
                            @Override
                            public ScheduleAndAssignment apply(Object[] objects) throws Exception {
                                // Objects[] is an array of combined results of completed requests

                                // do something with those results and emit new event

                                List<Object> objectList = Arrays.asList(objects);

                                return new ScheduleAndAssignment(objects[0], objects[1]);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        // After all requests had been performed the next observer will receive the Object, returned from Function
                        .subscribe(
                                // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
                                new Consumer<ScheduleAndAssignment>() {
                                    @Override
                                    public void accept(ScheduleAndAssignment scheduleAndAssignment) throws Exception {
                                        //Do something on successful completion of all requests

                                        List<Row> scheduleRows = scheduleAndAssignment.getScheduleResponse().getResponse().getRows();
                                        for (int i = 0; i < scheduleRows.size(); i++) {
                                            //objectList.add(scheduleRows.get(i));
                                            MessagesFragment.mEdsenseDatabase.getScheduleRowDao().insert(scheduleRows.get(i));
                                        }
                                        //objectList.add(scheduleAndAssignment.getScheduleResponse());
                                        //objectList.add(new NewsEvents());
                                        //objectList.add(new Header("Assignments", ASSIGNMENT_HEADER));
                                        List<AssignmentResponseModel> assignmentResponseList = scheduleAndAssignment.getAssignmentResponse().getResponse();
                                        for (int i = 0; i < assignmentResponseList.size(); i++) {
                                            //objectList.add(assignmentResponseList.get(i));
                                            MessagesFragment.mEdsenseDatabase.assignmentDao().insert(assignmentResponseList.get(i));
                                        }

                                        displayAssignmentsAndSchedulesFromDB(progressDialog);


                                    }
                                },

                                // Will be triggered if any error during requests will happen
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable e) throws Exception {
                                        progressDialog.dismiss();
                                        //Do something on error completion of requests
                                        if (e instanceof StreamResetException) {
                                            //login again
                                            e.printStackTrace();
                                            relogin();
                                        }
                                    }
                                }
                        );
            }else{
                displayAssignmentsAndSchedulesFromDB(progressDialog);
            }
        }

        scheduleRecyclerViewAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void onNewsClicked(int position) {
                Intent in = new Intent(getActivity(), AWFActivity.class);
                in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_NEWS);
                getActivity().startActivity(in);
            }

            @Override
            public void onEventsClicked(int position) {
                Intent in = new Intent(getActivity(), AWFActivity.class);
                in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_EVENTS);
                getActivity().startActivity(in);
            }
        });

        return view;
    }

    private void displayAssignmentsAndSchedulesFromDB(ProgressDialog progressDialog) {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(new Header("My Schedule", SCHEDULE_HEADER));
        List<Row> scheduleRows = MessagesFragment.mEdsenseDatabase.getScheduleRowDao().getAllRows();
        Collections.sort(scheduleRows, new Comparator<Row>() {
            public int compare(Row o1, Row o2) {
                String strDateFormat = "HH:MM a";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                try {
                    return (sdf.parse(o1.getStartTimeSlot()+" "+o1.getTimePeriod())).compareTo(sdf.parse(o2.getStartTimeSlot()+" "+o2.getTimePeriod()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        if(scheduleRows.size() > 0){
            objectList.addAll(scheduleRows);
        }
        else{
            objectList.add(new NoData());
        }

//        for(int i = 0; i<scheduleRows.size(); i++){
//            objectList.add(scheduleRows.get(i));
//        }

        objectList.add(new NewsEvents());
        objectList.add(new Header("Assignments", ASSIGNMENT_HEADER));
        List<AssignmentResponseModel> assignmentResponseList = MessagesFragment.mEdsenseDatabase.assignmentDao().getAllAssignments();
        if(assignmentResponseList.size() > 0){
            objectList.addAll(assignmentResponseList);
        }
        else{
            objectList.add(new NoData());
        }

//        for(int i = 0; i<assignmentResponseList.size(); i++){
//            objectList.add(assignmentResponseList.get(i));
//        }
        progressDialog.dismiss();
        scheduleRecyclerViewAdapter.setItems(objectList);
        scheduleRecyclerViewAdapter.notifyDataSetChanged();

    }

    private void setCurrentDate(){
        dateTV.setText(DateTimeUtils.getCurrentDateInString(DATE_FORMAT1));
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        titleTV.setTypeface(tf);
        dateTV.setTypeface(tf);
    }

}
