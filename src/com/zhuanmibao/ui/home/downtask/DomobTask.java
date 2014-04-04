/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import android.widget.Toast;
import cn.domob.data.OErrorInfo;
import cn.domob.data.OManager;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class DomobTask implements AdDownTaskInterface,OManager.AddWallListener{

	private Context context;
	private OManager mDomobOfferWallManager;
	@Override
	public void init(Context context) {
		this.context = context;
		mDomobOfferWallManager = new OManager(context,"96ZJ2b8QzehB3wTAwQ");
		mDomobOfferWallManager.setAddWallListener(this);
	}
	

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		mDomobOfferWallManager.loadOfferWall();
	}


	@Override
	public void onAddWallFailed(
			OErrorInfo mDomobOfferWallErrorInfo) {
		Toast.makeText(context, mDomobOfferWallErrorInfo.toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAddWallClose() {
		//此处可以设置为横屏...
	}

	@Override
	public void onAddWallSucess() {

	}

}
