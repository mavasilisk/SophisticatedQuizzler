package com.example.sophisticatedquizzler.data.remote;

import com.example.sophisticatedquizzler.data.model.SOQuestionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SOService {
// "/api.php?amount=50&category=15&difficulty=easy&type=boolean"
    @GET
    Call<SOQuestionsResponse> getResults(@Url String url);

}
