package com.example.ui.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

import com.example.layout.R;

public class MyProvider extends ActionProvider {
	private Context context;
	public MyProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		super.onPrepareSubMenu(subMenu);
		subMenu.clear();// 若不清除，将会不停累计，显示重复
		subMenu.setIcon(R.drawable.ic_launcher).add("启动")
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return true;
					}
				});

		subMenu.setIcon(R.drawable.ic_launcher).add("停止")
		.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				return true;
			}
		});
	}
	@Override
	public boolean hasSubMenu() {
		return true;
	}
}