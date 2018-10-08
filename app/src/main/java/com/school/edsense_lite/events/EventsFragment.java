package com.school.edsense_lite.events;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsFragment extends BaseFragment {
    @BindView(R.id.eventsRecyclerview)
    RecyclerView eventsRecyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
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
        View view = inflater.inflate(R.layout.fragment_events, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter();
        eventsRecyclerView.setAdapter(eventsRecyclerViewAdapter);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        eventsRecyclerViewAdapter.setItems(getEventsList());
        eventsRecyclerViewAdapter.notifyDataSetChanged();

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
