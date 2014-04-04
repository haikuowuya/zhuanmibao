/**
 * 
 */
package com.zhuanmibao.ui.game;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author cainli 2014年1月28日上午12:49:49
 * 
 */
public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context) {
		super(context);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}