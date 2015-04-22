package com.version1_0.ClubCrawl;

/**
 * This is the main application, it is the entrance of the application, 
 * so I can set the global vars here
 * @author Jiang Zhe Heng
 */
import java.util.HashMap;

import android.app.Application;
import android.widget.CheckBox;

public class MyApplication extends Application {

	private HashMap<String, Object> hashMap=new HashMap<String, Object>();
	private static MyApplication myApplication;
	
	public MyApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		myApplication=this;
	}
	
	public static MyApplication getInstance(){
		return myApplication;
	}

	public HashMap<String, Object> getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap<String, Object> hashMap) {
		this.hashMap = hashMap;
	}

}
