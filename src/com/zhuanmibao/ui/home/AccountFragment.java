/**
 * 
 */
package com.zhuanmibao.ui.home;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.MainActivity;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * @author cainli 2013年12月24日下午8:23:45
 * 
 */
public class AccountFragment extends BaseFragment implements OnClickListener {

	private HashMap<Integer, Class<? extends Fragment>> 
			fragments = new HashMap<Integer, Class<? extends Fragment>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.account, null);
		view.findViewById(R.id.tabButton1).setOnClickListener(this);
		fragments.put(R.id.tabButton1, PageFragment1.class);

		view.findViewById(R.id.tabButton2).setOnClickListener(this);
		fragments.put(R.id.tabButton2, PageFragment2.class);

		view.findViewById(R.id.tabButton3).setOnClickListener(this);
		fragments.put(R.id.tabButton3, PageFragment3.class);

		view.findViewById(R.id.tabButton4).setOnClickListener(this);
		fragments.put(R.id.tabButton4, PageFragment4.class);

		setFragment(R.id.tabButton1);
		return view;
	}

	@Override
	public String getTitle() {
		return "我的账户";
	}

	@Override
	public void onResume() {
		super.onResume();
		setTab(MainActivity.TAB_MONEY);
	}

	public static class PageFragment1 extends BaseFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.account_page1, container,
					false);
			return rootView;
		}

		@Override
		protected boolean canBack() {
			return true;
		}
	}

	public static class PageFragment2 extends BaseFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.account_page2, container,
					false);

			return rootView;
		}

		@Override
		protected boolean canBack() {
			return true;
		}
	}

	public static class PageFragment3 extends BaseFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.account_page3, container,
					false);

			return rootView;
		}

		@Override
		protected boolean canBack() {
			return true;
		}
	}

	public static class PageFragment4 extends BaseFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.account_page4, container,
					false);

			return rootView;
		}

		@Override
		protected boolean canBack() {
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		setFragment(v.getId());
	}

	/**
	 * @param i
	 */
	private void setFragment(int id) {
		Class<? extends Fragment> clazz = fragments.get(id);
		try {
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.tab_content, clazz.newInstance());
			transaction.commit();
		} catch (java.lang.InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
