package com.school.edsense_lite;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.firebase.FCMApi;
import com.school.edsense_lite.firebase.FcmUnRegRequest;
import com.school.edsense_lite.firebase.FcmUnRegResponse;
import com.school.edsense_lite.messages.MessagesFragment;
import com.school.edsense_lite.model.db.EdsenseDatabase;
import com.school.edsense_lite.notes.NotesFragment;
import com.school.edsense_lite.today.TodayFragment;
import com.school.edsense_lite.utils.CircleTransform;
import com.school.edsense_lite.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.EDSENSE_DATABASE;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_AVATAR_URL;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_BOARD_DATA;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_DISPLAY_NAME;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_SUBJECT_DATA;
import static com.school.edsense_lite.utils.Constants.KEY_USER_ROLE_STUDENT;
import static com.school.edsense_lite.utils.Constants.KEY_USER_ROLE_TEACHER;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_BEARER_TOKEN;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_FCM_TOKEN;

public class NavigationDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profileAvatar;
    TextView profileNameTV;
    TextView profileClassTV;
    TextView profileSubjectsTV;
    PreferenceHelper preferenceHelper;
    public static EdsenseDatabase mEdsenseDatabase;
    MenuItem attendanceItem;
    MenuItem notesItem;

    String market_uri = "https://play.google.com/store/apps/details?id=";
    private static final String TODAY_FRAGMENT_TAG = "TODAY_FRAGMENT";
    private static final String ATTENDANCE_FRAGMENT_TAG = "ATTENDANCE_FRAGMENT";
    private static final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT";
    private static final String RECOMENDATION_FRAGMENT_TAG = "RECOMENDATION_FRAGMENT";
    private static final String MESSAGES_FRAGMENT_TAG = "MESSAGES_FRAGMENT";

    @Inject
    FCMApi fcmApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEdsenseDatabase = Room.databaseBuilder(NavigationDrawerActivity.this, EdsenseDatabase.class, EDSENSE_DATABASE)
                .allowMainThreadQueries()
                .build();

        ActionBar actionBar = getSupportActionBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu nav_Menu = navigationView.getMenu();

        Boolean teacherRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_TEACHER, false);
        Boolean studentRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_STUDENT, false);
        if(studentRole && !teacherRole) {
            nav_Menu.findItem(R.id.nav_notes).setVisible(false);
            nav_Menu.findItem(R.id.nav_attendance).setVisible(false);
        }
        View headerview = navigationView.getHeaderView(0);

        profileAvatar = (ImageView)headerview.findViewById(R.id.profile_avatar);
        profileNameTV = (TextView)headerview.findViewById(R.id.profile_name);
        profileClassTV = (TextView)headerview.findViewById(R.id.profile_class);
        profileSubjectsTV = (TextView)headerview.findViewById(R.id.profile_subjects);
        String displayName = preferenceHelper.getString(NavigationDrawerActivity.this, KEY_PREF_DISPLAY_NAME, "");
        String boardData = preferenceHelper.getString(NavigationDrawerActivity.this, KEY_PREF_BOARD_DATA, "");
        String subjectData = preferenceHelper.getString(NavigationDrawerActivity.this, KEY_PREF_SUBJECT_DATA, "");


        profileNameTV.setText(displayName);
        profileClassTV.setText(boardData);
        profileSubjectsTV.setText("Subject: "+subjectData);
        profileClassTV.setText(boardData);
        String userAvatarUrl = preferenceHelper.getString(NavigationDrawerActivity.this, KEY_PREF_AVATAR_URL, "");
        if(!userAvatarUrl.isEmpty()) {
            Picasso.with(NavigationDrawerActivity.this).load(userAvatarUrl).fit()
                    .placeholder(R.drawable.man_user)
                    .error(R.drawable.man_user)
                    .transform(new CircleTransform())
                    .into(profileAvatar);
        }

        profileClassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayProfile(null);
            }
        });
        profileNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayProfile(null);
            }
        });
        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayProfile(null);
            }
        });

        displayTodayFragment();
    }

    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void displayProfile(View view){
        setTitle(getString(R.string.text_profile));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
////        Boolean teacherRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_TEACHER, false);
////        Boolean studentRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_STUDENT, false);
////
////        if(studentRole && !teacherRole){
////            //hide attendance and notes.
////
////          //  attendanceItem.setEnabled(false);
////         //   notesItem.setEnabled(false);
////            attendanceItem.setVisible(false);
////            notesItem.setVisible(false);
////        }
//        return true;
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Boolean teacherRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_TEACHER, false);
//        Boolean studentRole = preferenceHelper.getBoolean(NavigationDrawerActivity.this, KEY_USER_ROLE_STUDENT, false);
//        MenuInflater inflater = getMenuInflater();
//        if(studentRole && !teacherRole){
//            inflater.inflate(R.menu.menu_student, menu);
//        }
//        else{
//            inflater.inflate(R.menu.menu_teacher, menu);
//        }
//        return true;
//    }

    public void displayTodayFragment(){
        setTitle(null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  TodayFragment.newInstance(), TODAY_FRAGMENT_TAG)
                .commit();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signout) {
            doLogout();
        } else if(id == R.id.nav_today){
            displayTodayFragment();
        }else if (id == R.id.nav_attendance) {
            //  setTitle(getString(R.string.text_attendance));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,  AttendanceFragment.newInstance(), ATTENDANCE_FRAGMENT_TAG)
                    .commit();

        } else if (id == R.id.nav_notes) {
            //   setTitle(getString(R.string.text_notes));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,  NotesFragment.newInstance(), NOTES_FRAGMENT_TAG)
                    .commit();
        }
