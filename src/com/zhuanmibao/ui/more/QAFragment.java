/**
 * 
 */
package com.zhuanmibao.ui.more;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;

/**
 * @author cainli 2013年12月24日下午8:23:45
 * 
 */
@SuppressLint("NewApi")
public class QAFragment extends BaseFragment {

	private ListView listView;
	private QAAdapter adapter;

	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.qa, null);
		listView = (ListView) view.findViewById(R.id.list);
		adapter = new QAAdapter();
		listView.setAdapter(adapter);
		loadData();
		return view;
	}

	/**
	 * 
	 */
	private void loadData() {
		adapter.data.add(new Pair<String, String>("什么是赚米宝",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("米币可以干什么？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("多少米币可以提现？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("是否会泄漏我的隐私？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("怎么邀请好友？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("如果没有收到米币怎么办？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("下载费流量吗？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.data.add(new Pair<String, String>("为什么兑换后不给发放？",
				"赚米宝是一款帮助用户下载并且使用应用的软件"));
		adapter.notifyDataSetChanged();

	}

	@Override
	public String getTitle() {
		return "问题解答";
	}

	class QAAdapter extends BaseAdapter implements OnClickListener {

		List<Pair<String, String>> data = new ArrayList<Pair<String, String>>();

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.qa_item, null);
				holder = new Holder();
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.content = (TextView) convertView
						.findViewById(R.id.content);
				holder.line = convertView.findViewById(R.id.line);
				holder.arrow = (CheckBox) convertView.findViewById(R.id.arrow);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Pair<String, String> pair = data.get(position);
			holder.name.setText(pair.first);
			holder.name.setOnClickListener(this);
			holder.name.setTag(holder);
			holder.content.setText(pair.second);
			holder.arrow.setOnClickListener(this);
			holder.arrow.setTag(holder);
			return convertView;
		}

		@Override
		public void onClick(View v) {
			Holder holder = (Holder) v.getTag();
			if(v.getId() == R.id.name){
				holder.arrow.performClick();
				return;
			}
			int visible = holder.content.getVisibility() == View.VISIBLE ? View.GONE
					: View.VISIBLE;
			
			holder.content.setVisibility(visible);
			holder.line.setVisibility(visible);
		}

	}

	static class Holder {
		public TextView name, content;
		public CheckBox arrow;
		public View line;
	}

}
