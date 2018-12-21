package com.school.edsense_lite.attendance;

import com.school.edsense_lite.model.AttendanceBySectionModel;

public interface ClickListener {
    void onModifyButtonClicked(AttendanceBySectionModel attendanceModel, int position);
}
