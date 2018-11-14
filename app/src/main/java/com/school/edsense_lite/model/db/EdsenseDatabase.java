package com.school.edsense_lite.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.school.edsense_lite.model.AssignmentResponseModel;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.RecomendationModel;
import com.school.edsense_lite.model.Row;
import com.school.edsense_lite.model.ScheduleResponseModel;
import com.school.edsense_lite.model.Subscription;
import com.school.edsense_lite.model.dao.AssignmentDao;
import com.school.edsense_lite.model.dao.MessagesDao;
import com.school.edsense_lite.model.dao.NewsDao;
import com.school.edsense_lite.model.dao.NotesDao;
import com.school.edsense_lite.model.dao.RecomendationDao;
import com.school.edsense_lite.model.dao.ScheduleRowDao;
import com.school.edsense_lite.news.News;

@Database(entities = {MessagesResponseModel.class,AssignmentResponseModel.class,
        RecomendationModel.class,Row.class,Subscription.class,News.class},version = 1)
public abstract class EdsenseDatabase extends RoomDatabase {
    public abstract MessagesDao messagesDao();
    public abstract AssignmentDao assignmentDao();
    public abstract NewsDao newsDao();
    //public abstract NotesDao notesDao();
    public abstract RecomendationDao recomendationDao();
    public abstract ScheduleRowDao scheduleRowDao();

}
