package com.school.edsense_lite.today;

import java.util.List;

public class NewsResponse {
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
        private Integer newsId;
        private String newsName;
        private Object typeName;
        private String description;
        private Integer newsTypeID;
        private String newsDate;
        private String expirationDate;
        private String link;
        private Integer prority;
        private Integer organizationId;
        private Object viewPermissionMode;
        private Object viewPermissionInfo;
        private Object viewPermissionInfoJson;
        private Boolean isAdmin;
        private Boolean isPublish;

        public Integer getNewsId() {
            return newsId;
        }

        public void setNewsId(Integer newsId) {
            this.newsId = newsId;
        }

        public String getNewsName() {
            return newsName;
        }

        public void setNewsName(String newsName) {
            this.newsName = newsName;
        }

        public Object getTypeName() {
            return typeName;
        }

        public void setTypeName(Object typeName) {
            this.typeName = typeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getNewsTypeID() {
            return newsTypeID;
        }

        public void setNewsTypeID(Integer newsTypeID) {
            this.newsTypeID = newsTypeID;
        }

        public String getNewsDate() {
            return newsDate;
        }

        public void setNewsDate(String newsDate) {
            this.newsDate = newsDate;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Integer getPrority() {
            return prority;
        }

        public void setPrority(Integer prority) {
            this.prority = prority;
        }

        public Integer getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(Integer organizationId) {
            this.organizationId = organizationId;
        }

        public Object getViewPermissionMode() {
            return viewPermissionMode;
        }

        public void setViewPermissionMode(Object viewPermissionMode) {
            this.viewPermissionMode = viewPermissionMode;
        }

        public Object getViewPermissionInfo() {
            return viewPermissionInfo;
        }

        public void setViewPermissionInfo(Object viewPermissionInfo) {
            this.viewPermissionInfo = viewPermissionInfo;
        }

        public Object getViewPermissionInfoJson() {
            return viewPermissionInfoJson;
        }

        public void setViewPermissionInfoJson(Object viewPermissionInfoJson) {
            this.viewPermissionInfoJson = viewPermissionInfoJson;
        }

        public Boolean getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(Boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public Boolean getIsPublish() {
            return isPublish;
        }

        public void setIsPublish(Boolean isPublish) {
            this.isPublish = isPublish;
        }
    }
}
