package com.candy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.candy.R;

public class CoverItemView extends LinearLayout {
	TextView tv = null;
//	TextView operator = null;

	public CoverItemView(Context context) {
		super(context);
		init(context);
	}

	public CoverItemView(Context context,AttributeSet attrs) {
		super(context,attrs);
		init(context);
	}
	
	private void init(Context context){
		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.cover_item, this, true);
		tv = (TextView)findViewById(R.id.split);
//		operator = (TextView)findViewById(R.id.operator);
		
	}
	
	public void setText(String i){
		tv.setText(i);
	}
	
	public CharSequence getText(){
		return tv.getText();
	}

	public void setTextColor(int color) {
		tv.setTextColor(color);
//		operator.setTextColor(color);
	}
	
	/*public void setOperator(String oper){
		operator.setText(oper);
	}*/
}
