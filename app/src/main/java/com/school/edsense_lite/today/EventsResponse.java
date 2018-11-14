package com.school.edsense_lite.today;

import java.util.List;

public class EventsResponse {
    private List<Response> response = null;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
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
    public class Response{
        private Integer id;
        private String title;
        private Object day;
        private Boolean isRecursive;
        private String start;
        private String end;
        private Object parentEndDate;
        private Boolean isToday;
        private Boolean isTommorow;
        private Object parentStartDate;
        private Boolean isDisplayToChild;
        private Boolean isInheritiated;
        private Object currentEventUpdate;
        private Object orgCalendarTypeId;
        private Object orgId;
        private Integer orgEventTypeId;
        private String eventName;
        private String eventUniqueName;
        private String color;
        private Object recursiveEventType;
        private Object isRecursiveDelete;
        private String description;
        private Boolean allDay;
        private Object formLayout;
        private Object formLayoutJson;
        private Object formLayoutView;
        private Object formLayoutJsonView;
        private Integer calendarYearId;
        private Boolean canEdit;
        private String viewPermissionMode;
        private String displayName;
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

        public Object getDay() {
            return day;
        }

        public void setDay(Object day) {
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

        public Object getParentEndDate() {
            return parentEndDate;
        }

        public void setParentEndDate(Object parentEndDate) {
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

        public Object getParentStartDate() {
            return parentStartDate;
        }

        public void setParentStartDate(Object parentStartDate) {
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

        public Object getCurrentEventUpdate() {
            return currentEventUpdate;
        }

        public void setCurrentEventUpdate(Object currentEventUpdate) {
            this.currentEventUpdate = currentEventUpdate;
        }

        public Object getOrgCalendarTypeId() {
            return orgCalendarTypeId;
        }

        public void setOrgCalendarTypeId(Object orgCalendarTypeId) {
            this.orgCalendarTypeId = orgCalendarTypeId;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
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

        public Object getRecursiveEventType() {
            return recursiveEventType;
        }

        public void setRecursiveEventType(Object recursiveEventType) {
            this.recursiveEventType = recursiveEventType;
        }

        public Object getIsRecursiveDelete() {
            return isRecursiveDelete;
        }

        public void setIsRecursiveDelete(Object isRecursiveDelete) {
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

        public Object getFormLayout() {
            return formLayout;
        }

        public void setFormLayout(Object formLayout) {
            this.formLayout = formLayout;
        }

        public Object getFormLayoutJson() {
            return formLayoutJson;
        }

        public void setFormLayoutJson(Object formLayoutJson) {
            this.formLayoutJson = formLayoutJson;
        }

        public Object getFormLayoutView() {
            return formLayoutView;
        }

        public void setFormLayoutView(Object formLayoutView) {
            this.formLayoutView = formLayoutView;
        }

        public Object getFormLayoutJsonView() {
            return formLayoutJsonView;
        }

        public void setFormLayoutJsonView(Object formLayoutJsonView) {
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
}
