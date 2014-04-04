/**
 * 
 */
package com.zhuanmibao.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuanmibao.R;
import com.zhuanmibao.pojo.Item;
import com.zhuanmibao.ui.base.BaseFragment;
import com.zhuanmibao.ui.base.FragmentFactory;
import com.zhuanmibao.utils.Utils;

/**
 * @author cainli 2013年12月24日下午8:24:11
 * 
 */
public class ExchangeFragment extends BaseFragment {

	private ListView listview;
	private ExchangeAdapter adapter;
	private LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.exchange, null);
		listview = (ListView) view.findViewById(R.id.exchange_list);
		adapter = new ExchangeAdapter();
		listview.setAdapter(adapter);
		loadData();
		return view;
	}

	@Override
	protected boolean canBack() {
		return false;
	}

	@Override
	public String getTitle() {
		return "兑换";
	}

	private void loadData() {
		ListItem listItem = new ListItem("Q币", "当前可兑换 %1$d Q币");
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(0,0, R.drawable.gold_icon, "1Q币", "10W米币"));
		listItem.items.add(new Item(0,1, R.drawable.gold_icon, "10Q币", "100W米币"));
		listItem.items.add(new Item(0,2, R.drawable.gold_icon, "20Q币", "200米币"));
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(0,3, R.drawable.gold_icon, "50Q币", "500W米币"));
		listItem.items.add(new Item(0,4, R.drawable.gold_icon, "100Q币", "1000W米币"));
		adapter.data.add(listItem);
		listItem = new ListItem("QQ服务", "当前可兑换 %1$d 项服务");
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(1,0, R.drawable.qqvip_icon, "会员一个月", "100W米币"));
		listItem.items.add(new Item(1,1, R.drawable.diamond_yellow_icon, "黄钻一个月","100W米币"));
		listItem.items.add(new Item(1,2, R.drawable.diamond_red_icon, "红钻一个月","100W米币"));
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(1,3, R.drawable.diamond_green_icon, "红钻一个月","100W米币"));
		listItem.items.add(new Item(1,4, R.drawable.diamond_purple_icon, "紫钻一个月","100W米币"));
		adapter.data.add(listItem);
		listItem = new ListItem("话费", "当前可兑换 %1$d 元话费");
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(2,0, R.drawable.phone_charge_icon, "10元", "100W米币"));
		listItem.items.add(new Item(2,1, R.drawable.phone_charge_icon, "20元", "200W米币"));
		listItem.items.add(new Item(2,2, R.drawable.phone_charge_icon, "50元", "500W米币"));
		adapter.data.add(listItem);
		listItem = new ListItem("支付宝提现", "当前可兑换 %1$d 元现金");
		adapter.data.add(listItem);
		listItem = new ListItem();
		listItem.items.add(new Item(3,0, R.drawable.money_icon, "10元", "100W米币"));
		listItem.items.add(new Item(3,1, R.drawable.money_icon, "20元", "200W米币"));
		listItem.items.add(new Item(3,2, R.drawable.money_icon, "50元", "500W米币"));
		adapter.data.add(listItem);
