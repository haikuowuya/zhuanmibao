/**
 * 
 */
package com.zhuanmibao.ui.game;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.utils.Utils;
import com.zhuanmibao.widget.wheel.OnWheelChangedListener;
import com.zhuanmibao.widget.wheel.OnWheelScrollListener;
import com.zhuanmibao.widget.wheel.WheelView;
import com.zhuanmibao.widget.wheel.adapters.AbstractWheelAdapter;

/**
 * @author cainli 2014年1月20日下午11:25:19
 * 
 */
public class SlotMachineFragment extends BaseFragment {

	@Override
	public String getTitle() {
		return "老虎机";
	}

	private View view;
	// Image size
	static int IMAGE_WIDTH;
	static int IMAGE_HEIGHT;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		IMAGE_WIDTH = Utils.dip2px(getActivity(), 40);
		IMAGE_HEIGHT = Utils.dip2px(getActivity(), 40);
		view = inflater.inflate(R.layout.slot_machine_layout, null);
		view.findViewById(R.id.scroll_banner).requestFocus();
		
		initWheel(R.id.slot_1);
		initWheel(R.id.slot_2);
		initWheel(R.id.slot_3);

		Button mix = (Button) view.findViewById(R.id.btn_mix);
		mix.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mixWheel(R.id.slot_1);
				mixWheel(R.id.slot_2);
				mixWheel(R.id.slot_3);
			}
		});
		updateStatus();

		return view;
	}

	// Wheel scrolled flag
	private boolean wheelScrolled = false;

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollingStarted(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollingFinished(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
		}
	};

	// Wheel changed listener
	private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (!wheelScrolled) {
				updateStatus();
			}
		}
	};

	/**
	 * Updates status
	 */
	private void updateStatus() {
		TextView text = (TextView) view.findViewById(R.id.pwd_status);
		if (test()) {
			text.setText("Congratulation!");
		} else {
			text.setText("");
		}
	}

	/**
	 * Initializes wheel
	 * 
	 * @param id
	 *            the wheel widget Id
	 */
	private void initWheel(int id) {
		WheelView wheel = getWheel(id);
		wheel.setViewAdapter(new SlotMachineAdapter(getActivity()));
		wheel.setCurrentItem((int) (Math.random() * 10));

		wheel.addChangingListener(changedListener);
		wheel.addScrollingListener(scrolledListener);
		wheel.setCyclic(true);
		wheel.setEnabled(false);
	}

	/**
	 * Returns wheel by Id
	 * 
	 * @param id
	 *            the wheel Id
	 * @return the wheel with passed Id
	 */
	private WheelView getWheel(int id) {
		return (WheelView) view.findViewById(id);
	}

	/**
	 * Tests wheels
	 * 
	 * @return true
	 */
	private boolean test() {
		int value = getWheel(R.id.slot_1).getCurrentItem();
		return testWheelValue(R.id.slot_2, value)
				&& testWheelValue(R.id.slot_3, value);
	}

	/**
	 * Tests wheel value
	 * 
	 * @param id
	 *            the wheel Id
	 * @param value
	 *            the value to test
	 * @return true if wheel value is equal to passed value
	 */
	private boolean testWheelValue(int id, int value) {
		return getWheel(id).getCurrentItem() == value;
	}

	/**
	 * Mixes wheel
	 * 
	 * @param id
	 *            the wheel id
	 */
	private void mixWheel(int id) {
		WheelView wheel = getWheel(id);
		wheel.scroll(-350 + (int) (Math.random() * 50), 2000);
	}

	/**
	 * Slot machine adapter
	 */
	private class SlotMachineAdapter extends AbstractWheelAdapter {

		// Slot machine symbols
		private final int items[] = new int[] { android.R.drawable.star_big_on,
				android.R.drawable.stat_sys_warning,
				android.R.drawable.radiobutton_on_background,
				android.R.drawable.ic_delete };

		// Cached images
		private List<SoftReference<Bitmap>> images;

		// Layout inflater
		private Context context;

		/**
		 * Constructor
		 */
		public SlotMachineAdapter(Context context) {
			this.context = context;
			images = new ArrayList<SoftReference<Bitmap>>(items.length);
			for (int id : items) {
				images.add(new SoftReference<Bitmap>(loadImage(id)));
			}
		}

		/**
		 * Loads image from resources
		 */
		private Bitmap loadImage(int id) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), id);
			Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH,
					IMAGE_HEIGHT, true);
			bitmap.recycle();
			return scaled;
		}

		@Override
		public int getItemsCount() {
			return items.length;
		}

		// Layout params for image view
		final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			ImageView img;
			if (cachedView != null) {
				img = (ImageView) cachedView;
			} else {
				img = new ImageView(context);
			}
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			img.setLayoutParams(params);
			SoftReference<Bitmap> bitmapRef = images.get(index);
			Bitmap bitmap = bitmapRef.get();
			if (bitmap == null) {
				bitmap = loadImage(items[index]);
				images.set(index, new SoftReference<Bitmap>(bitmap));
			}
			img.setImageBitmap(bitmap);

			return img;
		}
	}

}
