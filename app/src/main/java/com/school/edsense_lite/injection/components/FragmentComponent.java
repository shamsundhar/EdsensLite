package com.school.edsense_lite.injection.components;



import com.school.edsense_lite.attendance.AttendanceFragment;
import com.school.edsense_lite.events.EventsFragment;
import com.school.edsense_lite.injection.modules.FragmentModule;
import com.school.edsense_lite.injection.scopes.PerFragment;
import com.school.edsense_lite.login.ResetPasswordEnterEmailFragment;
import com.school.edsense_lite.news.NewsFragment;
import com.school.edsense_lite.notes.NotesFragment;
import com.school.edsense_lite.today.TodayFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(NotesFragment fragment);
    void inject(AttendanceFragment fragment);
    void inject(EventsFragment fragment);
    void inject(ResetPasswordEnterEmailFragment fragment);
    void inject(TodayFragment fragment);
    void inject(NewsFragment fragment);
}
