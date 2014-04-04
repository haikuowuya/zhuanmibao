/**
 * 
 */
package com.zhuanmibao.ui;

import android.os.Bundle;

import com.zhuanmibao.Constants;
import com.zhuanmibao.server.biz.BizFacade;
import com.zhuanmibao.ui.base.BaseActivity;
import com.zhuanmibao.utils.WUtils;

/**
 * @author cainli  2013年12月24日下午7:33:22
 *
 */
public class SplashActivity extends BaseActivity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//判断是否需要登陆
		if(hasToken()){
			BizFacade.login(this);
		}else{
			WUtils.toLoginPage(this);
			finish();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.BaseActivity#update(int, java.lang.String, java.lang.Object)
	 */
	@Override
	public void update(int code, String service, Object body) {
		super.update(code, service, body);
		if(code == Constants.JsonConstants.RESULT_OK && service == Constants.BizType.LOGIN){
			WUtils.toMainPage(this);
		}else{
			WUtils.toLoginPage(this);
		}
		finish();
	}

	/**
	 * @return
	 */
	private boolean hasToken() {

		return WUtils.getToken() != null;
	}
}
