package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_schedule_row")
public class Row {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "schedlue_row_sortID")
    private Integer SortId;
    @ColumnInfo(name = "schedlue_row_learningObjectID")
    private Integer LearningObjectID;
    @ColumnInfo(name = "schedlue_row_eventId")
    private String EventId;
    @ColumnInfo(name = "schedlue_row_title")
    private String Title;
    @ColumnInfo(name = "schedlue_row_parentId")
    private Integer ParentId;
    @ColumnInfo(name = "schedlue_row_cSSClass")
    private String CSSClass;
    @ColumnInfo(name = "schedlue_row_tagCategory")
    private String TagCategory;
    @ColumnInfo(name = "schedlue_row_teacherName")
    private String TeacherName;
    @ColumnInfo(name = "schedlue_row_startTimeSlot")
    private String StartTimeSlot;
    @ColumnInfo(name = "schedlue_row_endTimeSlot")
    private String EndTimeSlot;
    @ColumnInfo(name = "schedlue_row_timePeriod")
    private String TimePeriod;
    @ColumnInfo(name = "schedlue_row_gradeName")
    private String GradeName;
    @ColumnInfo(name = "schedlue_row_subject")
    private String Subject;
    @ColumnInfo(name = "schedlue_row_sectionName")
    private String SectionName;

    public Integer getLearningObjectID() {
        return LearningObjectID;
    }

    public void setLearningObjectID(Integer learningObjectID) {
        LearningObjectID = learningObjectID;
    }
    @NonNull
    public Integer getSortId() {
        return SortId;
    }

    public void setSortId(@NonNull Integer sortId) {
        SortId = sortId;
    }
    public String getEventId() {
        return EventId;
    }

    public void setEventId(String eventId) {
        EventId = eventId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getParentId() {
        return ParentId;
    }

    public void setParentId(Integer parentId) {
        ParentId = parentId;
    }

    public String getCSSClass() {
        return CSSClass;
    }

    public void setCSSClass(String CSSClass) {
        this.CSSClass = CSSClass;
    }

    public String getTagCategory() {
        return TagCategory;
    }

    public void setTagCategory(String tagCategory) {
        TagCategory = tagCategory;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getStartTimeSlot() {
        return StartTimeSlot;
    }

    public void setStartTimeSlot(String startTimeSlot) {
        StartTimeSlot = startTimeSlot;
    }

    public String getEndTimeSlot() {
        return EndTimeSlot;
    }

    public void setEndTimeSlot(String endTimeSlot) {
        EndTimeSlot = endTimeSlot;
    }

    public String getTimePeriod() {
        return TimePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        TimePeriod = timePeriod;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

}