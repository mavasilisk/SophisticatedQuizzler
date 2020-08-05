package com.example.sophisticatedquizzler.util;

import com.example.sophisticatedquizzler.data.remote.RetrofitClient;
import com.example.sophisticatedquizzler.data.remote.SOService;

public class RetrofitUtil {
    public static final String BASE_URL = "https://opentdb.com/";
//    public static final String VIDEO_GAMES_URL = "api.php?amount=50&category=15&difficulty=easy&type=boolean";
    public static final String VIDEO_GAMES_URL = "api.php?amount=10&difficulty=easy&type=boolean";

    public static SOService getSOService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
