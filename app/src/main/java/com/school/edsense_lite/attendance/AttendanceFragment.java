package com.school.edsense_lite.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.events.Event;
import com.school.edsense_lite.events.EventsRecyclerViewAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;
import com.school.edsense_lite.today.TodayFragment;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT3;

public class AttendanceFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.attendanceRecyclerview)
    RecyclerView attendanceRecyclerView;
    @BindView(R.id.sectionTV)
    TextView sectionTV;
    @BindView(R.id.date)
    TextView dateTV;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.textChooseSection)
    TextView chooseSection;

    @OnClick(R.id.sectionLayout)
    public void ClickOnSectionLayout(){
        displaySectionsPopup();
    }
    @OnClick(R.id.calendar)
    public void ClickOnCalendar(){
        displayDateDialog();
    }
    AttendanceRecyclerViewAdapter attendanceRecyclerViewAdapter;
    SectionsListAdapter sectionsListAdapter;
    @Inject
    AttendanceApi attendanceApi;
    ArrayList<SectionResponse.Response> sectionResponseList;
    List<Object> userResponseList;
    private String selectedDate;
    private String selectedSectionId;
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
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        applyFonts();
        setCurrentDate();
        attendanceRecyclerViewAdapter = new AttendanceRecyclerViewAdapter();
        attendanceRecyclerView.setAdapter(attendanceRecyclerViewAdapter);
        attendanceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        attendanceRecyclerViewAdapter.setItems(getAttendanceList());
//        attendanceRecyclerViewAdapter.notifyDataSetChanged();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        if(!bearerToken.isEmpty()) {
            attendanceApi.getSectionsForAttendance(bearerToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SectionResponse>() {
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
                        public void onNext(SectionResponse sectionResponse) {
                            progressDialog.dismiss();
                            if (sectionResponse.getIsSuccess().equals("true")) {
                                String responseString = sectionResponse.getResponseString();
                                ArrayList<SectionResponse.Response> yourArray = new Gson().
                                        fromJson(responseString,
                                                new TypeToken<List<SectionResponse.Response>>(){}.getType());

                                sectionResponseList = new ArrayList<SectionResponse.Response>(yourArray);

                            } else if (!sectionResponse.getErrorCode().equals("200")) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_login_failed,
                                        sectionResponse.getErrorMessage(),
                                        null);
                            }
                        }
                    });
        }
        return view;
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        titleTV.setTypeface(tf);
        dateTV.setTypeface(tf);
        chooseSection.setTypeface(tf);
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
    private void displaySectionsPopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
        listView.setTextFilterEnabled(true);
        if(sectionResponseList != null) {
            sectionsListAdapter = new SectionsListAdapter(sectionResponseList, getActivity().getBaseContext());
            listView.setAdapter(sectionsListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                SectionResponse.Response dataModel = sectionResponseList.get(position);
                sectionTV.setText(dataModel.getCompositeTagName());
                selectedSectionId = dataModel.getCompositeTagId();
                      //  Snackbar.make(view, " " +dataModel.getCompositeTagName()+" "+dataModel.getCompositeTagId(), Snackbar.LENGTH_LONG)
                      //          .setAction("No action", null).show();
                        getUsersBasedOnSection();
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void getUsersBasedOnSection(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        if(!bearerToken.isEmpty()) {
            String date = DateTimeUtils.parseDateTime(selectedDate, DATE_FORMAT2, DATE_FORMAT3);
            GetUserRequest request = new GetUserRequest(selectedSectionId, date);
            attendanceApi.getUsersBasedOnSection(bearerToken,request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetUserResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GetUserResponse getUserResponse) {
                            progressDialog.dismiss();
                            if (getUserResponse.getIsSuccess().equals("true")) {
                                userResponseList = new ArrayList<Object>(getUserResponse.getResponse());
                                attendanceRecyclerViewAdapter.setItems(userResponseList);
                                attendanceRecyclerViewAdapter.notifyDataSetChanged();
                            } else if (!getUserResponse.getErrorCode().equals("200")) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_login_failed,
                                        getUserResponse.getErrorMessage(),
                                        null);
                            }
                        }
                    });
        }
    }
    private void setCurrentDate(){
        selectedDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT2);
        String date = DateTimeUtils.getCurrentDateInString(DATE_FORMAT1);
        dateTV.setText(date);
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
        date.setListener(AttendanceFragment.this);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
        selectedDate = strDate;
        strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT1);
        dateTV.setText(strDate);

//        int year = view.getYear();
//        int month = view.getMonth();
//        int day = view.getDayOfMonth();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);
//
//        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT1);
//        String strDate = format.format(calendar.getTime());
//        selectedDate = strDate;
//        dateTV.setText(strDate);
    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
}
