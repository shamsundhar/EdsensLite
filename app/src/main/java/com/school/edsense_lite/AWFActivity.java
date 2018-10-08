package com.school.edsense_lite;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.edsense_lite.events.EventsFragment;
import com.school.edsense_lite.news.NewsFragment;
import com.school.edsense_lite.today.TodayFragment;

import butterknife.ButterKnife;

import static com.school.edsense_lite.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_EVENTS;
import static com.school.edsense_lite.utils.Constants.BUNDLE_VALUE_NEWS;

/*
* AWF - Activity With Fragment. this activity will act as holder activity for fragment - which holds only one fragment.
*
* */
public class AWFActivity extends BaseActivity {

    private static final String NEWS_FRAGMENT_TAG = "NEWS_FRAGMENT";
    private static final String EVENTS_FRAGMENT_TAG = "EVENST_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String displayFragment = bundle.getString(BUNDLE_KEY_DISPLAY_FRAGMENT);
            switch (displayFragment){
                case BUNDLE_VALUE_NEWS :
                    displayNewsFragment();
                    break;
                case BUNDLE_VALUE_EVENTS :
                    displayEventsFragment();
                    break;
            }
        }

    }
    public void displayNewsFragment(){
        setTitle(null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  NewsFragment.newInstance(), NEWS_FRAGMENT_TAG)
                .commit();
    }
    public void displayEventsFragment(){
        setTitle(null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  EventsFragment.newInstance(), EVENTS_FRAGMENT_TAG)
                .commit();
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
