package com.school.edsense_lite.model;

import java.util.List;

//@Entity(tableName = "schedule_response_model")
public class ScheduleResponseModel{

    private List<Row> rows = null;

    public List<Row> getRows() {
        return rows;
    }
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
