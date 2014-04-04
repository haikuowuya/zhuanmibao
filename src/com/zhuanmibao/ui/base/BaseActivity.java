/**
 * 
 */
package com.zhuanmibao.ui.base;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.zhuanmibao.R;
import com.zhuanmibao.push.Utils;
import com.zhuanmibao.server.Observer;

/**
 * @author cainli  2013年12月22日下午4:50:08
 *
 */
public class BaseActivity extends FragmentActivity implements Observer{

	private static final String TAG = BaseActivity.class.getSimpleName();
	protected FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
		// 这里把apikey存放于manifest文件中，只是一种存放方式，
		// 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
		// "api_key")
		// 通过share preference实现的绑定标志开关，如果已经成功绑定，就取消这次绑定
		if (!Utils.hasBind(getApplicationContext())) {
			Log.d(TAG, "before start work at "+ Calendar.getInstance().getTimeInMillis());
			PushManager.startWork(getApplicationContext(),
					PushConstants.LOGIN_TYPE_API_KEY,
					Utils.getMetaValue(BaseActivity.this, "api_key"));
			Log.d(TAG, "after start work at "+ Calendar.getInstance().getTimeInMillis());
			// Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
			PushManager.enableLbs(getApplicationContext());
			Log.d(TAG, "after enableLbs at "+ Calendar.getInstance().getTimeInMillis());
		}

		fragmentManager = getSupportFragmentManager();
	}
	/**
	 * @param checkedId
	 */
	public void setFragment(int checkedId) {
		setFragment(checkedId,false);
	}
	
	/**
	 * @param id
	 * @param data
	 */
	public void setFragment(int id, Bundle data) {
		setFragment(id,false,data);
	}
	/**
	 * @param checkedId
	 */
	public void setFragment(int checkedId,boolean clearn) {
		setFragment(checkedId, clearn, null);
	}
	/**
	 * @param checkedId
	 */
	public void setFragment(int checkedId,boolean clearn,Bundle args) {
		FragmentTransaction transaction = fragmentManager
				.beginTransaction();
		if(transaction  == null){
			Toast.makeText(this," transation is null", Toast.LENGTH_SHORT).show();
			return;
		}
		BaseFragment fragment = FragmentFactory
				.getInstanceByIndex(checkedId,args);
		if(fragment == null){
			return;
		}
		if(fragment.getTitle()!=null){
			setTitle(fragment.getTitle());
		}
		fragment.setArguments(args);
		transaction.replace(R.id.content, fragment);
		if(clearn){
			fragmentManager.popBackStack(null, 
					FragmentManager.POP_BACK_STACK_INCLUSIVE);  
		}else{
			transaction.addToBackStack(null);
		}
		//改变buttomTab
		if(fragment.isBottomTabFragement()){
			
		}
		transaction.commit();
	}
	
	public void showDailog(DialogFragment dialog,int dialogId){
		FragmentTransaction transaction = fragmentManager
				.beginTransaction();
		if(transaction  == null){
			Toast.makeText(this," transation is null", Toast.LENGTH_SHORT).show();
			return;
		}
		transaction.add(dialogId, dialog);
		transaction.commit();
	}
	
	public void back(){
		fragmentManager.popBackStack();
	}

	@Override
	public void update(int code, String service, Object body) {
		
	}
	
	@Override
	public void error(int code, String service, String msg) {
		
	}
	

}
