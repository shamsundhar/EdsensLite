package com.school.edsense_lite.login;

public class SendOtpRequest {
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    public class Value {

        private String userKey;
        private Integer otpContextId;
        private Integer subscriptionId;
        private String communicationType;
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

        public String getCommunicationType() {
            return communicationType;
        }

        public void setCommunicationType(String communicationType) {
            this.communicationType = communicationType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
