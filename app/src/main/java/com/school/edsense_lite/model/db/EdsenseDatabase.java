package com.school.edsense_lite.model.db;

import com.school.edsense_lite.attendance.GetUserResponseModel;
import com.school.edsense_lite.model.AssignmentResponseModel;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.RecomendationModel;
import com.school.edsense_lite.model.Row;
import com.school.edsense_lite.model.SectionResponseModel;
import com.school.edsense_lite.model.Subscription;
import com.school.edsense_lite.model.dao.AssignmentDao;
import com.school.edsense_lite.model.dao.GetUserResponseDao;
import com.school.edsense_lite.model.dao.MessagesDao;
import com.school.edsense_lite.model.dao.NewsDao;
import com.school.edsense_lite.model.dao.NotesDao;
import com.school.edsense_lite.model.dao.RecomendationDao;
import com.school.edsense_lite.model.dao.ScheduleRowDao;
import com.school.edsense_lite.model.dao.SectionResponseDao;
import com.school.edsense_lite.news.News;

@Database(entities = {MessagesResponseModel.class,AssignmentResponseModel.class,
        RecomendationModel.class,Row.class,Subscription.class,News.class,GetUserResponseModel.class,
        SectionResponseModel.class},version = 1)

public abstract class EdsenseDatabase extends RoomDatabase {
    public abstract MessagesDao messagesDao();
    public abstract AssignmentDao assignmentDao();
    public abstract NewsDao newsDao();
    public abstract NotesDao notesDao();
    public abstract RecomendationDao recomendationDao();
    public abstract ScheduleRowDao scheduleRowDao();
    public abstract GetUserResponseDao getUserResponseDao();
    public abstract SectionResponseDao sectionResponseDao();

}
