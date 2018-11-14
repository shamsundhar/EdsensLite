package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.Row;

import java.util.List;

@Dao
public interface ScheduleRowDao {
    @Insert
    void insert(Row row);

    @Query("DELETE FROM table_schedule_row")
    void deleteAll();

    @Query("SELECT * from table_schedule_row ORDER BY schedlue_row_title ASC")
    List<Row> getAllRows();
}
