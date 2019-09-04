package com.example.submissionempat.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_FAVORITE = "favorite";

    static final class FavoriteColumn implements BaseColumns {
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String ID = "id";
        static String IMAGE = "image";
    }
}
