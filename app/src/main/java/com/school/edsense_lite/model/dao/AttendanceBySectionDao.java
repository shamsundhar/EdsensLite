package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.AttendanceBySectionModel;

import java.util.List;
@Dao
public interface  AttendanceBySectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AttendanceBySectionModel attendanceBySectionModel);

    @Query("DELETE FROM table_attendancebysection")
    void deleteAll();


    @Query("SELECT * FROM table_attendancebysection WHERE attendancebysection_date LIKE :date AND attendancebysection_compositeTagName LIKE :sectionName ORDER BY attendancebysection_DisplayName ASC")
    public abstract List<AttendanceBySectionModel> findAttendanceByDateAndSection(String date, String sectionName);

    //Optional queries
    @Query("SELECT * from table_attendancebysection ORDER BY attendancebysection_DisplayName ASC")
    List<AttendanceBySectionModel> getAllAttendancesBySection();

    @Query("SELECT * FROM table_attendancebysection WHERE attendancebysection_date LIKE :date ORDER BY attendancebysection_DisplayName ASC")
    public abstract List<AttendanceBySectionModel> findAttendanceByDate(String date);

    @Query("SELECT * FROM table_attendancebysection WHERE attendancebysection_compositeTagName LIKE :sectionName ORDER BY attendancebysection_DisplayName ASC")
    public abstract List<AttendanceBySectionModel> findAttendanceBySection(String sectionName);
}
