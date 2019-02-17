package com.example.jeff.butterfly.Helpers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import com.example.jeff.butterfly.Model.Transaction;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper{
    private final static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "butterflyDB";
    private static final String CREATE_TABLE_LOLLIPOP =
            "CREATE TABLE IF NOT EXISTS Lollipop(" +
                    "postID INTEGER PRIMARY KEY, " +
                    "isPub INTEGER, " +
                    "title TEXT, " +
                    "body TEXT, " +
                    "timestamp TEXT DEFAULT (datetime('now', 'localtime'))," +
                    "owner INTEGER" +
                    // "saved INTEGER" +
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

    public long makeTransaction(String title, String body, Integer isPub){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put("title",title);
        content.put("body",body);
        content.put("isPub",isPub);
        content.put("owner",1);
        // content.put("saved",0);
        return db.insert("Lollipop",null, content);
    }

    public ArrayList<Transaction> getYourPosts(){
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Lollipop WHERE owner = 1 ORDER by timestamp DESC", null);
        while(c.moveToNext()){
            list.add(
                new Transaction()
                    .setTitle(c.getString(2))
                    .setBody(c.getString(3))
                    .setDateAndTime(c.getString(5))
                    );
        }
        c.close();
        return list;
    }

    public ArrayList<Transaction> getSocialPosts(){
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * saved FROM Lollipop WHERE isPub = 1 ORDER by timestamp DESC", null);
        while(c.moveToNext()){
            list.add(
                new Transaction()
                    .setTitle(c.getString(2))
                    .setBody(c.getString(3))
                    .setDateAndTime(c.getString(5))
                    );
        }
        c.close();
        return list;
    }

    public ArrayList<Transaction> getRandomPosts(){
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Lollipop WHERE owner = 1 BY RAND() LIMIT 1", null);
        if(c.moveToFirst()){
            list.add(
                new Transaction()
                    .setTitle(c.getString(2))
                    .setBody(c.getString(3))
                    .setDateAndTime(c.getString(5))
                    );
        }
        c.close();
        return list;
    }

    public ArrayList<Transaction> getSavedPosts(){
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Lollipop WHERE saved = 1", null);
        while(c.moveToNext()){
            list.add(
                new Transaction()
                    .setTitle(c.getString(2))
                    .setBody   (c.getString(3))
                    .setDateAndTime(c.getString(5))
                    );
        }
        c.close();
        return list;
    }

}

