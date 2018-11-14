package com.school.edsense_lite.model.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.AssignmentResponseModel;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Insert
    void insert(AssignmentResponseModel assignmentResponseModel);

    @Query("DELETE FROM table_assignments")
    void deleteAll();

    @Query("SELECT * from table_assignments ORDER BY assignment_id ASC")
    List<AssignmentResponseModel> getAllAssignments();
}
