package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.activity.IntentCallPhone;
import com.example.activity.OnStartActivityExtraBundleA;
import com.example.activity.SaveInstanceTest;
import com.example.basicinfo.ActivityExitAll;
import com.example.broadcast.BroadCastActivity;
import com.example.callback.CallBackActivityTest;
import com.example.db.DBActivity;
import com.example.handler.HandlerMessage;
import com.example.layout.LayoutAll;
import com.example.layout.TestCodeActvity;
import com.example.mediaplay.Mp3PlayActivity;
import com.example.server.ServerActivity;
import com.example.store.file.SendImageStore;
import com.example.store.sms.SmsActivity;
import com.example.ui.adapter.Event_ArrayAdapter;
import com.example.ui.adapter.Event_SimpleAdapter;
import com.example.ui.adapter.GridViewAdapter;
import com.example.ui.adapter.GridViewTextAdapter;
import com.example.ui.adapter.MyAdpater_Test;
import com.example.ui.adapter.MyPractiveAdapter;
import com.example.ui.adapter.ViewPagerAdapter;
import com.example.ui.dialog.AlertDialogBasic;
import com.example.ui.meituan.StartImageActivity;
import com.example.ui.menu.ContextMenuActivityTest;
import com.example.ui.menu.PopupWindowActivity;
import com.example.ui.shape.ShapeActivity;
import com.example.ui.tab.TabActivityTest;
import com.example.ui.widget.Button_color;
import com.example.ui.widget.CheckBoxTest;
import com.example.ui.widget.EidtViewTest;
import com.example.ui.widget.EventButton;
import com.example.ui.widget.ImageViewTest;
import com.example.ui.widget.Logcat_Switch;
import com.example.ui.widget.SexRadioButton;
import com.example.ui.widget.bar.ProgessSeekBar;

public class TestActivityList extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 记录界面（后面一键退出）
		ActivityExitAll main = ActivityExitAll.getInstance();
		main.addActivity(this);
		
		String[] from = { "title" };
		int[] to = { android.R.id.text1 };
		SimpleAdapter adapter = new SimpleAdapter(this, setData(),
				android.R.layout.simple_list_item_1, from, to);

		setListAdapter(adapter);
		
	}
	public ArrayList<HashMap<String, Object>> setData(){
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		addItem(data, "布局管理", LayoutAll.class);
		addItem(data, "按钮颜色设置", Button_color.class);
		addItem(data, "代码写界面", TestCodeActvity.class);
		addItem(data, "登录界面", EidtViewTest.class);
		addItem(data, "按钮触发事件", EventButton.class);
		addItem(data, "图片", ImageViewTest.class);
		addItem(data, "单选控件", SexRadioButton.class);
		addItem(data, "多选控件", CheckBoxTest.class);
		addItem(data, "开关按钮", Logcat_Switch.class);
		addItem(data, "android自动保存", SaveInstanceTest.class);
		addItem(data, "数据传递(一键退出)", OnStartActivityExtraBundleA.class);
		addItem(data, "intent测试", IntentCallPhone.class);
		addItem(data, "Array适配器", Event_ArrayAdapter.class);
		addItem(data, "Simple适配器", Event_SimpleAdapter.class);
		addItem(data, "自定义适配器", MyAdpater_Test.class);
		addItem(data, "仿美团界面", MyPractiveAdapter.class);
		addItem(data, "GridVIew", GridViewAdapter.class);
		addItem(data, "带标记gridview", GridViewTextAdapter.class);
		addItem(data, "viewpager", ViewPagerAdapter.class);
		addItem(data, "进度条", ProgessSeekBar.class);
		addItem(data, "对话框", AlertDialogBasic.class);
		addItem(data, "TabActivity", TabActivityTest.class);
		addItem(data, "美团", StartImageActivity.class);
		addItem(data, "菜单", ContextMenuActivityTest.class);
		addItem(data, "shape", ShapeActivity.class);
		addItem(data, "popupWindow", PopupWindowActivity.class);
		addItem(data, "handler", HandlerMessage.class);
		addItem(data, "回调CallBack",CallBackActivityTest.class);
		addItem(data, "数据库", DBActivity.class);
		addItem(data, "file文件存储", SendImageStore.class);
		addItem(data, "短信监听", SmsActivity.class);
		addItem(data, "MP3播放", Mp3PlayActivity.class);
		addItem(data, "ServerActivity", ServerActivity.class);
		addItem(data, "广播", BroadCastActivity.class);
		
		return data;
	}
	
	

	public void addItem(ArrayList<HashMap<String, Object>> data, String name, Class<?> c) {
		addItem(data, name, new Intent(this, c));
	} 
	
	public void addItem(ArrayList<HashMap<String,Object>> data,String name,Intent intent){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", name);
		map.put("intent", intent);
		data.add(map);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);

		
		
	}
}
