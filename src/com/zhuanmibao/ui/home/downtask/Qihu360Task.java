/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;

import com.qihoo360.union.sdk.UnionManager;
import com.zhuanmibao.utils.WLog;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class Qihu360Task implements AdDownTaskInterface {

	private static final String TAG = Qihu360Task.class.getSimpleName();
	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
	}

	@Override
	public void destory(Context context) {
	}

	@Override
	public void onClick(Context context) {
		try {
			UnionManager.getInstance(context).startAppList();
		} catch (SecurityException e) {
			WLog.d(TAG, "e :" + e);
		}

	}

}
