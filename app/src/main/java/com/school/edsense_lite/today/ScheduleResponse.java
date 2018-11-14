package com.school.edsense_lite.today;

import com.school.edsense_lite.model.Subscription;

import java.util.List;

/**
 * Created by shyam on 4/10/2018.
 */

public class ScheduleResponse {
    private Response response;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
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

    public class Response{
        private List<Row> rows = null;

        public List<Row> getRows() {
            return rows;
        }
        public void setRows(List<Row> rows) {
            this.rows = rows;
        }

    }

}
