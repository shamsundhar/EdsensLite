package com.school.edsense_lite.events;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.news.News;
import com.school.edsense_lite.news.NewsFragment;
import com.school.edsense_lite.news.NewsRecyclerViewAdapter;
import com.school.edsense_lite.today.EventsRequest;
import com.school.edsense_lite.today.EventsResponse;
import com.school.edsense_lite.today.NewsRequest;
import com.school.edsense_lite.today.NewsResponse;
import com.school.edsense_lite.today.TodayApi;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventsFragment extends BaseFragment {
    @BindView(R.id.eventsRecyclerview)
    RecyclerView eventsRecyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;

    @Inject
    TodayApi todayApi;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


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
        if(!bearerToken.isEmpty()) {
            todayApi.getEvents(bearerToken, eventsRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<EventsResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            System.out.println("error called::"+e.fillInStackTrace());
                            progressDialog.dismiss();
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
                                eventsRecyclerViewAdapter.setItems(new ArrayList<Object>(eventsResponse.getResponse()));
                                eventsRecyclerViewAdapter.notifyDataSetChanged();

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

        return view;
    }
    private ArrayList<Object> getEventsList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Event("Bandh", "lorem ipsum","12-12-1998"));
        items.add(new Event("holidya", "lorem ipsum","12-12-1994"));
        items.add(new Event("indipendence day", "lorem ipsum","12-12-1991"));
        items.add(new Event("policia", "lorem ipsum","12-12-1995"));
        items.add(new Event("Basasndh", "lorem ipsum","12-12-1997"));
        items.add(new Event("divadsdsd dsdsd li", "lorem ipsum","12-12-1994"));
        items.add(new Event("dusserah", "lorem ipsum","12-12-1999"));
        items.add(new Event("ganesh", "lorem ipsum","12-12-1990"));
        items.add(new Event("bharath bandh", "lorem ipsum","12-12-1993"));
        items.add(new Event("ramzaan", "lorem ipsum","12-12-1996"));

        return items;
    }
}
