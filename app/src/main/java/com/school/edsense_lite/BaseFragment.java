package com.school.edsense_lite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.school.edsense_lite.injection.components.DaggerFragmentComponent;
import com.school.edsense_lite.injection.components.FragmentComponent;
import com.school.edsense_lite.login.LoginActivity;
import com.school.edsense_lite.login.LoginApi;
import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;
import com.school.edsense_lite.utils.Constants;
import com.school.edsense_lite.utils.CustomAlertDialog;
import com.school.edsense_lite.utils.PreferenceHelper;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;
    @Inject
    LoginApi loginApi;
    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        mFragmentComponent = null;
    }


    protected final FragmentComponent fragmentComponent() {
        if(mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .activityComponent(((BaseActivity) getActivity()).activityComponent())
                    .build();
        }

        return mFragmentComponent;
    }

    public int dimen(@DimenRes int resId) {
        return (int) getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }

    public String string(@StringRes int resId) {
        return getResources().getString(resId);
    }

    public void relogin(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_reconnecting));
        progressDialog.show();
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        String username = preferenceHelper.getString(getActivity(),Constants.PREF_KEY_USERNAME,"");
        String password = preferenceHelper.getString(getActivity(),Constants.PREF_KEY_PASSWORD,"");
        LoginRequest request = new LoginRequest();
        request.setUserkey(username);
        request.setPassword(password);
        String subscriptionId = preferenceHelper.getString(getActivity(),
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
                        if(loginResponse.getIsSuccess().equals("true")) {
                            onLoginSuccess(loginResponse);
                        }
                        else if(!loginResponse.getErrorCode().equals("200")){
                            //display error.
                            new CustomAlertDialog().showAlert1(
                                    getActivity(),
                                    R.string.text_login_failed,
                                    loginResponse.getErrorMessage(),
                                    null);
                        }

                    }
                });
    }
    public void onLoginSuccess(LoginResponse loginResponse) {
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        if(!loginResponse.getResponse().getBearerToken().isEmpty()){
           preferenceHelper.setString(getActivity(), Constants.PREF_KEY_BEARER_TOKEN, "Bearer "+loginResponse.getResponse().getBearerToken());
        }
        navigateToHome();
    }
    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
        signout();
    }
    public void navigateToHome(){
        Intent in = new Intent(getActivity(), NavigationDrawerActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        getActivity().finish();
    }
    public void signout(){
        PreferenceHelper preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        preferenceHelper.clear(getActivity());
        Intent in = new Intent(getActivity(), MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        getActivity().finish();
    }
}
