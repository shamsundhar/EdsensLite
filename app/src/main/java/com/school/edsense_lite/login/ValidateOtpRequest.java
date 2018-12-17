package com.school.edsense_lite.login;

public class ValidateOtpRequest {
    private ValidateOtpRequest.Value value;

    public ValidateOtpRequest.Value getValue() {
        return value;
    }

    public void setValue(ValidateOtpRequest.Value value) {
        this.value = value;
    }
    public class Value {

        private String userKey;
        private Integer otpContextId;
        private Integer subscriptionId;
        private String otpDetails;
        private String userName;

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public Integer getOtpContextId() {
            return otpContextId;
        }

        public void setOtpContextId(Integer otpContextId) {
            this.otpContextId = otpContextId;
        }

        public Integer getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(Integer subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public String getOtpDetails() {
            return otpDetails;
        }

        public void setOtpDetails(String otpDetails) {
            this.otpDetails = otpDetails;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
