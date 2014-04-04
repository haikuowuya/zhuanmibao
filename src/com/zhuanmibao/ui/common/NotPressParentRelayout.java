/**
 * 
 */
package com.zhuanmibao.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author cainli  2014年2月6日下午1:41:06
 *
 */
public class NotPressParentRelayout extends RelativeLayout {
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public NotPressParentRelayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public NotPressParentRelayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 */
	public NotPressParentRelayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.view.View#setPressed(boolean)
	 */
	@Override
	public void setPressed(boolean pressed) {
      // Make sure the parent is a View prior casting it to View
      if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
          return;
      }
      super.setPressed(pressed);
	}


}
