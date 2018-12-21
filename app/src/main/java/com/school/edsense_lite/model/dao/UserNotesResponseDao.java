package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.AttendanceBySectionModel;
import com.school.edsense_lite.notes.UserNotesResponseModel;

import java.util.List;

@Dao
public interface UserNotesResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserNotesResponseModel userNotesResponseModel);

    @Query("DELETE FROM table_usernotesresponse")
    void deleteAll();

    @Query("SELECT * from table_usernotesresponse ORDER BY usernotesresponse_StudentName ASC")
    List<UserNotesResponseModel> getAllUserNotes();

//    @Query("SELECT * FROM table_usernotesresponse WHERE usernotesresponse_CreatedDate LIKE :date AND usernotesresponse_Class LIKE :sectionName ORDER BY usernotesresponse_StudentName ASC")
//    public abstract List<UserNotesResponseModel> findUserNotesByDateAndSection(String date, String sectionName);
}
