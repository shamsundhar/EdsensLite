package com.school.edsense_lite.attendance;

public class GetUserRequest {
    public GetUserRequest(String compositeTagId, String date) {
        this.compositeTagId = compositeTagId;
        this.date = date;
    }

    private String compositeTagId;
    private String date;
    public String getCompositeTagId() {
        return compositeTagId;
    }

    public void setCompositeTagId(String compositeTagId) {
        this.compositeTagId = compositeTagId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
