package com.school.edsense_lite.subscription;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.school.edsense_lite.BaseActivity;
import com.school.edsense_lite.NavigationDrawerActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.login.ForgotPasswordActivity;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.DOMAIN_POSTFIX;

public class SubscriptionActivity extends BaseActivity {
    private static final String TAG = "SubscriptionActivity";

    @BindView(R.id.hostname)
    EditText _hostnameText;

    @BindView(R.id.btn_continue)
    Button _continueButton;

    @Inject
    SubsciptionApi subsciptionApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String loginToken = preferenceHelper.getString(SubscriptionActivity.this, Constants.PREF_KEY_BEARER_TOKEN, "");
        if(loginToken.isEmpty()){
            setContentView(R.layout.activity_subscription);
            ButterKnife.bind(this);
            _hostnameText.setText("glendale");
        }
        else{
            displayLoginActivity();
        }
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        _continueButton.setTypeface(tf);
    }

    @OnClick(R.id.btn_continue)
    public void login() {
        Log.d(TAG, "Subscription");

        if (!validate()) {
            return;
        }

        _continueButton.setEnabled(false);

        //  displayLoginActivity();

        final ProgressDialog progressDialog = new ProgressDialog(SubscriptionActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();

        String hostname = _hostnameText.getText().toString().trim();
        String domain = hostname + DOMAIN_POSTFIX;

        subsciptionApi.getSubscriptionInfoByDomain(domain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubscriptionResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        _continueButton.setEnabled(true);
                        tryLater();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubscriptionResponse subscriptionResponse){
                        progressDialog.dismiss();
                        _continueButton.setEnabled(true);
                        if(subscriptionResponse.getIsSuccess().equals("true")) {
                            onSubsciptionSuccess(subscriptionResponse);
                        }
                        else if(!subscriptionResponse.getErrorCode().equals("200")){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    SubscriptionActivity.this,
                                    R.string.text_login_failed,
                                    subscriptionResponse.getErrorMessage(),
                                    null);
                        }

                    }
                });
    }


    public void onSubsciptionSuccess(SubscriptionResponse subscriptionResponse) {
        _continueButton.setEnabled(true);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        if(subscriptionResponse.getResponse().size() > 0) {
            if (!subscriptionResponse.getResponse().get(0).getSubscriberId().isEmpty()) {
                preferenceHelper.setString(SubscriptionActivity.this,
                        Constants.PREF_KEY_SUBSCRIPTION_ID,
                        Integer.toString(subscriptionResponse.getResponse().get(0).getSubscriptionId()));
                preferenceHelper.setString(SubscriptionActivity.this,
                        Constants.PREF_KEY_LOGO_URL,
                        subscriptionResponse.getResponse().get(0).getLogoUrl()
                );
            }
        }
        displayLoginActivity();
    }
    private void displayLoginActivity()
    {
        startActivity(new Intent(SubscriptionActivity.this, LoginActivity.class));
        finish();
    }
    public boolean validate() {
        boolean valid = true;

        String hostname = _hostnameText.getText().toString().trim();
        if (hostname.isEmpty()) {
            _hostnameText.setError("Enter a valid host name");
            valid = false;
        } else {
            _hostnameText.setError(null);
        }
        return valid;
    }
    private void tryLater(){
        Toast.makeText(getBaseContext(), "Try again later", Toast.LENGTH_LONG).show();
    }

}
