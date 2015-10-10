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

import com.example.layout.R;

public class GridViewAdapter extends Activity {
	private GridView mGridView;
	private int[] mImageView = {R.drawable.meituan_image1,R.drawable.meituan_image3,R.drawable.meituan_image8,
			R.drawable.meituan_image1,R.drawable.meituan_image3,R.drawable.meituan_image8,R.drawable.meituan_image1,
			R.drawable.meituan_image3,R.drawable.meituan_image8,R.drawable.meituan_image1,R.drawable.meituan_image3,
			R.drawable.meituan_image8,R.drawable.meituan_image1,R.drawable.meituan_image3,R.drawable.meituan_image8};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		setContentView(R.layout.adapter_gridview);
		mGridView = (GridView) findViewById(R.id.gridview_image);
		
		ImageAdapter adapter = new ImageAdapter(this);
		adapter.setData(mImageView);
		mGridView.setAdapter(adapter);
		
	}
	
	public class ImageAdapter extends BaseAdapter{
		private int[] image = {};//若不写实例，则会报空指针
		private LayoutInflater inflater;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		public ImageAdapter(Context context){
			inflater = LayoutInflater.from(context);
		}
		
		public void setData(int[] image){
			this.image = image;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return image.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return image[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView gridview;
			
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.gridview_item_image, null);
				 gridview = (ImageView) convertView.findViewById(R.id.item_img);
				
				convertView.setTag(gridview);
			}else{
				gridview = (ImageView) convertView.getTag();
			}
			
			 
//			gridview.setImageResource((Integer) getItem(position));
			gridview.setBackgroundResource((Integer) getItem(position));
			
			
			return convertView;
		}
		
	}
}
