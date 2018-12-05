package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.notes.Note;

import java.util.List;

@Dao
public interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Attendance attendance);

    @Query("DELETE FROM table_attendance")
    void deleteAll();

    @Query("SELECT * from table_attendance ORDER BY attendance_name ASC")
    List<Attendance> getAllAttendances();
}
