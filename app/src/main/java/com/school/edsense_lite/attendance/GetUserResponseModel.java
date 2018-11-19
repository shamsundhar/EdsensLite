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
