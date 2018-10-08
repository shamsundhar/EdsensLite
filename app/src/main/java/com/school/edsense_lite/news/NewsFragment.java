package com.school.edsense_lite.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.AWFActivity;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.today.Assignment;
import com.school.edsense_lite.today.AssignmentRecyclerViewAdapter;
import com.school.edsense_lite.today.Schedule;
import com.school.edsense_lite.today.ScheduleRecyclerViewAdapter;
import com.school.edsense_lite.today.TodayFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.newsRecyclerview)
    RecyclerView newsRecyclerView;

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
        newsRecyclerView.setAdapter(newsRecyclerViewAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsRecyclerViewAdapter.setItems(getNewsList());
        newsRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }
    private ArrayList<Object> getNewsList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new News("Bandh", "lorem ipsum","12-12-1998"));
        items.add(new News("Bandasash", "lorem ipsum","12-12-1994"));
        items.add(new News("Bansasadh", "lorem ipsum","12-12-1991"));
        items.add(new News("Bansasadh", "lorem ipsum","12-12-1995"));
        items.add(new News("Basasndh", "lorem ipsum","12-12-1997"));
        items.add(new News("Basasndh", "lorem ipsum","12-12-1994"));
        items.add(new News("Basasandh", "lorem ipsum","12-12-1999"));
        items.add(new News("Basasasndh", "lorem ipsum","12-12-1990"));
        items.add(new News("Basasandh", "lorem ipsum","12-12-1993"));
        items.add(new News("Baasasandh", "lorem ipsum","12-12-1996"));

        return items;
    }
}
