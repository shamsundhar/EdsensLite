package com.school.edsense_lite.subscription;

import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SubsciptionApi {

    //https://apitst.edsense.co.in/api/portal/GetSubscriptionInfoByDomain?domain=glendale.edsense.app,
    @GET("portal/GetSubscriptionInfoByDomain")
   // @Headers({"Content-Type: application/json"})
    Observable<SubscriptionResponse> getSubscriptionInfoByDomain(@Query("domain") String domain);

}
