/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import android.widget.Toast;
import cn.waps.AppConnect;
import cn.waps.UpdatePointsNotifier;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class WapsTask implements AdDownTaskInterface, UpdatePointsNotifier {

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		// 2 万普
		// 初始化统计器，并通过代码设置APP_ID, APP_PID
		AppConnect.getInstance("09f277ca386ee99cb4c910e09f562112", "default",
				context);
	}

	@Override
	public void destory(Context context) {
		AppConnect.getInstance(context).close();
	}

	@Override
	public void getUpdatePoints(String arg0, int arg1) {
		Toast.makeText(context, "ad callback " + arg0, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void getUpdatePointsFailed(String arg0) {
		Toast.makeText(context, "ad callback failed " + arg0,
				Toast.LENGTH_SHORT).show();
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.home.downtask.AdDownTaskInterface#onClick()
	 */
	@Override
	public void onClick(Context context) {
		AppConnect.getInstance(context).showOffers(context);
	}

}
