/**
 * 
 */
package com.zhuanmibao.ui.home.downtask;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author cainli  2014年2月19日下午9:17:10
 *
 */
@SuppressLint("UseSparseArrays")
public class AdChannelManager {
	public static HashMap<Integer,AdDownTaskInterface> channels = new HashMap<Integer, AdDownTaskInterface>();
	static{
		channels.put(1, new YoumiTask());
		channels.put(2, new WapsTask());
		channels.put(3, new BaiduTask());
		channels.put(4, new Qihu360Task());
		channels.put(5, new LimeiTask());
		channels.put(6, new EScoreTask());
		channels.put(7, new MiidiTask());
		channels.put(8, new DianjinTask());
		channels.put(9, new DomobTask());
		channels.put(10, new KugaoTask());
		channels.put(11, new WiTask());
		channels.put(12, new ZhidianTask());
		channels.put(13, new WinadsTask());
	}
	public static void init(Context context){
		AdDownTaskInterface channel;
		for(int id : channels.keySet()){
			channel = channels.get(id);
			channel.init(context);
		}
	}
	public static void destory(Context context){
		AdDownTaskInterface channel;
		for(int id : channels.keySet()){
			channel = channels.get(id);
			channel.destory(context);
		}
	}
	
	public static AdDownTaskInterface getChannel(int id){
		return channels.get(id);
	}
}
