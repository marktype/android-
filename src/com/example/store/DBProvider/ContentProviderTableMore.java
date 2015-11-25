package com.example.store.DBProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ContentProviderTableMore extends ContentProvider {
	private SQLiteDatabase db;
private static final String AUTHORITY = "com.example.tearchprovider";
	
	private static final String USER_PATH = "user";
	private static final String TEARCH_PATH = "student";
	
	private static final int USER_CODE = 0;
	private static final int STUDENT_CODE = 1;
	
	public static final Uri CONTENT_URI_USER = Uri.parse("content://"+ AUTHORITY + "/"+USER_PATH);
	public static final Uri CONTENT_URI_TEARCH = Uri.parse("content://"+ AUTHORITY + "/"+TEARCH_PATH);
	public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);  //加载匹配
	static {
		uriMatcher.addURI(AUTHORITY, USER_PATH, USER_CODE);
		uriMatcher.addURI(AUTHORITY, TEARCH_PATH, STUDENT_CODE);
	}
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {

		
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int code = uriMatcher.match(uri);
		switch(code){
		case USER_CODE:
			long id = db.insert(DataBaseUtil.TableUserColumn.TABLE_USER, null, values);
			return Uri.withAppendedPath(uri, String.valueOf(id));
			
		case STUDENT_CODE:
			id = db.insert(DataBaseUtil.TableStudentColumn.TABLE_STUDENT, null, values);
			return Uri.withAppendedPath(uri, String.valueOf(id)); // content://com.scxh.android1503.tearchprovider/tearch/1
		
		default:
			return null;
		}
	}

	@Override
	public boolean onCreate() {
		ContentProviderMoreTable helper = new ContentProviderMoreTable(getContext());
		db = helper.getReadableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		int code = uriMatcher.match(uri);
		switch(code){
		case USER_CODE:
			return db.query(DataBaseUtil.TableUserColumn.TABLE_USER, projection, selection,selectionArgs, null, null, sortOrder);
		case STUDENT_CODE:
			return db.query(DataBaseUtil.TableStudentColumn.TABLE_STUDENT, projection,selection, selectionArgs, null, null, sortOrder);
		default:
			return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
