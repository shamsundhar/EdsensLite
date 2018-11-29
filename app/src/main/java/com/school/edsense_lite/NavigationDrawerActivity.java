package com.school.edsense_lite;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.messages.MessagesFragment;
import com.school.edsense_lite.notes.NotesFragment;
import com.school.edsense_lite.recomendations.RecomendationFragment;
import com.school.edsense_lite.today.TodayFragment;
import com.school.edsense_lite.utils.CircleTransform;
import com.school.edsense_lite.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.edsense_lite.utils.Constants.KEY_PREF_AVATAR_URL;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_BOARD_DATA;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_DISPLAY_NAME;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_SUBJECT_DATA;

public class NavigationDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profileAvatar;
    TextView profileNameTV;
    TextView profileClassTV;
    TextView profileSubjectsTV;

    String market_uri = "https://play.google.com/store/apps/details?id=";
    private static final String TODAY_FRAGMENT_TAG = "TODAY_FRAGMENT";
    private static final String ATTENDANCE_FRAGMENT_TAG = "ATTENDANCE_FRAGMENT";
    private static final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT";
    private static final String RECOMENDATION_FRAGMENT_TAG = "RECOMENDATION_FRAGMENT";
    private static final String MESSAGES_FRAGMENT_TAG = "MESSAGES_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerview = navigationView.getHeaderView(0);

        profileAvatar = (ImageView)headerview.findViewById(R.id.profile_avatar);
        profileNameTV = (TextView)headerview.findViewById(R.id.profile_name);
        profileClassTV = (TextView)headerview.findViewById(R.id.profile_class);
        profileSubjectsTV = (TextView)headerview.findViewById(R.id.profile_subjects);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
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

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.navigation_drawer, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchManager searchManager = (SearchManager) NavigationDrawerActivity.this.getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(NavigationDrawerActivity.this.getComponentName()));
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    public void displayProfile(View view){
        setTitle(getString(R.string.text_profile));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }
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
        } else if (id == R.id.nav_recomendation) {
            //   setTitle(getString(R.string.text_recomendation));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new RecomendationFragment(), RECOMENDATION_FRAGMENT_TAG)
                    .commit();
        } else if (id == R.id.nav_messages) {
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
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        preferenceHelper.clear(NavigationDrawerActivity.this);
        Intent in = new Intent(NavigationDrawerActivity.this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
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












