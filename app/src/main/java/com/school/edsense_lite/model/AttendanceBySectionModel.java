package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_attendancebysection")
public class AttendanceBySectionModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "attendancebysection_studentUserId")
    private String studentUserId;
    @ColumnInfo(name = "attendancebysection_DisplayName")
    private String DisplayName;
    @ColumnInfo(name = "attendancebysection_isAttended")
    private String isAttended;
    @ColumnInfo(name = "attendancebysection_compositeTagId")
    private String compositeTagId;
    @ColumnInfo(name = "attendancebysection_compositeTagName")
    private String compositeTagName;
    @ColumnInfo(name = "attendancebysection_isEarlyOut")
    private String isEarlyOut;
    @ColumnInfo(name = "attendancebysection_isLateIn")
    private String isLateIn;
    @ColumnInfo(name = "attendancebysection_earlyOutTime")
    private String earlyOutTime;
    @ColumnInfo(name = "attendancebysection_lateInTime")
    private String lateInTime;
    @ColumnInfo(name = "attendancebysection_userAttendanceId")
    private String userAttendanceId;
    @ColumnInfo(name = "attendancebysection_reason")
    private String reason;
    @ColumnInfo(name = "attendancebysection_date")
    private String date;
    @ColumnInfo(name = "attendancebysection_totalCount")
    private String totalCount;
    @ColumnInfo(name = "attendancebysection_isSynced")
    private transient boolean isSynced;
    @ColumnInfo(name = "attendancebysection_Gender")
    private int Gender;

    @NonNull
    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(@NonNull String studentUserId) {
        this.studentUserId = studentUserId;
    }
    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }
    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getIsAttended() {
        return isAttended;
    }

    public void setIsAttended(String isAttended) {
        this.isAttended = isAttended;
    }

    public String getCompositeTagId() {
        return compositeTagId;
    }

    public void setCompositeTagId(String compositeTagId) {
        this.compositeTagId = compositeTagId;
    }

    public String getCompositeTagName() {
        return compositeTagName;
    }

    public void setCompositeTagName(String compositeTagName) {
        this.compositeTagName = compositeTagName;
    }

    public String getEarlyOutTime() {
        return earlyOutTime;
    }

    public void setEarlyOutTime(String earlyOutTime) {
        this.earlyOutTime = earlyOutTime;
    }

    public String getLateInTime() {
        return lateInTime;
    }

    public void setLateInTime(String lateInTime) {
        this.lateInTime = lateInTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getUserAttendanceId() {
        return userAttendanceId;
    }

    public void setUserAttendanceId(String userAttendanceId) {
        this.userAttendanceId = userAttendanceId;
    }
}
