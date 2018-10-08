package com.school.edsense_lite.injection.components;


import com.school.edsense_lite.injection.modules.ActivityModule;
import com.school.edsense_lite.injection.scopes.PerActivity;
import com.school.edsense_lite.login.LoginActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {

//    @ActivityContext Context activityContext();
//    @ActivityFragmentManager FragmentManager defaultFragmentManager();


    // create inject methods for your Activities here

  //  void inject(SignupActivity activity);
    void inject(LoginActivity activity);
  //  void inject(ProductListActivity activity);

}
