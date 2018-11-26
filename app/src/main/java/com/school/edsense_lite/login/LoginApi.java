package com.school.edsense_lite.login;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("account/Login?device=mobile&browserPlatform=AndroidEdSenseLite")
    @Headers({"Content-Type: application/json"})
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    //https://apitst.edsense.co.in/api/account/GetUserRegistrationDetails
    @GET("account/GetUserRegistrationDetails")
    @Headers({"Content-Type: application/json"})
    Observable<ProfileResponse> getUserRegistrationDetails(@Header("Authorization") String token);
}
