package com.school.edsense_lite.today;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.messages.MessagesModel;
import com.school.edsense_lite.messages.MessagesRecyclerViewAdapter;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_EVENTS;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_NEWS;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;

public class TodayFragment extends BaseFragment {
    @BindView(R.id.todayRecyclerview)
    RecyclerView todayRecyclerView;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.textMySchedule)
    TextView myScheduleTV;
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

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        String currentDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT2);
        if(!bearerToken.isEmpty()) {

            List<Observable<?>> requests = new ArrayList<>();

            // Make a collection of all requests you need to call at once, there can be any number of requests, not only 3. You can have 2 or 5, or 100.
            requests.add(todayApi.getSchedules(bearerToken));
            requests.add(todayApi.getAssignmentsForLoginUser(bearerToken));

//            Observable<ScheduleResponse> scheduleResponseObservable = todayApi.getSchedules(bearerToken)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());
//
//            Observable<AssignmentResponse> assignmentResponseObservable = todayApi.getAssignmentsForLoginUser(bearerToken)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());


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
                                    List<Object> objectList = new ArrayList<Object>();
                                    List<Row> scheduleRows = scheduleAndAssignment.getScheduleResponse().getResponse().getRows();
                                    for(int i = 0; i<scheduleRows.size(); i++){
                                        objectList.add(scheduleRows.get(i));
                                    }
                                 //   objectList.add(scheduleAndAssignment.getScheduleResponse());
                                    objectList.add(new NewsEvents());
                                    objectList.add(new Header("Assignments"));
                                    List<AssignmentResponse.Response> assignmentResponseList = scheduleAndAssignment.getAssignmentResponse().getResponse();
                                    for(int i = 0; i<assignmentResponseList.size(); i++){
                                        objectList.add(assignmentResponseList.get(i));
                                    }
                                 //   objectList.add(scheduleAndAssignment.getAssignmentResponse());
                                    progressDialog.dismiss();
                                    scheduleRecyclerViewAdapter.setItems(objectList);
                                    scheduleRecyclerViewAdapter.notifyDataSetChanged();

                                }
                            },

                            // Will be triggered if any error during requests will happen
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable e) throws Exception {
                                    progressDialog.dismiss();
                                    //Do something on error completion of requests
                                }
                            }
                    );


//            todayApi.getSchedules(bearerToken)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<ScheduleResponse>() {
//                        @Override
//                        public void onError(Throwable e) {
//                            progressDialog.dismiss();
//                        }
//                        @Override
//                        public void onComplete() {
//
//                        }
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//                        @Override
//                        public void onNext(ScheduleResponse scheduleResponse) {
//                            progressDialog.dismiss();
//                            if (scheduleResponse.getIsSuccess().equals("true")) {
//                                scheduleRecyclerViewAdapter.setItems(prepareList(scheduleResponse));
//                                scheduleRecyclerViewAdapter.notifyDataSetChanged();
//                            } else if (!scheduleResponse.getErrorCode().equals("200")) {
//                                //display error.
//                                new CustomAlertDialog().showAlert1(
//                                        getActivity(),
//                                        R.string.text_login_failed,
//                                        scheduleResponse.getErrorMessage(),
//                                        null);
//                            }
//                        }
//                    });
//            todayApi.getAssignmentsForLoginUser(bearerToken)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<AssignmentResponse>() {
//                        @Override
//                        public void onError(Throwable e) {
//                            progressDialog.dismiss();
//                        }
//                        @Override
//                        public void onComplete() {
//
//                        }
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//                        @Override
//                        public void onNext(AssignmentResponse assignmentResponse) {
//                            progressDialog.dismiss();
//                            if (assignmentResponse.getIsSuccess().equals("true")) {
//                                scheduleRecyclerViewAdapter.setItems(prepareList2(assignmentResponse));
//                                scheduleRecyclerViewAdapter.notifyDataSetChanged();
//                            } else if (!assignmentResponse.getErrorCode().equals("200")) {
//                                //display error.
//                                new CustomAlertDialog().showAlert1(
//                                        getActivity(),
//                                        R.string.text_login_failed,
//                                        assignmentResponse.getErrorMessage(),
//                                        null);
//                            }
//                        }
//                    });
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
        myScheduleTV.setTypeface(tf);
    }
    private ArrayList<Object> prepareList(ScheduleResponse scheduleResponse){
        ArrayList<Object> items = new ArrayList<>();
        items.addAll(scheduleResponse.getResponse().getRows());

        items.add(new NewsEvents());

        items.add(new Header("Assignments"));

        items.add(new Assignment("12-1-2018", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2013", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2015", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2019", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        return items;
    }
    private ArrayList<Object> prepareList2(AssignmentResponse assignmentResponse){
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Schedule("9:30", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:31", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:32", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:33", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:34", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:35", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:36", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:37", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:38", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:39", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:40", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:41", "French","IX","Lorem Ipsum"));

        items.add(new NewsEvents());

        items.add(new Header("Assignments"));
        items.addAll(assignmentResponse.getResponse());

        return items;
    }
    private ArrayList<Object> getScheduleList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Schedule("9:30", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:31", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:32", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:33", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:34", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:35", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:36", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:37", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:38", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:39", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:40", "French","IX","Lorem Ipsum"));
        items.add(new Schedule("9:41", "French","IX","Lorem Ipsum"));

        items.add(new NewsEvents());

        items.add(new Header("Assignments"));

        items.add(new Assignment("12-1-2018", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2013", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2015", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2019", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        return items;
    }
    private ArrayList<Object> getAssignmentList(){
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Assignment("12-1-2018", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2013", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2015", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2016", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2012", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2019", "12-12-2014","Pending","Lorem Ipsum"));
        items.add(new Assignment("12-1-2014", "12-12-2014","Pending","Lorem Ipsum"));

        return items;
    }

}
