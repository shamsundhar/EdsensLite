package com.school.edsense_lite.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.events.Event;
import com.school.edsense_lite.events.EventsRecyclerViewAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;
import com.school.edsense_lite.messages.MessagesFragment;
import com.school.edsense_lite.model.SectionResponseModel;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.today.TodayFragment;
import com.school.edsense_lite.utils.Common;
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
import okhttp3.internal.http2.StreamResetException;

import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT3;
import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

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

    List<SectionResponseModel> sectionResponseList;
    ArrayList<Object> userResponseList;
    //List<Object> userResponseList;
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
        attendanceRecyclerViewAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void onModifyButtonClicked(GetUserResponseModel attendanceModel) {
                displayAbsentPopup(attendanceModel);
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        MessagesFragment.mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        if(Common.isNetworkAvailable(getActivity())) {
            if (!bearerToken.isEmpty()) {
                attendanceApi.getSectionsForAttendance(bearerToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SectionResponse>() {
                            @Override
                            public void onError(Throwable e) {
                                System.out.println("error called::" + e.fillInStackTrace());
                                progressDialog.dismiss();
                                if (e instanceof StreamResetException) {
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
                                    ArrayList<SectionResponseModel> yourArray = new Gson().
                                            fromJson(responseString,
                                                    new TypeToken<List<SectionResponseModel>>() {
                                                    }.getType());
                                    if(yourArray != null && yourArray.size()>0){
                                        for(SectionResponseModel model : yourArray){
                                            MessagesFragment.mEdsenseDatabase.sectionResponseDao().insert(model);
                                        }
                                    }
                                    sectionResponseList = MessagesFragment.mEdsenseDatabase.sectionResponseDao().getAllSectionResponses();//new ArrayList<SectionResponseModel>(yourArray);

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
        }else{
            sectionResponseList = MessagesFragment.mEdsenseDatabase.sectionResponseDao().getAllSectionResponses();//new ArrayList<SectionResponseModel>(yourArray);
            progressDialog.dismiss();
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
    private void displayAbsentPopup(final GetUserResponseModel attendanceModel){
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setCanceledOnTouchOutside(false);
        builder.setContentView(R.layout.popup_absentview2);

        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        final RadioGroup radioGroup = (RadioGroup)builder.findViewById(R.id.myRadioGroup);
        RadioButton absentRB = (RadioButton)builder.findViewById(R.id.absent);
        RadioButton lateinRB = (RadioButton)builder.findViewById(R.id.latein);
        RadioButton earlyoutRB = (RadioButton)builder.findViewById(R.id.earlyout);

        final EditText timeEditText = (EditText) builder.findViewById(R.id.input_time);
        final TextInputLayout timeInputLayout = (TextInputLayout)builder.findViewById(R.id.input_timelayout);
        final EditText reasonEditText = (EditText)builder.findViewById(R.id.input_reason);

        final ImageView close = (ImageView)builder.findViewById(R.id.close);
        Button save = (Button)builder.findViewById(R.id.btn_save);

        absentRB.setTypeface(tf);lateinRB.setTypeface(tf);
        earlyoutRB.setTypeface(tf);timeEditText.setTypeface(tf);
        reasonEditText.setTypeface(tf);save.setTypeface(tf);

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeEditText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.absent) {
                    timeInputLayout.setVisibility(View.GONE);
                } else if(checkedId == R.id.latein) {
                    timeInputLayout.setVisibility(View.VISIBLE);
                } else {
                    timeInputLayout.setVisibility(View.VISIBLE);
                }
            }

        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeEditText.getText().toString().trim();
                String reason = reasonEditText.getText().toString().trim();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                Boolean isAttended = true;
                Boolean isLatein = false;
                Boolean isEarlyout = false;
                // find which radioButton is checked by id
                if(selectedId == R.id.absent) {
                    isAttended = false;
                } else if(selectedId == R.id.latein) {
                    isLatein = true;
                } else if(selectedId == R.id.earlyout){
                    isEarlyout = true;
                }

                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.text_please_wait));
                progressDialog.show();
                PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
                String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
                if(!bearerToken.isEmpty()) {
                    SaveAttendanceRequest attendanceRequest = new SaveAttendanceRequest();
                    ArrayList<SaveAttendanceRequest.User> usersList = new ArrayList<SaveAttendanceRequest.User>();
                    SaveAttendanceRequest.User user = attendanceRequest.new User();

                    user.setDisplayName(attendanceModel.getDisplayName());
                    user.setIsAttended(isAttended);
                    user.setIsEarlyOut(isEarlyout);
                    user.setIsLateIn(isLatein);
                    user.setStudentUserId(attendanceModel.getStudentUserId());
                    user.setUserAttendanceId(1497);
                    user.setTotalCount(Integer.parseInt(attendanceModel.getTotalCount()));
                    user.setReason(reason);
                    user.setDate("2018-10-03T06:54:00.891Z");
                    usersList.add(user);
                    Gson gson = new Gson();

                    attendanceRequest.setUsers(gson.toJson(usersList).toString());
                    attendanceApi.saveUserAttendance(bearerToken, attendanceRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SaveAttendanceResponse>() {
                                @Override
                                public void onError(Throwable e) {
                                    System.out.println("error called::" + e.fillInStackTrace());
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
                                public void onNext(SaveAttendanceResponse sectionResponse) {
                                    progressDialog.dismiss();
                                    if (sectionResponse.getIsSuccess().equals("true")) {
                                        builder.dismiss();
                                    } else if (!sectionResponse.getErrorCode().equals("200")) {
                                        //display error.
                                        new CustomAlertDialog().showAlert1(
                                                getActivity(),
                                                R.string.text_failed,
                                                sectionResponse.getErrorMessage(),
                                                null);
                                    }
                                }
                            });
                }
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
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
                SectionResponseModel dataModel = sectionResponseList.get(position);
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
        if(Common.isNetworkAvailable(getActivity())) {
            if (!bearerToken.isEmpty()) {
                String date = DateTimeUtils.parseDateTime(selectedDate, DATE_FORMAT2, DATE_FORMAT3);
                GetUserRequest request = new GetUserRequest(selectedSectionId, date);
                attendanceApi.getUsersBasedOnSection(bearerToken, request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GetUserResponse>() {
                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                if (e instanceof StreamResetException) {
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
                                    ArrayList<GetUserResponseModel> yourArray = new Gson().
                                            fromJson(responseString,
                                                    new TypeToken<List<GetUserResponseModel>>() {
                                                    }.getType());

                                    //  userResponseList = new ArrayList<GetUserResponse.Response>(yourArray);
                                    //     userResponseList.addAll(yourArray);
                                    if(yourArray != null && yourArray.size()>0){
                                        for(GetUserResponseModel model : yourArray){
                                            MessagesFragment.mEdsenseDatabase.getUserResponseDao().insert(model);
                                        }
                                    }

                                    displayUserResponseFromDB(progressDialog);
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
        }else{
            displayUserResponseFromDB(progressDialog);
        }
    }

    private void displayUserResponseFromDB(ProgressDialog progressDialog) {

        List<GetUserResponseModel> yourArray = MessagesFragment.mEdsenseDatabase.getUserResponseDao().getAllUserResponses();
        userResponseList = new ArrayList<Object>(yourArray);
        progressDialog.dismiss();
        attendanceRecyclerViewAdapter.setItems(userResponseList);

        attendanceRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void setCurrentDate(){
        selectedDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT2);
        String date = DateTimeUtils.getCurrentDateInString(DATE_FORMAT1);
        dateTV.setText(date);
    }

    private void displayAttandanceFromDB(ProgressDialog progressDialog){
//        ArrayList<GetUserResponse.Response> yourArray = new Gson().
//                fromJson(responseString,
//                        new TypeToken<List<GetUserResponse.Response>>(){}.getType());
//
//        progressDialog.dismiss();
//        userResponseList = new ArrayList<Object>(yourArray);
//        attendanceRecyclerViewAdapter.setItems(userResponseList);
//        attendanceRecyclerViewAdapter.notifyDataSetChanged();

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
    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }

}
