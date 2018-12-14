package com.school.edsense_lite.login;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("account/Login?device=mobile&browserPlatform=AndroidEdSenseLite")
    @Headers({"Content-Type: application/json"})
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    //https://apitst.edsense.co.in/api/account/GetUserRegistrationDetails
    @GET("account/GetUserRegistrationDetails")
    @Headers({"Content-Type: application/json"})
    Observable<ProfileResponse> getUserRegistrationDetails(@Header("Authorization") String token);

   // https://apitst.edsense.co.in/api/Account/GetUserByName?value=GLA468&subscriptionId=5
   @GET("account/GetUserByName")
   @Headers({"Content-Type: application/json"})
   Observable<GetUserDetailsResponse> getUserByName(@Query("value") String username, @Query("subscriptionId") String subscriptionId);

}
