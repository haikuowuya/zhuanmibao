package com.zhuanmibao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.zhuanmibao.pojo.DeviceInfo;

/**
 * 
 * @author cainli 2013-12-19下午9:50:57
 */
public class Utils {

	public static boolean isNetworkAvailable(Context context) {
		if (context == null) {
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	public static DeviceInfo getPhoneNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		DeviceInfo deviceInfo = new DeviceInfo();
		deviceInfo.deviceid = tm.getDeviceId();
		deviceInfo.tel = tm.getLine1Number();
		deviceInfo.imei = tm.getSimSerialNumber();
		deviceInfo.imsi = tm.getSubscriberId();
		deviceInfo.netType = tm.getNetworkType();
		return deviceInfo;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

}
