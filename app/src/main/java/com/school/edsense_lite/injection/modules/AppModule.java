package com.school.edsense_lite.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.school.edsense_lite.injection.qualifier.AppContext;
import com.school.edsense_lite.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    @AppContext
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

}
