package com.school.edsense_lite.notes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "table_usernotesresponse")
public class UserNotesResponseModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "usernotesresponse_StudentNotesId")
    private Integer StudentNotesId;
    @ColumnInfo(name = "usernotesresponse_Note")
    private String Note;
    @ColumnInfo(name = "usernotesresponse_IsPublic")
    private Boolean IsPublic;
//    @ColumnInfo(name = "usernotesresponse_IsVisibletoParent")
//    private Boolean IsVisibletoParent;
    @ColumnInfo(name = "usernotesresponse_DateCommented")
    private String DateCommented;
    @ColumnInfo(name = "usernotesresponse_SeverityTypeId")
    private Integer SeverityTypeId;
    @ColumnInfo(name = "usernotesresponse_CreatedDate")
    private String CreatedDate;
    @ColumnInfo(name = "usernotesresponse_CreatedBy")
    private String CreatedBy;
    @ColumnInfo(name = "usernotesresponse_SeverityTypeName")
    private String SeverityTypeName;
    @ColumnInfo(name = "usernotesresponse_MeetingTypeId")
    private Integer MeetingTypeId;
    @ColumnInfo(name = "usernotesresponse_MeetingTypeName")
    private String MeetingTypeName;
//    @ColumnInfo(name = "usernotesresponse_Class")
//    private String Class;
    @ColumnInfo(name = "usernotesresponse_CreatedUserName")
    private String CreatedUserName;
    @ColumnInfo(name = "usernotesresponse_RoleName")
    private String RoleName;
    @ColumnInfo(name = "usernotesresponse_ImageFullPath")
    private String ImageFullPath;
    @ColumnInfo(name = "usernotesresponse_CreatedByName")
    private String CreatedByName;
    @ColumnInfo(name = "usernotesresponse_StudentName")
    private String StudentName;
    @ColumnInfo(name = "usernotesresponse_Image")
    private String Image;
//    @ColumnInfo(name = "usernotesresponse_StudentNotesId")
//    private List<GetUserNotesResponse.Tag> Tags = null;
    @ColumnInfo(name = "usernotesresponse_IsAdmin")
    private Integer IsAdmin;


    public Integer getStudentNotesId() {
        return StudentNotesId;
    }

    public void setStudentNotesId(Integer studentNotesId) {
        StudentNotesId = studentNotesId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Boolean getPublic() {
        return IsPublic;
    }

    public void setPublic(Boolean aPublic) {
        IsPublic = aPublic;
    }

//    public Boolean getVisibletoParent() {
//        return IsVisibletoParent;
//    }
//
//    public void setVisibletoParent(Boolean visibletoParent) {
//        IsVisibletoParent = visibletoParent;
//    }

    public String getDateCommented() {
        return DateCommented;
    }

    public void setDateCommented(String dateCommented) {
        DateCommented = dateCommented;
    }

    public Integer getSeverityTypeId() {
        return SeverityTypeId;
    }

    public void setSeverityTypeId(Integer severityTypeId) {
        SeverityTypeId = severityTypeId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getSeverityTypeName() {
        return SeverityTypeName;
    }

    public void setSeverityTypeName(String severityTypeName) {
        SeverityTypeName = severityTypeName;
    }

    public Integer getMeetingTypeId() {
        return MeetingTypeId;
    }

    public void setMeetingTypeId(Integer meetingTypeId) {
        MeetingTypeId = meetingTypeId;
    }

    public String getMeetingTypeName() {
        return MeetingTypeName;
    }

    public void setMeetingTypeName(String meetingTypeName) {
        MeetingTypeName = meetingTypeName;
    }

//    public String getClas() {
//        return Class;
//    }
//
//    public void setClas(String aClass) {
//        Class = aClass;
//    }

    public String getCreatedUserName() {
        return CreatedUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        CreatedUserName = createdUserName;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getImageFullPath() {
        return ImageFullPath;
    }

    public void setImageFullPath(String imageFullPath) {
        ImageFullPath = imageFullPath;
    }

    public String getCreatedByName() {
        return CreatedByName;
    }

    public void setCreatedByName(String createdByName) {
        CreatedByName = createdByName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

//    public List<GetUserNotesResponse.Tag> getTags() {
//        return Tags;
//    }
//
//    public void setTags(List<GetUserNotesResponse.Tag> tags) {
//        Tags = tags;
//    }

    public Integer getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        IsAdmin = isAdmin;
    }
}
