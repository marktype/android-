package com.example.db;

import com.example.layout.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CousorActivity extends Activity {

	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.db_listview_adapter);
		mListView  = (ListView) findViewById(R.id.db_listview);
		
		MyDataBase base  = new MyDataBase(this);
		SQLiteDatabase db = base.getReadableDatabase();
		
		Cousor couAdapter = new Cousor(this, db.query(MyDataBase.TABLE_USER, null, null, null, null, null, null));
		mListView.setAdapter(couAdapter);
	}

	public class Cousor extends CursorAdapter{
		LayoutInflater layoutInflater;
		public Cousor(Context context, Cursor c) {
			super(context, c,true);
			layoutInflater = LayoutInflater.from(context);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			
			TextView name = (TextView) view.findViewById(R.id.db_query_name_txt);
			TextView password = (TextView) view.findViewById(R.id.db_query_password_txt);
			
			name.setText(cursor.getString(cursor.getColumnIndex("name")));
			password.setText(cursor.getString(cursor.getColumnIndex("password")));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return layoutInflater.inflate(R.layout.db_listview_layout, null);
		}
		
	}
}
