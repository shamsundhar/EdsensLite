package com.school.edsense_lite.login;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.edsense_lite.BaseActivity;
import com.school.edsense_lite.MainActivity;
import com.school.edsense_lite.NavigationDrawerActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by shyam on 2/19/2018.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_FORGOT_PASSWORD = 1;

    @BindView(R.id.input_username) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signin) Button _loginButton;
    @BindView(R.id.link_forgotpassword)
    TextView _forgotPasswordTV;
    @BindView(R.id.logo)
    ImageView _logoImageView;

    @Inject
    LoginApi loginApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String loginToken = preferenceHelper.getString(LoginActivity.this, Constants.PREF_KEY_BEARER_TOKEN, "");
        String logoUrl = preferenceHelper.getString(LoginActivity.this, Constants.PREF_KEY_LOGO_URL, "");
        if(loginToken.isEmpty()){
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            _emailText.setText("GLA468");
            _passwordText.setText("Joselives199*");
            // Picasso.with(LoginActivity.this).load(logoUrl).into(_logoImageView);
            applyFonts();
            Picasso.with(LoginActivity.this).load(logoUrl).fit()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(_logoImageView);
        }
        else{
            displayNavigationActivity();
        }
    }
    private void applyFonts(){
        // Font path
        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        _forgotPasswordTV.setTypeface(tf);
        _loginButton.setTypeface(tf);
    }
    @OnClick(R.id.link_forgotpassword)
    public void forgotPassword(){
        //  Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        //   startActivityForResult(intent, REQUEST_FORGOT_PASSWORD);
        comingSoon();
    }
    @OnClick(R.id.btn_signin)
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        //  displayNavigationActivity();

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_authenticating));
        progressDialog.show();

        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        LoginRequest request = new LoginRequest();
        request.setUserkey(username);
        request.setPassword(password);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String subscriptionId = preferenceHelper.getString(LoginActivity.this,
                Constants.PREF_KEY_SUBSCRIPTION_ID, "");
        request.setSubscriptionId(subscriptionId);
        request.setKeepAlive("false");
        request.setLogintype("1");

        loginApi.login(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        onLoginFailed();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse){
                        progressDialog.dismiss();
                        _loginButton.setEnabled(true);
                        if(loginResponse.getIsSuccess().equals("true")) {
                            onLoginSuccess(loginResponse);
                        }
                        else if(!loginResponse.getErrorCode().equals("200")){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    LoginActivity.this,
                                    R.string.text_login_failed,
                                    loginResponse.getErrorMessage(),
                                    null);
                        }

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

//                new CustomAlertDialog().showAlert1(
//                        LoginActivity.this,
//                        R.string.text_account_created,
//                        data.getExtras().getString(BUNDLE_KEY_OTP_VERIFIED_MESSAGE),
//                        null);
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                // this.finish();
            }
        }
        if (requestCode == REQUEST_FORGOT_PASSWORD) {
            if (resultCode == RESULT_OK) {
//                if(data.getExtras().getBoolean(BUNDLE_KEY_RESET_PASSWORD_RESULT_FLAG, false)){
//                    //display alert reset password completed successfully
//                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(LoginResponse loginResponse) {
        _loginButton.setEnabled(true);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        //  preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_LOGIN_ID, loginResponse.getRespon);
        if(!loginResponse.getResponse().getBearerToken().isEmpty()){
            preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_BEARER_TOKEN, "Bearer "+loginResponse.getResponse().getBearerToken());
        }
        displayNavigationActivity();
    }
    private void displayNavigationActivity()
    {
        startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }
    public void comingSoon(){
        Toast.makeText(getBaseContext(), "Coming Soon", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;

        String username = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();
        if (username.isEmpty() || username.length() < 4) {
            _emailText.setError("Enter a valid Username");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
