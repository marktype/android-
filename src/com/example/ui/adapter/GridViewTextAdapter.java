package com.example.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.layout.R;

public class GridViewTextAdapter extends Activity {
	private GridView mGridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		setContentView(R.layout.adapter_gridview);
		mGridView = (GridView) findViewById(R.id.gridview_image);
		setList(list);
		ImageAdapter adapter = new ImageAdapter(this);
		adapter.setData(list);
		mGridView.setAdapter(adapter);
		
	}
	public void setList(ArrayList<HashMap<String, Object>> list){
		
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image1);
		item.put("title", "第一张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image3);
		item.put("title", "第二张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image8);
		item.put("title", "第三张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image1);
		item.put("title", "第四张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image3);
		item.put("title", "第五张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image8);
		item.put("title", "第六张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image1);
		item.put("title", "第七张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image3);
		item.put("title", "第八张图片");
		list.add(item);
		
		item = new HashMap<String, Object>();
		item.put("image", R.drawable.meituan_image8);
		item.put("title", "第九张图片");
		list.add(item);
		
		
	}
	
	
	public class ImageAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		public ImageAdapter(Context context){
			inflater = LayoutInflater.from(context);
		}
		public void setData(ArrayList<HashMap<String, Object>> list){
			this.list = list;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
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
				viewHolder = new ViewHolder();
				convertView = inflater.inflate(R.layout.gridview_item_image, null);
				viewHolder.gridview = (ImageView) convertView.findViewById(R.id.item_img);
				viewHolder.imageTxt = (TextView) convertView.findViewById(R.id.declare_Txt);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
			viewHolder.gridview.setImageResource((Integer) item.get("image"));
			viewHolder.imageTxt.setText((CharSequence) item.get("title"));
			return convertView;
		}
		
	}
	
	public class ViewHolder{
		ImageView gridview;
		TextView imageTxt;
	}
}
