package com.school.edsense_lite.messages;

import com.school.edsense_lite.model.MessagesResponseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDetailsResponse {
    private Response response;
    private boolean isSuccess;
    private int errorCode;
    private String errorMessage;
    private boolean isUserActive;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

    public class Response {

        private List<MessageDetailsModel> rows = null;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public List<MessageDetailsModel> getRows() {
            return rows;
        }

        public void setRows(List<MessageDetailsModel> rows) {
            this.rows = rows;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
