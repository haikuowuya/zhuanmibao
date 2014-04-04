/**
 * 
 */
package com.zhuanmibao.ui.home;

import java.util.ArrayList;
import java.util.List;

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
import com.zhuanmibao.ui.home.downtask.AdChannelManager;
import com.zhuanmibao.ui.home.downtask.AdDownTaskInterface;

/**
 * @author cainli 2013年12月29日下午2:30:40
 * 
 */
public class AdDownTaskFragment extends BaseFragment implements
		OnItemClickListener {

	private ListView channelListView;
	private ChannalAdapater adapter;
	private LayoutInflater inflater;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.ad_down_task, null);
		channelListView = (ListView) view.findViewById(R.id.channel_listview);
		adapter = new ChannalAdapater();
		channelListView.setAdapter(adapter);
		channelListView.setOnItemClickListener(this);
		loadData();

		return view;
	}

	@Override
	public String getTitle() {
		return "下载任务";
	}
	
	private int taskIconResIds[] = new int[] { R.drawable.task_icon_1,
			R.drawable.task_icon_2, R.drawable.task_icon_3,
			R.drawable.task_icon_4, R.drawable.task_icon_5 };
	private static String name[] = new String[]{
		"渠道任务一","渠道任务二","渠道任务三","渠道任务四","渠道任务五","渠道任务六","渠道任务七"
	};
	private static String desc[] = new String[]{
		"官方推荐，精品多多","每一样都是你的装机必备","最热门的渠道，最热门的应用",
		"赚钱时间短，利用您的零碎时间","深度体验内容，赚取更多米币","用户最喜欢的渠道","百万米币赚不停"
	};

	/**
	 * 
	 */
	private void loadData() {
		int size = Math.min(AdChannelManager.channels.size(),
				Math.min(name.length,desc.length));
		for (int i = 0; i < size ; i++) {
			adapter.channels.add(new Channel(i,
					taskIconResIds[i%taskIconResIds.length], 
					name[i],desc[i]));
			adapter.notifyDataSetChanged();
		}

	}

	class ChannalAdapater extends BaseAdapter {

		public List<Channel> channels = new ArrayList<Channel>();

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
				convertView = inflater
						.inflate(R.layout.ad_down_task_item, null);
				holder = new Holder();
				holder.icon = (ImageView) convertView
						.findViewById(R.id.channel_icon);
				holder.name = (TextView) convertView
						.findViewById(R.id.channel_name);
				holder.desc = (TextView) convertView
						.findViewById(R.id.channel_desc);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			Channel channel = channels.get(position);
			holder.icon.setImageResource(channel.resId);
			holder.name.setText(channel.name);
			holder.desc.setText(channel.desc);
			return convertView;
		}

	}

	static class Holder {
		public ImageView icon;
		public TextView name,desc;
	}

	class Channel {
		public int resId = R.drawable.ic_launcher;
		public String name = "渠道";
		public String desc = "";
		public int id;

		/**
		 * @param resId
		 * @param name
		 */
		public Channel(int id,int resId, String name,String desc) {
			super();
			this.id = id;
			this.resId = resId;
			this.name = name;
			this.desc = desc;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Channel channel = adapter.channels.get(position);
		AdDownTaskInterface task = AdChannelManager.getChannel(channel.id);
		if(task != null){
			task.onClick(getActivity());
		}
	}
}
