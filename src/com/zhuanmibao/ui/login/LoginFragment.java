/**
 * 
 */
package com.zhuanmibao.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhuanmibao.Constants;
import com.zhuanmibao.R;
import com.zhuanmibao.server.Observer;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;
import com.zhuanmibao.utils.WUtils;

/**
 * @author cainli  2014年2月25日上午12:41:02
 *
 */
public class LoginFragment extends BaseFragment implements OnClickListener,Observer{

	private ProgressDialog pd;
	private EditText loginNameEt, passwordEt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.general_login, null);
		loginNameEt = (EditText) view.findViewById(R.id.loginName_et);
		passwordEt = (EditText) view.findViewById(R.id.password_et);
		view.findViewById(R.id.ok).setOnClickListener(this);
		view.findViewById(R.id.register).setOnClickListener(this);
		view.findViewById(R.id.forget_pwd).setOnClickListener(this);
		return view;
	}
	
	
	
	@Override
	protected boolean canBack() {
		return false;
	}
	
	
	@Override
	public String getTitle() {
		return "赚米宝";
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ok:
//			 pd = ProgressDialog.show(getActivity(), "","登陆中..." );
			// BizFacade.login(loginNameEt.getText().toString(),
			// passwordEt.getText().toString(),
			// this);
//			RewardDialog newFragment = RewardDialog.newInstance();
//			newFragment.show(getSupportFragmentManager(), "dialog");
			WUtils.toMainPage(getActivity());
			break;
		case R.id.register:
			openFragment(WConstants.REGISTER);
			break;
		default:
			break;
		}
	}

	@Override
	public void update(int code, String service, Object body) {
		if (service.equals(Constants.BizType.LOGIN)
				|| service.equals(Constants.BizType.QUICKLY_REGISTER)) {
			WUtils.toMainPage(getActivity());
		}
	}
	

	@Override
	public void error(int code, String service, String msg) {
		if(pd!=null){
			pd.dismiss();
		}
	}
}
