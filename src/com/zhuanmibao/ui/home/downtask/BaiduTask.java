/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import com.baidu.mobads.appoffers.OffersManager;
import com.baidu.mobads.appoffers.PointsChangeListener;

import android.content.Context;
import android.widget.Toast;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class BaiduTask implements AdDownTaskInterface,PointsChangeListener{

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		OffersManager.setPointsChangeListener(this);
	}

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		OffersManager.showOffers(context);
	}

	@Override
	public void onPointsChanged(int arg0) {
		Toast.makeText(context, "ad callback failed " + arg0,
				Toast.LENGTH_SHORT).show();
	}

}
