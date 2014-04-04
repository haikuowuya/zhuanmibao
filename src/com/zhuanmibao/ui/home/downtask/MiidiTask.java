/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import net.miidiwall.SDK.AdWall;
import net.miidiwall.SDK.IAdWallShowAppsNotifier;
import android.content.Context;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class MiidiTask implements AdDownTaskInterface,IAdWallShowAppsNotifier{


	@Override
	public void init(Context context) {
		AdWall.init(context, "6", "6666666666666666");
		
	}

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		AdWall.showAppOffers(this);
	}

	@Override
	public void onDismissApps() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onShowApps() {
		// TODO Auto-generated method stub
		
	}


}
