/**
 * 
 */
package com.zhuanmibao.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli 2013年12月24日下午8:24:35
 * 
 */
public class LuckFragment extends BaseFragment {

	private ListView listview;
	private LuckAdapter adapter;
	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.game, null);
		listview = (ListView) view.findViewById(R.id.luck_list);
		adapter = new LuckAdapter();
		listview.setAdapter(adapter);
		loadDate();
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				LuckItem item = (LuckItem) adapter.getItem(position);
				openFragment(item.id);
			}
		});
		return view;
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.base.BaseFragment#canBack()
	 */
	@Override
	protected boolean canBack() {
		return false;
	}
	@Override
	public String getTitle() {
		return "碰运气";
	}

	/**
	 * 
	 */
	private void loadDate() {
		if (adapter != null) {
			adapter.data.clear();
			adapter.data.add(new LuckItem(WConstants.LUCK_BIGWHEEL, R.drawable.ic_launcher, "大转盘",
					"大转盘的最高奖励10元话费，还有跟多道具等着你奥!", "22736人获得话费"));
			adapter.data.add(new LuckItem(WConstants.LUCK_SHAKE, R.drawable.ic_launcher, "摇一摇",
					"大转盘的最高奖励10元话费，还有跟多道具等着你奥!", "3643431人获得话费"));
			adapter.data.add(new LuckItem(WConstants.LUCK_SLOTMACHINE, R.drawable.ic_launcher, "老虎机",
					"大转盘的最高奖励10元话费，还有跟多道具等着你奥!", "8798732人获得话费"));
			adapter.notifyDataSetChanged();
		}

	}

	class LuckAdapter extends BaseAdapter {

		public ArrayList<LuckItem> data = new ArrayList<LuckFragment.LuckItem>();

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			if (position >= 0 && position < data.size()) {
				return data.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getView(int, android.view.View,
		 * android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.game_item, null);
				holder = new Holder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.summary = (TextView) convertView
						.findViewById(R.id.summary);
				holder.popuInfo = (TextView) convertView
						.findViewById(R.id.popuInfo);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			LuckItem item = data.get(position);
			holder.icon.setImageResource(item.resId);
			holder.title.setText(item.title);
			holder.summary.setText(item.summary);
			holder.popuInfo.setText(item.popuInfo);
			return convertView;
		}

	}

	static final class Holder {
		ImageView icon;
		TextView title, summary, popuInfo;
	}

	class LuckItem {
		public int id;
		public int resId;
		public String title;
		public String summary;
		public String popuInfo;

		/**
		 * @param id
		 * @param resId
		 * @param title
		 * @param summary
		 * @param popuInfo
		 */
		public LuckItem(int id, int resId, String title, String summary,
				String popuInfo) {
			super();
			this.id = id;
			this.resId = resId;
			this.title = title;
			this.summary = summary;
			this.popuInfo = popuInfo;
		}

	}
}
