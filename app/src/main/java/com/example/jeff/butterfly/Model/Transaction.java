package com.example.jeff.butterfly.Model;


import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.ContentValues;
import java.text.ParseException;
import android.database.sqlite.SQLiteDatabase;


public class Transaction {
	private int postID;
	private boolean isPub;
	private String title;
	private String body;
	private String date;
	private boolean saved;
	private boolean you;

	private Date rawDate;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Transaction(){
	}

	public Transaction(String title, String body) {
        this.title = title;
        this.body = body;
        this.rawDate = new Date();
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("body", body);

        return contentValues;
    }

    public int getID(){
    	return postID;
    } 

    public String getTitle(){
    	return title;
    } 

    public String getBody(){
    	return body;
    }

    public String getDate(){
    	return date;
    }

	public Transaction setTitle(String title){
		this.title = title;
		return this;
	}

    public Transaction setBody(String body){
		this.body = body;
		return this;
	}

	public Transaction setDateAndTime(String timestamp) {
		try {
			Date datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp);
			date = dateFormat.format(datetime);
		} catch(ParseException pe) {
			pe.printStackTrace();
			date = "error";
		}
		return this;
	}

}
