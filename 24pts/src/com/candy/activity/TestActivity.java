package com.candy.activity;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.candy.R;
import com.candy.view.BlockView;
import com.candy.view.CardBottomView;
import com.candy.view.CardTopView;
import com.candy.view.CardView;
import com.candy.view.CoverView;
import com.candy.view.ImgAndLabelComp;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		testCardTopView();
//		testCardBottomView();
//		testCardView();
//		testCoverView();
		testBlockView();
	}
	
	private void testCardView(){
		CardView cv = new CardView(this);
		cv.setText("112");
		setContentView(cv);
	}
	
	private void testCoverView(){
		CoverView cv = new CoverView(this);
		setContentView(cv);
	}
	
	private void testBlockView(){
		BlockView bv = new BlockView(this);
		setContentView(bv);
	}
	
	private void testCardTopView(){
		CardTopView ctv = new CardTopView(this);
		testImgAndLabelComp(ctv);
	}
	
	private void testCardBottomView(){
		CardBottomView cbv = new CardBottomView(this);
		testImgAndLabelComp(cbv);
	}
	
	private void testImgAndLabelComp(ImgAndLabelComp view){
		int i = 112;
		int color = new Random().nextInt(4);
		int card_color = 0;
		SpannableStringBuilder builder = new SpannableStringBuilder(String.valueOf(i));
		ForegroundColorSpan textSpan = null;
		switch (color) {
		case 0:
			textSpan = new ForegroundColorSpan(Color.BLACK);
			card_color  = R.drawable.hei;
			break;
		case 1:
			textSpan = new ForegroundColorSpan(Color.RED);
			card_color = R.drawable.hong;
			break;
		case 2:
			textSpan = new ForegroundColorSpan(Color.GREEN);
			card_color = R.drawable.mei;
			break;
		case 3:
			textSpan = new ForegroundColorSpan(Color.YELLOW);
			card_color = R.drawable.fang;
			break;
		default:
			textSpan = new ForegroundColorSpan(Color.RED);
			card_color = R.drawable.hei;
		}
		builder.setSpan(textSpan, 0, String.valueOf(i).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		view.setText(builder);
		view.setImageResource(card_color);
		setContentView(view);
		
	}

}
