package com.school.edsense_lite.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.news.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insert(News news);

    @Query("DELETE FROM table_news")
    void deleteAll();

    @Query("SELECT * from table_news ORDER BY news_title ASC")
    List<News> getAllNews();
}
