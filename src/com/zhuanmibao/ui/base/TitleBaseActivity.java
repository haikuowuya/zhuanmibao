/**
 * 
 */
package com.zhuanmibao.ui.base;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.server.Observer;

/**
 * @author cainli  2013年12月22日下午4:50:08
 *
 */
public abstract class TitleBaseActivity extends BaseActivity implements Observer{

	private TextView leftTextView,centerTextView,rightTextView;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	    setContentView();
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);  
	    leftTextView = (TextView) findViewById(R.id.left_text);
	    centerTextView = (TextView) findViewById(R.id.center_text);
	    rightTextView = (TextView) findViewById(R.id.right_text);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		// // TODO Auto-generated method stub
		// super.setTitle(title);
		if (centerTextView != null ) {
			centerTextView.setText(title);
		}

	}
	public void setLeftView(String str,OnClickListener click){
		if(leftTextView!=null){
			leftTextView.setText(str);
			leftTextView.setOnClickListener(click);
		}
	}
	

	public void setLeftView() {
		if(leftTextView!=null){
			leftTextView.setVisibility(View.VISIBLE);
			leftTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					back();
				}
			});
		}
	}
	
	public void setLeftViewVisiable(int visibility){
		if(leftTextView!=null){
			leftTextView.setVisibility(visibility);
		}
	}
	public void setRightViewVisiable(int visibility){
		if(rightTextView!=null){	
			rightTextView.setVisibility(visibility);
		}
	}
	public void setTitleViewVisiable(int visibility){
		if(centerTextView!=null){	
			centerTextView.setVisibility(visibility);
		}
	}
	
	
	public int getTitleHeight(){
		return (int) getResources().getDimension(R.dimen.title_bar_height);
	}
	
	public int getBottomBarHeight(){
		return (int) getResources().getDimension(R.dimen.buttom_bar_height);
	}
	
	protected abstract void setContentView();
	/* (non-Javadoc)
	 * @see com.zhuanmibao.server.Observer#update(int, java.lang.String, java.lang.Object)
	 */
	@Override
	public void update(int code, String service, Object body) {
		
	}


}
