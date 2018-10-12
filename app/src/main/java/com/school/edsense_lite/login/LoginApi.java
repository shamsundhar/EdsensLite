package com.school.edsense_lite.login;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("account/Login?device=desktop&browserPlatform=Win32")
    @Headers({"Content-Type: application/json"})
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

}
