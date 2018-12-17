package com.school.edsense_lite.login;

public class SendOtpResponse {
    private Boolean response;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
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

}
