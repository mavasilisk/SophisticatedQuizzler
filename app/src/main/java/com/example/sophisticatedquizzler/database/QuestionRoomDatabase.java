package com.example.sophisticatedquizzler.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sophisticatedquizzler.data.model.Question;
import com.example.sophisticatedquizzler.data.model.SaveProgress;
import com.example.sophisticatedquizzler.database.question.QuestionDAO;
import com.example.sophisticatedquizzler.database.saveProgress.SaveProgressDAO;
import com.example.sophisticatedquizzler.util.RoomUtil;

@Database(
        entities = {
                Question.class,
                SaveProgress.class
                    },
                exportSchema = false, version = 1)
public abstract class QuestionRoomDatabase extends RoomDatabase {
//    private static final String DB_NAME = "question";

    private static QuestionRoomDatabase instance;

    public static synchronized QuestionRoomDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionRoomDatabase.class, RoomUtil.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract QuestionDAO questionDAO();
    public abstract SaveProgressDAO saveProgressDAO();
}
