package com.school.edsense_lite.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.notes.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Query("DELETE FROM table_notes")
    void deleteAll();

    @Query("SELECT * from table_notes ORDER BY notes_StudentNotesId ASC")
    List<Note> getAllNotes();
    @Query("SELECT * from table_notes WHERE notes_CreatedDate=:cDate AND notes_Class=:sectionID ORDER BY notes_StudentNotesId ASC")
    List<Note> getAllNotesByDateAndSection(String cDate,String sectionID);

    @Query("DELETE FROM table_notes WHERE notes_StudentNotesId=:selectedStudentNotesId")
    void deleteSelectedNotes(int selectedStudentNotesId);
}
