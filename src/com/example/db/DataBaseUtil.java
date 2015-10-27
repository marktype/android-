package com.example.db;

import android.provider.BaseColumns;

public class DataBaseUtil {

	public abstract class TableUserColumn implements BaseColumns{
		public static final String DB_BASENAME = "testuser.db";//数据库名
		public static final String TABLE_USER = "user";//表名
		public static final String NAME = "name";//属性 名字
		public static final String PASSWORD = "password";//属性 密码
		public static final String _ID = "_id"; //自增id cursorAdapter遍历使用_id
		public static final String _ID_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT";//自增属性
	}
}
