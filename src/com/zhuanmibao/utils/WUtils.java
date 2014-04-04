/**
 * 
 */
package com.zhuanmibao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.zhuanmibao.WApplication;
import com.zhuanmibao.ui.MainActivity;
import com.zhuanmibao.ui.login.LoginActivity;

/**
 * @author cainli 2013年12月24日下午8:44:03
 * 
 */
public class WUtils {


	public static void toMainPage(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 
	 */
	public static void toLoginPage(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}

	/**
	 * @return
	 */
	public static String getToken() {
		SharedPreferences userInfo = WApplication.context.getSharedPreferences("userInfo", 0);
		return userInfo.getString("token", null);
	}

}
