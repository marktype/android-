package com.example.db;

import com.example.basicinfo.Logs;

import android.app.DownloadManager.Query;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestAndroidDB extends AndroidTestCase {

	private SQLiteDatabase db;

	/*
	 * 执行全部测试或单项测试时都需首先执行
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MyAndroidDB database = new MyAndroidDB(getContext());
//		db = database.getWritableDatabase();
		db = database.getReadableDatabase();
	}

	// android方式添加，用Java代码实现
	public void testAddByAndroidSql() {
		ContentValues values = new ContentValues();
		values.put("id", "123");
		values.put("name", "张三");
		db.insert("student", null, values);
	}

	// 用sql语句增加
	public void testAddBySql() {
		String sql = "insert into student (id ,name) values (111,'李四')";
		db.execSQL(sql);
	}

	public void testSelect() {
		String[] colums = new String[] { "id", "name" }; // 查询的哪列或哪几列，用数组表示

		// "id asc" 升序排列 ，设为null则为默认排序
		Cursor cursor = db.query("student", colums, null, null, null, null,
				"id asc");

		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				
				Logs.e(id + "      " + name);
			}
		}

	}
	//删除数据
	public void testdelete(){
		String whereClause = "id = ?";//通过id删除
		String[] whereArgs = new String[]{"123"};//id对应编号
		
		db.delete("student", whereClause, whereArgs);
	}
	
	

}
