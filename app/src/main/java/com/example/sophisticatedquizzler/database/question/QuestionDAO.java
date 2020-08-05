package com.example.sophisticatedquizzler.database.question;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sophisticatedquizzler.data.model.Question;
import com.example.sophisticatedquizzler.util.RoomUtil;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Query("SELECT * FROM question")
    List<Question> getQuestionDBList();

    @Query("SELECT * FROM question WHERE category = :category")
    List<Question> getCategoryQuestionList(String category);

    @Insert
    void insertAllQuestionsToDB(List<Question> questionList);

    @Insert
    void insertQuestionToDB(Question question);

    @Delete
    void deleteQuestionFromDB(Question question);



}
