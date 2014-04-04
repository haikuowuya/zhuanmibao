/**
 * 
 */
package com.zhuanmibao.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import cn.kugao.escore.sdk.util.Util;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.utils.Utils;
import com.zhuanmibao.utils.ZLog;

/**
 * @author cainli 2014年2月3日上午1:11:05
 * 
 */
public class ShareFragment extends BaseFragment implements OnClickListener,
		PlatformActionListener {

	private GridView gridview;
	private IconsAdapter adapter;
	private LayoutInflater inflater;

	private static final int WX = 1;
	private static final int QZONE = 2;
	private static final int WEIBO = 3;

	private Button btn;
	private EditText editText;
	
	private ArrayList<ShareChannel> checkedList = new ArrayList<ShareChannel>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		ShareSDK.initSDK(getActivity());

		View view = inflater.inflate(R.layout.share, null);
		btn = (Button) view.findViewById(R.id.send);
		btn.setOnClickListener(this);
		editText = (EditText) view.findViewById(R.id.content);
		gridview = (GridView) view.findViewById(R.id.share_icons);
		adapter = new IconsAdapter();
		gridview.setAdapter(adapter);
		loadData();

		return view;
	}

	

	@Override
	public void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(getActivity());
	}
	/**
	 * 
	 */
	private void loadData() {
		adapter.channels.clear();
		adapter.channels.add(new ShareChannel(WX, R.drawable.share_wx_icon));
		adapter.channels.add(new ShareChannel(QZONE,
				R.drawable.share_qzone_icon));
		adapter.channels.add(new ShareChannel(WEIBO, R.drawable.share_wx_icon));
		adapter.notifyDataSetChanged();
	}

	@Override
	public String getTitle() {
		return "分享给好友";
	}

	private class IconsAdapter extends BaseAdapter implements OnClickListener {

		public ArrayList<ShareChannel> channels = new ArrayList<ShareFragment.ShareChannel>();

		@Override
		public int getCount() {
			return channels.size();
		}

		@Override
		public Object getItem(int position) {
			return channels.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = inflater.inflate(R.layout.share_item, null);
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.checked = (ImageView) convertView
						.findViewById(R.id.checked_icon);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			ShareChannel shareChannel = channels.get(position);
			holder.icon.setImageResource(shareChannel.iconResId);
			holder.pos = position;
			if(holder.checked.getVisibility() == View.VISIBLE){
				checkedList.add(shareChannel);
			}else{
				checkedList.remove(shareChannel);
			}
			convertView.setOnClickListener(this);
			return convertView;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			Holder holder = (Holder) v.getTag();
			boolean bVisible = holder.checked.getVisibility() == View.VISIBLE;
			int visible  = bVisible ? View.INVISIBLE : View.VISIBLE;
			holder.checked.setVisibility(visible);
			ShareChannel shareChannel = channels.get(holder.pos);
			if(holder.checked.getVisibility() == View.VISIBLE){
				checkedList.add(shareChannel);
			}else{
				checkedList.remove(shareChannel);
			}
			
		}

	}

	static class Holder {
		public ImageView icon, checked;
		public int pos;
	}

	private class ShareChannel {
		public int id;
		public int iconResId;

		/**
		 * @param id
		 * @param iconResId
		 */
		public ShareChannel(int id, int iconResId) {
			super();
			this.id = id;
			this.iconResId = iconResId;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		ShareParams sp = new ShareParams();
		sp.setText(editText.getText().toString());
		Platform plat = null;
		for(ShareChannel sc: checkedList){
			switch (sc.id) {
			case WX:
				plat = ShareSDK.getPlatform(getActivity(), Wechat.NAME);
				break;
			case WEIBO:
				plat = ShareSDK.getPlatform(getActivity(), SinaWeibo.NAME);
				break;
			case QZONE:
				plat = ShareSDK.getPlatform(getActivity(), QZone.NAME);
				break;
			default:
				break;
			}
			if(plat!=null){
				plat.setPlatformActionListener(this);
				plat.share(sp);
			}
		}
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		ZLog.v("shareFragment", ""+arg2.getMessage());
	}

}
