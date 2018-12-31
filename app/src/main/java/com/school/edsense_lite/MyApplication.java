package com.school.edsense_lite;

import android.app.Application;
import android.content.res.Resources;

import com.school.edsense_lite.injection.components.AppComponent;
import com.school.edsense_lite.injection.components.DaggerAppComponent;
import com.school.edsense_lite.injection.modules.AppModule;
import com.school.edsense_lite.injection.modules.NetModule;
import com.school.edsense_lite.utils.PreferenceHelper;

//import org.acra.ACRA;
//import org.acra.ReportField;
//import org.acra.ReportingInteractionMode;
//import org.acra.annotation.ReportsCrashes;

//@ReportsCrashes(mailTo = "mobiledev@edsense.in", customReportContent = {
//        ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
//        ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
//        ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT}, mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class MyApplication extends Application {

    private static MyApplication sInstance = null;

    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
//        sAppComponent = DaggerAppComponent.create();


        // The following line triggers the initialization of ACRA for crash Log Reporting
        //for prod - make below boolean value as false
        // for internal - make it as true
//        if (PreferenceHelper.getPrefernceHelperInstace().getBoolean(
//                this, PreferenceHelper.SUBMIT_LOGS, false)) {
//            ACRA.init(this);
//        }

    }

    public static MyApplication getInstance() { return sInstance; }

    public static AppComponent getAppComponent() { return sAppComponent; }

    public static Resources getRes() { return sInstance.getResources(); }
}
