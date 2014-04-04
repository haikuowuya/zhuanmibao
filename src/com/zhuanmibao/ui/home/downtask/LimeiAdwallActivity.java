/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import cn.immob.sdk.ImmobView;
import cn.immob.sdk.LMAdListener;

import com.zhuanmibao.R;

/**
 * @author cainli 2014年2月20日下午4:27:59
 *TODO 改成fragement
 */
public class LimeiAdwallActivity extends Activity implements LMAdListener {
	private String tag = "AdwallActivity";
	private ImmobView view = null;
	private LinearLayout layout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.limei);
		layout = (LinearLayout) findViewById(R.id.layout);
		Context context = this;
		view = new ImmobView(context, "0f470c7647f9a7b0550d562638fcb577");
		// 设置布局文件
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		view.setLayoutParams(params);
		// 增加webview
		view.setAdListener(this);
		layout.addView(view);
		layout.setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (view != null) {
			view.destroy();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 调用此方法，后台广告停止请求广告
		view.onPause();
	}


	@Override
	protected void onResume() {
		super.onResume();
		// 调用此方法，后台广告恢复请求广告
		view.onResume();
	}


	/**
	 * This method will be called when a single ad request performed
	 * successfully.
	 */
	@Override
	public void onAdReceived(ImmobView arg0) {
		if (view != null) {
			view.display();
		}
	}

	/**
	 * This method will be called when some error
	 * 
	 * @param eCode
	 */
	@Override
	public void onDismissScreen(ImmobView arg0) {

	}

	/**
	 * Called when an ad is clicked and about to return to the application.</br>
	 * 当（全屏）广告被点击或者被关闭，将要返回返回主程序见面时被调用。
	 * 
	 */
	@Override
	public void onFailedToReceiveAd(ImmobView arg0, int arg1) {

	}

	/**
	 * Called when an ad is clicked and going to start a new Activity that will
	 * leave the application</br> 当广告调用一个新的activity并且会导致离开目前运行程序时被调用。如：调用本地地图程序。
	 * 
	 */
	@Override
	public void onLeaveApplication(ImmobView arg0) {

	}

	/**
	 * Called when an Activity is created in front of the app.</br>
	 * 当广告activity被撞见并且显示在覆盖在屏幕上面时调用本方法。
	 */
	@Override
	public void onPresentScreen(ImmobView arg0) {

	}
}
