package com.candy.activity;

import java.util.StringTokenizer;

import android.R.color;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

import com.candy.CandyApp;
import com.candy.R;
import com.candy.db.DAO;
import com.candy.view.BlockView;
import com.candy.view.CoverItemView;

public class GameActivity extends Activity implements
		GestureDetector.OnGestureListener,OnClickListener {
	private GestureDetector detector = null;
	BlockView card1 = null;
	BlockView card2 = null;
	BlockView card3 = null;
	BlockView card4 = null;
	private long startTime = 0;
	private OnDragListener textOnDragListener = new TextDragListener();
	
	private OnLongClickListener mLongClick = new OnLongClickListener() {
		@Override
		public boolean onLongClick(View view) {
			
			ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
			String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
			ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			
			view.startDrag( data, //data to be dragged
			       shadowBuilder, //drag shadow
			       view, //local data about the drag and drop operation
			       0   //no needed flags
			     );
//			view.setVisibility(View.INVISIBLE);
			
			//标记当前被拖动的view
			CandyApp.getInstance().setOrigCard((BlockView)view);

			if(card1 != view){
				card1.calc();
			}
			if(card2 != view){
				card2.calc();
			}
			if(card3 != view){
				card3.calc();
			}
			if(card4 != view){
				card4.calc();
			}
			
			return true;
		}
	};
	private long firstime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		//标记游戏开始时间戳
		if(startTime == 0){
			startTime = System.currentTimeMillis();
		}
		
		card1 = (BlockView) findViewById(R.id.card1);
		card2 = (BlockView) findViewById(R.id.card2);
		card3 = (BlockView) findViewById(R.id.card3);
		card4 = (BlockView) findViewById(R.id.card4);
		
		//为四张牌赋值
		setQuestion();
		
		//为四张牌设置长按监听
		setListener();
		
		//为运算结果临时显示区设置拖拽监听
		setCoverOnDragListener();
		
		//设置reset按钮
		findViewById(R.id.bottom_reset).setOnClickListener(this);
		
		detector = new GestureDetector(this, this);
		getResources().getStringArray(R.array.question);
	}

	private void setQuestion() {
		StringTokenizer st = new StringTokenizer(CandyApp.getInstance().currentQuestion(), "_");
		card1.setText(st.nextToken());
		card2.setText(st.nextToken());
		card3.setText(st.nextToken());
		card4.setText(st.nextToken());
	}

	/**
	 * 为四张牌设置长按监听
	 */
	private void setListener() {
		card1.setOnLongClickListener(mLongClick);
		card2.setOnLongClickListener(mLongClick);
		card3.setOnLongClickListener(mLongClick);
		card4.setOnLongClickListener(mLongClick);
	}

	/**
	 * 为运算结果临时显示区设置拖拽监听
	 */
	private void setCoverOnDragListener() {
		card1.setCoverOnDragListener(textOnDragListener);
		card2.setCoverOnDragListener(textOnDragListener);
		card3.setCoverOnDragListener(textOnDragListener);
		card4.setCoverOnDragListener(textOnDragListener);
	}

	/**
	 * 消除计算浮层
	 */
	private void disappear(){
		card1.disappear();
		card2.disappear();
		card3.disappear();
		card4.disappear();
	}

	@Override
	public void finish() {
		super.finish();
		System.exit(0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int flip_distance = CandyApp.getInstance().getFlipDistance();
		if (Math.abs(e1.getX() - e2.getX()) < flip_distance) {
			return false;
		}
		if ((e2.getX() - e1.getX()) > flip_distance) {
			CandyApp.getInstance().prePage();
		} else {
			CandyApp.getInstance().nextPage();
		}
		Intent intent = new Intent(this, GameActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long secondtime = System.currentTimeMillis();
			if (secondtime - firstime > 3000) {
				Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
				firstime = System.currentTimeMillis();
				return true;
			} else {
				finish();
			}
		}
		return false;
	}
	
	
	class TextDragListener implements OnDragListener {

	     @Override
	     public boolean onDrag(View v, DragEvent event) {
	    	 
	    	 BlockView orig = CandyApp.getInstance().getOrigCard();
	    	 BlockView currentBlock = (BlockView)v.getParent().getParent().getParent().getParent().getParent();
	    	 
	    	 if(orig == null || orig == currentBlock){
	    		 return false;
	    	 }
	    
	         // Handles each of the expected events
	         switch (event.getAction()) {
	          
	         //signal for the start of a drag and drop operation.
	         case DragEvent.ACTION_DRAG_STARTED:
	             // do nothing
	             break;
	              
	         //the drag point has entered the bounding box of the View
	         case DragEvent.ACTION_DRAG_ENTERED:
	        	 ((CoverItemView)v).setTextColor(getResources().getColor(color.holo_blue_light));
//	        	 v.setBackground(getResources().getDrawable(R.drawable.bg2));
	        	 v.setBackgroundResource(R.drawable.bg2);
//	        	 v.setBackgroundColor(getResources().getColor(color.background_light));
	             break;
	              
	         //the user has moved the drag shadow outside the bounding box of the View
	         case DragEvent.ACTION_DRAG_EXITED:
	        	 ((CoverItemView)v).setTextColor(getResources().getColor(color.holo_green_dark));
	        	 v.setBackgroundResource(R.drawable.bg1);
	             break;
	              
	         //drag shadow has been released,the drag point is within the bounding box of the View
	         case DragEvent.ACTION_DROP:
	        	 ((CoverItemView)v).setTextColor(getResources().getColor(color.holo_green_dark));//恢复浮层字体颜色
	        	 currentBlock.setText(((CoverItemView)v).getText().toString());
	        	 v.setBackgroundResource(R.drawable.bg1);//恢复浮层背景
	        	 orig.setVisibility(View.INVISIBLE);
	        	 disappear();
	        	 win();
	             break;
	                
	         //the drag and drop operation has concluded.
	         case DragEvent.ACTION_DRAG_ENDED:
	        	 disappear();
	             //v.setBackground(normalShape);   //go back to normal shape
	          
	         default:
	             break;
	         }
	         return true;
	     }
	    }


	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(this, GameActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	private void win(){
		if(card1.getVisibility() + card2.getVisibility() + card3.getVisibility() + card4.getVisibility()  == 12){
			if((card1.getVisibility() == View.VISIBLE && card1.getCardNum() == 24) 
					|| (card2.getVisibility() == View.VISIBLE && card2.getCardNum() == 24)
					|| (card3.getVisibility() == View.VISIBLE && card3.getCardNum() == 24)
					|| (card4.getVisibility() == View.VISIBLE && card4.getCardNum() == 24)){
				new DAO().saveLevel();
				double totalTime = (System.currentTimeMillis() - startTime)/1000.0;
				Toast.makeText(this, "解题成功！用时:" + String.format("%.2f", totalTime)+"秒", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
