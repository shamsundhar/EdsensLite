package com.school.edsense_lite.firebase;

public class FcmUnRegRequest {
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public class Value {

        private String pushChannel;

        public String getPushChannel() {
            return pushChannel;
        }

        public void setPushChannel(String pushChannel) {
            this.pushChannel = pushChannel;
        }

    }
}
