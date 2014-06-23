package com.candy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImgAndLabelComp  extends LinearLayout {

	public ImgAndLabelComp(Context context) {
		super(context);
	}

	public ImgAndLabelComp(Context context,AttributeSet set) {
		super(context, set);
	}

	TextView tv = null;
	ImageView iv = null;
	
	public void setImageResource(int i){
		iv.setImageResource(i);
	}
	
	public void setText(CharSequence text){
		tv.setText(text);
	}
}
