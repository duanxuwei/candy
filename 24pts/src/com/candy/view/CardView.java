package com.candy.view;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.candy.R;

public class CardView extends LinearLayout {
	TextView tv = null;
	CardTopView top = null;
	CardBottomView bottom = null;
	private int color = 0;

	public CardView(Context context) {
		super(context);
		init(context);
	}

	public CardView(Context context,AttributeSet attrs) {
		super(context,attrs);
		init(context);
	}
	
	private void init(Context context){
		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.card, this, true);
		tv = (TextView)findViewById(R.id.card_num);
		top = (CardTopView)findViewById(R.id.card_top);
		bottom = (CardBottomView)findViewById(R.id.card_bottom);
		color = new Random().nextInt(4);
		setColor(color);
		
	}
	
	public void setText(String i){
		SpannableStringBuilder builder = new SpannableStringBuilder(i);
		ForegroundColorSpan textSpan = null;
		switch (color) {
		case 0:
			textSpan = new ForegroundColorSpan(Color.BLACK);
			break;
		case 1:
			textSpan = new ForegroundColorSpan(Color.RED);
			break;
		case 2:
			textSpan = new ForegroundColorSpan(Color.BLACK);
			break;
		case 3:
			textSpan = new ForegroundColorSpan(Color.RED);
			break;
		default:
			textSpan = new ForegroundColorSpan(Color.BLACK);
		}
		builder.setSpan(textSpan, 0, i.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(builder);
		top.setText(builder);
		bottom.setText(builder);
		setColor(color);
	}
	
	private void setColor(int i){
		color = i;
		int card_color = 0;
		switch (color) {
		case 0:
			card_color  = R.drawable.hei;
			break;
		case 1:
			card_color = R.drawable.hong;
			break;
		case 2:
			card_color = R.drawable.mei;
			break;
		case 3:
			card_color = R.drawable.fang;
			break;
		default:
			card_color = R.drawable.hei;
		}
		top.setImageResource(card_color);
		bottom.setImageResource(card_color);
	}
}
