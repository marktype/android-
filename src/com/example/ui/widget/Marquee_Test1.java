package com.example.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.TextView;

public class Marquee_Test1 extends TextView{

	public Marquee_Test1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public Marquee_Test1(Context context, AttributeSet attrs) {
		  super(context, attrs);
		}
		public Marquee_Test1(Context context, AttributeSet attrs, int defStyle) {
		  super(context, attrs, defStyle);
		}
		
//		@Override
//		protected void onFocusChanged(boolean focused, int direction,
//		   Rect previouslyFocusedRect) {  
//		}
	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;
	}
}
