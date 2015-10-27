package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyAndroidDB extends SQLiteOpenHelper {

	private static final String DB_NAME = "testandroid.db"; //数据库名
	private static final int DB_VERSION = 2;  //版本信息
	private static final String TABLE_STUDENT = "student"; //表名
	
	
	
	
	
	//精简代码，其中3个参数在super中用3个定值代替达到简化效果
	public MyAndroidDB(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/*
	 * 创造数据库，只执行一次，在表格没有时建立
	 * */
	@Override
	public void onCreate(SQLiteDatabase arg0) {

		String sql = "create table "+TABLE_STUDENT+" (id int, name varchar)";
		arg0.execSQL(sql);
	}

	/*
	 * 版本更新时调用
	 * */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		//修改数据库结构
		String sql = "alter table "+TABLE_STUDENT+" add column number varchar(10)";
		db.execSQL(sql);
	}

}
