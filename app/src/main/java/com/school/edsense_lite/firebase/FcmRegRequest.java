package com.school.edsense_lite.firebase;

public class FcmRegRequest {
    private String Platform;
    private String PushChannel;
    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getPushChannel() {
        return PushChannel;
    }

    public void setPushChannel(String pushChannel) {
        PushChannel = pushChannel;
    }
}
