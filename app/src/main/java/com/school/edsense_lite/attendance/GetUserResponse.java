package com.school.edsense_lite.attendance;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserResponse {
//    private List<GetUserResponse.Response> response = null;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    @SerializedName("response")
    private String responseString;
//    public List<GetUserResponse.Response> getResponse() {
//        return response;
//    }
//
//    public void setResponse(List<GetUserResponse.Response> response) {
//        this.response = response;
//    }

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

}
