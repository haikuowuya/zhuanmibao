/**
 * 
 */
package com.zhuanmibao.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * @author cainli  2014年2月11日下午2:18:59
 *
 */
public class CompleteAccountInfoFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.complete_account_info, null);

		return view;
	}


	@Override
	public String getTitle() {
		return "注册";
	}
}
