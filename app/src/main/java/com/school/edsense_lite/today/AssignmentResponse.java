package com.school.edsense_lite.today;

import com.school.edsense_lite.model.AssignmentResponseModel;

import java.util.List;

public class AssignmentResponse {
    private List<AssignmentResponseModel> response = null;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

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

    public List<AssignmentResponseModel> getResponse() {
        return response;
    }
    public void setResponse(List<AssignmentResponseModel> response) {
        this.response = response;
    }

//    public class Response {
//
//        private Integer assignmentId;
//        private String name;
//        private String description;
//        private Integer assignmentTypeId;
//        private String assignmentType;
//        private String assignmentTypeUniqueName;
//        private Integer dateTypeId;
//        private String dateType;
//        private String dateTypeUniqueName;
//        private Boolean isPublished;
//        private Object points;
//        private String startDate;
//        private String endDate;
//        private Object durationInDays;
//        private Integer subscriptionId;
//        private Object parentAssignmentId;
//        private Object formLayout;
//        private Object formLayoutJson;
//        private String createdUserName;
//        private String createdDate;
//        private Integer gradingCount;
//        private Object tag;
//        private Object attachmentIds;
//        private Object attachments;
//        private Object childAssociations;
//        private Object parentAssociations;
//        private Object tags;
//        private Object userResponse;
//        private Boolean isCompletedResponse;
//        private Boolean isAssigned;
//        private Boolean isAdmin;
//        private String childAssociationsJoin;
//        private String parentAssociationsJoin;
//        private Object attachmentIdsJoin;
//        private Object contentCollection;
//        private Object deletedContent;
//
//        public Integer getAssignmentId() {
//            return assignmentId;
//        }
//
//        public void setAssignmentId(Integer assignmentId) {
//            this.assignmentId = assignmentId;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public Integer getAssignmentTypeId() {
//            return assignmentTypeId;
//        }
//
//        public void setAssignmentTypeId(Integer assignmentTypeId) {
//            this.assignmentTypeId = assignmentTypeId;
//        }
//
//        public String getAssignmentType() {
//            return assignmentType;
//        }
//
//        public void setAssignmentType(String assignmentType) {
//            this.assignmentType = assignmentType;
//        }
//
//        public String getAssignmentTypeUniqueName() {
//            return assignmentTypeUniqueName;
//        }
//
//        public void setAssignmentTypeUniqueName(String assignmentTypeUniqueName) {
//            this.assignmentTypeUniqueName = assignmentTypeUniqueName;
//        }
//
//        public Integer getDateTypeId() {
//            return dateTypeId;
//        }
//
//        public void setDateTypeId(Integer dateTypeId) {
//            this.dateTypeId = dateTypeId;
//        }
//
//        public String getDateType() {
//            return dateType;
//        }
//
//        public void setDateType(String dateType) {
//            this.dateType = dateType;
//        }
//
//        public String getDateTypeUniqueName() {
//            return dateTypeUniqueName;
//        }
//
//        public void setDateTypeUniqueName(String dateTypeUniqueName) {
//            this.dateTypeUniqueName = dateTypeUniqueName;
//        }
//
//        public Boolean getIsPublished() {
//            return isPublished;
//        }
//
//        public void setIsPublished(Boolean isPublished) {
//            this.isPublished = isPublished;
//        }
//
//        public Object getPoints() {
//            return points;
//        }
//
//        public void setPoints(Object points) {
//            this.points = points;
//        }
//
//        public String getStartDate() {
//            return startDate;
//        }
//
//        public void setStartDate(String startDate) {
//            this.startDate = startDate;
//        }
//
//        public String getEndDate() {
//            return endDate;
//        }
//
//        public void setEndDate(String endDate) {
//            this.endDate = endDate;
//        }
//
//        public Object getDurationInDays() {
//            return durationInDays;
//        }
//
//        public void setDurationInDays(Object durationInDays) {
//            this.durationInDays = durationInDays;
//        }
//
//        public Integer getSubscriptionId() {
//            return subscriptionId;
//        }
//
//        public void setSubscriptionId(Integer subscriptionId) {
//            this.subscriptionId = subscriptionId;
//        }
//
//        public Object getParentAssignmentId() {
//            return parentAssignmentId;
//        }
//
//        public void setParentAssignmentId(Object parentAssignmentId) {
//            this.parentAssignmentId = parentAssignmentId;
//        }
//
//        public Object getFormLayout() {
//            return formLayout;
//        }
//
//        public void setFormLayout(Object formLayout) {
//            this.formLayout = formLayout;
//        }
//
//        public Object getFormLayoutJson() {
//            return formLayoutJson;
//        }
//
//        public void setFormLayoutJson(Object formLayoutJson) {
//            this.formLayoutJson = formLayoutJson;
//        }
//
//        public String getCreatedUserName() {
//            return createdUserName;
//        }
//
//        public void setCreatedUserName(String createdUserName) {
//            this.createdUserName = createdUserName;
//        }
//
//        public String getCreatedDate() {
//            return createdDate;
//        }
//
//        public void setCreatedDate(String createdDate) {
//            this.createdDate = createdDate;
//        }
//
//        public Integer getGradingCount() {
//            return gradingCount;
//        }
//
//        public void setGradingCount(Integer gradingCount) {
//            this.gradingCount = gradingCount;
//        }
//
//        public Object getTag() {
//            return tag;
//        }
//
//        public void setTag(Object tag) {
//            this.tag = tag;
//        }
//
//        public Object getAttachmentIds() {
//            return attachmentIds;
//        }
//
//        public void setAttachmentIds(Object attachmentIds) {
//            this.attachmentIds = attachmentIds;
//        }
//
//        public Object getAttachments() {
//            return attachments;
//        }
//
//        public void setAttachments(Object attachments) {
//            this.attachments = attachments;
//        }
//
//        public Object getChildAssociations() {
//            return childAssociations;
//        }
//
//        public void setChildAssociations(Object childAssociations) {
//            this.childAssociations = childAssociations;
//        }
//
//        public Object getParentAssociations() {
//            return parentAssociations;
//        }
//
//        public void setParentAssociations(Object parentAssociations) {
//            this.parentAssociations = parentAssociations;
//        }
//
//        public Object getTags() {
//            return tags;
//        }
//
//        public void setTags(Object tags) {
//            this.tags = tags;
//        }
//
//        public Object getUserResponse() {
//            return userResponse;
//        }
//
//        public void setUserResponse(Object userResponse) {
//            this.userResponse = userResponse;
//        }
//
//        public Boolean getIsCompletedResponse() {
//            return isCompletedResponse;
//        }
//
//        public void setIsCompletedResponse(Boolean isCompletedResponse) {
//            this.isCompletedResponse = isCompletedResponse;
//        }
//
//        public Boolean getIsAssigned() {
//            return isAssigned;
//        }
//
//        public void setIsAssigned(Boolean isAssigned) {
//            this.isAssigned = isAssigned;
//        }
//
//        public Boolean getIsAdmin() {
//            return isAdmin;
//        }
//
//        public void setIsAdmin(Boolean isAdmin) {
//            this.isAdmin = isAdmin;
//        }
//
//        public String getChildAssociationsJoin() {
//            return childAssociationsJoin;
//        }
//
//        public void setChildAssociationsJoin(String childAssociationsJoin) {
//            this.childAssociationsJoin = childAssociationsJoin;
//        }
//
//        public String getParentAssociationsJoin() {
//            return parentAssociationsJoin;
//        }
//
//        public void setParentAssociationsJoin(String parentAssociationsJoin) {
//            this.parentAssociationsJoin = parentAssociationsJoin;
//        }
//
//        public Object getAttachmentIdsJoin() {
//            return attachmentIdsJoin;
//        }
//
//        public void setAttachmentIdsJoin(Object attachmentIdsJoin) {
//            this.attachmentIdsJoin = attachmentIdsJoin;
//        }
//
//        public Object getContentCollection() {
//            return contentCollection;
//        }
//
//        public void setContentCollection(Object contentCollection) {
//            this.contentCollection = contentCollection;
//        }
//
//        public Object getDeletedContent() {
//            return deletedContent;
//        }
//
//        public void setDeletedContent(Object deletedContent) {
//            this.deletedContent = deletedContent;
//        }
//    }
}