//		listItem = new ListItem("京东购物卡", "当前可兑换 %1$d 元京东购物卡");
//		adapter.data.add(listItem);
//		listItem = new ListItem();
//		listItem.items.add(new Item(4,0, R.drawable.ic_launcher, "10元", "100W米币"));
//		listItem.items.add(new Item(4,1, R.drawable.ic_launcher, "20元", "100W米币"));
//		listItem.items.add(new Item(4,2, R.drawable.ic_launcher, "50元", "100W米币"));
//		adapter.data.add(listItem);
		adapter.notifyDataSetChanged();
	}

	class ExchangeAdapter extends BaseAdapter {
		public List<ListItem> data = new ArrayList<ListItem>();

		@Override
		public int getItemViewType(int position) {
			return data.get(position).getType();
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

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
			ListItem listViewItem = data.get(position);
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				switch (listViewItem.getType()) {
				case 0:
					convertView = inflater.inflate(R.layout.exchange_item1,
							null);
					holder.tName = (TextView) convertView
							.findViewById(R.id.text1);
					holder.tDesc = (TextView) convertView
							.findViewById(R.id.text2);
					break;
				case 1:
					convertView = inflater.inflate(R.layout.exchange_item2,
							null);
					holder.rLayout[0] = (RelativeLayout) convertView.findViewById(R.id.rlayout_1);
					holder.img[0] = (ImageView) convertView
							.findViewById(R.id.item_1_img);
					holder.name[0] = (TextView) convertView
							.findViewById(R.id.item_1_name);
					holder.desc[0] = (TextView) convertView
							.findViewById(R.id.item_1_desc);
					holder.rLayout[1] = (RelativeLayout) convertView.findViewById(R.id.rlayout_2);
					holder.img[1] = (ImageView) convertView
							.findViewById(R.id.item_2_img);
					holder.name[1] = (TextView) convertView
							.findViewById(R.id.item_2_name);
					holder.desc[1] = (TextView) convertView
							.findViewById(R.id.item_2_desc);
					holder.rLayout[2] = (RelativeLayout) convertView.findViewById(R.id.rlayout_3);
					holder.img[2] = (ImageView) convertView
							.findViewById(R.id.item_3_img);
					holder.name[2] = (TextView) convertView
							.findViewById(R.id.item_3_name);
					holder.desc[2] = (TextView) convertView
							.findViewById(R.id.item_3_desc);
					break;
				default:
					break;
				}
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			//处理第一个第一个view的上边缘距离
			if (listViewItem.getType() == 0) {
				if (position == 0) {
					convertView.setPadding(convertView.getPaddingLeft(), 0,
							convertView.getPaddingRight(),
							convertView.getPaddingBottom());
				} else {
					convertView.setPadding(convertView.getPaddingLeft(),
							Utils.dip2px(getActivity(), 13),
							convertView.getPaddingRight(),
							convertView.getPaddingBottom());
				}
			}

			switch (listViewItem.getType()) {
			case 0:
				holder.tName.setText(listViewItem.name);
				holder.tDesc.setText(listViewItem.desc);
				break;
			case 1:
				Item item = null;
				int i = 0;
				for (; i < listViewItem.items.size(); i++) {
					item = listViewItem.items.get(i);
					holder.img[i].setImageResource(item.resId);
					holder.name[i].setText(item.name);
					holder.desc[i].setText(item.desc);
					holder.rLayout[i].setVisibility(View.VISIBLE);
					holder.rLayout[i].setOnClickListener(new ExchangeOnClick(item));
				}
				for (; i < 3; i++) {
					holder.rLayout[i].setVisibility(View.INVISIBLE);
					//为了让UI对齐
					if(item != null){
						holder.img[i].setImageResource(item.resId);
						holder.name[i].setText(item.name);
						holder.desc[i].setText(item.desc);
					}
				}
				break;
			default:
				break;
			}
			return convertView;
		}

	}
	
	class ExchangeOnClick implements OnClickListener{
		
		Item item;
		/**
		 * @param item
		 */
		public ExchangeOnClick(Item item) {
			this.item = item;
		}

		@Override
		public void onClick(View v) {
			Bundle data = new Bundle();
			data.putParcelable("item", item);
			openFragment(FragmentFactory.EXCHANGE_DETAIL,data);
		}
		
	}

	static class Holder {
		public RelativeLayout[] rLayout = new RelativeLayout[3];
		public ImageView[] img = new ImageView[3];
		public TextView[] name = new TextView[3];
		public TextView[] desc = new TextView[3];
		public TextView tName, tDesc;
	}

	class ListItem {
		public List<Item> items = new ArrayList<Item>(3);
		public String name, desc;

		public int getType() {
			return items.size() > 0 ? 1 : 0;
		}

		/**
		 * @param name
		 * @param desc
		 * @param type
		 */
		public ListItem(String name, String desc) {
			super();
			this.name = name;
			this.desc = desc;
		}

		/**
		 * 
		 */
		public ListItem() {

		}

	}

	

}
