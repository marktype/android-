package com.example.db;

import java.util.ArrayList;

import com.example.layout.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QueryInfoActivity extends Activity {

	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_listview_adapter);
		
		//查询表中的信息
		MyDataBase dataBase = MyDataBase.getInstance(this);
		ArrayList<User> userData = dataBase.query();
		//设置数据源
		mListView = (ListView) findViewById(R.id.db_listview);
		UserAdapter  adapter = new UserAdapter(this); 
		adapter.setData(userData);
		mListView.setAdapter(adapter);
		
	}
	
	public class UserAdapter extends BaseAdapter{

		private ArrayList<User> data = new ArrayList<User>();
		private LayoutInflater layoutInflater;
		public UserAdapter(Context context){
			layoutInflater = LayoutInflater.from(context);
		}
		
		 public void setData(ArrayList<User> data) {
			this.data = data;
		notifyDataSetChanged();
		 }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.db_listview_layout, null);
				viewHolder = new ViewHolder();
				viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.db_query_name_txt);
				viewHolder.passwordTxt  = (TextView) convertView.findViewById(R.id.db_query_password_txt);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			User user = (User) getItem(position);
			
			viewHolder.nameTxt.setText(user.getName());
			viewHolder.passwordTxt.setText(user.getPassword());
			
			return convertView;
		}
		
		public class ViewHolder{
			TextView nameTxt,passwordTxt;
		}
		
	}
	
	
}
