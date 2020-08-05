package com.example.sophisticatedquizzler.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sophisticatedquizzler.R;
import com.example.sophisticatedquizzler.adapter.PickCategoryRecyclerViewAdapter;
import com.example.sophisticatedquizzler.data.model.Question;
import com.example.sophisticatedquizzler.data.model.SOQuestionsResponse;
import com.example.sophisticatedquizzler.data.model.SaveProgress;
import com.example.sophisticatedquizzler.data.remote.SOService;
import com.example.sophisticatedquizzler.database.question.QuestionRepository;
import com.example.sophisticatedquizzler.database.saveProgress.SaveProgressRepository;
import com.example.sophisticatedquizzler.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SOQuestionsResponse soQuestionsResponse;
    private SOService soService;
    private Button videoGamesbutton;
    QuestionRepository questionRepository;
    private SaveProgressRepository saveProgressRepository;
    private RecyclerView recyclerViewAdapter;
    private PickCategoryRecyclerViewAdapter adapter;
    private List<String> saveProgressesInputList = new ArrayList<>();
    int questionCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteDatabase("question");
        saveProgressRepository = new SaveProgressRepository(getApplication());
        for (SaveProgress saveProgress: saveProgressRepository.getAllSaveProgressFromDB()){
//            Log.d("ADDINGTOLIST",saveProgress.getCategoryName());
            saveProgressesInputList.add(saveProgress.getCategoryName());
        }

        questionRepository = new QuestionRepository(getApplication());

        soService = RetrofitUtil.getSOService();
        soQuestionsResponse = new SOQuestionsResponse();

        loadAnswers();

        recyclerViewAdapter = findViewById(R.id.recycler_view);
        recyclerViewAdapter.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PickCategoryRecyclerViewAdapter(
                this,
                saveProgressRepository.getAllSaveProgressFromDB(),
                new PickCategoryRecyclerViewAdapter.RecyclerViewOnItemClick() {
                    @Override
                    public void itemClickCallback(String categoryName, int position) {
//                        Toast.makeText(MainActivity.this,categoryName, Toast.LENGTH_SHORT).show();
                        Intent goToQuestions = new Intent(MainActivity.this, QuestionActivity.class);
                        goToQuestions.putExtra("databaseCategory",categoryName);
                        MainActivity.this.startActivity(goToQuestions);
                    }
                });

        recyclerViewAdapter.setAdapter(adapter);


    }
 
    @Override
    public void onClick(View view) {

    }

    public void loadAnswers(){
        soService.getResults(RetrofitUtil.VIDEO_GAMES_URL).enqueue(new Callback<SOQuestionsResponse>() {
            @Override
            public void onResponse(Call<SOQuestionsResponse> call, Response<SOQuestionsResponse> response) {
                storeToDatabase(response);
            }

            @Override
            public void onFailure(Call<SOQuestionsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error downloading", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void storeToDatabase(Response<SOQuestionsResponse> response) {
        if (response.isSuccessful()) {
            for (Question question : response.body().getResults()) {
                for (int i = 0; i < response.body().getResults().size(); i ++) {
                    if (response.body().getResults().get(i).getCategory().equals(question.getCategory())){
                        questionCount++;
                    }
                }
                questionRepository.insert(question);
                if (!saveProgressesInputList.contains(question.getCategory())) {

                    saveProgressesInputList.add(question.getCategory());
                    saveProgressRepository.insertSaveProgress(
                            new SaveProgress(
                                    question.getCategory(),
                                    questionCount,
                                    0
                            ));
                }
                questionCount = 0;
            }
            adapter.updateList(saveProgressRepository.getAllSaveProgressFromDB());
        } else {
        }
    }

}