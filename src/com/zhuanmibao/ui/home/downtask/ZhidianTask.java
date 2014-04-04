/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;

import com.adzhidian.ui.AdView;
import com.adzhidian.ui.ZhidianManager;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class ZhidianTask implements AdDownTaskInterface{

	private Context context;

	@Override
	public void init(Context context) {
		this.context = context;
		//初始化
				ZhidianManager.zhiInit("457DC521152C05F6CAB16C14F9FDB5B5", "zhidian");
	}
	

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		//启动应用墙
		AdView.getAdInstance(context).startrecommendWall(context);
	}


}
