package com.school.edsense_lite.model.db;

import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.dao.MessagesDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MessagesResponseModel.class},version = 1)
public abstract class EdsenseDatabase extends RoomDatabase {
    public abstract MessagesDao messagesDao();

}
