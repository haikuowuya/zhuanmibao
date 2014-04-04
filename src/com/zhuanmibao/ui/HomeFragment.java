/**
 * 
 */
package com.zhuanmibao.ui;

import java.util.ArrayList;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.FragmentFactory;
import com.zhuanmibao.ui.base.WConstants;


/**
 * @author cainli  2013年12月24日下午8:23:45
 *
 */
public class HomeFragment extends BaseFragment implements OnItemClickListener,OnClickListener{
	
	private ListView activeListView;
	private ActiveAdapater activeAdapter;
	private LayoutInflater inflater;
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, 
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.home, null);
		View account = view.findViewById(R.id.user_info_area);
		account.setOnClickListener(this);
		activeListView = (ListView) view.findViewById(R.id.active_listview);
		activeAdapter = new ActiveAdapater();
		activeListView.setAdapter(activeAdapter);
		activeListView.setOnItemClickListener(this);
		
		view.findViewById(R.id.shake).setOnClickListener(this);
		view.findViewById(R.id.bigwheel).setOnClickListener(this);
		view.findViewById(R.id.slotmach).setOnClickListener(this);
		loadData();
		
		return view;
	}
	

	@Override
	public String getTitle() {
		return "赚米宝";
	}
	
	@Override
	protected boolean canBack() {
		return false;
	}

	/**
	 * 
	 */
	private void loadData() {
		activeAdapter.actives.clear();
		activeAdapter.actives.add(new Active(WConstants.TASK_NEWER,
				R.drawable.task_newer_icon, "新手任务","新手快速赚钱，还能抽奖哦！"));
		activeAdapter.actives.add(new Active(WConstants.TASK_DOWN,
				R.drawable.task_download_icon, "下载任务","多种渠道任你选择，惊喜连连！"));
		activeAdapter.actives.add(new Active(WConstants.TASK_ACTIVE,
				R.drawable.task_active_icon, "活动任务","每日任务，让你赚不停！"));
//		activeAdapter.actives.add(new Active(WConstants.TASK_PROPS,
//				R.drawable.ic_launcher, "任务道具"));
		activeAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Active active = activeAdapter.actives.get(position);
		openFragment(active.id);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user_info_area:
			openFragment(WConstants.ACCOUNT_INFO);
			break;
		case R.id.shake:
			openFragment(WConstants.LUCK_SHAKE);
			break;
		case R.id.bigwheel:
			openFragment(WConstants.LUCK_BIGWHEEL);
			break;
		case R.id.slotmach:
			openFragment(WConstants.LUCK_SLOTMACHINE);
			break;
		default:
			break;
		}
		
	}

	
	class ActiveAdapater extends BaseAdapter{
		public ArrayList<Active> actives = new ArrayList<HomeFragment.Active>();

		@Override
		public int getCount() {
			return actives!=null?actives.size():0;
		}

		@Override
		public Object getItem(int position) {
			if(actives!=null && actives.size() > position){
				return actives.get(position);
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
				convertView = inflater.inflate(R.layout.home_item, null);
				holder.icon = (ImageView) convertView.findViewById(R.id.active_img);
				holder.nameTv = (TextView) convertView.findViewById(R.id.active_name);
				holder.descTv = (TextView) convertView.findViewById(R.id.active_desc);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			Active active = actives.get(position);
			holder.icon.setImageResource(active.iconResId);
			holder.nameTv.setText(active.name);
			holder.descTv.setText(active.desc);
			return convertView;
		}
	}
	
	static class Holder{
		public ImageView icon;
		public TextView nameTv,descTv;
	}
	
	class Active{
		
		/**
		 * @param id
		 * @param iconResId
		 * @param name
		 */
		public Active(int id, int iconResId, String name,String desc) {
			super();
			this.id = id;
			this.iconResId = iconResId;
			this.name = name;
			this.desc = desc;
		}
		public int id;
		public int iconResId = R.drawable.ic_launcher;
		public String name;
		public String desc;
	}


}
