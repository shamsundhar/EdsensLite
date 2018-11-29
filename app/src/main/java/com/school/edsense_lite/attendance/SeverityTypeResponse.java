package com.school.edsense_lite.attendance;

import java.util.List;

public class SeverityTypeResponse {
    private List<Response> response = null;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }
   public class Response{
        private Integer id;
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
