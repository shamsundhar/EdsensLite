package com.school.edsense_lite.notes;

import android.view.View;

import com.school.edsense_lite.attendance.GetUserResponse;

public interface ClickListener {
    void onModifyButtonClicked(Note notesModel, int position);
}
