package com.school.edsense_lite.notes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.attendance.AttendanceApi;
import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.attendance.AttendanceRecyclerViewAdapter;
import com.school.edsense_lite.attendance.GetUserRequest;
import com.school.edsense_lite.attendance.GetUserResponse;
import com.school.edsense_lite.attendance.SaveAttendanceRequest;
import com.school.edsense_lite.attendance.SaveAttendanceResponse;
import com.school.edsense_lite.attendance.SectionResponse;
import com.school.edsense_lite.attendance.SectionsListAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

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
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT3;

public class NotesFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.notesRecyclerview)
    RecyclerView notesRecyclerView;
    @BindView(R.id.sectionTV)
    TextView sectionTV;
    @BindView(R.id.date)
    TextView dateTV;
    @BindView(R.id.pagetitle)
    TextView titleTV;
    @BindView(R.id.textChooseSection)
    TextView chooseSection;
    @Inject
    AttendanceApi attendanceApi;
    ArrayList<SectionResponse.Response> sectionResponseList;
    ArrayList<Object> userResponseList;
    SectionsListAdapter sectionsListAdapter;
    private String selectedDate;
    private String selectedSectionId;

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
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        applyFonts();
        setCurrentDate();
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter();
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notesRecyclerViewAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void onModifyButtonClicked(View v, int position) {
                displayNotesPopup();
            }
        });

    //    notesRecyclerViewAdapter.setItems(getNotesList());
     //   notesRecyclerViewAdapter.notifyDataSetChanged();

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
                            progressDialog.dismiss();
                            if(e instanceof StreamResetException)
                            {
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
    private void displayNotesPopup(){
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_absentview);

        final CheckBox absent = (CheckBox)builder.findViewById(R.id.absentCheckBox);
        final EditText lateInEditText = (EditText) builder.findViewById(R.id.input_latein);
        final EditText earlyOutEditText = (EditText)builder.findViewById(R.id.input_earlyout);
        final EditText reasonEditText = (EditText)builder.findViewById(R.id.input_reason);
        Button save = (Button)builder.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lateIn = lateInEditText.getText().toString().trim();
                String earlyOut = earlyOutEditText.getText().toString().trim();
                String reason = reasonEditText.getText().toString().trim();
                Boolean isAbsent = absent.isChecked();

//                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
//                        R.style.AppTheme_Dark_Dialog);
//                progressDialog.setIndeterminate(true);
//                progressDialog.setMessage(getString(R.string.text_please_wait));
//                progressDialog.show();
//                PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
//                String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
//                if(!bearerToken.isEmpty()) {
//                    SaveAttendanceRequest attendanceRequest = new SaveAttendanceRequest();
//                    ArrayList<SaveAttendanceRequest.User> usersList = new ArrayList<SaveAttendanceRequest.User>();
//                    SaveAttendanceRequest.User user = attendanceRequest.new User();
//                    user.setDisplayName("Annapu Reddy Pradham Reddy");
//                    user.setIsAttended(false);
//                    user.setIsEarlyOut(false);
//                    user.setIsLateIn(false);
//                    user.setStudentUserId("14B6C8E7-FE8E-41D4-BC8B-824DA61F11E8");
//                    user.setUserAttendanceId(1497);
//                    user.setTotalCount(25);
//                    user.setDate("2018-10-03T06:54:00.891Z");
//                    usersList.add(user);
//                    Gson gson = new Gson();
//
//                    attendanceRequest.setUsers(gson.toJson(usersList).toString());
//                    attendanceApi.saveUserAttendance(bearerToken, attendanceRequest)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<SaveAttendanceResponse>() {
//                                @Override
//                                public void onError(Throwable e) {
//                                    System.out.println("error called::" + e.fillInStackTrace());
//                                    progressDialog.dismiss();
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                    System.out.println("complete called");
//                                }
//
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                    System.out.println("onsubscribe called");
//                                }
//
//                                @Override
//                                public void onNext(SaveAttendanceResponse sectionResponse) {
//                                    progressDialog.dismiss();
//                                    if (sectionResponse.getIsSuccess().equals("true")) {
//                                        builder.dismiss();
//                                    } else if (!sectionResponse.getErrorCode().equals("200")) {
//                                        //display error.
//                                        new CustomAlertDialog().showAlert1(
//                                                getActivity(),
//                                                R.string.text_failed,
//                                                sectionResponse.getErrorMessage(),
//                                                null);
//                                    }
//                                }
//                            });
//                }
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void setCurrentDate(){
        selectedDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT2);
        String date = DateTimeUtils.getCurrentDateInString(DATE_FORMAT1);
        dateTV.setText(date);
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
                            if(e instanceof StreamResetException)
                            {
                                //login again
                                e.printStackTrace();
                                relogin();
                            }
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
                                String responseString = getUserResponse.getResponseString();
                                ArrayList<GetUserResponse.Response> yourArray = new Gson().
                                        fromJson(responseString,
                                                new TypeToken<List<GetUserResponse.Response>>(){}.getType());

                                userResponseList = new ArrayList<Object>(yourArray);
                                notesRecyclerViewAdapter.setItems(userResponseList);
                                notesRecyclerViewAdapter.notifyDataSetChanged();
                            } else if (!getUserResponse.getErrorCode().equals("200")) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_failed,
                                        getUserResponse.getErrorMessage(),
                                        null);
                            }
                        }
                    });
        }
    }
    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
        selectedDate = strDate;
        strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT1);
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
