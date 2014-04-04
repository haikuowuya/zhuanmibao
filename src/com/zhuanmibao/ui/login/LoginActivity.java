/**
 * 
 */
package com.zhuanmibao.ui.login;

import android.os.Bundle;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.TitleBaseActivity;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli 2013年12月22日下午4:50:22
 * 
 */
public class LoginActivity extends TitleBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("赚米宝");
		setFragment(WConstants.GUID_LOGIN,true);
	}

	
	@Override
	protected void setContentView() {
		setContentView(R.layout.login);
	}
}