//        else if (id == R.id.nav_recomendation) {
//            //   setTitle(getString(R.string.text_recomendation));
////            getSupportFragmentManager()
////                    .beginTransaction()
////                    .replace(R.id.container, new RecomendationFragment(), RECOMENDATION_FRAGMENT_TAG)
////                    .commit();
//            Toast.makeText(NavigationDrawerActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
//        }
        else if (id == R.id.nav_messages) {
            //  setTitle(getString(R.string.text_messages));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MessagesFragment.newInstance(), MESSAGES_FRAGMENT_TAG)
                    .commit();
        } else if (id == R.id.nav_share) {
            market_uri = market_uri + getPackageName();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareSub = "I found a cool Android App";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, market_uri);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }else if (id == R.id.nav_rateapp) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                uri = Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()+"");
                Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goMarket);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void doLogout(){
        String bearerToken = preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_BEARER_TOKEN, "");
        String fcmToken = preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_FCM_TOKEN, "");
        final ProgressDialog progressDialog = new ProgressDialog(NavigationDrawerActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_authenticating));
        progressDialog.show();

        //initiate gcm un registration call
        FcmUnRegRequest fcmUnRegRequest = new FcmUnRegRequest();
        FcmUnRegRequest.Value value = fcmUnRegRequest.new Value();
        value.setPushChannel(fcmToken);
        fcmUnRegRequest.setValue(value);
        fcmApi.fcmUnRegistration(bearerToken, fcmUnRegRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FcmUnRegResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        // onLoginFailed();
                        navigateToMainActivity();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FcmUnRegResponse fcmUnRegResponse){
                        progressDialog.dismiss();
                        navigateToMainActivity();
//                        if(fcmUnRegResponse.getIsSuccess().equals("true")) {
//                           // onLoginSuccess(username, password, fcmUnRegResponse);
//                        }
//                        else if(!fcmUnRegResponse.getErrorCode().equals("200")){
//                            //display error.
//                            new CustomAlertDialog().showAlert1(
//                                    NavigationDrawerActivity.this,
//                                    R.string.text_failed,
//                                    fcmUnRegResponse.getErrorMessage(),
//                                    null);
//                        }
                    }
                });
    }
    private void navigateToMainActivity(){
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String fcmToken = preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_FCM_TOKEN, "");
        preferenceHelper.clear(NavigationDrawerActivity.this);
        //clear will clear all data, so after clearing we are setting fcm token again in preferences.
        preferenceHelper.setString(NavigationDrawerActivity.this, PREF_KEY_FCM_TOKEN, fcmToken);
        clearDB();
        Intent in = new Intent(NavigationDrawerActivity.this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
    private void clearDB(){
        mEdsenseDatabase.getScheduleRowDao().deleteAll();
        mEdsenseDatabase.assignmentDao().deleteAll();
        mEdsenseDatabase.messagesDao().deleteAll();
        mEdsenseDatabase.getSectionResponseDao().deleteAll();
        mEdsenseDatabase.getAttendanceBySectionDao().deleteAll();
        mEdsenseDatabase.getAttendanceDao().deleteAll();
        mEdsenseDatabase.getEventsDao().deleteAll();
        mEdsenseDatabase.getNewsDao().deleteAll();
        mEdsenseDatabase.getNotesDao().deleteAll();
        mEdsenseDatabase.getRecomendationDao().deleteAll();
        mEdsenseDatabase.getUserResponseDao().deleteAll();
        mEdsenseDatabase.getNotesTagDao().deleteAll();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus){

        // set toolbar logo to center programmatically
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        int offset = (toolbar.getWidth() / 2) - (logo.getWidth() / 2);
        // set
        logo.setX(offset);

    }
}












