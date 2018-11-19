package com.school.edsense_lite.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.edsense_lite.model.RecomendationModel;

import java.util.List;

@Dao
public interface RecomendationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecomendationModel recomendationModel);

    @Query("DELETE FROM table_recomendations")
    void deleteAll();

    @Query("SELECT * from table_recomendations ORDER BY recomendations_name ASC")
    List<RecomendationModel> getAllRecomendations();
}
