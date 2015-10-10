package com.example.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.layout.R;

public class Event_ArrayAdapter extends Activity {
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.arrayadapter_event);
	mListView = (ListView) findViewById(R.id.adapter_listview);
	
	String[] str = {"1","2","3"};
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, str);//此处调用系统格式应选好样式，否则会报错
	
	mListView.setAdapter(adapter);
	mListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView view = (TextView)arg1;
			Toast.makeText(Event_ArrayAdapter.this, view.getText(), Toast.LENGTH_SHORT).show();
		}
	});
	}
}
