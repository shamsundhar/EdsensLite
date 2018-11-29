package com.school.edsense_lite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.firebase.messaging.FirebaseMessaging;
import com.school.edsense_lite.firebase.Config;
import com.school.edsense_lite.firebase.NotificationUtils;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.subscription.SubscriptionActivity;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.PreferenceHelper;

public class MainActivity extends AppCompatActivity {
    // private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView)findViewById(R.id.logo);
//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // checking for type intent filter
//                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
//                    // gcm successfully registered
//                    // now subscribe to `global` topic to receive app wide notifications
//                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
//
//                 //   displayFirebaseRegId();
//
//                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
//                    // new push notification is received
//
//                    String message = intent.getStringExtra("message");
//
//                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
//
////                    txtMessage.setText(message);
//                }
//            }
//        };


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
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        logo.startAnimation(myanim);
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // register GCM registration complete receiver
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(Config.REGISTRATION_COMPLETE));
//
//        // register new push message receiver
//        // by doing this, the activity will be notified each time a new message arrives
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(Config.PUSH_NOTIFICATION));
//
//        // clear the notification area when the app is opened
//        NotificationUtils.clearNotifications(getApplicationContext());
//    }
}
