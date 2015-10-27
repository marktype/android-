package com.example.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1; // 版本信息
	

	/*
	 * 单例模式
	 */
	private static MyDataBase sDataBase;

	public static MyDataBase getInstance(Context context) {
		if (sDataBase == null) {
			sDataBase = new MyDataBase(context);
		}
		return sDataBase;
	}

	public MyDataBase(Context context) {
		super(context, DataBaseUtil.TableUserColumn.DB_BASENAME, null,
				DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + DataBaseUtil.TableUserColumn.TABLE_USER
				+ " (" + DataBaseUtil.TableUserColumn._ID + " "
				+ DataBaseUtil.TableUserColumn._ID_KEY + ","
				+ DataBaseUtil.TableUserColumn.NAME + " varchar,"
				+ DataBaseUtil.TableUserColumn.PASSWORD + " varchar)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void add(String name, String password) {
		SQLiteDatabase db = getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseUtil.TableUserColumn.NAME, name);
		values.put(DataBaseUtil.TableUserColumn.PASSWORD, password);
		db.insert(DataBaseUtil.TableUserColumn.TABLE_USER, null, values);
	}

	public ArrayList<User> query() {
		ArrayList<User> userData = new ArrayList<User>();
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(DataBaseUtil.TableUserColumn.TABLE_USER, null,
				null, null, null, null, null);
		if (cursor.getCount() > 0 && cursor != null) {
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor
						.getColumnIndex(DataBaseUtil.TableUserColumn.NAME));
				String password = cursor.getString(cursor
						.getColumnIndex(DataBaseUtil.TableUserColumn.PASSWORD));

				User user = new User();
				user.setName(name);
				user.setPassword(password);
				userData.add(user);
			}

		}

		return userData;
	}
}
