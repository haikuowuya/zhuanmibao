/**
 * 
 */
package com.zhuanmibao.ui.common;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.utils.Utils;

/**
 * @author cainli 2014年2月25日上午2:03:49
 * 
 */

@SuppressLint("ValidFragment")
public class ZAlertDialog extends DialogFragment implements OnClickListener {

	public static ZAlertDialog dialog;
	public static final int TYPE1=1;
	public static final int TYPE2=2;
	public static final int TYPE3=3;
	public static final int TYPE4=4;
	
	private String title ="title",content = "content",okBtnText ="确定",cancleBtnText="取消";
	private int drawableResId = R.drawable.ic_launcher;
	private int iconType;
	
	private TextView titleTV,contentTV;
	private ImageView icon;
	private Button okBtn,cancleBtn;
	private int dialogType;
	
	ZAlertDialog(int dialogType) {
		this.dialogType = dialogType;
	}
	
	@SuppressLint("NewApi")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		Dialog dialog = new Dialog(getActivity(), R.style.DialogStyle);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = null;
		switch (dialogType) {
		case TYPE1:
			view = inflater.inflate(R.layout.dialog, null);
			contentTV = (TextView) view.findViewById(R.id.content);
			contentTV.setText(content);
			break;
		case TYPE2:
			view = inflater.inflate(R.layout.dialog, null);
			contentTV = (TextView) view.findViewById(R.id.content);
			contentTV.setText(content);
			icon = (ImageView) view.findViewById(R.id.icon);
			icon.setVisibility(View.VISIBLE);
			icon.setBackgroundDrawable(getIconDrawable());
			break;
		case TYPE3:
			view = inflater.inflate(R.layout.dialog2, null);
			contentTV = (TextView) view.findViewById(R.id.content);
			contentTV.setText(content);
			icon = (ImageView) view.findViewById(R.id.icon);
			icon.setVisibility(View.VISIBLE);
			icon.setBackgroundDrawable(getIconDrawable());
			okBtn = (Button) view.findViewById(R.id.ok);
			okBtn.setText(okBtnText);
			okBtn.setOnClickListener(this);
			cancleBtn = (Button) view.findViewById(R.id.cancel);
			cancleBtn.setText(cancleBtnText);
			cancleBtn.setOnClickListener(this);
			break;
		case TYPE4:
			view = inflater.inflate(R.layout.dialog3, null);
			titleTV = (TextView) view.findViewById(R.id.title);
			titleTV.setText(title);
			contentTV = (TextView) view.findViewById(R.id.content);
			contentTV.setText(content);
			icon = (ImageView) view.findViewById(R.id.icon);
			icon.setVisibility(View.VISIBLE);
			icon.setBackgroundDrawable(getIconDrawable());
			okBtn = (Button) view.findViewById(R.id.ok);
			okBtn.setText(okBtnText);
			okBtn.setOnClickListener(this);
			break;
		default:
			break;
		}
		dialog.setContentView(view);
		return dialog;

	}

	@Override
	public void onStart() {
		super.onStart();
		int dialogWidth = 0,dialogHeight = 0;
		switch (dialogType) {
		case TYPE1:
		case TYPE2:
			dialogWidth = Utils.dip2px(getActivity(), 200);
			dialogHeight = Utils.dip2px(getActivity(), 100);
			break;
		case TYPE3:
			dialogWidth = Utils.dip2px(getActivity(), 247);
			dialogHeight = Utils.dip2px(getActivity(), 127);
			break;
		case TYPE4:
			dialogWidth = Utils.dip2px(getActivity(), 220);
			dialogHeight = Utils.dip2px(getActivity(), 160);
			break;

		default:
			break;
		}
		getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @param okBtnText the okBtnText to set
	 */
	public void setOkBtnText(String okBtnText) {
		this.okBtnText = okBtnText;
	}


	/**
	 * @param cancleBtnText the cancleBtnText to set
	 */
	public void setCancleBtnText(String cancleBtnText) {
		this.cancleBtnText = cancleBtnText;
	}
	

	/**
	 * @param drawableResId the drawableResId to set
	 */
	public void setDrawableResId(int drawableResId) {
		this.drawableResId = drawableResId;
	}

	
	/**
	 * @param iconType the iconType to set
	 */
	public void setIconType(int iconType) {
		this.iconType = iconType;
	}

	/**
	 * @param iconType2
	 * @return
	 */
	private Drawable getIconDrawable() {
		return getResources().getDrawable(R.drawable.ic_launcher);
	}



	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
