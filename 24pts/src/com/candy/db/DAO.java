package com.candy.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

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
	
	public void saveLevelInfo(int id , long ltime){
		double _time = ltime/1000.0;
		SQLiteDatabase db = CandyApp.getInstance().openOrCreateDatabase("candy.db", Context.MODE_PRIVATE, null);
		db.execSQL("insert into c_stat(id,_time) values ("+id + 
				","+ _time + ") on duplicate key update _time = (if (_time > "+ 
				_time + " , "+ _time + ",_time))");
		db.close();
	}

}
