package com.candy;

import com.candy.db.DAO;
import com.candy.view.BlockView;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class CandyApp extends Application {
	
	private static CandyApp app = null;
	private static int position = 0;
	private int QUESTION_TOTAL = 0 ;
	private String [] questions = null;
	
	private int flip_distance = 50;
	private BlockView origCard = null;
	
	public BlockView getOrigCard() {
		return origCard;
	}

	public void setOrigCard(BlockView origCard) {
		this.origCard = origCard;
	}

	public static final CandyApp getInstance(){
		return app;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;

		initDB();
		
		questions = getResources().getStringArray(R.array.question);
		QUESTION_TOTAL = getResources().getInteger(R.integer.question_total);
		flip_distance = getResources().getInteger(R.integer.flip_distance);
		
		int _curr = new DAO().currentLevel();
		if(_curr >= QUESTION_TOTAL){
			position = 0;
		}else{
			position = _curr;
		}
	}
	
	public String currentQuestion(){
		return questions[position];
	}
	
	public void nextPage(){
		if(position < QUESTION_TOTAL-1){
			position++;
		}else{
			position = 0;
		}
	}
	
	public void prePage(){
		if(position > 0){
			position--;
		}else{
			position = QUESTION_TOTAL - 1;
		}
	}
	
	public int getFlipDistance(){
		return flip_distance;
	}
	
	public int currentPage(){
		return position;
	}
	
	private void initDB(){
		SQLiteDatabase db = openOrCreateDatabase("candy.db", MODE_PRIVATE, null);
		 db.execSQL("DROP TABLE IF EXISTS c_stat");
		 db.execSQL("CREATE TABLE c_stat (id INTEGER PRIMARY KEY , _time decimal(8,2))");
		 db.close();
	}

}
