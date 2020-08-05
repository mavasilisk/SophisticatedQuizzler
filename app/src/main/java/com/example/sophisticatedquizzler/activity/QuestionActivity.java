package com.example.sophisticatedquizzler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sophisticatedquizzler.R;
import com.example.sophisticatedquizzler.data.model.Question;
import com.example.sophisticatedquizzler.database.question.QuestionRepository;
import com.example.sophisticatedquizzler.util.RoomUtil;

import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView question_textview;
    QuestionRepository questionRepository;
    List<Question> questionList;
    private int progress = 0;
    private Button true_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        true_button = findViewById(R.id.true_button);
        true_button.setOnClickListener(this);
        questionRepository = new QuestionRepository(getApplication());

        questionList = questionRepository.getQuestionsByCategory(
                getIntent().getStringExtra("databaseCategory"));

        questionRepository.printQuestions();

        question_textview = findViewById(R.id.question_textview);
        question_textview.setText(questionList.get(progress).getQuestion());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.true_button:
                progress++;
                question_textview.setText(questionList.get(progress).getQuestion());
                Log.d("TAG", String.valueOf(progress));
                break;
        }
    }
}