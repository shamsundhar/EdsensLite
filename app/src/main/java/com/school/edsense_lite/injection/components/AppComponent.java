package com.school.edsense_lite.injection.components;

import android.content.Context;
import android.content.res.Resources;

import com.school.edsense_lite.attendance.AttendanceApi;
import com.school.edsense_lite.injection.modules.AppModule;
import com.school.edsense_lite.injection.modules.DataModule;
//import com.school.edsense_lite.injection.modules.NetModule;
import com.school.edsense_lite.injection.modules.NetModule;
import com.school.edsense_lite.injection.qualifier.AppContext;
import com.school.edsense_lite.injection.scopes.PerApplication;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.messages.MessagesApi;
import com.school.edsense_lite.subscription.SubsciptionApi;
import com.school.edsense_lite.today.TodayApi;

import dagger.Component;

@PerApplication
@Component(modules={AppModule.class, DataModule.class, NetModule.class})
public interface AppComponent {
    @AppContext
    Context appContext();
    Resources resources();
    //    RefWatcher refWatcher();
//
//    Realm realm();
//    CountryRepo countryRepo();
    LoginApi loginApi();
    TodayApi todayApi();
    AttendanceApi attendanceApi();
    SubsciptionApi subscriptionApi();
    MessagesApi messagesApi();
}
