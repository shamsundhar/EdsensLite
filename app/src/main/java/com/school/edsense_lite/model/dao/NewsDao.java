package com.school.edsense_lite.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.news.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Query("DELETE FROM table_news")
    void deleteAll();

    @Query("SELECT * from table_news ORDER BY news_newsId ASC")
    List<News> getAllNews();
}
