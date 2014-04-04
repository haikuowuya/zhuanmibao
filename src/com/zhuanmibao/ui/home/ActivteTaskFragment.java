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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli 2013年12月29日下午2:30:40
 * 
 */
public class ActivteTaskFragment extends BaseFragment implements OnItemClickListener {

	private ListView taskListView;
	private TaskAdapter mAdapter;
	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.activity_task, null);
		taskListView = (ListView) view.findViewById(R.id.task_listview);
		mAdapter = new TaskAdapter();
		taskListView.setAdapter(mAdapter);
		taskListView.setOnItemClickListener(this);
		loadData();
		return view;
	}
	

	@Override
	public String getTitle() {
		return "活动任务";
	}

	/**
	 * 
	 */
	private void loadData() {
		mAdapter.taskList.add(new Task(1, R.drawable.ic_launcher, "分享赚米宝给好友",
				"分享赚米宝到微信，微博，人人网，QQ空间",WConstants.SHARE_TASK));
		mAdapter.taskList.add(new Task(2, R.drawable.ic_launcher, "争当任务达人",
				"完成组合任务，赢双倍积分道具，下载积分全部双倍哦！",WConstants.TOBE_GENIUS));
		mAdapter.notifyDataSetChanged();
	}

	class TaskAdapter extends BaseAdapter {

		public List<Task> taskList = new ArrayList<Task>();

		@Override
		public int getCount() {
			return taskList.size();
		}

		@Override
		public Object getItem(int position) {
			return taskList.get(position);
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
				convertView = inflater.inflate(R.layout.activity_task_item,
						null);
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.desc = (TextView) convertView.findViewById(R.id.desc);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Task task = taskList.get(position);
			holder.icon.setImageResource(task.resId);
			holder.title.setText(task.title);
			holder.desc.setText(task.desc);
			return convertView;
		}

	}

	static class Holder {
		ImageView icon;
		TextView title, desc;
	}

	class Task {
		int id;
		int resId;
		String title, desc;
		int fragementId;

		/**
		 * @param id
		 * @param resId
		 * @param title
		 * @param desc
		 */
		public Task(int id, int resId, String title, String desc,int fragmentId) {
			super();
			this.id = id;
			this.resId = resId;
			this.title = title;
			this.desc = desc;
			this.fragementId = fragmentId;
		}

	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Task task = (Task) mAdapter.getItem(position);
		openFragment(task.fragementId);
		
	}
}
