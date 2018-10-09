package com.school.edsense_lite.notes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.attendance.AttendanceRecyclerViewAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.notesRecyclerview)
    RecyclerView notesRecyclerView;
    @BindView(R.id.sectionTV)
    TextView sectionTV;
    @BindView(R.id.date)
    TextView dateTV;

    @OnClick(R.id.sectionLayout)
    public void ClickOnSectionLayout(){
        displaySectionsPopup();
    }
    @OnClick(R.id.calendar)
    public void ClickOnCalendar(){
        displayDateDialog();
    }
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
    private void displaySectionsPopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.TOP;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

        final String[] hospitals = getResources().getStringArray(R.array.sections_array);

        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, hospitals));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                sectionTV.setText(hospitals[position]);
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void displayDateDialog(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setListener(NotesFragment.this);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String strDate = padding(i2)+" - "+padding(i1)+" - "+padding(i);
        dateTV.setText(strDate);
    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
}
