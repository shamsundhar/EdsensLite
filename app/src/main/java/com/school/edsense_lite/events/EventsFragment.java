package com.school.edsense_lite.events;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.news.News;
import com.school.edsense_lite.news.NewsFragment;
import com.school.edsense_lite.news.NewsRecyclerViewAdapter;
import com.school.edsense_lite.today.EventsRequest;
import com.school.edsense_lite.today.EventsResponse;
import com.school.edsense_lite.today.EventsResponseModel;
import com.school.edsense_lite.today.NewsRequest;
import com.school.edsense_lite.today.NewsResponse;
import com.school.edsense_lite.today.TodayApi;
import com.school.edsense_lite.utils.Common;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

public class EventsFragment extends BaseFragment {
    @BindView(R.id.eventsRecyclerview)
    RecyclerView eventsRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    @BindView(R.id.tv1)
    TextView tv1;
    @Inject
    TodayApi todayApi;
    public static EdsenseDatabase mEdsenseDatabase;
    private ArrayList<EventsResponseModel> eventsList;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);

        applyFonts();
        empty_view.setText(R.string.empty_events_list_message);
        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter();
        eventsRecyclerView.setAdapter(eventsRecyclerViewAdapter);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        EventsRequest eventsRequest = new EventsRequest();
        EventsRequest.Value value = eventsRequest.new Value();
        value.setValue("0");
        value.setTop("2");
        eventsRequest.setValue(value);
        if(Common.isNetworkAvailable(getActivity())) {
        if (!bearerToken.isEmpty()) {
            todayApi.getEvents(bearerToken, eventsRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<EventsResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            if (e instanceof StreamResetException) {
                                //login again
                                e.printStackTrace();
                                relogin();
                            }
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("complete called");
                        }

                        @Override
                        public void onSubscribe(Disposable d) {
                            System.out.println("onsubscribe called");
                        }

                        @Override
                        public void onNext(EventsResponse eventsResponse) {
                            progressDialog.dismiss();
                            if (eventsResponse.getIsSuccess().equals(true)) {
                                if (eventsResponse.getResponse() != null && !eventsResponse.getResponse().isEmpty()) {
                                    for (EventsResponseModel event : eventsResponse.getResponse()) {
                                        if (event != null) {
                                            mEdsenseDatabase.getEventsDao().insert(event);
                                        }
                                    }
                                    displayEventsFromDB();
                                }


                            } else if (!eventsResponse.getErrorCode().equals(200)) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_failed,
                                        eventsResponse.getErrorMessage(),
                                        null);
                            }
                        }
                    });
        }
    }else{
            progressDialog.dismiss();
            displayEventsFromDB();
        }

        return view;
    }

    public void displayEventsFromDB(){
        eventsList = (ArrayList<EventsResponseModel>)mEdsenseDatabase.getEventsDao().getAllEvents();
        if (eventsList != null && !eventsList.isEmpty()) {
//            eventsRecyclerView.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);

        } else {
//            eventsRecyclerView.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        }
        eventsRecyclerViewAdapter.setItems(eventsList);
        eventsRecyclerViewAdapter.notifyDataSetChanged();
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        tv1.setTypeface(tf);
    }
}
