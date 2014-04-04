/**
 * 
 */
package com.zhuanmibao.ui.home;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * 道具
 * 
 * @author cainli 2013年12月24日下午8:23:45
 * 
 */

public class PropsFragment extends BaseFragment {
	private GridView gridView;
	private PropsAdapter adapter;
	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.props, null);
		gridView = (GridView) view.findViewById(R.id.props_grid);
		adapter = new PropsAdapter();
		gridView.setAdapter(adapter);
		loadDate();
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
			}
		});
		return view;
	}

	@Override
	public String getTitle() {
		return "任务道具";
	}

	/**
	 * 
	 */
	private void loadDate() {
		if (adapter != null) {
			adapter.data.clear();
			adapter.data.add(new Props(0, R.drawable.ic_launcher, "双倍积分", "x2", true));
			adapter.data.add(new Props(0, R.drawable.ic_launcher, "升级加速", "+30%", true));
			adapter.data.add(new Props(0, R.drawable.ic_launcher, "即将推出", "", false));

			adapter.notifyDataSetChanged();
		}

	}

	class PropsAdapter extends BaseAdapter {

		public ArrayList<Props> data = new ArrayList<Props>();

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
				convertView = inflater.inflate(R.layout.props_item, null);
				holder = new Holder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.fun = (TextView) convertView
						.findViewById(R.id.fun);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Props item = data.get(position);
			holder.icon.setImageResource(item.resId);
			holder.title.setText(item.title);
			holder.fun.setText(item.fun);
//			if(!item.canUse){
//				holder.icon.getDrawable().setAlpha(60);
//			}else{
//				holder.icon.getDrawable().setAlpha(255);
//			}
			return convertView;
		}

	}

	static final class Holder {
		ImageView icon;
		TextView title, fun;
	}

	class Props {
		public int id;
		public int resId;
		public String title;
		public String fun;
		public boolean canUse;
		/**
		 * @param id
		 * @param resId
		 * @param title
		 * @param fun
		 * @param canUse
		 */
		public Props(int id, int resId, String title, String fun, boolean canUse) {
			super();
			this.id = id;
			this.resId = resId;
			this.title = title;
			this.fun = fun;
			this.canUse = canUse;
		}

	
	}
}
