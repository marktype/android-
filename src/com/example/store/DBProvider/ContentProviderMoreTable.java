package com.example.store.DBProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ContentProviderMoreTable extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;  //版本信息
	public ContentProviderMoreTable(Context context) {
		super(context, DataBaseUtil.TableUserColumn.DB_BASENAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String student_sql = "create table "
				+ DataBaseUtil.TableStudentColumn.TABLE_STUDENT + " ("
				+ DataBaseUtil.TableStudentColumn.NAME + " varchar, "
				+ DataBaseUtil.TableStudentColumn.NUMBER + " varchar)";
		db.execSQL(student_sql);
		String user_sql = "create table "
				+ DataBaseUtil.TableUserColumn.TABLE_USER + " ("
				+ DataBaseUtil.TableUserColumn.NAME + " varchar, "
				+ DataBaseUtil.TableUserColumn.PASSWORD + " varchar)";
		db.execSQL(user_sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
