/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import android.widget.Toast;

import com.qiang.escore.scorewall.ScoreWallSDK;
import com.qiang.escore.sdk.YjfSDK;
import com.qiang.escore.sdk.widget.UpdateScordNotifier;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class EScoreTask implements AdDownTaskInterface,UpdateScordNotifier{

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		YjfSDK.getInstance(context, null).initInstance("208","EMRXWIV4DUNHTEEWO9M7IH7ZW1PVI4N9GE","21","sdk 3.1.7");
	}

	@Override
	public void destory(Context context) {
		YjfSDK.getInstance(context, null).recordAppClose();
	}


	@Override
	public void onClick(Context context) {
		ScoreWallSDK.getInstance(context).showAdlist(this);
	}

	@Override
	public void updateScoreFailed(int arg0, int arg1, String arg2) {
		Toast.makeText(context, "ad callback failed " + arg0,
				Toast.LENGTH_SHORT).show();
	}

	
	@Override
	public void updateScoreSuccess(int arg0, int arg1, int arg2, String arg3) {
		Toast.makeText(context, "ad callback " + arg0, Toast.LENGTH_SHORT)
		.show();
	}

}
