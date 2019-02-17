package com.example.jeff.butterfly.Helpers;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.example.jeff.butterfly.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    private static final String AHHHHHHH =
            "Insert Into Lollipop (owner, isPub, title, body) Values (0, 0, 'A Stern University That Made Me a Really Excited Student', 'Today, I got an email from the university I''ve always wanted to go to, Stern NYU! Ever since I visited the campus, I''ve long since fallen in love with it. Thank you!!');";
                   // "('1', '1', 'To My PERSON-al alarm clock', 'Thank you for always waking me up in the morning and not making a fuss even though my alarm wakes you up. Because of you, I''m always on time for class. Bless.')," +
                    // "(0, 0, 'A Stern University That Made Me a Really Excited Student', 'Today, I got an email from the university I''ve always wanted to go to, Stern NYU! Ever since I visited the campus, I''ve long since fallen in love with it. Thank you!!')," +
                    // "(0, 0, 'I''m Not One to Go Out and Social, But Today Was Different', 'I recently started a new job and they happened to have a quarterly fun day the same week I was hired. The company took 6 of us bowling and it was probably the most fun I had in a long while. We were all joking around and having a blast. That made me extremely happy to feel like I fit in.')," +
                    // "(0, 0, 'Appreciating the Person Closest to Me', 'When I woke up and turned around, my boyfriend was still sleeping next to me. Seeing him sleep peacefully, without any worries makes me happy. He''s brought a lot of calm and rest into my life. I was happy by myself before I met him, but with him it''s like that happiness is amplified.')," +
                    // "(0, 0, 'I had a conversation over the phone with my mom for the first time in a while today. I was happy to talk with her', '')," +
                    // "(1, 0, 'A friend of mine gave me her gloves when my hands were cold.', '')," +
                    // "(0, 0, 'Someone held the door for me.', ''), "+
                    // "(0, 1, 'Coffee. It gives me life—literally', '')," +
                    // "(0, 1, 'The clearance rack was full of clothes in my size. What are the chances?!', '')," +
                    // "(0, 0, 'I found a skirt with pockets! What a concept!', '')," +
                    // "(0, 0, 'I finally evolved my magikarp to a gyarados today!', '')," +
                    // "(1, 0, 'My grandma baked me her specialty raspberry pie.', '')," +
                    // "(0, 0, 'A stranger on the street stopped me, and told me they liked my shoes.', '')," +
                    // "(0, 0, 'My local deli gave me extra meat on the house.', '')," +
                    // "(0, 0, 'HackNYU''s cupcakes really just came in my time of need.', '');";
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
        db.execSQL(AHHHHHHH);
        Log.e("alsdkfskdlfsdafkl","alksdflksadfadfjkls");
        return db.insert("Lollipop",null, content);

    }

    public List<Transaction> getYourPosts(){
        List<Transaction> list = new ArrayList<Transaction>();
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

    public List<Transaction> getSocialPosts(){
        List<Transaction> list = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *  FROM Lollipop WHERE isPub = 1 ORDER by timestamp DESC", null);
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

    public Transaction getRandomPost(){
        Transaction list = new Transaction();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Lollipop WHERE owner = 1 ORDER BY RANDOM() LIMIT 1", null);
        if(c.moveToFirst()){
            list.setTitle(c.getString(2));
            list.setBody(c.getString(3));
            list.setDateAndTime(c.getString(5));
        }
        c.close();
        return list;
    }

    // public List<Transaction> getSavedPosts(){
    //     List<Transaction> list = new ArrayList<Transaction>();
    //     SQLiteDatabase db = this.getReadableDatabase();
    //     Cursor c = db.rawQuery("SELECT * FROM Lollipop WHERE saved = 1", null);
    //     while(c.moveToNext()){
    //         list.add(
    //             new Transaction()
    //                 .setTitle(c.getString(2))
    //                 .setBody   (c.getString(3))
    //                 .setDateAndTime(c.getString(5))
    //                 );
    //     }
    //     c.close();
    //     return list;
    // }

}

