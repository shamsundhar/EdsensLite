package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.notes.Note;
import com.school.edsense_lite.notes.Tag;

import java.util.List;

@Dao
public interface NotesTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tag tag);

    @Query("DELETE FROM table_notesTags")
    void deleteAll();

    @Query("SELECT * from table_notesTags ORDER BY notesTags_PkValue ASC")
    List<Tag> getAllNotesTags();
    @Query("SELECT * from table_notesTags WHERE notesTags_PkValue=:studentNotesID ORDER BY notesTags_PkValue ASC")
    List<Tag> getAllNotesTags(String studentNotesID);
}
