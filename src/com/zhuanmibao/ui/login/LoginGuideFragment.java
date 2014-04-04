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

import com.zhuanmibao.Constants;
import com.zhuanmibao.R;
import com.zhuanmibao.server.Observer;
import com.zhuanmibao.server.biz.BizFacade;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;
import com.zhuanmibao.utils.WUtils;

/**
 * @author cainli  2014年2月25日上午12:41:02
 *
 */
public class LoginGuideFragment extends BaseFragment implements OnClickListener,Observer {


	private ProgressDialog pd;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.guide_login, null);
		view.findViewById(R.id.register_btn).setOnClickListener(this);
		view.findViewById(R.id.login_btn).setOnClickListener(this);
		view.findViewById(R.id.quick_register_btn).setOnClickListener(this);
		return view;
	}
	@Override
	protected boolean canBack() {
		return false;
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_btn:
			openFragment(WConstants.REGISTER);
			break;
		case R.id.login_btn:
			openFragment(WConstants.LOGIN);
			break;
		case R.id.quick_register_btn:
			pd = ProgressDialog.show(getActivity(), "", "激活中...");
			BizFacade.quicklyRegister("", getActivity(), this);
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
