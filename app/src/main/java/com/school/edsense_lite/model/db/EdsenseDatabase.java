package com.school.edsense_lite.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.dao.MessagesDao;


@Database(entities = {MessagesResponseModel.class},version = 1)
public abstract class EdsenseDatabase extends RoomDatabase {
    public abstract MessagesDao messagesDao();

}
