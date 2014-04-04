/**
 * 
 */
package com.zhuanmibao.ui.game;

import java.io.IOException;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeAnimator;
import android.app.Service;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.utils.WLog;

/**
 * @author cainli 2014年1月20日下午11:25:19
 * 
 */
public class ShakeFragment extends BaseFragment implements SensorEventListener,
		AnimationListener{

	private static final String TAG = ShakeFragment.class.getSimpleName();
	private SensorManager manager;
	private Vibrator vibrator;
	private MediaPlayer mediaPlayer;
	private float X = 0.0f;
	private float Y = 0.0f;
	private float Z = 0.0f;
	private Context mContext;

	private RelativeLayout rl;
	private ImageView topView, bottomView;
	private Animation topAnim1, topAnim2, bottomAnim1, bottomAnim2;
	private int halfHeight;

	private boolean canShake = true;

	MediaPlayer mp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		manager = (SensorManager) mContext
				.getSystemService(mContext.SENSOR_SERVICE); // 首先得到传感器管理器对象
		vibrator = (Vibrator) mContext
				.getSystemService(Service.VIBRATOR_SERVICE);

         mp = new MediaPlayer();
		View view = inflater.inflate(R.layout.shake, null);

		// get view Height;
		DisplayMetrics displayMetrics = mContext.getResources()
				.getDisplayMetrics();
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(
				displayMetrics.widthPixels, MeasureSpec.EXACTLY);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(
				displayMetrics.heightPixels, MeasureSpec.EXACTLY);
		view.measure(widthMeasureSpec, heightMeasureSpec);
		int halfHeight = (view.getMeasuredHeight() - getTitleHeight() - getBottomBarHeight()) / 2;

		// add view;
		rl = (RelativeLayout) view.findViewById(R.id.bg_parent);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, halfHeight);
		topView = new ImageView(mContext);
		topView.setBackgroundColor(Color.BLUE);
		rl.addView(topView, params);
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				halfHeight);
		params.topMargin = halfHeight;
		bottomView = new ImageView(mContext);
		bottomView.setBackgroundColor(Color.RED);
		rl.addView(bottomView, params);

		// animation
		int openHeight = halfHeight / 2;
		topAnim1 = new TranslateAnimation(0, 0, 0, -openHeight);
		topAnim1.setAnimationListener(this);
		topAnim1.setInterpolator(new AccelerateInterpolator());
		topAnim1.setDuration(500);
		topAnim1.setFillAfter(true);
		topAnim2 = new TranslateAnimation(0, 0, -openHeight, 0);
		topAnim2.setAnimationListener(this);
		topAnim2.setInterpolator(new AccelerateInterpolator());
		topAnim2.setDuration(500);
		topAnim2.setFillAfter(true);

		bottomAnim1 = new TranslateAnimation(0, 0, 0, openHeight);
		bottomAnim1.setAnimationListener(this);
		bottomAnim1.setInterpolator(new AccelerateInterpolator());
		bottomAnim1.setDuration(500);
		bottomAnim1.setFillAfter(true);
		bottomAnim2 = new TranslateAnimation(0, 0, openHeight, 0);
		bottomAnim2.setInterpolator(new AccelerateInterpolator());
		bottomAnim2.setAnimationListener(this);
		bottomAnim2.setDuration(500);
		bottomAnim2.setFillAfter(true);

		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
		manager.unregisterListener(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mp.stop();
	}

	@Override
	public void onResume() {
		super.onResume();
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				manager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public String getTitle() {
		return "摇一摇";
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// 传感器信息改变时执行该方法
		float[] values = event.values;
		float x = values[0]; // x轴方向的重力加速度，向右为正
		float y = values[1]; // y轴方向的重力加速度，向前为正
		float z = values[2]; // z轴方向的重力加速度，向上为正
//		WLog.d(TAG, "x:" + x + ",y:" + y + ",z:" + z);
		if ((x > 10 || x < -10 || y > 10 || y < -10 || z > 10 || z < -10)
				&& canShake) {
			// 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。当然这个值可以根据需要，自己定义。
			vibrator.vibrate(100);
			try {
				mp.reset();
				AssetFileDescriptor afd = mContext.getResources()
						.openRawResourceFd(R.raw.shake_sound_male);
				mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
				afd.close();
				mp.prepare();
				mp.start();
			} catch (Exception e) {
				WLog.e(TAG, "", e);
			}
			Message msg = new Message();
			msg.what = 0;
			handler.sendMessage(msg);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.hardware.SensorEventListener#onAccuracyChanged(android.hardware
	 * .Sensor, int)
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				topView.startAnimation(topAnim1);
				bottomView.startAnimation(bottomAnim1);
				break;

			}
		}
	};

	@Override
	public void onAnimationStart(Animation animation) {
		if (animation == topAnim1 || animation == bottomAnim1) {
			canShake = false;
		}

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == topAnim1) {
			topView.startAnimation(topAnim2);
		} else if (animation == bottomAnim1) {
			bottomView.startAnimation(bottomAnim2);
		}
		;
		if (animation == topAnim2 || animation == bottomAnim2) {
			canShake = true;
			try {
				mp.reset();
				AssetFileDescriptor afd = mContext.getResources()
						.openRawResourceFd(R.raw.shake_match);
				mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
				afd.close();
				mp.prepare();
				mp.start();
			}  catch (Exception e) {
				WLog.e(TAG, "",e);
			}
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}



}
