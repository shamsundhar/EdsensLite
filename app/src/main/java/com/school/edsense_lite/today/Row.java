package com.school.edsense_lite.today;

public class Row {
    private String startTimeSlot;
    private Object endTimeSlot;
    private String timePeriod;
    private String gradeName;
    private String subject;
    private String sectionName;
    private String title;
    private Integer learningObjectID;
    private Integer parentId;
    private Object eventId;
    private Object cSSClass;
    private Object tagCategory;
    private Object teacherName;

    public String getStartTimeSlot() {
        return startTimeSlot;
    }

    public void setStartTimeSlot(String startTimeSlot) {
        this.startTimeSlot = startTimeSlot;
    }

    public Object getEndTimeSlot() {
        return endTimeSlot;
    }

    public void setEndTimeSlot(Object endTimeSlot) {
        this.endTimeSlot = endTimeSlot;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningObjectID() {
        return learningObjectID;
    }

    public void setLearningObjectID(Integer learningObjectID) {
        this.learningObjectID = learningObjectID;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Object getEventId() {
        return eventId;
    }

    public void setEventId(Object eventId) {
        this.eventId = eventId;
    }

    public Object getCSSClass() {
        return cSSClass;
    }

    public void setCSSClass(Object cSSClass) {
        this.cSSClass = cSSClass;
    }

    public Object getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(Object tagCategory) {
        this.tagCategory = tagCategory;
    }

    public Object getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(Object teacherName) {
        this.teacherName = teacherName;
    }

}