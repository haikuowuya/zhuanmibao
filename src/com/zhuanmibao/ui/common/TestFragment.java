/**
 * 
 */
package com.zhuanmibao.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * @author cainli  2014年3月10日上午12:22:03
 *
 */
public class TestFragment extends BaseFragment implements OnClickListener{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test, null);
		view.findViewById(R.id.test1).setOnClickListener(this);
		view.findViewById(R.id.test2).setOnClickListener(this);
		view.findViewById(R.id.test3).setOnClickListener(this);
		view.findViewById(R.id.test4).setOnClickListener(this);
		view.findViewById(R.id.test5).setOnClickListener(this);
		view.findViewById(R.id.test6).setOnClickListener(this);
		return view;
	}

	ZAlertDialog dialog;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test1:
			dialog = new ZAlertDialog(ZAlertDialog.TYPE1);
			break;
		case R.id.test2:
			dialog = new ZAlertDialog(ZAlertDialog.TYPE2);
			break;
		case R.id.test3:
			dialog = new ZAlertDialog(ZAlertDialog.TYPE3);
			break;
		case R.id.test4:
			dialog = new ZAlertDialog(ZAlertDialog.TYPE4);
			break;
		case R.id.test5:
			
			break;
		case R.id.test6:
			
			break;

		default:
			break;
		}
		dialog.show(getFragmentManager(), ""+R.id.test1);
	}
}
