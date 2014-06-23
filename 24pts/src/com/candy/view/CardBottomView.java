package com.candy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.candy.R;

public class CardBottomView extends ImgAndLabelComp {

	public CardBottomView(Context context) {
		super(context);
		init(context);
	}

	public CardBottomView(Context context,AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){

		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.card_bottom, this, true);

		// Get references to the child controls.
		tv = (TextView) findViewById(R.id.bottom_num);
		iv = (ImageView) findViewById(R.id.bottom_img);
		
	}
}
