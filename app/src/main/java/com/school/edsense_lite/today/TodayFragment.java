package com.school.edsense_lite.today;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_EVENTS;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_NEWS;

public class TodayFragment extends BaseFragment {
    @BindView(R.id.scheduleRecyclerview)
    RecyclerView scheduleRecyclerView;
    @BindView(R.id.assignmentsRecyclerview)
    RecyclerView assignmentRecyclerView;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.textMySchedule)
    TextView myScheduleTV;
    @BindView(R.id.date)
    TextView dateTV;

    private ScheduleRecyclerViewAdapter scheduleRecyclerViewAdapter;
    private AssignmentRecyclerViewAdapter assignmentRecyclerViewAdapter;
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
    @OnClick(R.id.newsLayout)
    public void clickOnNews(){
        Intent in = new Intent(getActivity(), AWFActivity.class);
        in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_NEWS);
        getActivity().startActivity(in);
    }
    @OnClick(R.id.eventsLayout)
    public void clickOnEvents(){
        Intent in = new Intent(getActivity(), AWFActivity.class);
        in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_EVENTS);
        getActivity().startActivity(in);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
       applyFonts();

     //   scheduleRecyclerViewAdapter = new ScheduleRecyclerViewAdapter();
     //   scheduleRecyclerView.setAdapter(scheduleRecyclerViewAdapter);
     //   scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

     //   assignmentRecyclerViewAdapter = new AssignmentRecyclerViewAdapter();
      //  assignmentRecyclerView.setAdapter(assignmentRecyclerViewAdapter);
     //   assignmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

     //   scheduleRecyclerViewAdapter.setItems(getScheduleList());
     //   scheduleRecyclerViewAdapter.notifyDataSetChanged();

      //  assignmentRecyclerViewAdapter.setItems(getAssignmentList());
      //  assignmentRecyclerViewAdapter.notifyDataSetChanged();

        return view;
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
