package com.school.edsense_lite.notes;

public class GetUsersForNotesRequest {
    public Integer getSelectedRoleId() {
        return selectedRoleId;
    }

    public void setSelectedRoleId(Integer selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    private Integer selectedRoleId;
    private String gradeId;
}
