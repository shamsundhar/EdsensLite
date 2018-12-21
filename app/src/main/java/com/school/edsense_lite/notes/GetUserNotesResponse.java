package com.school.edsense_lite.notes;

import java.io.Serializable;
import java.util.List;

public class GetUserNotesResponse {
    private String response;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
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

    public class Response implements Serializable {
        private Integer StudentNotesId;
        private String StudentId;
        private String Note;
        private Boolean IsPublic;
        private Integer IsEditable;
        private Boolean IsVisibletoParent;
        private String DateCommented;
        private Integer SeverityTypeId;
        private String CreatedDate;
        private String CreatedBy;
        private String SeverityTypeName;
        private Integer MeetingTypeId;
        private String MeetingTypeName;
        private String Class;
        private String CreatedUserName;
        private String RoleName;
        private String ImageFullPath;
        private String CreatedByName;
        private String StudentName;
        private String Image;
        private List<Tag> Tags = null;
        private Integer IsAdmin;
        private Integer Gender;
        public Integer getGender() {
            return Gender;
        }
        public void setGender(Integer gender) {
            Gender = gender;
        }
        public Integer getIsEditable() {
            return IsEditable;
        }

        public void setIsEditable(Integer isEditable) {
            IsEditable = isEditable;
        }
        public String getStudentId() {
            return StudentId;
        }

        public void setStudentId(String studentId) {
            StudentId = studentId;
        }
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

        public Boolean getVisibletoParent() {
            return IsVisibletoParent;
        }

        public void setVisibletoParent(Boolean visibletoParent) {
            IsVisibletoParent = visibletoParent;
        }

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

        public String getClas() {
            return Class;
        }

        public void setClas(String aClass) {
            Class = aClass;
        }

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

        public Integer getIsAdmin() {
            return IsAdmin;
        }

        public void setIsAdmin(Integer isAdmin) {
            IsAdmin = isAdmin;
        }
    }



    public class Tag implements Serializable{

        private Integer TagId;
        private String Value;
        private String Category;
        private Integer CategoryId;
        private String Type;
        private String Name;
        private String TagName;
        private Integer PkValue;
        public Integer getTagId() {
            return TagId;
        }

        public void setTagId(Integer tagId) {
            TagId = tagId;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String category) {
            Category = category;
        }

        public Integer getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(Integer categoryId) {
            CategoryId = categoryId;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getTagName() {
            return TagName;
        }

        public void setTagName(String tagName) {
            TagName = tagName;
        }

        public Integer getPkValue() {
            return PkValue;
        }

        public void setPkValue(Integer pkValue) {
            PkValue = pkValue;
        }
    }
}
