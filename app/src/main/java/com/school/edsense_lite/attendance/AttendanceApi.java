package com.school.edsense_lite.attendance;

import com.school.edsense_lite.today.AssignmentResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface AttendanceApi {
    //https://apitst.edsense.co.in/api/Academic/getFiltersforAttendance?returnType=json
    @GET("Academic/getFiltersforAttendance?returnType=json")
    //@Headers({"Content-Type: application/json"})
    Observable<SectionResponse> getSectionsForAttendance(@Header("Authorization") String token);
}
