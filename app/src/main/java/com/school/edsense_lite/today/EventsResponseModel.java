package com.school.edsense_lite.today;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName ="table_events")
public class EventsResponseModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "events_id")
    private Integer id;
    @ColumnInfo(name = "events_title")
    private String title;
    @ColumnInfo(name = "events_day")
    private String day;
    @ColumnInfo(name = "events_isRecursive")
    private Boolean isRecursive;
    @ColumnInfo(name = "events_start")
    private String start;
    @ColumnInfo(name = "events_end")
    private String end;
    @ColumnInfo(name = "events_parentEndDate")
    private String parentEndDate;
    @ColumnInfo(name = "events_isToday")
    private Boolean isToday;
    @ColumnInfo(name = "events_isTommorow")
    private Boolean isTommorow;
    @ColumnInfo(name = "events_parentStartDate")
    private String parentStartDate;
    @ColumnInfo(name = "events_isDisplayToChild")
    private Boolean isDisplayToChild;
    @ColumnInfo(name = "events_isInheritiated")
    private Boolean isInheritiated;
    @ColumnInfo(name = "events_currentEventUpdate")
    private String currentEventUpdate;
    @ColumnInfo(name = "events_orgCalendarTypeId")
    private String orgCalendarTypeId;
    @ColumnInfo(name = "events_orgId")
    private String orgId;
    @ColumnInfo(name = "events_orgEventTypeId")
    private Integer orgEventTypeId;
    @ColumnInfo(name = "events_eventName")
    private String eventName;
    @ColumnInfo(name = "events_eventUniqueName")
    private String eventUniqueName;
    @ColumnInfo(name = "events_color")
    private String color;
    @ColumnInfo(name = "events_recursiveEventType")
    private String recursiveEventType;
    @ColumnInfo(name = "events_isRecursiveDelete")
    private String isRecursiveDelete;
    @ColumnInfo(name = "events_description")
    private String description;
    @ColumnInfo(name = "events_allDay")
    private Boolean allDay;
    @ColumnInfo(name = "events_formLayout")
    private String formLayout;
    @ColumnInfo(name = "events_formLayoutJson")
    private String formLayoutJson;
    @ColumnInfo(name = "events_formLayoutView")
    private String formLayoutView;
    @ColumnInfo(name = "events_formLayoutJsonView")
    private String formLayoutJsonView;
    @ColumnInfo(name = "events_calendarYearId")
    private Integer calendarYearId;
    @ColumnInfo(name = "events_canEdit")
    private Boolean canEdit;
    @ColumnInfo(name = "events_viewPermissionMode")
    private String viewPermissionMode;
    @ColumnInfo(name = "events_displayName")
    private String displayName;
    @ColumnInfo(name = "events_showOnCalendar")
    private Boolean showOnCalendar;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getIsRecursive() {
        return isRecursive;
    }

    public void setIsRecursive(Boolean isRecursive) {
        this.isRecursive = isRecursive;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getParentEndDate() {
        return parentEndDate;
    }

    public void setParentEndDate(String parentEndDate) {
        this.parentEndDate = parentEndDate;
    }

    public Boolean getIsToday() {
        return isToday;
    }

    public void setIsToday(Boolean isToday) {
        this.isToday = isToday;
    }

    public Boolean getIsTommorow() {
        return isTommorow;
    }

    public void setIsTommorow(Boolean isTommorow) {
        this.isTommorow = isTommorow;
    }

    public String getParentStartDate() {
        return parentStartDate;
    }

    public void setParentStartDate(String parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    public Boolean getIsDisplayToChild() {
        return isDisplayToChild;
    }

    public void setIsDisplayToChild(Boolean isDisplayToChild) {
        this.isDisplayToChild = isDisplayToChild;
    }

    public Boolean getIsInheritiated() {
        return isInheritiated;
    }

    public void setIsInheritiated(Boolean isInheritiated) {
        this.isInheritiated = isInheritiated;
    }

    public String getCurrentEventUpdate() {
        return currentEventUpdate;
    }

    public void setCurrentEventUpdate(String currentEventUpdate) {
        this.currentEventUpdate = currentEventUpdate;
    }

    public String getOrgCalendarTypeId() {
        return orgCalendarTypeId;
    }

    public void setOrgCalendarTypeId(String orgCalendarTypeId) {
        this.orgCalendarTypeId = orgCalendarTypeId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgEventTypeId() {
        return orgEventTypeId;
    }

    public void setOrgEventTypeId(Integer orgEventTypeId) {
        this.orgEventTypeId = orgEventTypeId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventUniqueName() {
        return eventUniqueName;
    }

    public void setEventUniqueName(String eventUniqueName) {
        this.eventUniqueName = eventUniqueName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRecursiveEventType() {
        return recursiveEventType;
    }

    public void setRecursiveEventType(String recursiveEventType) {
        this.recursiveEventType = recursiveEventType;
    }

    public String getIsRecursiveDelete() {
        return isRecursiveDelete;
    }

    public void setIsRecursiveDelete(String isRecursiveDelete) {
        this.isRecursiveDelete = isRecursiveDelete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
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

    public String getFormLayoutView() {
        return formLayoutView;
    }

    public void setFormLayoutView(String formLayoutView) {
        this.formLayoutView = formLayoutView;
    }

    public String getFormLayoutJsonView() {
        return formLayoutJsonView;
    }

    public void setFormLayoutJsonView(String formLayoutJsonView) {
        this.formLayoutJsonView = formLayoutJsonView;
    }

    public Integer getCalendarYearId() {
        return calendarYearId;
    }

    public void setCalendarYearId(Integer calendarYearId) {
        this.calendarYearId = calendarYearId;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public String getViewPermissionMode() {
        return viewPermissionMode;
    }

    public void setViewPermissionMode(String viewPermissionMode) {
        this.viewPermissionMode = viewPermissionMode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getShowOnCalendar() {
        return showOnCalendar;
    }

    public void setShowOnCalendar(Boolean showOnCalendar) {
        this.showOnCalendar = showOnCalendar;
    }
}
