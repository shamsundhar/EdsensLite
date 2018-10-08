package com.school.edsense_lite.injection.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.school.edsense_lite.injection.qualifier.ActivityContext;
import com.school.edsense_lite.injection.qualifier.ActivityFragmentManager;
import com.school.edsense_lite.injection.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    Context provideActivityContext() { return mActivity; }

    @Provides
    @PerActivity
    @ActivityFragmentManager
    FragmentManager provideFragmentManager() { return mActivity.getSupportFragmentManager(); }
}
