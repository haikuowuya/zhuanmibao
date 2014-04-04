/**
 * 
 */
package com.zhuanmibao.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zhuanmibao.ui.MainActivity;

/**
 * @author cainli  2013年12月24日下午9:34:03
 *
 */
public abstract class BaseFragment extends Fragment {
	
	public void openFragment(int id, Bundle data){
		if(getActivity() instanceof BaseActivity){
			((BaseActivity)getActivity()).setFragment(id,data);
		}
	}
	
	public void openFragment(int id){
		if(getActivity() instanceof BaseActivity){
			((BaseActivity)getActivity()).setFragment(id);
		}
	}
	
	public void setTab(int index){
		if(getActivity() instanceof MainActivity){
			((MainActivity)getActivity()).setTab(index);
		}
	}
	
	//显示返回
	protected boolean canBack(){
		return true;
	}

	public  String getTitle(){
		return null;
	};
	
	public int getTitleHeight(){
		if(getActivity() instanceof MainActivity){
			return ((MainActivity)getActivity()).getTitleHeight();
		}
		return 0;
	}
	
	public int getBottomBarHeight(){
		if(getActivity() instanceof MainActivity){
			return ((MainActivity)getActivity()).getBottomBarHeight();
		}
		return 0;
	}
	
	public boolean isBottomTabFragement(){
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		if(getActivity() instanceof TitleBaseActivity){
			TitleBaseActivity activity = (TitleBaseActivity) getActivity();
			if(getTitle()!=null){
				activity.setTitle(getTitle());
			}
			if(canBack()){
				activity.setLeftView();
			}else{
				activity.setLeftViewVisiable(View.INVISIBLE);
			}
		}
	}


}
