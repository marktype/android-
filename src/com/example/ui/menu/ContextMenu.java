package com.example.ui.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;

import com.example.layout.R;

public class ContextMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
	}
	@Override
	public void onCreateContextMenu(android.view.ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater content = getMenuInflater();
		content.inflate(R.menu.context_menu, menu);
		menu.add("添加");
		menu.add("删除");
	}
}
