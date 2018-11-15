package com.school.edsense_lite.messages;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MessagesApi {
    @POST("NC/GetNotificationList")
    @Headers({"Content-Type: application/json"})
    Observable<MessagesResponse> fetch(@Header("Authorization") String token, @Body MessagesRequest messagesRequest);

    @POST("NC/GetNotificationDetails")
    @Headers({"Content-Type: application/json"})
    Observable<MessagesResponse> fetchNotificationDetails(@Header("Authorization") String token, @Body String messageId);


    @POST("NC/SaveNotification")
    @Headers({"Content-Type: application/json"})
    Observable<MessagesResponse> sendNotification(@Header("Authorization") String token, @Body String messageId);

}
