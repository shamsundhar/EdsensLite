package com.school.edsense_lite.attendance;

import com.school.edsense_lite.notes.GetUserNotesResponse;
import com.school.edsense_lite.notes.GetUsersForNotesRequest;
import com.school.edsense_lite.notes.GetUsersForNotesResponse;
import com.school.edsense_lite.notes.SaveNotesRequest;
import com.school.edsense_lite.notes.SaveNotesResponse;
import com.school.edsense_lite.today.AssignmentResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AttendanceApi {
    //https://apitst.edsense.co.in/api/Academic/getFiltersforAttendance?returnType=json
    @GET("Academic/getFiltersforAttendance?returnType=json")
    @Headers({"Content-Type: application/json"})
    Observable<SectionResponse> getSectionsForAttendance(@Header("Authorization") String token);

    //https://apitst.edsense.co.in/api/Academic/getUsers
    @POST("Academic/getUsers")
    @Headers({"Content-Type: application/json"})
    Observable<GetUserResponse> getUsersBasedOnSection(@Header("Authorization") String token, @Body GetUserRequest getUserRequest);

    //https://apitst.edsense.co.in/api/Account/SearchUser
    @POST("Account/SearchUser")
    @Headers({"Content-Type: application/json"})
    Observable<GetUsersForNotesResponse> getUsersForAddingNotes(@Header("Authorization") String token, @Body GetUsersForNotesRequest getUsersForNotesRequest);


    //https://apitst.edsense.co.in/api/Academic/UsersSaveAttendance
    @POST("Academic/UsersSaveAttendance")
    @Headers({"Content-Type: application/json"})
    Observable<SaveAttendanceResponse> saveUserAttendance(@Header("Authorization") String token, @Body SaveAttendanceRequest saveAttendanceRequest);

    //  https://apitst.edsense.co.in/api/Academic/GetSeverityTypes
    @GET("Academic/GetSeverityTypes")
    @Headers({"Content-Type: application/json"})
    Observable<SeverityTypeResponse> getSeverityTypes(@Header("Authorization") String token);

    // https://apitst.edsense.co.in/api/Portal/SearchTagsByCategoryId
    @POST("Portal/SearchTagsByCategoryId")
    @Headers({"Content-Type: application/json"})
    Observable<GetTraitsResponse> getTraits(@Header("Authorization") String token, @Body GetTraitsRequest getUserRequest);

    @POST("Academic/GetNotes")
    @Headers({"Content-Type: application/json"})
    Observable<GetUserNotesResponse> getNotes(@Header("Authorization") String token, @Body GetUserRequest getUserRequest);

    //https://apitst.edsense.co.in/api/Academic/SaveStudentNotes
    @POST("Academic/SaveStudentNotes")
    @Headers({"Content-Type: application/json"})
    Observable<SaveNotesResponse> saveNotes(@Header("Authorization") String token, @Body SaveNotesRequest saveUserNotesRequest);

}
