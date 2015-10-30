package com.example.store.DBProvider;

import com.example.basicinfo.Logs;
import com.example.db.DataBaseUtil;
import com.example.db.MyDataBase;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

public class ContentProviderCase extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		
	}
	
	public void testContent(){
//		add("张三", "123");
//		add("李四", "456");
//		add("王五", "789");
//		delete("张三");
		update("李四", "111");
		query();
	}
	
	/*
	 * 通过contentprovider操作数据，原理同数据库操作相同，插入
	 * */
	public void add(String name ,String password){
		ContentResolver content = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put(DataBaseUtil.TableUserColumn.NAME, name);
		values.put(DataBaseUtil.TableUserColumn.PASSWORD, password);
		content.insert(ContentProviderTest.URI, values);
	}
	//删除
	public void delete(String name){
		ContentResolver content = getContext().getContentResolver();
		String where = "name = ?";
		String[] selectionArgs = {name};
		content.delete(ContentProviderTest.URI, where, selectionArgs);
	}
	//修改
	public void update(String name,String password){
		ContentResolver content = getContext().getContentResolver();
		String where = "name = ?";
		String[] selectionArgs = {name};
		ContentValues values = new ContentValues();
//		values.put(DataBaseUtil.TableUserColumn.NAME, name);
		values.put(DataBaseUtil.TableUserColumn.PASSWORD, password);
		
		content.update(ContentProviderTest.URI, values, where, selectionArgs);
	}
	
	//查询
	public void query(){
		ContentResolver content = getContext().getContentResolver();
		Cursor c = content.query(ContentProviderTest.URI, null, null, null, null);
		Logs.e( "姓名" +"\t"+ "密码" );
		if (c.getCount() > 0) {
			while (c.moveToNext()) {
				
				String name = c.getString(c.getColumnIndex(DataBaseUtil.TableUserColumn.NAME));
				String password = c.getString(c.getColumnIndex(DataBaseUtil.TableUserColumn.PASSWORD));
				Logs.e( name +"\t"+ password );
			}
		}
	}
}
