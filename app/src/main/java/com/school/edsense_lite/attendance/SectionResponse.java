package com.school.edsense_lite.attendance;

import com.school.edsense_lite.today.AssignmentResponse;

import java.util.List;

public class SectionResponse {

    private List<SectionResponse.Response> response = null;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

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
        return response;
    }

    public void setResponse(List<SectionResponse.Response> response) {
        this.response = response;
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
