package com.example.layout;

import com.example.ui.widget.Button_color;
import com.example.ui.widget.Marquee_Test;
import com.example.ui.widget.TwoMarquee_Test;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LayoutAll extends ListActivity {

	String[] string = {"线","相对","列","跑马灯","多个同时跑马灯"}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_list_item_1, string);
	setListAdapter(ap);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			startActivity(new Intent(this,TestLinearlayout.class));
			break;
		case 1:
			startActivity(new Intent(this,TestRelative.class));
			break;
		case 2:
			startActivity(new Intent(this,TestRelativelayout.class));
			break;
		case 3:
			startActivity(new Intent(this,Marquee_Test.class));
			break;
		case 4:
			startActivity(new Intent(this,TwoMarquee_Test.class));
			break;
		
		}
	}
}
