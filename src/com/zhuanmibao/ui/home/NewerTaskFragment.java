/**
 * 
 */
package com.zhuanmibao.ui.home;

import java.util.ArrayList;

import javax.mail.Quota.Resource;

import android.content.res.Resources;
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
import com.zhuanmibao.ui.MainActivity;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli  2013年12月29日下午2:30:40
 *
 */
public class NewerTaskFragment extends BaseFragment implements OnItemClickListener {
	

	private ListView taskListView;
	private ActiveAdapater taskAdapter;
	private LayoutInflater inflater;
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, 
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.newer_task, null);
		taskListView = (ListView) view.findViewById(R.id.task_listview);
		taskAdapter = new ActiveAdapater();
		taskListView.setAdapter(taskAdapter);
		taskListView.setOnItemClickListener(this);
		loadData();
		
		return view;
	}
	
	@Override
	public String getTitle() {
		return "新手任务";
	}

	/**
	 * 
	 */
	private void loadData() {
		taskAdapter.tasks.clear();
		taskAdapter.tasks.add(new Task(WConstants.NEWER_TASK_1, "完善账号信息", 0));
		taskAdapter.tasks.add(new Task(WConstants.SHARE_TASK, "分享给QQ空间，微博", 0));
		taskAdapter.tasks.add(new Task(WConstants.LUCK, "免费体验博彩游戏", 0));
		taskAdapter.tasks.add(new Task(WConstants.TASK_DOWN, "体验做任务赚虚拟币", 0));
		taskAdapter.tasks.add(new Task(WConstants.TASK_DOWN, "再次做任务赚虚拟币", 0));
		taskAdapter.tasks.add(new Task(WConstants.EXCHANGE, "兑换一个Q币", 0));
		taskAdapter.notifyDataSetChanged();
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.ui.base.BaseFragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		setTab(MainActivity.TAB_MONEY);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Task task = taskAdapter.tasks.get(position);
		openFragment(task.id);
	}
	
	class ActiveAdapater extends BaseAdapter{
		public ArrayList<Task> tasks = new ArrayList<Task>();

		@Override
		public int getCount() {
			return tasks!=null?tasks.size():0;
		}

		@Override
		public Object getItem(int position) {
			if(tasks!=null && tasks.size() > position){
				return tasks.get(position);
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if(convertView == null){
				holder = new Holder();
				convertView = inflater.inflate(R.layout.newer_task_item, null);
				holder.name = (TextView) convertView.findViewById(R.id.task_name);
				holder.state = (TextView) convertView.findViewById(R.id.task_state);
				holder.arrow = (ImageView) convertView.findViewById(R.id.task_arrow);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			Task task = tasks.get(position);
			Resources  res = getActivity().getResources();
			int identifier = res.getIdentifier("newer_task_bg"+(position+1), "drawable", "com.zhuanmibao");
			convertView.setBackgroundResource(identifier);
//			holder.name.setText(String.valueOf(position+1).concat(".").concat(task.name));
			holder.name.setText(task.name);
			holder.state.setVisibility(task.state == 1?View.VISIBLE:View.GONE);
			holder.arrow.setVisibility(task.state == 1?View.GONE:View.VISIBLE);
			return convertView;
		}
	}
	
	static class Holder{
		public TextView name,state;
		public ImageView arrow;
	}
	
	class Task{
		/**
		 * @param id
		 * @param name
		 * @param state
		 */
		public Task(int id, String name, int state) {
			super();
			this.id = id;
			this.name = name;
			this.state = state;
		}
		public int id;
		public String name;
		public int state = 0;
	}
}
