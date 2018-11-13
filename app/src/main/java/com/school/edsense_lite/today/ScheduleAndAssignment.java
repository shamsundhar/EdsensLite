package com.school.edsense_lite.today;

public class ScheduleAndAssignment {
    public ScheduleAndAssignment(Object ob1, Object ob2) {
        if(ob1 instanceof ScheduleResponse)
        this.scheduleResponse = (ScheduleResponse) ob1;
        if(ob2 instanceof AssignmentResponse)
        this.assignmentResponse = (AssignmentResponse)ob2;
    }

    private ScheduleResponse scheduleResponse;

    public ScheduleResponse getScheduleResponse() {
        return scheduleResponse;
    }

    public void setScheduleResponse(ScheduleResponse scheduleResponse) {
        this.scheduleResponse = scheduleResponse;
    }

    public AssignmentResponse getAssignmentResponse() {
        return assignmentResponse;
    }

    public void setAssignmentResponse(AssignmentResponse assignmentResponse) {
        this.assignmentResponse = assignmentResponse;
    }

    private AssignmentResponse assignmentResponse;


}
