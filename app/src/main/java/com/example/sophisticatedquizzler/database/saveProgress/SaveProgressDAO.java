package com.example.sophisticatedquizzler.database.saveProgress;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sophisticatedquizzler.data.model.SaveProgress;

import java.util.List;

@Dao
public interface SaveProgressDAO {

    @Query("SELECT * FROM save_state")
    List<SaveProgress> getSavedProgress();

    @Insert
    void insertSavedState(SaveProgress saveProgress);
}
