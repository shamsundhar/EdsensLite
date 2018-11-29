package com.school.edsense_lite.attendance;

import java.util.List;

public class GetTraitsResponse {
    private List<Response> response = null;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }
    public class Response{
        private Integer tagId;
        private String tagName;
        private Boolean selected;
        private String parentTags;

        public Integer getTagId() {
            return tagId;
        }

        public void setTagId(Integer tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public String getParentTags() {
            return parentTags;
        }

        public void setParentTags(String parentTags) {
            this.parentTags = parentTags;
        }
    }
}
