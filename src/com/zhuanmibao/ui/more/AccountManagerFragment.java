/**
 * 
 */
package com.zhuanmibao.ui.more;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;


/**
 * @author cainli  2013年12月24日下午8:23:45
 *
 */
@SuppressLint("NewApi")
public class AccountManagerFragment extends BaseFragment{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.account_manager, null);
		return view;
	}
	


	@Override
	public String getTitle() {
		return "账号管理";
	}

}
