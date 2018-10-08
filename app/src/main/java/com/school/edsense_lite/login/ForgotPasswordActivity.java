package com.school.edsense_lite.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.school.edsense_lite.BaseActivity;
import com.school.edsense_lite.R;

/**
 * Created by shyam on 2/19/2018.
 */

public class ForgotPasswordActivity extends BaseActivity implements ResetPasswordEnterEmailFragment.ActivityListener {
    private static final String RESET_PASSWORD_FRAGMENT_TAG = "RESET_PASSWORD";
    private static final String RESET_PASSWORD_ENTER_OTP_FRAGMENT_TAG = "RESET_PASSWORD_ENTER_OTP";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ResetPasswordEnterEmailFragment(), RESET_PASSWORD_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void resetPasswordResult(Boolean flag) {
//        Intent intent = new Intent();
//        intent.putExtra(BUNDLE_KEY_RESET_PASSWORD_RESULT_FLAG, flag);
//        setResult(RESULT_OK, intent);
//        finish();//finishing activity
    }

    @Override
    public void displayOtpPage(String mobileNumber) {
//        Bundle bundle = new Bundle();
//        bundle.putString(BUNDLE_KEY_MOBILE_NUMBER, mobileNumber);
//        ResetPasswordEnterOtpFragment fragment = new ResetPasswordEnterOtpFragment();
//        fragment.setArguments(bundle);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment, RESET_PASSWORD_ENTER_OTP_FRAGMENT_TAG)
//                .commit();
    }
}
