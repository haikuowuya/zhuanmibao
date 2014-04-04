/**
 * 
 */
package com.zhuanmibao.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * @author cainli  2014年3月10日下午8:45:24
 *
 */
public class SelectedButton extends RadioButton {

	public SelectedButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SelectedButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SelectedButton(Context context) {
		this(context, null);
	}

}
