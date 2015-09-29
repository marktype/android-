package com.example.basicinfo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

public class ActivityExitAll extends Application{

	private ArrayList<Activity> activity = new ArrayList();
	private static ActivityExitAll instance;
	
	public void addActivity(Activity act){
		activity.add(act);
	}
	public static ActivityExitAll getInstance() {
        if (instance ==null) {
            instance = new ActivityExitAll();            
        }
        return instance;
    }
	public void finishAll(){
		for (Activity object : activity) {
			if (!object.isFinishing()) {
				object.finish();
			}
		}
	}
}
