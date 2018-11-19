package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.AssignmentResponseModel;
import com.school.edsense_lite.model.SectionResponseModel;

import java.util.List;

@Dao
public interface SectionResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SectionResponseModel sectionResponseModel);

    @Query("DELETE FROM table_section_response")
    void deleteAll();

    @Query("SELECT * from table_section_response") // ORDER BY assignment_id ASC")
    List<SectionResponseModel> getAllSectionResponses();
}
