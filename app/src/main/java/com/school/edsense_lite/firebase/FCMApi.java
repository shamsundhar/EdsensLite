package com.school.edsense_lite.firebase;

import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMApi {
    ///api/Portal/CreateOrUpdateInstallation
    @POST("Portal/CreateOrUpdateInstallation")
    @Headers({"Content-Type: application/json"})
    Observable<FcmRegResponse> fcmRegistration(@Header("Authorization") String token, @Body FcmRegRequest fcmRegRequest);

    ///api/Portal/DeleteInstallation
    @POST("Portal/DeleteInstallation")
    @Headers({"Content-Type: application/json"})
    Observable<FcmUnRegResponse> fcmUnRegistration(@Header("Authorization") String token, @Body FcmUnRegRequest fcmUnRegRequest);
}
