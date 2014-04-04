/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import android.content.Context;
import cn.kugao.escore.scorewall.KuGaoScoreWallSDK;
import cn.kugao.escore.sdk.KuGaoSDK;
import cn.kugao.escore.sdk.widget.UpdateNotifier;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class KugaoTask implements AdDownTaskInterface, UpdateNotifier {


	@Override
	public void init(Context context) {
		// 初始化,当Activity第一次创建时调用，且只调用一次.
		KuGaoSDK.getInstance(context, null).initInstance("50186",
				"5OWQ1KKH6HGX9U97EFEANLHLZDK8M3C82F", "59436", "adview");
	}

	@Override
	public void destory(Context context) {
		// 关闭SDK应用
		// 当开发者应用推出程序时,调用改接口释放内存
		KuGaoSDK.getInstance(context, null).recordAppClose();
	}

	@Override
	public void onClick(Context context) {
		KuGaoScoreWallSDK.getInstance(context,
				this).showAdlist();// 回调接口中返回激活信息
	}


	@Override
	public void updateFailed(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * 方法: updateScoreSuccess 描述: 返回成功的回调. TODO 参数: @param
	 * type为操作标识，0：初始化操作，1：查询积分操作， 2：消费积分操作，3：增加积分操作,
	 * 
	 * @param currentScore
	 *            当前的积分 参数: @param changeScore 变更的积分 参数: @param unit:虚拟货币单位 返回:
	 *            void
	 */
	@Override
	public void updateSuccess(int arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}


}
