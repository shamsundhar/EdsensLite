package com.school.edsense_lite.attendance;

import com.google.gson.annotations.SerializedName;

public class SaveAttendanceRequest {
    @SerializedName("users")
    private String userString;


    public String getUsers() {
        return userString;
    }

    public void setUsers(String userString) {
        this.userString = userString;
    }
    public class User {

        private String displayName;
        private String studentUserId;
        private Boolean isAttended;
        private Boolean isLateIn;
        private Boolean isEarlyOut;
        private Integer userAttendanceId;
        private Integer totalCount;
        private String date;
        private Object inTime;
        private Object outTime;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        private String reason;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getStudentUserId() {
            return studentUserId;
        }

        public void setStudentUserId(String studentUserId) {
            this.studentUserId = studentUserId;
        }

        public Boolean getIsAttended() {
            return isAttended;
        }

        public void setIsAttended(Boolean isAttended) {
            this.isAttended = isAttended;
        }

        public Boolean getIsLateIn() {
            return isLateIn;
        }

        public void setIsLateIn(Boolean isLateIn) {
            this.isLateIn = isLateIn;
        }

        public Boolean getIsEarlyOut() {
            return isEarlyOut;
        }

        public void setIsEarlyOut(Boolean isEarlyOut) {
            this.isEarlyOut = isEarlyOut;
        }

        public Integer getUserAttendanceId() {
            return userAttendanceId;
        }

        public void setUserAttendanceId(Integer userAttendanceId) {
            this.userAttendanceId = userAttendanceId;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Object getInTime() {
            return inTime;
        }

        public void setInTime(Object inTime) {
            this.inTime = inTime;
        }

        public Object getOutTime() {
            return outTime;
        }

        public void setOutTime(Object outTime) {
            this.outTime = outTime;
        }

    }
}
