package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.AssignmentResponseModel;
import com.school.edsense_lite.today.EventsResponseModel;

import java.util.List;

@Dao
public interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EventsResponseModel eventsResponseModel);

    @Query("DELETE FROM table_events")
    void deleteAll();

    @Query("SELECT * from table_events ORDER BY events_id ASC")
    List<EventsResponseModel> getAllEvents();
}
