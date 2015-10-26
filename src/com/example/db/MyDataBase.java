package com.example.db;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper{
	private static final String DB_BASENAME = "testuser.db"; //数据库名
	private static final int DB_VERSION = 1;  //版本信息
	public static final String TABLE_USER = "user"; //表名
	public MyDataBase(Context context) {
		super(context, DB_BASENAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table "+TABLE_USER+" (_id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar,password varchar)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void add(String name,String password){
		SQLiteDatabase db = getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("password", password);
		db.insert(MyDataBase.TABLE_USER, null, values);
	}
	public ArrayList<User> query(){
		ArrayList<User> userData = new ArrayList<User>();
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USER, null, null, null, null, null, null);
		if (cursor.getCount() > 0 && cursor!=null) {
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String password = cursor.getString(cursor.getColumnIndex("password"));
			
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			userData.add(user);
			}
			
		}
		
		return userData;
	}
}
