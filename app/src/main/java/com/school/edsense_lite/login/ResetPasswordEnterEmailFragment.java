package com.school.edsense_lite.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.school.edsense_lite.BaseFragment;
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
import retrofit2.HttpException;

/**
 * Created by shyam on 2/19/2018.
 */

public class ResetPasswordEnterEmailFragment extends BaseFragment {
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.input_username)
    EditText _usernameText;
    @BindView(R.id.input_otp)
    EditText _otpText;
    @BindView(R.id.input_newpassword)
    EditText newPassword;
    @BindView(R.id.input_confpassword)
    EditText confPassword;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_mobile)
    TextInputLayout tilMobile;
    @BindView(R.id.forgotPassword_layout)
    LinearLayout forgotPasswordLayout;
    @BindView(R.id.changePassword_layout)
    LinearLayout changePasswordLayout;
    @BindView(R.id.send_otp_layout)
    LinearLayout sendOtpLayout;
    @BindView(R.id.verify_otp_layout)
    LinearLayout verifyOtpLayout;
    @BindView(R.id.btn_ok)
    Button buttonOk;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    ActivityListener activityListener;
    PreferenceHelper preferenceHelper;
    @Inject
    LoginApi loginApi;

    String user_email;
    String user_phonenumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password_enter_mobile, null);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String logoUrl = preferenceHelper.getString(getActivity(), Constants.PREF_KEY_LOGO_URL, "");
        if(!logoUrl.isEmpty()) {
            Picasso.with(getActivity()).load(logoUrl).fit()
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(logo);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rb_email) {
                    tilMobile.setVisibility(View.GONE);
                    tilEmail.setVisibility(View.VISIBLE);
                } else if(checkedId == R.id.rb_phone) {
                    tilMobile.setVisibility(View.VISIBLE);
                    tilEmail.setVisibility(View.GONE);
                }
            }

        });
        return view;
    }
    @OnClick({R.id.btn_cancel, R.id.btn_cancel2, R.id.btn_cancel3})
    public void clickOnCancel(){
        ((ActivityListener)getActivity()).cancelClicked();
    }
    @OnClick(R.id.btn_ok)
    public void clickOnOk(){
        if(validateUsername()){
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();

            String subscriptionId = preferenceHelper.getString(getActivity(),
                    Constants.PREF_KEY_SUBSCRIPTION_ID, "");
            String username = _usernameText.getText().toString().trim();

            loginApi.getUserByName(username, subscriptionId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetUserDetailsResponse>() {
                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            //onLoginFailed();
                            if(e instanceof HttpException){
                                    new CustomAlertDialog().showAlert1(
                                            getActivity(),
                                            R.string.text_error,
                                            "User not found",
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
                        public void onNext(GetUserDetailsResponse response){
                            progressDialog.dismiss();
                            buttonOk.setEnabled(true);
                            if(response.getIsSuccess().equals(true)) {
                                user_email = response.getResponse().getEmail();
                                user_phonenumber = response.getResponse().getPhoneNumber();
                                sendOtpLayout.setVisibility(View.VISIBLE);
                                buttonOk.setVisibility(View.GONE);
                            }
                            else if(response.getErrorCode().equals(404)){
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_error,
                                        response.getErrorMessage(),
                                        null);
                            }
                            else if(!response.getErrorCode().equals(200)){
                                //display error.
                                new CustomAlertDialog().showAlert1(
                                        getActivity(),
                                        R.string.text_failed,
                                        response.getErrorMessage(),
                                        null);
                            }

                        }
                    });

        }

        // Animation leftToRight = AnimationUtils.loadAnimation(getActivity(), R.anim.lefttoright);
        //   Animation rightToLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.righttoleft);
//        leftToRight.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        //    buttonOk.startAnimation(rightToLeft);

        //      sendOtpLayout.startAnimation(rightToLeft);
    }
    @OnClick(R.id.btn_sendotp)
    public void clickOnSendOtp(){
        if(validateSendOtpPage()) {
            sendOtpLayout.setVisibility(View.GONE);
            verifyOtpLayout.setVisibility(View.VISIBLE);
            _usernameText.setEnabled(false);
        }
    }
    @OnClick(R.id.btn_verify)
    public void clickOnVeify(){
        if(validateVerifyPage()){
            forgotPasswordLayout.setVisibility(View.GONE);
            changePasswordLayout.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.btn_submit)
    public void clickOnSubmit(){
        String newpswd = newPassword.getText().toString().trim();
        String confpswd = confPassword.getText().toString().trim();
        if(validatePasswordPage()) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();
//            loginApi.forgotPassword(new ForgotPasswordRequest(mobileNumber))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<BaseResponse>() {
//                        @Override
//                        public void onError(Throwable e) {
//                            progressDialog.dismiss();
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(BaseResponse baseResponse){
//                            progressDialog.dismiss();
//                            if(baseResponse.getStatus().equals("200")) {
//                                ((ActivityListener)getActivity()).displayOtpPage(mobileNumber);
//                            }
//                            else if(baseResponse.getStatus().equals("206")){
//                                //display error.
//                                new CustomAlertDialog().showAlert1(
//                                        getActivity(),
//                                        R.string.text_reset_password_failed,
//                                        baseResponse.getMessage(),
//                                        null);
//                            }
//
//                        }
//                    });
        }

    }
    public Boolean validateUsername(){
        if(_usernameText.getText().toString().trim().length() > 0){
            _usernameText.setError(null);
            return true;
        }
        else{
            _usernameText.setError("Username should not be empty");
            return false;
        }
    }
    public Boolean validateSendOtpPage(){
        String username = _usernameText.getText().toString().trim();
        String email = _emailText.getText().toString().trim();
        String phone = _mobileText.getText().toString().trim();
        Boolean flag = true;
        if(username.isEmpty()){
            _usernameText.setError("Username should not be empty");
            flag = false;
        }
        else{
            _usernameText.setError(null);
        }
        if(tilEmail.getVisibility() == View.VISIBLE){
            if(email.isEmpty()){
                _emailText.setError("Email address should not be empty");
                flag = false;
            }
            else{
                if(!user_email.equals(email)){
                    flag = false;
                    _emailText.setError("Email address is not linked with provided username");
                }
                else{
                    _emailText.setError(null);
                }
            }
        }
        else if(tilMobile.getVisibility() == View.VISIBLE){
            if(phone.isEmpty()){
                _mobileText.setError("Phone number should not be empty");
                flag = false;
            }
            else{
                if(!user_phonenumber.equals(email)){
                    flag = false;
                    _mobileText.setError("Phone number is not linked with provided username");
                }
                else{
                    _mobileText.setError(null);
                }
            }
        }
        return flag;
    }
    public Boolean validateVerifyPage(){
        String otp = _otpText.getText().toString().trim();
        if(otp.isEmpty()){
            _otpText.setError("OTP should not be empty");
            return false;
        }
        else{
            _otpText.setError(null);
            return true;
        }
    }
    public Boolean validatePasswordPage(){
        Boolean valid = true;
        String newpswd = newPassword.getText().toString().trim();
        String confpswd = confPassword.getText().toString().trim();
        if(newpswd.isEmpty()){
            valid = false;
            newPassword.setError("Password should not be empty");
        }
        else{
            newPassword.setError(null);
        }
        if(confpswd.isEmpty()){
            valid = false;
            confPassword.setError("Password should not be empty");
        }
        else{
            confPassword.setError(null);
        }
        if(!newpswd.equals(confpswd)){
            valid = false;
            new CustomAlertDialog().showAlert1(
                    getActivity(),
                    R.string.text_error,
                    "Passwords are not matching",
                    null);
        }
        return valid;
    }
    public interface ActivityListener{
        void displayOtpPage(String mobileNumber);
        void resetPasswordResult(Boolean flag);
        void cancelClicked();
    }
}
