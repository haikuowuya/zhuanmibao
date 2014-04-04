/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsChangeNotify;
import net.youmi.android.offers.PointsManager;
import android.content.Context;
import android.widget.Toast;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class YoumiTask implements AdDownTaskInterface, PointsChangeNotify {

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		// 1 有米
		AdManager.getInstance(context).init("73057fb81878f2ba",
				"f2707618edb7c2a4", false);
		// 如果使用积分广告，请务必调用积分广告的初始化接口:
		OffersManager.getInstance(context).onAppLaunch();
		PointsManager.getInstance(context).registerNotify(this);
	}

	@Override
	public void destory(Context context) {
		PointsManager.getInstance(context).unRegisterNotify(this);
		OffersManager.getInstance(context).onAppExit();
	}

	@Override
	public void onPointBalanceChange(int arg0) {
		Toast.makeText(context, "ad callback " + arg0, Toast.LENGTH_SHORT)
				.show();
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.home.downtask.AdDownTaskInterface#onClick(android.content.Context)
	 */
	@Override
	public void onClick(Context context) {
		OffersManager.getInstance(context).showOffersWall();
	}

}
