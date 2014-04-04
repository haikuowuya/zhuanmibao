/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import net.miidiwall.SDK.AdWall;
import net.miidiwall.SDK.IAdWallShowAppsNotifier;
import android.content.Context;
import android.widget.Toast;

import com.wiyun.offer.WiOffer;
import com.wiyun.offer.WiOfferClient;
import com.zhuanmibao.R;

/**
 * @author cainli 2014年2月19日下午8:58:43
 * 
 */
public class WiTask implements AdDownTaskInterface{

	private static final String APP_KEY = "4e861427e3253720";
	private static final String SECRET_KEY = "dqpSaVecbtUBtruKxFyYNm2DBnU4LgsZ";

	@Override
	public void init(final Context context) {
		WiOffer.init(context, APP_KEY, SECRET_KEY);
		// 您可以为offer界面设置一个自定义的标题, 缺省的标题是"赚取XX", XX是您在后台设置的虚拟货币名称
//		WiOffer.setTitle(getString(R.string.title));
		WiOffer.addWiOfferClient(new WiOfferClient() {
			@Override
			public void wyOfferCompleted(String offerId, String offerName, int bonus) {
				Toast.makeText(context, String.format("you have earned %d coins!", bonus), Toast.LENGTH_SHORT).show();
				// 刷新用户的金币数量, 仅当您选择了"微云托管"方式时需要调用
				WiOffer.getCoins();
			}
			
			@Override
			public void wyCoinsGot(int count) {
				Toast.makeText(context, "wyCoinsGot: " + count, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void wyGetCoinsFailed(String error) {
			}
			
			@Override
			public void wyAddCoinsFailed(String error) {
				Toast.makeText(context, "add coin error: " + error, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void wyCoinsAdded(int count) {
				Toast.makeText(context, "wyCoinsAdded " + count, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void wyCoinsUsed(int count) {
			}
			
			@Override
			public void wyUseCoinsFailed(String error) {
			}
		});
		// 您可以设置任何额外信息，这些信息会被回传给服务器
		WiOffer.setExtraInfo("test_key", "test_value");
		// 获得当前用户的金币数量，如果您使用WiGame集成方式，则不需要调用这个方法
		// 如果您使用自己的服务器保存货币数量，则请调用您自己的方法
		// 如果您在本地保存用户的货币数量，则也不需要调用此方法
		// demo使用的是微云托管模式，即用户货币存放在微云服务器端，因此可以调用这个方法获得
		WiOffer.getCoins();
	}

	@Override
	public void destory(Context context) {
	}


	@Override
	public void onClick(Context context) {
		WiOffer.showOffers();
	}

}
