package com.school.edsense_lite.recomendations;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.messages.MessagesModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecomendationFragment extends BaseFragment {
@BindView(R.id.recycler_view)
RecyclerView recomendationRecyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecomendationRecyclerViewAdapter recomendationRecyclerViewAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecomendationFragment newInstance() {
        RecomendationFragment fragment = new RecomendationFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomendation, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        recomendationRecyclerView.setHasFixedSize(true);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        recomendationRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        ArrayList<RecomendationModel> list = getRecomendationsList();
        recomendationRecyclerViewAdapter = new RecomendationRecyclerViewAdapter(getContext(),list);
        recomendationRecyclerView.setAdapter(recomendationRecyclerViewAdapter);

        return view;

    }


    private ArrayList<RecomendationModel> getRecomendationsList() {
        ArrayList<RecomendationModel> items = new ArrayList<>();
        items.add(new RecomendationModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Ridhisri Chowdhary Vajendla", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Aanya Rajeev Kumar", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Maanvi Mittal", "Lorem Ipsum is simply dummy text"));
        items.add(new RecomendationModel("Ravi Sarayu Reddy", "Lorem Ipsum is simply dummy text"));

        return items;
    }
}
