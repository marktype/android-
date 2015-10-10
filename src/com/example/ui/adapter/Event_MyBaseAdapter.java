package com.example.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.layout.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Event_MyBaseAdapter extends BaseAdapter{
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private Context context;
	public Event_MyBaseAdapter(Context context) {
		this.context = context;
		
	
	}
	
	public void setlist(ArrayList<HashMap<String, Object>> list){
		this.list = list;
	}
	
	@Override
	public int getCount() {//目录数量
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		ViewHolder viewHolder;
		if (convertView == null) {
			//解析xml
			convertView = inflater.inflate(R.layout.simple_adapter_item_list, null);
			viewHolder = new ViewHolder();
			//获取格式
			viewHolder.image = (ImageView) convertView.findViewById(R.id.simple_adapter_img);
			viewHolder.title = (TextView) convertView.findViewById(R.id.simple_title_txt);
			viewHolder.content = (TextView) convertView.findViewById(R.id.simple_content_txt);
			
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//获取单项目录
		HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
		//分别获取单项中的数据
		int img = (Integer) item.get("icon");
		String titleTxt = (String) item.get("title");
		String conTxt = (String) item.get("content");
		//设置数据
		viewHolder.image.setImageResource(img);
		viewHolder.title.setText(titleTxt);
		viewHolder.content.setText(conTxt);
		
		
		return convertView;
	}
	//自定义存取对象
	class ViewHolder{
		ImageView image ;
		TextView title ;
		TextView content ;
	}
}
