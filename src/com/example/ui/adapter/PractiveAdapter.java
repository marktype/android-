package com.example.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import com.example.layout.R;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class PractiveAdapter extends BaseAdapter {
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private LayoutInflater inflater;
	public PractiveAdapter(Context context) {
		
		inflater = LayoutInflater.from(context);
	}
	public void setList(ArrayList<HashMap<String, Object>> list) {
		this.list = list;
		notifyDataSetChanged();//更新数据
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.practive_adapter_item_list, null);
			viewHolder = new ViewHolder();
			viewHolder.iconImage1 = (ImageView) convertView.findViewById(R.id.practive_image1_list);
			viewHolder.iconImage2 = (ImageView) convertView.findViewById(R.id.practive_image2_list);
			viewHolder.title = (TextView) convertView.findViewById(R.id.name_list);
			viewHolder.descrpe = (TextView) convertView.findViewById(R.id.describe_list);
			viewHolder.price = (TextView) convertView.findViewById(R.id.price_list);
			viewHolder.deletPrice = (TextView) convertView.findViewById(R.id.price_cut1_list);
			viewHolder.grade = (TextView) convertView.findViewById(R.id.grade_list);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
		int icon1 = (Integer) item.get("iconImage1");
		int icon2 = (Integer) item.get("iconImage2");
		String titleTxt = (String) item.get("title");
		String descTxt = (String) item.get("descripe");
		String priceTxt = (String) item.get("price");
		String deletTxt = (String) item.get("deletprice");
		String gradeTxt = (String) item.get("grade");
		
		viewHolder.iconImage1.setImageResource(icon1);
		viewHolder.iconImage2.setImageResource(icon2);
		viewHolder.title.setText(titleTxt);
		viewHolder.descrpe.setText(descTxt);
		viewHolder.price.setText(setSpannableFontAndColor(priceTxt));
		viewHolder.deletPrice.setText(setSpannableString(deletTxt));
		viewHolder.grade.setText(gradeTxt);
		
		
		return convertView;
	}
	
	/*
	 * 设置字体样式
	 * */
	public SpannableString setSpannableString(CharSequence sequence){
		SpannableString string = new SpannableString(sequence);
		
		//此处为加一条删除线
		string.setSpan(new StrikethroughSpan(), 0, sequence.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return string;
	}
	public SpannableString setSpannableFontAndColor(CharSequence sequence){
		SpannableString string = new SpannableString(sequence);
		
		//改变字体大小
		string.setSpan(new AbsoluteSizeSpan(20, true),0, string.length()-1,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//设置字体颜色
		string.setSpan(new ForegroundColorSpan(Color.GREEN),0, 
				string.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		return string;
	}

	class ViewHolder{
		ImageView iconImage1,iconImage2;
		TextView title,descrpe,price,deletPrice,grade;
	}
}
