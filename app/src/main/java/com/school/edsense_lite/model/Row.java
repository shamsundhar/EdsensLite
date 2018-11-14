package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_schedule_row")
public class Row {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "schedlue_row_title")
    private String title;
    @ColumnInfo(name = "schedlue_row_learningObjectID")
    private Integer learningObjectID;
    @ColumnInfo(name = "schedlue_row_parentId")
    private Integer parentId;
    @ColumnInfo(name = "schedlue_row_eventId")
    private String eventId;
    @ColumnInfo(name = "schedlue_row_cSSClass")
    private String cSSClass;
    @ColumnInfo(name = "schedlue_row_tagCategory")
    private String tagCategory;
    @ColumnInfo(name = "schedlue_row_teacherName")
    private String teacherName;
    @ColumnInfo(name = "schedlue_row_startTimeSlot")
    private String startTimeSlot;
    @ColumnInfo(name = "schedlue_row_endTimeSlot")
    private String endTimeSlot;
    @ColumnInfo(name = "schedlue_row_timePeriod")
    private String timePeriod;
    @ColumnInfo(name = "schedlue_row_gradeName")
    private String gradeName;
    @ColumnInfo(name = "schedlue_row_subject")
    private String subject;
    @ColumnInfo(name = "schedlue_row_sectionName")
    private String sectionName;


    public String getStartTimeSlot() {
        return startTimeSlot;
    }

    public void setStartTimeSlot(String startTimeSlot) {
        this.startTimeSlot = startTimeSlot;
    }

    public String getEndTimeSlot() {
        return endTimeSlot;
    }

    public void setEndTimeSlot(String endTimeSlot) {
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCSSClass() {
        return cSSClass;
    }

    public void setCSSClass(String cSSClass) {
        this.cSSClass = cSSClass;
    }

    public String getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(String tagCategory) {
        this.tagCategory = tagCategory;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}