package com.school.edsense_lite.attendance;

import java.util.List;

public class GetUserResponse {
    private List<GetUserResponse.Response> response = null;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public List<GetUserResponse.Response> getResponse() {
        return response;
    }

    public void setResponse(List<GetUserResponse.Response> response) {
        this.response = response;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(String isUserActive) {
        this.isUserActive = isUserActive;
    }

    public class Response {
        private String DisplayName;
        private String studentUserId;
        private String isAttended;
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
}
