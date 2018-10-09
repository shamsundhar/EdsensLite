package com.school.edsense_lite.notes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.attendance.AttendanceRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesFragment extends BaseFragment {
    @BindView(R.id.notesRecyclerview)
    RecyclerView notesRecyclerView;
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);


        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter();
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        notesRecyclerViewAdapter.setItems(getNotesList());
        notesRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }
    private ArrayList<Object> getNotesList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Note("Shyam", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam1", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam2", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam3", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam4", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam5", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam6", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam7", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam8", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam9", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam10", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam11", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam12", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam13", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam14", "Calm, Caring","Going to home town",""));
        items.add(new Note("Shyam15", "Calm, Caring","Going to home town",""));

        return items;
    }
}
