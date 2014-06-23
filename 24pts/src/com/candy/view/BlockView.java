package com.candy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.candy.R;

public class BlockView extends FrameLayout {
	CardView cardView = null;
	CoverView coverView = null;
	private int cardNum = 0;

	public BlockView(Context context) {
		super(context);
		init(context);
	}

	public BlockView(Context context,AttributeSet attrs) {
		super(context,attrs);
		init(context);
	}
	
	private void init(Context context){
		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.block, this, true);
		cardView = (CardView)findViewById(R.id.card);
		coverView = (CoverView)findViewById(R.id.cover);
	}
	

	public void setText(String i){
		setTag(i);
		cardNum = Integer.valueOf(i);
		cardView.setText(i);
	}
	
	public int getCardNum(){
		return cardNum;
	}
	
	public void calc(){
		coverView.calc(cardNum);
		coverView.setVisibility(VISIBLE);
	}
	
	/**
	 * 浮层占有空间只是不可视
	 */
	public void disappear(){
		coverView.setVisibility(INVISIBLE);
	}
	
	/**
	 * 为运算结果临时显示区设置拖拽监听
	 * @param listener
	 */
	public void setCoverOnDragListener(OnDragListener listener){
		coverView.setCoverOnDragListener(listener);
	}

}
