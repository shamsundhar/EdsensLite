package com.school.edsense_lite.subscription;

import com.school.edsense_lite.login.LoginResponse;

import java.util.List;

public class SubscriptionResponse {

    private List<SubscriptionResponse.Response> response = null;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public List<Response> getResponse() {
        return response;
    }
    public void setResponse(List<Response> response) {
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
        private Integer subscriptionId;
        private String subscriberId;
        private String expiry;
        private String name;
        private Integer allowedUsersCount;
        private String primaryUrl;
        private String secondaryUrl;
        private String logoUrl;

        public Integer getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(Integer subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public String getSubscriberId() {
            return subscriberId;
        }

        public void setSubscriberId(String subscriberId) {
            this.subscriberId = subscriberId;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAllowedUsersCount() {
            return allowedUsersCount;
        }

        public void setAllowedUsersCount(Integer allowedUsersCount) {
            this.allowedUsersCount = allowedUsersCount;
        }

        public String getPrimaryUrl() {
            return primaryUrl;
        }

        public void setPrimaryUrl(String primaryUrl) {
            this.primaryUrl = primaryUrl;
        }

        public String getSecondaryUrl() {
            return secondaryUrl;
        }

        public void setSecondaryUrl(String secondaryUrl) {
            this.secondaryUrl = secondaryUrl;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }
    }
}
