package com.school.edsense_lite.attendance;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_getuser_response")
public class GetUserResponseModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "getuser_studentUserId")
    private String studentUserId;
    @ColumnInfo(name = "getuser_DisplayName")
    private String DisplayName;
    @ColumnInfo(name = "getuser_isAttended")
    private String isAttended;
    @ColumnInfo(name = "getuser_totalCount")
    private String totalCount;
    @ColumnInfo(name = "getuser_userAttendanceId")
    private String userAttendanceId;
    @ColumnInfo(name = "getuser_isEarlyOut")
    private String isEarlyOut;
    @ColumnInfo(name = "getuser_isLateIn")
    private String isLateIn;
    @ColumnInfo(name = "getuser_reason")
    private String reason;

    public String getUserAttendanceId() {
        return userAttendanceId;
    }

    public void setUserAttendanceId(String userAttendanceId) {
        this.userAttendanceId = userAttendanceId;
    }

    public String getIsEarlyOut() {
        return isEarlyOut;
    }

    public void setIsEarlyOut(String isEarlyOut) {
        this.isEarlyOut = isEarlyOut;
    }

    public String getIsLateIn() {
        return isLateIn;
    }

    public void setIsLateIn(String isLateIn) {
        this.isLateIn = isLateIn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getIsAttended() {
        return isAttended;
    }

    public void setIsAttended(String isAttended) {
        this.isAttended = isAttended;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

}
