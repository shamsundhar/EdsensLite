package com.school.edsense_lite.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_assignments")
public class AssignmentResponseModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="assignment_id")
    private int assignmentId;
    @ColumnInfo(name="assignment_name")
    private String name;
    @ColumnInfo(name="assignment_description")
    private String description;
    @ColumnInfo(name="assignment_type_id")
    private int assignmentTypeId;
    @ColumnInfo(name="assignment_type")
    private String assignmentType;
    @ColumnInfo(name="assignment_type_unique_name")
    private String assignmentTypeUniqueName;
    @ColumnInfo(name="assignment_date_type_id")
    private int dateTypeId;
    @ColumnInfo(name="assignment_date_type")
    private String dateType;
    @ColumnInfo(name="assignment_date_type_unique_name")
    private String dateTypeUniqueName;
    @ColumnInfo(name="assignment_is_published")
    private boolean isPublished;
    @ColumnInfo(name="assignment_points")
    private String points;
    @ColumnInfo(name="assignment_start_date")
    private String startDate;
    @ColumnInfo(name="assignment_end_date")
    private String endDate;
    @ColumnInfo(name="assignment_duration_indays")
    private String durationInDays;
    @ColumnInfo(name="assignment_subscription_id")
    private int subscriptionId;
    @ColumnInfo(name="assignment_parent_assignment_id")
    private String parentAssignmentId;
    @ColumnInfo(name="assignment_form_layout")
    private String formLayout;
    @ColumnInfo(name="assignment_form_layout_json")
    private String formLayoutJson;
    @ColumnInfo(name="assignment_created_user_name")
    private String createdUserName;
    @ColumnInfo(name="assignment_created_date")
    private String createdDate;
    @ColumnInfo(name="assignment_grading_count")
    private int gradingCount;
    @ColumnInfo(name="assignment_tag")
    private String tag;
    @ColumnInfo(name="assignment_attachment_id")
    private String attachmentIds;
    @ColumnInfo(name="assignment_attachments")
    private String attachments;
    @ColumnInfo(name="assignment_child_associations")
    private String childAssociations;
    @ColumnInfo(name="assignment_parent_associations")
    private String parentAssociations;
    @ColumnInfo(name="assignment_tags")
    private String tags;
    @ColumnInfo(name="assignment_user_response")
    private String userResponse;
    @ColumnInfo(name="assignment_is_completed_response")
    private boolean isCompletedResponse;
    @ColumnInfo(name="assignment_is_assigned")
    private boolean isAssigned;
    @ColumnInfo(name="assignment_is_admin")
    private boolean isAdmin;
    @ColumnInfo(name="assignment_child_associations_join")
    private String childAssociationsJoin;
    @ColumnInfo(name="assignment_parent_associations_join")
    private String parentAssociationsJoin;
    @ColumnInfo(name="assignment_attachment_id_join")
    private String attachmentIdsJoin;
    @ColumnInfo(name="assignment_content_collection")
    private String contentCollection;
    @ColumnInfo(name="assignment_deleted_content")
    private String deletedContent;


    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(int assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentTypeUniqueName() {
        return assignmentTypeUniqueName;
    }

    public void setAssignmentTypeUniqueName(String assignmentTypeUniqueName) {
        this.assignmentTypeUniqueName = assignmentTypeUniqueName;
    }

    public int getDateTypeId() {
        return dateTypeId;
    }

    public void setDateTypeId(Integer dateTypeId) {
        this.dateTypeId = dateTypeId;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDateTypeUniqueName() {
        return dateTypeUniqueName;
    }

    public void setDateTypeUniqueName(String dateTypeUniqueName) {
        this.dateTypeUniqueName = dateTypeUniqueName;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(String durationInDays) {
        this.durationInDays = durationInDays;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getParentAssignmentId() {
        return parentAssignmentId;
    }

    public void setParentAssignmentId(String parentAssignmentId) {
        this.parentAssignmentId = parentAssignmentId;
    }

    public String getFormLayout() {
        return formLayout;
    }

    public void setFormLayout(String formLayout) {
        this.formLayout = formLayout;
    }

    public String getFormLayoutJson() {
        return formLayoutJson;
    }

    public void setFormLayoutJson(String formLayoutJson) {
        this.formLayoutJson = formLayoutJson;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getGradingCount() {
        return gradingCount;
    }

    public void setGradingCount(Integer gradingCount) {
        this.gradingCount = gradingCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getChildAssociations() {
        return childAssociations;
    }

    public void setChildAssociations(String childAssociations) {
        this.childAssociations = childAssociations;
    }

    public String getParentAssociations() {
        return parentAssociations;
    }

    public void setParentAssociations(String parentAssociations) {
        this.parentAssociations = parentAssociations;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public boolean getIsCompletedResponse() {
        return isCompletedResponse;
    }

    public void setIsCompletedResponse(boolean isCompletedResponse) {
        this.isCompletedResponse = isCompletedResponse;
    }

    public boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getChildAssociationsJoin() {
        return childAssociationsJoin;
    }

    public void setChildAssociationsJoin(String childAssociationsJoin) {
        this.childAssociationsJoin = childAssociationsJoin;
    }

    public String getParentAssociationsJoin() {
        return parentAssociationsJoin;
    }

    public void setParentAssociationsJoin(String parentAssociationsJoin) {
        this.parentAssociationsJoin = parentAssociationsJoin;
    }

    public String getAttachmentIdsJoin() {
        return attachmentIdsJoin;
    }

    public void setAttachmentIdsJoin(String attachmentIdsJoin) {
        this.attachmentIdsJoin = attachmentIdsJoin;
    }

    public String getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(String contentCollection) {
        this.contentCollection = contentCollection;
    }

    public String getDeletedContent() {
        return deletedContent;
    }

    public void setDeletedContent(String deletedContent) {
        this.deletedContent = deletedContent;
    }
}
