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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.school.edsense_lite.BaseFragment;
import com.school.edsense_lite.R;
import com.school.edsense_lite.attendance.AttendanceApi;
import com.school.edsense_lite.attendance.GetTraitsRequest;
import com.school.edsense_lite.attendance.GetTraitsResponse;
import com.school.edsense_lite.attendance.SeverityListAdapter;
import com.school.edsense_lite.attendance.SeverityTypeResponse;
import com.school.edsense_lite.attendance.TraitsListAdapter;
import com.school.edsense_lite.fragment.DatePickerFragment;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.news.NewsFragment;
import com.school.edsense_lite.news.NewsRecyclerViewAdapter;
import com.school.edsense_lite.today.NewsRequest;
import com.school.edsense_lite.today.NewsResponse;
import com.school.edsense_lite.today.TodayApi;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.DateTimeUtils;
import com.school.edsense_lite.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_NOTES_MODEL;
import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_SECTION_ID;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_ADD_NOTES;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_EDIT_NOTES;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT1;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT2;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT3;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT4;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT6;
import static com.school.edsense_lite.utils.Constants.DATE_FORMAT8;
import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;

public class AddNotesFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    @Inject
    AttendanceApi attendanceApi;
    List<SeverityTypeResponse.Response> severityTypeList;
    List<GetTraitsResponse.Response> traitsList;
    List<GetUsersForNotesResponse.Response> usersList;
    GetTraitsResponse.Response selectedTrait;
    @BindView(R.id.severityTV)
    TextView severityTV;
    @BindView(R.id.traitsTV)
    TextView traitsTV;
    @BindView(R.id.dateTV)
    TextView dateTV;
    @BindView(R.id.studentTV)
    TextView studentTV;
    @BindView(R.id.input_notes)
    EditText notesET;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.tv1)
    TextView pageTitle;
    @BindView(R.id.editButtonLayout)
    LinearLayout editButtonLayout;
    @BindView(R.id.addButtonLayout)
    LinearLayout addButtonLayout;
    SeverityListAdapter severityListAdapter;
    TraitsListAdapter traitsListAdapter;
    UsersListAdapter usersListAdapter;
    private int selectedSeverityId;
    private String selectedStudentId;
    private int selectedStudentNotesId;
    private Boolean inEditingMode = false;
    private int selectedTraitsId;
    private String selectedDate;
    Bundle bundle;
    PreferenceHelper preferenceHelper;
    @BindView(R.id.studentLayout)
    RelativeLayout studentLayout;
    @BindView(R.id.traitsLayout)
    RelativeLayout traitsLayout;
    @BindView(R.id.severityLayout)
    RelativeLayout severityLayout;
    @BindView(R.id.dateLayout)
    RelativeLayout dateLayout;

    public static EdsenseDatabase mEdsenseDatabase;

    @OnClick(R.id.studentLayout)
    public void clickOnStudentLayout(){
        studentTV.setError(null);
        displayUsersListPopup();
    }
    @OnClick(R.id.traitsLayout)
    public void clickOnTraitsLayout(){
        traitsTV.setError(null);
        displayTraitsPopup();
    }
    @OnClick(R.id.severityLayout)
    public void clickOnSeverityLayout(){
        severityTV.setError(null);
        displaySeverityPopup();
    }
    @OnClick(R.id.dateLayout)
    public void clickOnDateLayout(){
        dateTV.setError(null);
        displayDateDialog();
    }
    @OnClick({R.id.btn_save, R.id.btn_save2})
    public void clickOnSaveButton(){
        saveNotes(false);
    }
    @OnClick(R.id.btn_delete)
    public void clickOnDeleteButton(){
        deleteNotes();
    }
    // TODO: Rename and change types and number of parameters
    public static AddNotesFragment newInstance() {
        AddNotesFragment fragment = new AddNotesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notes, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        bundle = getArguments();
        if(bundle != null){
            if(bundle.getString(BUNDLE_KEY_DISPLAY_FRAGMENT).equals(BUNDLE_VALUE_ADD_NOTES)){
                pageTitle.setText("Add Notes");
                processAddNotesFlow();
            }
            else if(bundle.getString(BUNDLE_KEY_DISPLAY_FRAGMENT).equals(BUNDLE_VALUE_EDIT_NOTES)){
                pageTitle.setText("Edit Notes");
                processEditNotesFlow();
            }
        }
        return view;
    }
    private void processEditNotesFlow(){
        inEditingMode = true;
        addButtonLayout.setVisibility(View.GONE);
        editButtonLayout.setVisibility(View.VISIBLE);
        Note userNotesModel = (Note)bundle.getSerializable(BUNDLE_KEY_NOTES_MODEL);
        String date = userNotesModel.getDateCommented();
        studentTV.setText(userNotesModel.getStudentName());
        dateTV.setText(DateTimeUtils.parseDateTime(date, DATE_FORMAT8, DATE_FORMAT4));
        severityTV.setText(userNotesModel.getSeverityTypeName());
        notesET.setText(userNotesModel.getNote());
        GetTraitsResponse.Response response = new GetTraitsResponse().new Response();
        List<Tag> tagList = userNotesModel.getTags();
        if(tagList != null && !tagList.isEmpty() && tagList.get(0) != null) {
            response.setTagId(Integer.valueOf(tagList.get(0).getTagId()));
            response.setTagName(tagList.get(0).getTagName());
            traitsTV.setText(tagList.get(0).getTagName());
        }
        response.setSelected(true);
        selectedTrait = response;
        selectedStudentNotesId = Integer.valueOf(userNotesModel.getStudentNotesId());
        selectedStudentId = userNotesModel.getStudentId();

        selectedDate = DateTimeUtils.parseDateTime(date,DATE_FORMAT8, DATE_FORMAT2);
        selectedSeverityId = Integer.valueOf(userNotesModel.getSeverityTypeId());
        studentLayout.setBackgroundResource(R.drawable.layout_corners_grey_border_solid_grey);
        dateLayout.setBackgroundResource(R.drawable.layout_corners_grey_border_solid_grey);
        severityLayout.setBackgroundResource(R.drawable.layout_corners_grey_border_solid_grey);
        traitsLayout.setBackgroundResource(R.drawable.layout_corners_grey_border_solid_grey);
        studentLayout.setOnClickListener(null);
        dateLayout.setOnClickListener(null);
        severityLayout.setOnClickListener(null);
        traitsLayout.setOnClickListener(null);
    }
    private void processAddNotesFlow(){
        addButtonLayout.setVisibility(View.VISIBLE);
        editButtonLayout.setVisibility(View.GONE);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();

        String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
        GetUsersForNotesRequest notesRequest = new GetUsersForNotesRequest();
        if(bundle != null){
            notesRequest.setGradeId(bundle.getString(BUNDLE_KEY_SECTION_ID));
        }
        notesRequest.setSelectedRoleId(10);
        if(!bearerToken.isEmpty()) {
            attendanceApi.getUsersForAddingNotes(bearerToken, notesRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetUsersForNotesResponse>() {
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
                        public void onNext(GetUsersForNotesResponse notesResponse) {
                            progressDialog.dismiss();
                            if (notesResponse.getIsSuccess().equals(true)) {

                                usersList = notesResponse.getResponse();
                            } else if (!notesResponse.getErrorCode().equals(200)) {
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_failed,
                                        notesResponse.getErrorMessage(),
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
    }
    private void displayUsersListPopup(){
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
        if(usersList != null) {
            usersListAdapter = new UsersListAdapter(usersList, getActivity().getBaseContext());
            listView.setAdapter(usersListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                GetUsersForNotesResponse.Response dataModel = usersList.get(position);
                studentTV.setText(dataModel.getDisplayName());
                selectedStudentId = dataModel.getUserId();
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
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
                severityTV.setText(dataModel.getType());
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
                selectedTrait = traitsList.get(position);
                traitsTV.setText(selectedTrait.getTagName());
                selectedTraitsId = selectedTrait.getTagId();

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
        date.setListener(AddNotesFragment.this);
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
        strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT4);
        dateTV.setText(strDate);
    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
    private void deleteNotes(){
        saveNotes(true);
    }
    private void saveNotes(final Boolean isDelete){
        if(validate()){
            String notes = notesET.getText().toString().trim();
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();
            PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
            String bearerToken = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "");
            if(!bearerToken.isEmpty()) {
                SaveNotesRequest saveNotesRequest = new SaveNotesRequest();
                saveNotesRequest.setIsDelete(isDelete);
                saveNotesRequest.setIsPublic(false);
                saveNotesRequest.setIsVisibletoParent(false);
                saveNotesRequest.setStudentid(selectedStudentId);
                saveNotesRequest.setNote(notes);
                if(inEditingMode){
                    saveNotesRequest.setStudentNotesId(selectedStudentNotesId);
                }
                List<SaveNotesRequest.Tag> tagsList = new ArrayList<SaveNotesRequest.Tag>();
                SaveNotesRequest.Tag tag = saveNotesRequest.new Tag();
                tag.setTagId(selectedTrait.getTagId());
                tag.setTagName(selectedTrait.getTagName());
                tag.setSelected(selectedTrait.getSelected());
                tagsList.add(tag);
                saveNotesRequest.setTags(tagsList);
                saveNotesRequest.setDateCommented(DateTimeUtils.parseDateTime(selectedDate, DATE_FORMAT2, DATE_FORMAT3));
                saveNotesRequest.setSeverityTypeId(selectedSeverityId);
                saveNotesRequest.setMeetingTypeId(1);
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
                                if (saveNotesResponse.getIsSuccess().equals(true)) {
//TODO save this info to database.
                                    if(isDelete){
                                        mEdsenseDatabase.getNotesDao().deleteSelectedNotes(selectedStudentNotesId);
                                        Toast.makeText(getActivity(), R.string.text_notes_deleted_success, Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(), R.string.text_notes_success, Toast.LENGTH_SHORT).show();
                                    }
                                    if(inEditingMode){
                                        getActivity().finish();
                                    }
                                    else{
                                        resetFormData();
                                    }

                                } else if (!saveNotesResponse.getErrorCode().equals(200)) {
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
    }
    private Boolean validate(){
        Boolean flag = true;
        String notes = notesET.getText().toString().trim();
        String dateString = dateTV.getText().toString().trim();
        String severityString = severityTV.getText().toString().trim();
        String traitsString = traitsTV.getText().toString().trim();
        String studentString = studentTV.getText().toString().trim();
        if(notes.isEmpty()){
            flag = false;
            notesET.setError("Notes should not be empty");
        }
        else{
            notesET.setError(null);
        }
        if(dateString.isEmpty())
        {
            flag = false;
            dateTV.setError("Date should not be empty");
        }
        else{
            dateTV.setError(null);
        }
        if(severityString.equals("Select One") || severityString.isEmpty())
        {
            severityTV.setError("Select Severity");
        }
        else{
            severityTV.setError(null);
        }
        if(traitsString.equals("Select One") || traitsString.isEmpty())
        {
            traitsTV.setError("Select Traits");
        }
        else{
            traitsTV.setError(null);
        }
        if(studentString.equals("Select One") || studentString.isEmpty())
        {
            studentTV.setError("Select Student");
        }
        else{
            studentTV.setError(null);
        }
        return flag;
    }
    private void resetFormData(){
        dateTV.setText("");
        traitsTV.setText("");
        severityTV.setText("");
        notesET.setText("");
        studentTV.setText("");
        selectedStudentId = "";
        selectedTrait = null;
        selectedDate = "";
        selectedSeverityId = 0;
    }
}
