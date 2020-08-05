package com.example.sophisticatedquizzler.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "category")
    @SerializedName("category")
    @Expose
    private String category;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    @Expose
    private String type;

    @ColumnInfo(name = "difficulty")
    @SerializedName("difficulty")
    @Expose
    private String difficulty;

    @ColumnInfo(name = "question")
    @SerializedName("question")
    @Expose
    private String question;

    @ColumnInfo(name = "correctAnswer")
    @SerializedName("correct_answer")
    @Expose
    private String correctAnswer;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question.replaceAll("&quot;", "'");
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    @NonNull
    @Override
    public String toString() {
        return "\ncategory: "+ getCategory() +
                "\ntype: " + getType() +
                "\ndifficulty: " + getDifficulty() +
                "\nquestion: " + getQuestion() +
                "\ncorrectAnswer: " + getCorrectAnswer();
    }
}
