package com.school.edsense_lite.attendance;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.school.edsense_lite.today.AssignmentResponse;

import java.util.ArrayList;
import java.util.List;

public class SectionResponse {

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    private List<SectionResponse.Response> responseList = null;
    @SerializedName("response")
    private String responseString;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
        ArrayList<Response> yourArray = new Gson().fromJson(responseString, new TypeToken<List<Response>>(){}.getType());
        setResponse(yourArray);
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
    public List<SectionResponse.Response> getResponse() {
        return responseList;
    }

    public void setResponse(List<SectionResponse.Response> responseList) {
        this.responseList = responseList;
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
        private String compositeTagName;
        private String compositeTagId;

        public String getCompositeTagName() {
            return compositeTagName;
        }

        public void setCompositeTagName(String compositeTagName) {
            this.compositeTagName = compositeTagName;
        }

        public String getCompositeTagId() {
            return compositeTagId;
        }

        public void setCompositeTagId(String compositeTagId) {
            this.compositeTagId = compositeTagId;
        }
    }
}
