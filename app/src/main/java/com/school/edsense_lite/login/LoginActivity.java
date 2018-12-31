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

import com.google.firebase.iid.FirebaseInstanceId;
import com.school.edsense_lite.BaseActivity;
import com.school.edsense_lite.MainActivity;
import com.school.edsense_lite.NavigationDrawerActivity;
import com.school.edsense_lite.R;
import com.school.edsense_lite.firebase.FCMApi;
import com.school.edsense_lite.firebase.FcmRegRequest;
import com.school.edsense_lite.firebase.FcmRegResponse;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.edsense_lite.utils.Constants.BOARD_STRING;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_AVATAR_URL;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_BOARD_DATA;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_DISPLAY_NAME;
import static com.school.edsense_lite.utils.Constants.KEY_PREF_SUBJECT_DATA;
import static com.school.edsense_lite.utils.Constants.KEY_USER_ROLE_STUDENT;
import static com.school.edsense_lite.utils.Constants.KEY_USER_ROLE_TEACHER;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_BEARER_TOKEN;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_FCM_TOKEN;
import static com.school.edsense_lite.utils.Constants.PREF_KEY_TOKEN_IS_REFRESHED;
import static com.school.edsense_lite.utils.Constants.SECTION_STRING;
import static com.school.edsense_lite.utils.Constants.SUBJECT_STRING;
import static com.school.edsense_lite.utils.Constants.TEACHER_STRING;


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
    @BindView(R.id.btn_reset) Button _resetButton;
    @BindView(R.id.link_forgotpassword)
    TextView _forgotPasswordTV;
    @BindView(R.id.logo)
    ImageView _logoImageView;
    PreferenceHelper preferenceHelper;

    @Inject
    LoginApi loginApi;
    @Inject
    FCMApi fcmApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        //PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String loginToken = preferenceHelper.getString(LoginActivity.this, PREF_KEY_BEARER_TOKEN, "");
        String logoUrl = preferenceHelper.getString(LoginActivity.this, Constants.PREF_KEY_LOGO_URL, "");

        if(loginToken.isEmpty()){
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            //    _emailText.setText("GLA468");
            //    _passwordText.setText("Joselives199*");
            applyFonts();
            if(!logoUrl.isEmpty()) {
                Picasso.with(LoginActivity.this).load(logoUrl).fit()
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .into(_logoImageView);
            }
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
        _emailText.setTypeface(tf);
        _passwordText.setTypeface(tf);
        _forgotPasswordTV.setTypeface(tf);
        _loginButton.setTypeface(tf);
    }
    @OnClick(R.id.link_forgotpassword)
    public void forgotPassword(){
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivityForResult(intent, REQUEST_FORGOT_PASSWORD);
        //comingSoon();
    }
    @OnClick(R.id.btn_reset)
    public void clickOnReset(){
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String fcmToken = preferenceHelper.getString(LoginActivity.this, PREF_KEY_FCM_TOKEN, "");
        preferenceHelper.clear(LoginActivity.this);
        //clear will clear all data, so after clearing we are setting fcm token again in preferences.
        preferenceHelper.setString(LoginActivity.this, PREF_KEY_FCM_TOKEN, fcmToken);


        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
    @OnClick(R.id.btn_signin)
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_authenticating));
        progressDialog.show();

        final String username = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        LoginRequest request = new LoginRequest();
        request.setUserkey(username);
        request.setPassword(password);

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
                        _loginButton.setEnabled(true);
                        if(e.getMessage().contains("HTTP 404"))
                        {
                            new CustomAlertDialog().showAlert1(
                                    LoginActivity.this,
                                    R.string.text_login_failed,
                                    "Please enter valid credentials",
                                    null);
                        }
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
                            onLoginSuccess(username, password, loginResponse);
                            getUserDetails();
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
    private void getUserDetails(){
        String bearerToken = preferenceHelper.getString(LoginActivity.this, PREF_KEY_BEARER_TOKEN, "");
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_retrieving_details));
        progressDialog.show();
        loginApi.getUserRegistrationDetails(bearerToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfileResponse>() {
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
                    public void onNext(ProfileResponse profileResponse){
                        progressDialog.dismiss();
                        _loginButton.setEnabled(true);
                        if(profileResponse.getIsSuccess().equals(true)) {
                            processProfileData(profileResponse);
                            //initiate gcm registration call
                            initiateFcmRegistration();
                        }
                        else if(!profileResponse.getErrorCode().equals(200)){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    LoginActivity.this,
                                    R.string.text_login_failed,
                                    profileResponse.getErrorMessage(),
                                    null);
                        }

                    }
                });
    }
    private void initiateFcmRegistration(){
        String bearerToken = preferenceHelper.getString(LoginActivity.this, PREF_KEY_BEARER_TOKEN, "");
        String fcmToken = preferenceHelper.getString(LoginActivity.this, PREF_KEY_FCM_TOKEN, "");
        if(fcmToken.trim().isEmpty()){
           fcmToken = FirebaseInstanceId.getInstance().getToken();
        }
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_registering_device));
        progressDialog.show();
        FcmRegRequest fcmRegRequest = new FcmRegRequest();
        fcmRegRequest.setPlatform("gcm");
        fcmRegRequest.setPushChannel(fcmToken);
        fcmApi.fcmRegistration(bearerToken, fcmRegRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FcmRegResponse>() {
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
                    public void onNext(FcmRegResponse fcmRegResponse){
                        progressDialog.dismiss();
                        if(fcmRegResponse.getIsSuccess().equals(true)) {
                            preferenceHelper.setBoolean(getApplicationContext(), PREF_KEY_TOKEN_IS_REFRESHED, false);

                            displayNavigationActivity();
                        }
                        else if(!fcmRegResponse.getErrorCode().equals(200)){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    LoginActivity.this,
                                    R.string.text_failed,
                                    fcmRegResponse.getErrorMessage(),
                                    null);
                        }

                    }
                });
    }
    private void processProfileData(ProfileResponse profileResponse){
        ProfileResponse.Response response = profileResponse.getResponse();
        String userRolesString = response.getUserRoles();
        if(userRolesString.trim().length() > 0){
            setUserRoleData(userRolesString);
        }
        String loggedInUserDisplayName = response.getDisplayName();
        preferenceHelper.setString(LoginActivity.this, KEY_PREF_DISPLAY_NAME, loggedInUserDisplayName);

        String userAvatarUrl = response.getImageFileFullPath();
        preferenceHelper.setString(LoginActivity.this, KEY_PREF_AVATAR_URL, userAvatarUrl);

        List<ProfileResponse.Tag> tagList = response.getTags();
        setBoardData(tagList);
        setSubjectsData(tagList);
    }
    private void setUserRoleData(String userRoleData){
        if(userRoleData.contains(TEACHER_STRING)){
            preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_TEACHER, true);
            preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_STUDENT, false);
        }
        else{
            preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_TEACHER, false);
            preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_STUDENT, true);

        }

       // preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_TEACHER, false);
      //  preferenceHelper.setBoolean(LoginActivity.this, KEY_USER_ROLE_STUDENT, true);

    }
    private void setBoardData(List<ProfileResponse.Tag> tagList){
        String boardData = "";
        for(int i = 0; i < tagList.size(); i++){
            ProfileResponse.Tag tag = tagList.get(i);
            if(tag.getCategoryName().equals(BOARD_STRING)){
                boardData = tag.getName();
            }
        }
        for(int i = 0; i < tagList.size(); i++){
            ProfileResponse.Tag tag = tagList.get(i);
            if(tag.getCategoryName().equals(SECTION_STRING)){
                if(boardData.trim().length() > 0){
                    boardData = boardData + ", " + tag.getName();
                }
                else{
                    boardData = tag.getName();
                }
            }
        }
        preferenceHelper.setString(LoginActivity.this, KEY_PREF_BOARD_DATA, boardData);

    }
    private void setSubjectsData(List<ProfileResponse.Tag> tagList){
        String subjectString = "";
        for(int i = 0; i < tagList.size(); i++){
            ProfileResponse.Tag tag = tagList.get(i);
            if(tag.getCategoryName().equals(SUBJECT_STRING)){
                if(subjectString.trim().length() > 0){
                    subjectString = subjectString + ", " + tag.getName();
                }
                else{
                    subjectString = tag.getName();
                }
            }
        }
        preferenceHelper.setString(LoginActivity.this, KEY_PREF_SUBJECT_DATA, subjectString);
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

    public void onLoginSuccess(String username, String password, LoginResponse loginResponse) {
        _loginButton.setEnabled(true);
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        //  preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_LOGIN_ID, loginResponse.getRespon);
        if(!loginResponse.getResponse().getBearerToken().isEmpty()){
            preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_USERNAME, username);
            preferenceHelper.setString(LoginActivity.this, Constants.PREF_KEY_PASSWORD, password);
            preferenceHelper.setString(LoginActivity.this, PREF_KEY_BEARER_TOKEN, "Bearer "+loginResponse.getResponse().getBearerToken());
        }
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
        if (username.isEmpty()) {
            _emailText.setError("Enter a valid Username");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("Enter between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
