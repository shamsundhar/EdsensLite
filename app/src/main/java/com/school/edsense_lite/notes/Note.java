package com.school.edsense_lite.notes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "table_notes")
public class Note implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "notes_StudentNotesId")
    private String StudentNotesId;
    @ColumnInfo(name = "notes_StudentId")
    private String StudentId;
    @ColumnInfo(name = "notes_Note")
    private String Note;
    @ColumnInfo(name = "notes_IsPublic")
    private String IsPublic;
    @ColumnInfo(name = "notes_IsEditable")
    private String IsEditable;
    @ColumnInfo(name = "notes_IsVisibletoParent")
    private String IsVisibletoParent;
    @ColumnInfo(name = "notes_DateCommented")
    private String DateCommented;
    @ColumnInfo(name = "notes_SeverityTypeId")
    private String SeverityTypeId;
    @ColumnInfo(name = "notes_CreatedDate")
    private String CreatedDate;
    @ColumnInfo(name = "notes_CreatedBy")
    private String CreatedBy;
    @ColumnInfo(name = "notes_SeverityTypeName")
    private String SeverityTypeName;
    @ColumnInfo(name = "notes_MeetingTypeId")
    private String MeetingTypeId;
    @ColumnInfo(name = "notes_MeetingTypeName")
    private String MeetingTypeName;
    @ColumnInfo(name = "notes_Class")
    private transient String  _Class;
    @ColumnInfo(name = "notes_CreatedUserName")
    private String CreatedUserName;
    @ColumnInfo(name = "notes_RoleName")
    private String RoleName;
    @ColumnInfo(name = "notes_ImageFullPath")
    private String ImageFullPath;
    @ColumnInfo(name = "notes_CreatedByName")
    private String CreatedByName;
    @ColumnInfo(name = "notes_StudentName")
    private String StudentName;
    @ColumnInfo(name = "notes_Image")
    private String Image;

    //@ColumnInfo(name = "notes_Tags")
    @Ignore
    private List<Tag> Tags = null;
    @ColumnInfo(name = "notes_IsAdmin")
    private String IsAdmin;
    @ColumnInfo(name = "notes_Gender")
    private String Gender;
    @ColumnInfo(name = "notes_isSynced")
    private transient boolean isSynced;

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public String get_Class() {
        return _Class;
    }

    public void set_Class(String _Class) {
        this._Class = _Class;
    }

    @NonNull
    public String getStudentNotesId() {
        return StudentNotesId;
    }

    public void setStudentNotesId(@NonNull String studentNotesId) {
        StudentNotesId = studentNotesId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getIsPublic() {
        return IsPublic;
    }

    public void setIsPublic(String isPublic) {
        IsPublic = isPublic;
    }

    public String getIsEditable() {
        return IsEditable;
    }

    public void setIsEditable(String isEditable) {
        IsEditable = isEditable;
    }

    public String getIsVisibletoParent() {
        return IsVisibletoParent;
    }

    public void setIsVisibletoParent(String isVisibletoParent) {
        IsVisibletoParent = isVisibletoParent;
    }

    public String getDateCommented() {
        return DateCommented;
    }

    public void setDateCommented(String dateCommented) {
        DateCommented = dateCommented;
    }

    public String getSeverityTypeId() {
        return SeverityTypeId;
    }

    public void setSeverityTypeId(String severityTypeId) {
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

    public String getMeetingTypeId() {
        return MeetingTypeId;
    }

    public void setMeetingTypeId(String meetingTypeId) {
        MeetingTypeId = meetingTypeId;
    }

    public String getMeetingTypeName() {
        return MeetingTypeName;
    }

    public void setMeetingTypeName(String meetingTypeName) {
        MeetingTypeName = meetingTypeName;
    }


//    public String getClass() {
//        return Class;
//    }
//
//    public void setClass(String aClass) {
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

    public List<Tag> getTags() {
        return Tags;
    }

    public void setTags(List<Tag> tags) {
        Tags = tags;
    }

    public String getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}