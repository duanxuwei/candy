package com.candy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

import com.candy.CandyApp;
import com.candy.R;

public class CoverView extends TableLayout {
	private CoverItemView split1 = null;
	private CoverItemView split2 = null;
	private CoverItemView split3 = null;
	private CoverItemView split4 = null;

	public CoverView(Context context) {
		super(context);
		init(context);
	}

	public CoverView(Context context,AttributeSet attrs) {
		super(context,attrs);
		init(context);
	}
	
	private void init(Context context){
		// Inflate the view from the layout resource.
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.cover, this, true);
		split1 = (CoverItemView)findViewById(R.id.split1);
		split2 = (CoverItemView)findViewById(R.id.split2);
		split3 = (CoverItemView)findViewById(R.id.split3);
		split4 = (CoverItemView)findViewById(R.id.split4);
	}
	
	public void calc(int m){
		int n = CandyApp.getInstance().getOrigCard().getCardNum();
		split1.setText(String.valueOf(n+m));
//		split1.setOperator("+");
		split2.setText(String.valueOf(n-m));
//		split2.setOperator("-");
		split3.setText(String.valueOf(n*m));
//		split3.setOperator("x");
		if(m == 0 || n/m != 1.0*n/m){
			split4.setVisibility(INVISIBLE);
		}else{
			split4.setText(String.valueOf(n/m));
			split4.setVisibility(VISIBLE);
//			split4.setOperator("/");
		}
		/*Log.e("com.candy", split1.getParent().getParent().getParent().toString());
		Log.e("com.candy", split1.getParent().getParent().getParent().getParent().getParent().toString());*/
	}
	
	public void setCoverOnDragListener(OnDragListener listener){
		split1.setOnDragListener(listener);
		split2.setOnDragListener(listener);
		split3.setOnDragListener(listener);
		split4.setOnDragListener(listener);
	}

}
