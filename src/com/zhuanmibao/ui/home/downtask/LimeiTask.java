/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import android.content.Intent;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class LimeiTask implements AdDownTaskInterface{

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
		Intent intent = new Intent(context, LimeiAdwallActivity.class);
		context.startActivity(intent);;
	}

}
