package com.example.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.activity.IntentCallPhone;
import com.example.activity.OnStartActivityExtraBundleA;
import com.example.activity.SaveInstanceTest;
import com.example.basicinfo.ActivityExitAll;
import com.example.layout.LayoutAll;
import com.example.layout.TestCodeActvity;
import com.example.ui.widget.Button_color;
import com.example.ui.widget.CheckBoxTest;
import com.example.ui.widget.EidtViewTest;
import com.example.ui.widget.EventButton;
import com.example.ui.widget.Event_ArrayAdapter;
import com.example.ui.widget.Event_SimpleAdapter;
import com.example.ui.widget.ImageViewTest;
import com.example.ui.widget.Logcat_Switch;
import com.example.ui.widget.SexRadioButton;

public class TestActivityList extends ListActivity{

	String[] string = {"布局管理","按钮颜色设置","代码写界面","登录界面",
			"按钮触发事件","图片","单选控件","多选控件","开关按钮",
			"android自动保存","数据传递(一键退出)","intent测试","Array适配器","Simple适配器"}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityExitAll main = ActivityExitAll.getInstance();
		main.addActivity(this);
		
		
	ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_list_item_1, string);
	
	setListAdapter(ap);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			startActivity(new Intent(this,LayoutAll.class));
			break;
		case 1:
			startActivity(new Intent(this,Button_color.class));
			break;
		case 2:
			startActivity(new Intent(this,TestCodeActvity.class));
			break;
		case 3:
			startActivity(new Intent(this,EidtViewTest.class));
			break;
		case 4:
			startActivity(new Intent(this,EventButton.class));
			break;
		case 5:
			startActivity(new Intent(this,ImageViewTest.class));
			break;
		case 6:
			startActivity(new Intent(this,SexRadioButton.class));
			break;
		case 7:
			startActivity(new Intent(this,CheckBoxTest.class));
			break;
		case 8:
			startActivity(new Intent(this,Logcat_Switch.class));
			break;
		case 9:
			startActivity(new Intent(this,SaveInstanceTest.class));
			break;
		case 10:
			startActivity(new Intent(this,OnStartActivityExtraBundleA.class));
			break;
		case 11:
			startActivity(new Intent(this,IntentCallPhone.class));
			break;
		case 12:
			startActivity(new Intent(this,Event_ArrayAdapter.class));
			break;
		case 13:
			startActivity(new Intent(this,Event_SimpleAdapter.class));
			break;
		}
	}
}
