package com.school.edsense_lite.notes;

import java.util.List;

public class SaveNotesRequest {
    private Boolean isPublic;
    private Boolean isVisibletoParent;
    private String dateCommented;
    private List<Tag> tags = null;
    private Integer meetingTypeId;
    private Integer severityTypeId;
    private String note;
    private String studentid;
    private Boolean isDelete;

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getIsVisibletoParent() {
        return isVisibletoParent;
    }

    public void setIsVisibletoParent(Boolean isVisibletoParent) {
        this.isVisibletoParent = isVisibletoParent;
    }

    public String getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(String dateCommented) {
        this.dateCommented = dateCommented;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getMeetingTypeId() {
        return meetingTypeId;
    }

    public void setMeetingTypeId(Integer meetingTypeId) {
        this.meetingTypeId = meetingTypeId;
    }

    public Integer getSeverityTypeId() {
        return severityTypeId;
    }

    public void setSeverityTypeId(Integer severityTypeId) {
        this.severityTypeId = severityTypeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public class ParentTag {

        private Integer tagId;
        private String tagName;

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

    }

    public class Tag {

        private String value;
        private String tagName;
        private Integer tagId;
        private Integer categoryId;
        private String category;
        private String type;
        private String name;
        private Boolean selected;
        private List<ParentTag> parentTags = null;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public Integer getTagId() {
            return tagId;
        }

        public void setTagId(Integer tagId) {
            this.tagId = tagId;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public List<ParentTag> getParentTags() {
            return parentTags;
        }

        public void setParentTags(List<ParentTag> parentTags) {
            this.parentTags = parentTags;
        }

    }
}
