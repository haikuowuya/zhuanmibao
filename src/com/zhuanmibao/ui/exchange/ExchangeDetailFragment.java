/**
 * 
 */
package com.zhuanmibao.ui.exchange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.pojo.Item;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * @author cainli 2014年2月6日下午2:02:20
 * 
 */

@SuppressLint("ValidFragment")
public class ExchangeDetailFragment extends BaseFragment implements
		OnClickListener {

	private Item item;
	private TextView account;
	private TextView itemName, itemDesc;
	private ImageView itemIcon;
	private EditText accountEd, mibiEd;
	private Button okBtn;
	
	public ExchangeDetailFragment() {

	}

	/**
	 * @param args
	 */
	public ExchangeDetailFragment(Bundle args) {
		item = args.getParcelable("item");
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.exchange_detail, null);
		itemIcon = (ImageView) view.findViewById(R.id.item_icon);
		itemName = (TextView) view.findViewById(R.id.item_name);
		itemDesc = (TextView) view.findViewById(R.id.item_desc);
		account = (TextView) view.findViewById(R.id.account);
		accountEd = (EditText) view.findViewById(R.id.account_ed);
		mibiEd = (EditText) view.findViewById(R.id.mibi_ed);
		okBtn = (Button) view.findViewById(R.id.ok);
		okBtn.setOnClickListener(this);
		String accuTvStr = null, accuEdStr = null;
		switch (item.typeId) {
		case 0:
			accuTvStr = "充值QQ账户:";
			accuEdStr = "3333333333";
			break;
		case 1:
			accuTvStr = "充值QQ账户:";
			accuEdStr = "33333333";
			break;
		case 2:
			accuTvStr = "充值手机号码:";
			accuEdStr = "3333333";
			break;
		case 3:
			accuTvStr = "支付宝账号:";
			accuEdStr = "33333";
			break;
		case 4:
			accuTvStr = "手机号码:";
			accuEdStr = "222222";
			break;
		default:
			break;
		}
		account.setText(accuTvStr);
		accountEd.setText(accuEdStr);
		mibiEd.setText(item.desc);
		itemIcon.setImageResource(item.resId);
		itemName.setText(item.name);
		itemDesc.setText(item.desc);

		return view;
	}

	@Override
	public String getTitle() {
		switch (item.typeId) {
		case 0:
			return "兑换Q币";
		case 1:
			return "QQ服务开通";
		case 2:
			return "兑换话费";
		case 3:
			return "提现金";
		case 4:
			return "兑换京东购物卡";
		default:
			break;
		}
		return super.getTitle();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
	}

}
