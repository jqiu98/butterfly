package com.example.jeff.butterfly.Helpers;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

public class DbHelper extends SQLiteOpenHelper{
    private final static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "butterflyDB";
    private static final String CREATE_TABLE_LOLLIPOP =
            "CREATE TABLE IF NOT EXISTS Lollipop(" +
                    "postID INTEGER PRIMARY KEY, " +
                    "isPub BOOLEAN, " +
                    "title TEXT, " +
                    "body TEXT, " +
                    "timestamp TEXT DEFAULT (datetime('now', 'localtime'))," +
                    "you BOOLEAN" +
                    ");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOLLIPOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Lollipop;");

        onCreate(db);
    }

}

