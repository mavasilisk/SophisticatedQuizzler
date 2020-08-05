package com.example.sophisticatedquizzler.database.question;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sophisticatedquizzler.data.model.Question;
import com.example.sophisticatedquizzler.database.QuestionRoomDatabase;
import com.example.sophisticatedquizzler.util.RoomUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuestionRepository {
    public QuestionRoomDatabase db;
    private QuestionDAO questionDAO;
    private List<Question> questionList;
    private List<Question> uniqueCategory;

    public QuestionRepository(Application application){
        this.db = QuestionRoomDatabase.getInstance(application);
        this.questionDAO = db.questionDAO();
        this.questionList = getAllQuestionFromDb();

    }

    public List<Question> getQuestionList(){
        return questionList;
    }

    public List<Question> getCategoryQuestionList(){
        return questionList;
    }


    public void insert (Question question ) {
        new insertAsyncTask(questionDAO).execute(question);
    }

    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDAO asyncQuestionDao;

        public insertAsyncTask(QuestionDAO asyncQuestionDao) {
            this.asyncQuestionDao = asyncQuestionDao;
        }

        @Override
        protected Void doInBackground(Question... questionDBS) {
            asyncQuestionDao.insertQuestionToDB(questionDBS[0]);
            return null;
        }
    }

    public List<Question> getAllQuestionFromDb() {
        try {
            return new GetQuestionsAsyncTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetQuestionsAsyncTask extends AsyncTask<Void, Void, List<Question>>
    {
        @Override
        protected List<Question> doInBackground(Void... url) {
            return db.questionDAO().getQuestionDBList();
        }
    }

    public List<Question> getQuestionsByCategory(String categoryName) {
        try {
            return new GetQuestionsByCategoryAsyncTask().execute(categoryName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetQuestionsByCategoryAsyncTask extends AsyncTask<String, Void, List<Question>>{
        @Override
        protected List<Question> doInBackground(String... strings) {
            return db.questionDAO().getCategoryQuestionList(strings[0]);
        }
    }

    public void printQuestions(){
        for (Question question: questionList){
            Log.d("QuestionList",question.getCategory());
        }
    }


}
