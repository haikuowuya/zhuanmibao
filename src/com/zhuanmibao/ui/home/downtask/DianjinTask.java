/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import android.widget.Toast;

import com.bodong.dianjinweb.DianJinPlatform;
import com.bodong.dianjinweb.listener.AppActiveListener;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class DianjinTask implements AdDownTaskInterface,AppActiveListener{

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		DianJinPlatform.initialize(context, 1010,
				"eb3de875023ff1db2077c13888a637a6", 1001);
		DianJinPlatform.setAppActivedListener(this);;
	}
	

	@Override
	public void destory(Context context) {
		DianJinPlatform.destory(context);
	}


	@Override
	public void onClick(Context context) {
		DianJinPlatform.showOfferWall(context);
	}


	@Override
	public void onSuccess(long reward) {
		Toast.makeText(context, "奖励金额:" + reward, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onError(int errorCode, String errorMessage) {
		switch (errorCode) {
		case DianJinPlatform.DIANJIN_NET_ERROR: // 网络不稳定
			Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
			break;
		case DianJinPlatform.DIANJIN_DUPLICATE_ACTIVATION: // 重复激活
			Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
			break;
		case DianJinPlatform.DIANJIN_ADVERTSING_EXPIRED: // 应用已下架
			Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
			break;
		case DianJinPlatform.DIANJIN_ACTIVATION_FAILURE: // 激活失败
			Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

}
