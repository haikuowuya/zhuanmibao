
package com.zhuanmibao.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.TitleBaseActivity;
import com.zhuanmibao.ui.base.WConstants;
import com.zhuanmibao.ui.home.downtask.AdChannelManager;

/**
 * 
 * @author cainli  2013年12月24日下午8:48:25
 *
 */
public class MainActivity extends TitleBaseActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	public static final int TAB_MONEY = 0;
	public static final int TAB_EXCHANGE = 1;
	public static final int TAB_LUCK = 2;
	public static final int TAB_MORE = 3;
	private boolean sureQuit = false;
	
	private RadioGroup radioGroup;
	private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int fragmentId = -1;
			switch (checkedId) {
			case R.id.money:
				fragmentId = WConstants.MONEY;
				break;
			case R.id.exchage:
				fragmentId = WConstants.EXCHANGE;
				break;
			case R.id.luck:
				fragmentId = WConstants.LUCK;
				break;
			case R.id.more:
				fragmentId = WConstants.MORE;
				break;

			default:
				break;
			}
			if(fragmentId != -1){
				setFragment(fragmentId,true);
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		radioGroup = (RadioGroup)findViewById(R.id.rg_tab);
		radioGroup.setOnCheckedChangeListener(listener);
		setFragment(WConstants.MONEY,true);
//		AdChannelManager.init(this);
	}
	

	@Override
	protected void onDestroy() {
//		AdChannelManager.destory(this);
		super.onDestroy();
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//两次back 退出逻辑
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(fragmentManager.getBackStackEntryCount() == 0){
				if(sureQuit){
					 finish();
					return true;
				}else{
					sureQuit = !sureQuit;
					Toast.makeText(this, "再back一次退出!", Toast.LENGTH_SHORT).show();
					return true;
				}
			}else{
				sureQuit = false;
				return super.onKeyDown(keyCode, event);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void setFragment(int checkedId) {
		super.setFragment(checkedId);
		setTab(checkedId);
	};
	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.base.BaseActivity#setFragment(int, boolean, android.os.Bundle)
	 */
	@Override
	public void setFragment(int checkedId, boolean clearn, Bundle args) {
		super.setFragment(checkedId, clearn, args);
		sureQuit = false;
	}
	
	public void setTab(int index){
		if(index%1000 == 0){
			if(radioGroup!=null){
				index = index/1000;
				RadioButton radioBtn = (RadioButton) radioGroup.getChildAt(index);
				radioGroup.setOnCheckedChangeListener(null);
				radioBtn.setChecked(true);
				radioGroup.setOnCheckedChangeListener(listener);
			}
		}
	}
	
	@Override
	protected void setContentView(){
		setContentView(R.layout.main);
	}

}
