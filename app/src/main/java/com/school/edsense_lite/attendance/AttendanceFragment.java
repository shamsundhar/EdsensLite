package com.school.edsense_lite.attendance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.events.Event;
import com.school.edsense_lite.events.EventsRecyclerViewAdapter;
import com.school.edsense_lite.today.TodayFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceFragment extends BaseFragment {
    @BindView(R.id.attendanceRecyclerview)
    RecyclerView attendanceRecyclerView;
    AttendanceRecyclerViewAdapter attendanceRecyclerViewAdapter;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceFragment newInstance() {
        AttendanceFragment fragment = new AttendanceFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        attendanceRecyclerViewAdapter = new AttendanceRecyclerViewAdapter();
        attendanceRecyclerView.setAdapter(attendanceRecyclerViewAdapter);
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        attendanceRecyclerViewAdapter.setItems(getAttendanceList());
        attendanceRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }
    private ArrayList<Object> getAttendanceList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Attendance("Shyam", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam2", "Early Out","Going to home town",""));
        items.add(new Attendance("Shyam3", "Late In","Going to home town",""));
        items.add(new Attendance("Shyam4", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam5", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam6", "Early Out","Going to home town",""));
        items.add(new Attendance("Shyam7", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam8", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam9", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam10", "Late In","Going to home town",""));
        items.add(new Attendance("Shyam11", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam12", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam13", "Absent","Going to home town",""));
        items.add(new Attendance("Shyam14", "Early Out","Going to home town",""));
        return items;
    }
}
