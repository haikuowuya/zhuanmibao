/**
 * 
 */
package com.zhuanmibao.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli 2013年12月24日下午8:25:03
 * 
 */
public class MoreFragment extends BaseFragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.more, null);
		view.findViewById(R.id.user_info_area).setOnClickListener(this);;
		view.findViewById(R.id.about).setOnClickListener(this);
		view.findViewById(R.id.feedback).setOnClickListener(this);
		view.findViewById(R.id.QA).setOnClickListener(this);
		//test
		view.findViewById(R.id.test_btn).setOnClickListener(this);
		
		return view;
	}
	
	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.base.BaseFragment#canBack()
	 */
	@Override
	protected boolean canBack() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.base.BaseFragment#getTitle()
	 */
	@Override
	public String getTitle() {
		return "更多";
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user_info_area:
			openFragment(WConstants.ACCOUNT_INFO);
			break;
		case R.id.about:
			openFragment(WConstants.ABOUT);
			break;
		case R.id.feedback:
			openFragment(WConstants.FEEDBACK);
			break;
		case R.id.QA:
			openFragment(WConstants.QA);
			break;
		case R.id.test_btn:
			openFragment(WConstants.TEST);
			break;

		default:
			break;
		}
	}
}
