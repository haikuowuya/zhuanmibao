package com.zhuanmibao.server.biz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuanmibao.Constants;
import com.zhuanmibao.pojo.ActivateBody;
import com.zhuanmibao.pojo.GetTaskBody;
import com.zhuanmibao.pojo.LoginBody;
import com.zhuanmibao.pojo.UserInfo;
import com.zhuanmibao.pojo.base.Req;
import com.zhuanmibao.pojo.base.ReqInterface;
import com.zhuanmibao.server.HttpRunnable;
import com.zhuanmibao.server.Observer;
import com.zhuanmibao.utils.ThreadPool;
import com.zhuanmibao.utils.WLog;
import com.zhuanmibao.utils.WUtils;


/**
 * 
 * @author cainli
 * 2013-12-19下午9:51:26
 */
public class BizFacade {
	
	private static final String TAG = BizFacade.class.getSimpleName();
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static ObjectMapper defaultMapper() {
	    return mapper;
	}
	
	public static <T> T parseJson(JsonNode node, Class<T> clazz) {
	    try {
	        return defaultMapper().treeToValue(node, clazz);
	    } catch (IOException e) {
	        WLog.e(TAG, "Failed to parse JSON entity " + clazz.getSimpleName(), e);
	        throw new RuntimeException(e);
	    }
	}
	
	public static void sendData(byte[] data,Observer mObserver){
		ThreadPool.execute(new HttpRunnable(mObserver,data));
	}
	
	private static void sendReq(Observer mObserver, Req<?> req) {
		byte[] data = null;
		try {
			WLog.v(TAG, mapper.writeValueAsString(req));
			data = mapper.writeValueAsBytes(req);
		} catch (JsonProcessingException e) {
			WLog.e(TAG, "", e);
		}
		sendData(data, mObserver);
	}
	
	
	public static void sendData(String str,Observer mObserver){
		byte[] data = null;
		if(str!=null){
			try {
				data = str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				WLog.e(TAG, "", e);
			}
		}
		sendData(data, mObserver);
	}
	
	public static  Req<ReqInterface> getReq(ReqInterface body){
		return getReq(body, body.getService());
	}
	public static <T> Req<T> getReq(T body,String service){
		Req<T> req = new Req<T>();
		req.version = Constants.AppConfig.API_VERSION;
		req.deviceUuid = Constants.AppConfig.IMEI;
		req.appId = Constants.AppConfig.APP_ID;
		req.appVersion = Constants.AppConfig.APP_VERSION_CODE;
		req.mobile = Constants.AppConfig.MOIBLE;
		req.token = WUtils.getToken();
		req.body = body;
		req.service = service;
		return req;
		
	}


	//----------------------------------业务--------------------------------------------
	
	/**
	 * 有登陆态的登陆
	 * @param mObserver
	 */
	public static void login(Observer mObserver){
		sendReq(mObserver, getReq(null, Constants.BizType.LOGIN));
	}
	
	/**
	 * 用户名，密码登陆
	 * @param name
	 * @param password
	 * @param mObserver
	 */
	public static void login(String name,String password,Observer mObserver ){
		LoginBody body = new LoginBody(name,password);
		sendReq(mObserver, getReq(body));
	}
	
	/**
	 * 更新用户资料，完善资料
	 * @param userInfo
	 * @param mObserver
	 */
	public static void updateInfo(UserInfo userInfo,Observer mObserver){
		sendReq(mObserver, getReq(userInfo, Constants.BizType.UPDATE_USER_INFO));
	}

	/**
	 * 一键注册
	 * @param referer 推荐者ID
	 * @param context
	 * @param mObserver
	 */
	public static void quicklyRegister(String referer,Context context,Observer mObserver){
		ActivateBody body = new ActivateBody(referer);
		sendReq(mObserver, getReq(body));
	}
	
	public static void getTaskList(int taskId,Observer mObserver){
		GetTaskBody body = new GetTaskBody(taskId);
		sendReq(mObserver, getReq(body));
	}
	
	public static void doTask(Observer mObserver){
		
	}
	
}
