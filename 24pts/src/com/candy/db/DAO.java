package com.candy.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.candy.CandyApp;

public class DAO {
	
	public void saveLevel(){
		if(currentLevel() <= CandyApp.getInstance().currentPage() ){
			SharedPreferences settings = CandyApp.getInstance().getSharedPreferences("candy_setting", Context.MODE_PRIVATE);
			 SharedPreferences.Editor edit = settings.edit();
			 edit.putInt("level", CandyApp.getInstance().currentPage() + 1);
			 edit.commit();
		}
	}
	
	public int currentLevel(){
		SharedPreferences settings = CandyApp.getInstance().getSharedPreferences("candy_setting", Context.MODE_PRIVATE);
		return settings.getInt("level", 0);
	}

}
