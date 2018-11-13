package com.school.edsense_lite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.subscription.SubscriptionActivity;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.PreferenceHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
                        String subscriptionId = preferenceHelper.getString(MainActivity.this,
                                Constants.PREF_KEY_SUBSCRIPTION_ID, "");

                        if(subscriptionId != "" && !subscriptionId.isEmpty()) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, SubscriptionActivity.class));
                        }
                        finish();
                    }
                }, 3000);
    }
}
