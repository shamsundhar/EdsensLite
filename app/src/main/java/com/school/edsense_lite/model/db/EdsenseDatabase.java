package com.school.edsense_lite.model.db;

import com.school.edsense_lite.attendance.Attendance;
import com.school.edsense_lite.attendance.GetUserResponseModel;
import com.school.edsense_lite.model.AssignmentResponseModel;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.school.edsense_lite.model.AttendanceBySectionModel;
import com.school.edsense_lite.model.MessagesResponseModel;
import com.school.edsense_lite.model.RecomendationModel;
import com.school.edsense_lite.model.Row;
import com.school.edsense_lite.model.SectionResponseModel;
import com.school.edsense_lite.model.Subscription;
import com.school.edsense_lite.model.dao.AssignmentDao;
import com.school.edsense_lite.model.dao.AttendanceBySectionDao;
import com.school.edsense_lite.model.dao.AttendanceDao;
import com.school.edsense_lite.model.dao.EventsDao;
import com.school.edsense_lite.model.dao.GetUserResponseDao;
import com.school.edsense_lite.model.dao.MessagesDao;
import com.school.edsense_lite.model.dao.NewsDao;
import com.school.edsense_lite.model.dao.NotesDao;
import com.school.edsense_lite.model.dao.NotesTagDao;
import com.school.edsense_lite.model.dao.RecomendationDao;
import com.school.edsense_lite.model.dao.ScheduleRowDao;
import com.school.edsense_lite.model.dao.SectionResponseDao;
import com.school.edsense_lite.model.dao.UserNotesResponseDao;
import com.school.edsense_lite.news.News;
import com.school.edsense_lite.notes.Note;
import com.school.edsense_lite.notes.Tag;
import com.school.edsense_lite.notes.UserNotesResponseModel;
import com.school.edsense_lite.today.EventsResponseModel;

@Database(entities = {MessagesResponseModel.class,AssignmentResponseModel.class,
        RecomendationModel.class,Row.class,Subscription.class,News.class,GetUserResponseModel.class,
        SectionResponseModel.class,Note.class,Tag.class,Attendance.class,AttendanceBySectionModel.class,
        EventsResponseModel.class},version = 1)

public abstract class EdsenseDatabase extends RoomDatabase {
    public abstract MessagesDao messagesDao();
    public abstract AssignmentDao assignmentDao();
    public abstract NewsDao getNewsDao();
    public abstract NotesDao getNotesDao();
    public abstract NotesTagDao getNotesTagDao();
    public abstract RecomendationDao getRecomendationDao();
    public abstract ScheduleRowDao getScheduleRowDao();
    public abstract GetUserResponseDao getUserResponseDao();
    public abstract SectionResponseDao getSectionResponseDao();
    public abstract AttendanceDao getAttendanceDao();
    public abstract AttendanceBySectionDao getAttendanceBySectionDao();
    public abstract EventsDao getEventsDao();
//    public abstract UserNotesResponseDao getUserNotesResponseDao();

}



