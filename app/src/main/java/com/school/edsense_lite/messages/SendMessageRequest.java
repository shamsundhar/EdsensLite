package com.school.edsense_lite.messages;

public class SendMessageRequest {
    private String body;
    private Integer contextTypeId;
    private String subject;
    private Integer mapId;
    private String recipientsInfo;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getContextTypeId() {
        return contextTypeId;
    }

    public void setContextTypeId(Integer contextTypeId) {
        this.contextTypeId = contextTypeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getRecipientsInfo() {
        return recipientsInfo;
    }

    public void setRecipientsInfo(String recipientsInfo) {
        this.recipientsInfo = recipientsInfo;
    }

}
