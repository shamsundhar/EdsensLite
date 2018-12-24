package com.school.edsense_lite.today;

import com.school.edsense_lite.login.LoginRequest;
import com.school.edsense_lite.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TodayApi {

 // https://edsensewebtst.azurewebsites.net/api/Academic/GetDayWiseScheduleByUser?currentDate=08-20-2018
 @GET("Academic/GetDayWiseScheduleByUser")
 @Headers({"Content-Type: application/json"})
 Observable<ScheduleResponse> getSchedules(@Query("currentDate") String currentDate, @Header("Authorization") String token);

 //https://edsensewebtst.azurewebsites.net/api/Assignment/GetAssignmentDetails?value=1
 @GET("Assignment/GetAssignmentDetails?value=1")
 @Headers({"Content-Type: application/json"})
 Observable<AssignmentResponse> getAssignmentsForLoginUser(@Header("Authorization") String token);

 //https://apitst.edsense.co.in/api/news/GetPortalNewsByTypeId
 @POST("news/GetPortalNewsByTypeId")
 @Headers({"Content-Type: application/json"})
 Observable<NewsResponse> getNews(@Header("Authorization") String token, @Body NewsRequest newsRequest);

 //https://apitst.edsense.co.in/api/calendar/GetEventbyOrgId
 @POST("calendar/GetEventbyOrgId")
 @Headers({"Content-Type: application/json"})
 Observable<EventsResponse> getEvents(@Header("Authorization") String token, @Body EventsRequest newsRequest);
}
