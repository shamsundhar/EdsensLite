package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.attendance.GetUserResponseModel;
import com.school.edsense_lite.model.MessagesResponseModel;

import java.util.List;
@Dao
public interface GetUserResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GetUserResponseModel getUserResponseModel);

    @Query("DELETE FROM table_getuser_response")
    void deleteAll();

    @Query("SELECT * from table_getuser_response") // ORDER BY message_id ASC")
    List<GetUserResponseModel> getAllUserResponses();
}
