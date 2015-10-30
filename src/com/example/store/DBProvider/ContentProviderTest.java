package com.example.store.DBProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.db.DataBaseUtil;
import com.example.db.MyDataBase;

public class ContentProviderTest extends ContentProvider {

	private SQLiteDatabase db;
	private static final String AUTHTITIES = "com.example.layout";
	public static final Uri URI = Uri.parse("content://" + AUTHTITIES);

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return db.delete(DataBaseUtil.TableUserColumn.TABLE_USER, arg1, arg2);
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db.insert(DataBaseUtil.TableUserColumn.TABLE_USER, null, values);
		return null;
	}

	@Override
	public boolean onCreate() {
		MyDataBase myDataBase = MyDataBase.getInstance(getContext());
		db = myDataBase.getReadableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return db.query(DataBaseUtil.TableUserColumn.TABLE_USER, projection,
				selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		db.update(DataBaseUtil.TableUserColumn.TABLE_USER, values, selection, selectionArgs);
		return 0;
	}

}
