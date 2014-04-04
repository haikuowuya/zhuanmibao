/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;

import com.winad.android.offers.AdManager;
import com.winad.android.offers.parameter.ShowEntranceListener;
import com.winad.android.offers.parameter.SpendScoreListener;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class WinadsTask implements AdDownTaskInterface,ShowEntranceListener, SpendScoreListener{

	private Context context;
	// 设置Point单位，默认为“积分”，可设置为“金币”等
	private String unit = "积分";

	@Override
	public void init(Context context) {
		this.context = context;
		// 初始化积分墙sdk
		AdManager.init(context);
		// 设置积分墙显示单位
		AdManager.setPointUnit(context, unit);
		// 设置积分墙开关监听
		AdManager.setShowEntranceListener(context, this);
	}

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		AdManager
		.showAdOffers(context);
	}

	@Override
	public void ConsumptionLose(String arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ConsumptionSuccess(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void EntranceHide() {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public void Entrancedisplay() {
		// TODO Auto-generated method stub
		
	}

}
