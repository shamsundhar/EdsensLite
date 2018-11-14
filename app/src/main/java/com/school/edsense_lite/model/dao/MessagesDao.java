package com.school.edsense_lite.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.MessagesResponseModel;

import java.util.List;


@Dao
public interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(MessagesResponseModel messagesResponseModel);

    @Query("DELETE FROM table_messages")
    void deleteAll();

    @Query("SELECT * from table_messages ORDER BY message_id ASC")
    List<MessagesResponseModel> getAllMessages();

}
