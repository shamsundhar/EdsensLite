package com.school.edsense_lite.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.school.edsense_lite.utils.PreferenceHelper;

import static com.school.edsense_lite.utils.Constants.PREF_KEY_FCM_TOKEN;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_TOKEN_IS_REFRESHED;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    PreferenceHelper preferenceHelper;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        preferenceHelper.setString(getApplicationContext(), PREF_KEY_FCM_TOKEN, token);
        preferenceHelper.setBoolean(getApplicationContext(), PREF_KEY_TOKEN_IS_REFRESHED, true);
    }

}
