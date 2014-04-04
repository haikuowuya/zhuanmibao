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
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.adzhidian.data.b.c;
import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.WConstants;

/**
 * @author cainli  2014年2月3日上午1:39:05
 *
 */
public class TobeGeniusFragment extends BaseFragment implements OnItemClickListener{

	private ListView listView;
	private TaskAdapter adapter;
	private LayoutInflater inflater;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.tobe_genius, null);
		listView = (ListView) view.findViewById(R.id.task_list);
		adapter = new TaskAdapter();
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		loadData();
		return view;
	}
	

	/**
	 * 
	 */
	private void loadData() {
		//0
		Task task = new Task(0, "每日签到", 0, -1);
		task.bgResId = R.drawable.newer_task_bg1;
		task.state = 2;
		task.dayCount = 3;
		adapter.taskList.add(task);
		//1
		task = new Task(1, "完成2个任务渠道任务", 0, WConstants.TASK_DOWN);
		task.bgResId = R.drawable.newer_task_bg2;
		task.finishTaskCount = 1;
		task.state = 1;
		adapter.taskList.add(task);
		//2
		task = new Task(2, "付费大转盘", 0, WConstants.LUCK_BIGWHEEL);
		task.bgResId = R.drawable.newer_task_bg3;
		adapter.taskList.add(task);
		
		adapter.notifyDataSetChanged();
	}


	@Override
	public String getTitle() {
		return "争当任务达人";
	}
	
	class TaskAdapter extends BaseAdapter{

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
			if(convertView == null){
				holder = new Holder();
				convertView = inflater.inflate(R.layout.tobe_genius_item, null);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.stateText = (TextView) convertView.findViewById(R.id.state);
				holder.rightImg = (ImageView) convertView.findViewById(R.id.right_icon);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			Task task = taskList.get(position);
			holder.name.setText(task.name);
			holder.stateText.setText(task.getStateText());
			if(task.isFinished()){
				holder.rightImg.setVisibility(View.VISIBLE);
			}else{
				holder.rightImg.setVisibility(View.INVISIBLE);
			}
			convertView.setBackgroundDrawable(getActivity().getResources().getDrawable(task.bgResId));
			return convertView;
		}
	}
	
	static class Holder{
		public TextView name,stateText;
		public ImageView rightImg;
	}
	
	class Task{
		int id;
		String name;
		int state = 0;//1 完成一半，2，完成
		int fragmentId;
		int dayCount;
		int finishTaskCount;
		int bgResId;
		/**
		 * @param finishedText
		 * @param id
		 * @param name
		 * @param finished
		 * @param fragmentId
		 */
		public Task( int id, String name,
				int state,int fragmentId) {
			super();
			this.id = id;
			this.name = name;
			this.state = state;
			this.fragmentId = fragmentId;
		}
		/**
		 * @return
		 */
		public CharSequence getStateText() {
			if(state == 2){
				if(id == 0){ //签到 才有这个逻辑
					return "已连续签到"+dayCount+"天";
				}
				return "已完成";
			}else if(state == 0){
				return "未完成";
			}else if(state == 1){
				if(id == 1){ //完成任务才有这个逻辑
					return "已完成"+finishTaskCount+"个";
				}
				return "";
			}else{
				return "";
			}

		}
		/**
		 * @return
		 */
		public boolean isFinished() {
			return state == 2;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Task task = (Task) adapter.getItem(position);
		openFragment(task.fragmentId);
	}
}
