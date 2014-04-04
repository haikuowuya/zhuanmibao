/**
 * 
 */
package com.zhuanmibao.ui.base;

import android.os.Bundle;

import com.zhuanmibao.ui.ExchangeFragment;
import com.zhuanmibao.ui.HomeFragment;
import com.zhuanmibao.ui.LuckFragment;
import com.zhuanmibao.ui.MoreFragment;
import com.zhuanmibao.ui.ShareFragment;
import com.zhuanmibao.ui.common.TestFragment;
import com.zhuanmibao.ui.exchange.ExchangeDetailFragment;
import com.zhuanmibao.ui.game.BigWheelFragment;
import com.zhuanmibao.ui.game.ShakeFragment;
import com.zhuanmibao.ui.game.SlotMachineFragment;
import com.zhuanmibao.ui.home.AccountFragment;
import com.zhuanmibao.ui.home.ActivteTaskFragment;
import com.zhuanmibao.ui.home.AdDownTaskFragment;
import com.zhuanmibao.ui.home.CompleteInfoFragment;
import com.zhuanmibao.ui.home.NewerTaskFragment;
import com.zhuanmibao.ui.home.PropsFragment;
import com.zhuanmibao.ui.home.TobeGeniusFragment;
import com.zhuanmibao.ui.login.LoginFragment;
import com.zhuanmibao.ui.login.LoginGuideFragment;
import com.zhuanmibao.ui.login.RegisterFragment;
import com.zhuanmibao.ui.more.AboutFragment;
import com.zhuanmibao.ui.more.AccountManagerFragment;
import com.zhuanmibao.ui.more.FeedbackFragment;
import com.zhuanmibao.ui.more.QAFragment;

/**
 * @author cainli  2013年12月24日下午8:19:24
 *
 */
public class FragmentFactory implements WConstants {
	/**
	 * @param checkedId
	 * @return
	 */
	public static BaseFragment getInstanceByIndex(int index) {
		
		return getInstanceByIndex(index,null);
	}

	/**
	 * @param checkedId
	 * @param args
	 * @return
	 */
	public static BaseFragment getInstanceByIndex(int index, Bundle args) {
		BaseFragment fragment = null;
		switch (index) {
		case MONEY:
			fragment = new HomeFragment();
			break;
		case EXCHANGE:
			fragment = new ExchangeFragment();
			break;
		case LUCK:
			fragment = new LuckFragment();
			break;
		case MORE:
			fragment = new MoreFragment();
			break;
		case TASK_NEWER:
			fragment = new NewerTaskFragment();
			break;
		case TASK_DOWN:
			fragment = new AdDownTaskFragment();
			break;
		case TASK_ACTIVE:
			fragment = new ActivteTaskFragment();
			break;
		case ACCOUNT_INFO:
			fragment = new AccountFragment();
			break;
		case ACCOUNT_MGR:
			fragment = new AccountManagerFragment();
			break;
		case NEWER_TASK_1:
			fragment = new CompleteInfoFragment();
			break;
		case NEWER_TASK_2:
			fragment = new ShareFragment();
			break;
		case LUCK_BIGWHEEL:
			fragment = new BigWheelFragment();
			break;
		case LUCK_SHAKE:
			fragment = new ShakeFragment();
			break;
		case LUCK_SLOTMACHINE:
			fragment = new SlotMachineFragment();
			break;
		case TASK_PROPS:
			fragment = new PropsFragment();
			break;
		case SHARE_TASK:
			fragment = new ShareFragment();
			break;
		case TOBE_GENIUS:
			fragment = new TobeGeniusFragment();
			break;
		case EXCHANGE_DETAIL:
			fragment = new ExchangeDetailFragment(args);
			break;
		case ABOUT:
			fragment = new AboutFragment();
			break;
		case FEEDBACK:
			fragment = new FeedbackFragment();
			break;
		case QA:
			fragment = new QAFragment();
			break;
		case LOGIN:
			fragment = new LoginFragment();
			break;
		case GUID_LOGIN:
			fragment = new LoginGuideFragment();
			break;
		case REGISTER:
			fragment = new RegisterFragment();
			break;
		case TEST:
			fragment = new TestFragment();
			break;
		default:
			break;
		}
		return fragment;
	}

}
