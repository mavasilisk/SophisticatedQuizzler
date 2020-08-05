package com.example.sophisticatedquizzler.util;

public class RoomUtil {
    public static final String DB_NAME = "question";

    public enum CATEGORY_NAME_ENUM {
        VIDEO_GAMES, COMPUTER_SCIENCE
    }

    public static String CATEGORY_NAME(CATEGORY_NAME_ENUM categoryName){
        switch (categoryName){
            case VIDEO_GAMES:
                return "Entertainment: Video Games";
            case COMPUTER_SCIENCE:
                return "COMPUTER_SCIENCE_TABLE";
        }
        return "";
    }
}
