package com.school.edsense_lite.model.dao;

import android.arch.lifecycle.LiveData;

import com.school.edsense_lite.model.MessagesResponseModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MessagesDao {

    @Insert
    void insert(MessagesResponseModel messagesResponseModel);

    @Query("DELETE FROM table_messages")
    void deleteAll();

    @Query("SELECT * from table_messages ORDER BY message_id ASC")
    List<MessagesResponseModel> getAllMessages();

}
