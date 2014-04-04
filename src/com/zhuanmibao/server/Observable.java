/**
 * 
 */
package com.zhuanmibao.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zhuanmibao.Constants;
import com.zhuanmibao.WApplication;
import com.zhuanmibao.pojo.UserInfo;
import com.zhuanmibao.pojo.base.Rsp;
import com.zhuanmibao.server.biz.BizFacade;
import com.zhuanmibao.utils.WLog;

/**
 * @author cainli  2013年12月22日下午2:42:18
 *
 */
public class Observable implements Subject{
	
	private static final String TAG = Observable.class.getSimpleName();
	
	public List<Observer> observers = new ArrayList<Observer>();

	/* (non-Javadoc)
	 * @see com.zhuanmibao.server.Subject#addObserver(com.zhuanmibao.server.Observer)
	 */
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
		
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.server.Subject#removeObserver(com.zhuanmibao.server.Observer)
	 */
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	/* (non-Javadoc)
	 * @see com.zhuanmibao.server.Subject#removeAllObserver()
	 */
	@Override
	public void removeAllObserver() {
		observers.clear();
	}

	/* (non-Javadoc)
	 * @see com.zhuanmibao.server.Subject#notifyObservers(java.lang.String)
	 * TODO save token, tips
	 */
	@Override
	public void notifyObservers(final int respCode, final String str) {
		try {
			//请求成功失败
			if(respCode == Constants.NetConstants.RESULT_SUCCESS){
				//返回的包不为空
				if(str!=null && !str.equals("")){
					//解包
					final Rsp rsp = BizFacade.defaultMapper().readValue(str, Rsp.class);
					Object obj = null;
					if(rsp == null){
						error(-1, null, null);
						return;
					}
					if (rsp.code == Constants.JsonConstants.RESULT_OK) {
						//TODO check version
						//TODO check permission
						
						// parse business
						if (rsp.service.equals(Constants.BizType.LOGIN)) {
							Constants.AppConfig.TOKEN = rsp.token;
							obj = BizFacade.defaultMapper().readValue(
									rsp.body.toString(), UserInfo.class);

						} else if (rsp.service
								.equals(Constants.BizType.QUICKLY_REGISTER)) {
							Constants.AppConfig.TOKEN = rsp.token;
							obj = BizFacade.defaultMapper().readValue(
									rsp.body.toString(), UserInfo.class);

						} else if (rsp.service
								.equals(Constants.BizType.UPDATE_USER_INFO)) {
							obj = BizFacade.defaultMapper().readValue(
									rsp.body.toString(), UserInfo.class);

						}
						//notify all
						update(rsp.code, rsp.service, obj);
					}else {
						WApplication.runOnUi(new Runnable() {
							public void run() {
								Toast.makeText(WApplication.context, rsp.msg, Toast.LENGTH_SHORT).show();
							}
						});
						//notify all
						error(rsp.code, rsp.service, rsp.msg);
					}
				}
			}else{
				WApplication.runOnUi(new Runnable() {
					public void run() {
						Toast.makeText(WApplication.context, str, Toast.LENGTH_SHORT).show();
					}
				});
				//notify all
				error(respCode, null, str);
				
			}
		} catch (JsonParseException e1) {
			WLog.e(TAG, "", e1);
		} catch (JsonMappingException e1) {
			WLog.e(TAG, "", e1);
		} catch (IOException e1) {
			WLog.e(TAG, "", e1);
		}

	}
	
	public void update(int code, String service, Object body) {
		for(Observer obs : observers){
			obs.update(code, service, body);
		} 
	}


	public void error(int code, String service, String msg) {
		//notify all
		for(Observer obs : observers){
			obs.error(code, service, msg);
		} 
	}

}
