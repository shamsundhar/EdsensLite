package com.school.edsense_lite.notes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.attendance.AttendanceApi;
import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.attendance.AttendanceRecyclerViewAdapter;
import com.school.edsense_lite.attendance.GetTraitsRequest;
import com.school.edsense_lite.attendance.GetTraitsResponse;
import com.school.edsense_lite.attendance.GetUserRequest;
import com.school.edsense_lite.attendance.GetUserResponse;
import com.school.edsense_lite.attendance.GetUserResponseModel;
import com.school.edsense_lite.attendance.SaveAttendanceRequest;
import com.school.edsense_lite.attendance.SaveAttendanceResponse;
import com.school.edsense_lite.attendance.SectionResponse;
import com.school.edsense_lite.attendance.SectionsListAdapter;
import com.school.edsense_lite.attendance.SeverityListAdapter;
import com.school.edsense_lite.attendance.SeverityTypeResponse;
import com.school.edsense_lite.attendance.TraitsListAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;
import com.school.edsense_lite.messages.MessagesFragment;
import com.school.edsense_lite.model.SectionResponseModel;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.utils.Common;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

import org.w3c.dom.Text;

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
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT4;
import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

public class NotesFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.notesRecyclerview)
    RecyclerView notesRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
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
    TextView severityTv;
    TextView traitsTv;
    List<SectionResponseModel> sectionResponseList;
    ArrayList<Object> notesResponseList;
    SectionsListAdapter sectionsListAdapter;
    SeverityListAdapter severityListAdapter;
    TraitsListAdapter traitsListAdapter;

    private String selectedDate;
    private String selectedSectionId;
    private int selectedSeverityId;
    private int selectedTraitsId;
    List<SeverityTypeResponse.Response> severityTypeList;
    List<GetTraitsResponse.Response> traitsList;

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
        empty_view.setText(R.string.empty_notes_list_message);
        notesRecyclerViewAdapter = new NotesRecyclerViewAdapter();
        notesRecyclerView.setAdapter(notesRecyclerViewAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notesRecyclerViewAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void onModifyButtonClicked(GetUserNotesResponse.Response notesModel, int position) {
                displayNotesPopup(notesModel);
            }
        });

        //    notesRecyclerViewAdapter.setItems(getNotesList());
        //   notesRecyclerViewAdapter.notifyDataSetChanged();

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

                                    if (yourArray != null && yourArray.size() > 0) {
                                        for (SectionResponseModel model : yourArray) {
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

                attendanceApi.getSeverityTypes(bearerToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SeverityTypeResponse>() {
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
                                System.out.println("complete called");
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("onsubscribe called");
                            }

                            @Override
                            public void onNext(SeverityTypeResponse severityTypeResponse) {
                                progressDialog.dismiss();
                                if (severityTypeResponse.getIsSuccess().equals(true)) {
                                    severityTypeList = severityTypeResponse.getResponse();

                                } else if (!severityTypeResponse.getErrorCode().equals(200)) {
                                    //display error.
                                    new CustomAlertDialog().showAlert1(
                                            getActivity(),
                                            R.string.text_failed,
                                            severityTypeResponse.getErrorMessage(),
                                            null);
                                }
                            }
                        });
                GetTraitsRequest request = new GetTraitsRequest();
                GetTraitsRequest.Value value = request.new Value();
                request.setValue(value);
                attendanceApi.getTraits(bearerToken, request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GetTraitsResponse>() {
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
                                System.out.println("complete called");
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("onsubscribe called");
                            }

                            @Override
                            public void onNext(GetTraitsResponse getTraitsResponse) {
                                progressDialog.dismiss();
                                if (getTraitsResponse.getIsSuccess().equals(true)) {
                                    traitsList = getTraitsResponse.getResponse();

                                } else if (!getTraitsResponse.getErrorCode().equals(200)) {
                                    //display error.
                                    new CustomAlertDialog().showAlert1(
                                            getActivity(),
                                            R.string.text_failed,
                                            getTraitsResponse.getErrorMessage(),
                                            null);
                                }
                            }
                        });
            }
        }else{
            sectionResponseList = MessagesFragment.mEdsenseDatabase.sectionResponseDao().getAllSectionResponses();//new ArrayList<SectionResponseModel>(yourArray);
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
    private void displayNotesPopup(final GetUserNotesResponse.Response notesModel){
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_notesview);

        final RelativeLayout severityLayout = (RelativeLayout) builder.findViewById(R.id.severityLayout);
        final RelativeLayout traitsLayout = (RelativeLayout)builder.findViewById(R.id.traitsLayout);
        final TextView dateTV = (TextView)builder.findViewById(R.id.date);
        dateTV.setText(DateTimeUtils.parseDateTime(selectedDate, DATE_FORMAT2, DATE_FORMAT4));
        severityTv = (TextView)builder.findViewById(R.id.severityTV);
        traitsTv = (TextView)builder.findViewById(R.id.traitsTV);
        Button save = (Button)builder.findViewById(R.id.btn_save);
        ImageView closeImage = (ImageView)builder.findViewById(R.id.close);
        final EditText reasonEditText = (EditText)builder.findViewById(R.id.input_reason);
        reasonEditText.clearFocus();
        severityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySeverityPopup();
            }
        });
        traitsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTraitsPopup();
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reason = reasonEditText.getText().toString().trim();
                builder.dismiss();
                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.text_please_wait));
                progressDialog.show();
                PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
                String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
                if(!bearerToken.isEmpty()) {
                    SaveNotesRequest saveNotesRequest = new SaveNotesRequest();
                    saveNotesRequest.setIsDelete(false);
                    saveNotesRequest.setIsPublic(false);
                    saveNotesRequest.setIsVisibletoParent(false);
//                    notesModel.get
//                    saveNotesRequest.setStudentid();
//                    saveNotesRequest.setNote();
//                    saveNotesRequest.setTags();
//                    saveNotesRequest.setDateCommented();
//                    saveNotesRequest.setSeverityTypeId();
//                    saveNotesRequest.setMeetingTypeId();
                    attendanceApi.saveNotes(bearerToken, saveNotesRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SaveNotesResponse>() {
                                @Override
                                public void onError(Throwable e) {
                                    System.out.println("error called::" + e.fillInStackTrace());
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
                                public void onNext(SaveNotesResponse saveNotesResponse) {
                                    progressDialog.dismiss();
                                    if (saveNotesResponse.getIsSuccess().equals("true")) {
                                        builder.dismiss();
                                    } else if (!saveNotesResponse.getErrorCode().equals("200")) {
                                        //display error.
                                        new CustomAlertDialog().showAlert1(
                                                getActivity(),
                                                R.string.text_failed,
                                                saveNotesResponse.getErrorMessage(),
                                                null);
                                    }
                                }
                            });
                }
            }
        });
        builder.setCanceledOnTouchOutside(false);
        builder.show();
    }
    private void setCurrentDate(){
        selectedDate = DateTimeUtils.getCurrentDateInString(DATE_FORMAT2);
        String date = DateTimeUtils.getCurrentDateInString(DATE_FORMAT1);
        dateTV.setText(date);
    }
    private void displaySeverityPopup(){
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
        if(severityTypeList != null) {
            severityListAdapter = new SeverityListAdapter(severityTypeList, getActivity().getBaseContext());
            listView.setAdapter(severityListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                SeverityTypeResponse.Response dataModel = severityTypeList.get(position);
                severityTv.setText(dataModel.getType());
                selectedSeverityId = dataModel.getId();
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void displayTraitsPopup(){
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
        if(traitsList != null) {
            traitsListAdapter = new TraitsListAdapter(traitsList, getActivity().getBaseContext());
            listView.setAdapter(traitsListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                GetTraitsResponse.Response dataModel = traitsList.get(position);
                traitsTv.setText(dataModel.getTagName());
                selectedTraitsId = dataModel.getTagId();

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
        if(Common.isNetworkAvailable(getActivity())) {
            if (!bearerToken.isEmpty()) {
                String date = DateTimeUtils.parseDateTime(selectedDate, DATE_FORMAT2, DATE_FORMAT3);
                GetUserRequest request = new GetUserRequest(selectedSectionId, date);
                attendanceApi.getNotes(bearerToken, request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GetUserNotesResponse>() {
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
                            public void onNext(GetUserNotesResponse getUserNotesResponse) {
                                progressDialog.dismiss();
                                if (getUserNotesResponse.getIsSuccess().equals(true)) {
                                    String responseString = getUserNotesResponse.getResponse();
                                    ArrayList<GetUserNotesResponse.Response> yourArray = new Gson().
                                            fromJson(responseString,
                                                    new TypeToken<List<GetUserNotesResponse.Response>>() {
                                                    }.getType());
                                    progressDialog.dismiss();
                                    if(yourArray != null && yourArray.size()>0){
                                        empty_view.setVisibility(View.GONE);
                                        notesRecyclerView.setVisibility(View.VISIBLE);
                                        notesResponseList = new ArrayList<Object>(yourArray);
                                        notesRecyclerViewAdapter.setItems(notesResponseList);
                                        notesRecyclerViewAdapter.notifyDataSetChanged();
                                    }else{
                                        empty_view.setVisibility(View.VISIBLE);
                                        notesRecyclerView.setVisibility(View.GONE);
                                    }

                                } else if (!getUserNotesResponse.getErrorCode().equals(200)) {
                                    //display error.
                                    new CustomAlertDialog().showAlert1(
                                            getActivity(),
                                            R.string.text_failed,
                                            getUserNotesResponse.getErrorMessage(),
                                            null);
                                }

                            }
                        });
            }
        }else{
          //  displayNotesResponseFromDB(progressDialog);
        }
    }

    private void displayNotesResponseFromDB(ProgressDialog progressDialog) {
        List<GetUserResponseModel> yourArray = MessagesFragment.mEdsenseDatabase.getUserResponseDao().getAllUserResponses();
        notesResponseList = new ArrayList<Object>(yourArray);
        notesRecyclerViewAdapter.setItems(notesResponseList);
        progressDialog.dismiss();
        notesRecyclerViewAdapter.notifyDataSetChanged();
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
