/**
 * 
 */
package com.zhuanmibao.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.common.ZAlertDialog;

/**
 * @author cainli 2014年2月11日下午2:18:59
 * 
 */
public class RegisterFragment extends BaseFragment implements OnClickListener {
	private EditText nickNameEd, emailEd, phoneEd, passwdEd, rePasswdEd;
	private TextView passwdTipsTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register, null);
		view.findViewById(R.id.commit).setOnClickListener(this);
		passwdTipsTv = (TextView) view.findViewById(R.id.passwd_tips);
		nickNameEd = (EditText) view.findViewById(R.id.nickname_value);
		nickNameEd.addTextChangedListener(new RegisterTextWatcher(nickNameEd.getId()));
		emailEd = (EditText) view.findViewById(R.id.email_value);
		emailEd.addTextChangedListener(new RegisterTextWatcher(emailEd.getId()));
		phoneEd = (EditText) view.findViewById(R.id.phone_value);
		phoneEd.addTextChangedListener(new RegisterTextWatcher(phoneEd.getId()));
		passwdEd = (EditText) view.findViewById(R.id.passwd_value);
		passwdEd.addTextChangedListener(new RegisterTextWatcher(passwdEd.getId()));
		rePasswdEd = (EditText) view.findViewById(R.id.re_passwd_value);
		rePasswdEd.addTextChangedListener(new RegisterTextWatcher(rePasswdEd.getId()));
		return view;
	}

	@Override
	protected boolean canBack() {
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit:
			break;

		default:
			break;
		}
	}

	class RegisterTextWatcher implements TextWatcher {

		private int viewId;

		/**
		 * @param id
		 */
		public RegisterTextWatcher(int id) {
			this.viewId = id;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			switch (viewId) {
			case R.id.nickname_value:

				break;
			case R.id.email_value:

				break;
			case R.id.phone_value:

				break;
			case R.id.passwd_value:

				break;
			case R.id.re_passwd_value:
				if (passwdEd.getText().toString()
						.indexOf(rePasswdEd.getText().toString()) != -1) {
					passwdTipsTv.setVisibility(View.INVISIBLE);
				} else {
					passwdTipsTv.setVisibility(View.VISIBLE);
				}
				break;

			default:
				break;
			}
		}

	}

}
