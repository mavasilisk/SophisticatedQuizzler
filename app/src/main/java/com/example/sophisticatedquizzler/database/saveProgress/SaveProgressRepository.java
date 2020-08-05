package com.example.sophisticatedquizzler.database.saveProgress;

import android.app.Application;
import android.os.AsyncTask;

import com.example.sophisticatedquizzler.data.model.SaveProgress;
import com.example.sophisticatedquizzler.database.QuestionRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SaveProgressRepository {
    public QuestionRoomDatabase db;
    private SaveProgressDAO saveProgressDAO;
    private List<SaveProgress> saveProgressesList;

    public SaveProgressRepository(Application application){
        this.db = QuestionRoomDatabase.getInstance(application);
        this.saveProgressDAO = db.saveProgressDAO();
        this.saveProgressesList = getAllSaveProgressFromDB();
    }

    public List<SaveProgress> getAllSaveProgressFromDB(){
        try {
            return new GetSaveProgressAsyncTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetSaveProgressAsyncTask extends AsyncTask<Void, Void, List<SaveProgress>> {
        @Override
        protected List<SaveProgress> doInBackground(Void... voids) {
            return db.saveProgressDAO().getSavedProgress();
        }
    }


    public void insertSaveProgress(SaveProgress saveProgress){

        new InsertSaveProgressAsyncTask().execute(saveProgress);
    }


    private class InsertSaveProgressAsyncTask extends AsyncTask<SaveProgress, Void, Void> {
        @Override
        protected Void doInBackground(SaveProgress... saveProgresses) {
            db.saveProgressDAO().insertSavedState(saveProgresses[0]);
            return null;
        }

    }


}
