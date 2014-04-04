/**
 * 
 */
package com.zhuanmibao.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.zhuanmibao.Constants;
import com.zhuanmibao.utils.WLog;

/**
 * @author cainli
 * 2013-12-19下午10:24:52
 */
public class HttpRunnable extends Observable implements Runnable{
	
	private static final String TAG = HttpRunnable.class.getSimpleName();

	private byte[] data;
	
	
	public HttpRunnable(Observer observer, byte[] data) {
		super();
		addObserver(observer);
		this.data = data;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection) (new URL(Constants.NetConstants.URL)).openConnection();
			conn.setRequestMethod(Constants.NetConstants.REQ_POST);
			conn.setConnectTimeout(Constants.NetConstants.TIME_OUT);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// Post 请求不能使用缓存  
			conn.setUseCaches(false);  
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Accept-Encoding", "identity");
			conn.setRequestProperty("Content-Type","application/json;charset=utf-8");   
			if(data!=null){
				conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			}
			conn.connect();
			if(data!=null){
				os = conn.getOutputStream();
				os.write(data);
				os.flush();
			}
			String result = null;
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
				is = conn.getInputStream();
				result = convertStreamToString(is);
				notifyObservers(Constants.NetConstants.RESULT_SUCCESS,result);
			}else{
				notifyObservers(Constants.NetConstants.RESULT_FAILE,"Http ResponseCode:"+responseCode);
			}
		} catch (ProtocolException e) {
			notifyObservers(Constants.NetConstants.RESULT_FAILE,e.getMessage());
			WLog.e(TAG, "", e);
		} catch (MalformedURLException e) {
			notifyObservers(Constants.NetConstants.RESULT_FAILE,e.getMessage());
			WLog.e(TAG, "", e);
		} catch (IOException e) {
			notifyObservers(Constants.NetConstants.RESULT_FAILE,e.getMessage());
			WLog.e(TAG, "", e);
		} finally{
			if(os!=null ){
				try {
					os.close();
				} catch (IOException e) {
					WLog.e(TAG, "", e);
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					WLog.e(TAG, "", e);
				}
			}
			if(conn!=null){
				conn.disconnect();
			}
			removeAllObserver();
		}
	}
	
	

	private String convertStreamToString(InputStream is) {
		if(is == null){
			return null;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			WLog.e(TAG, "", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				WLog.e(TAG, "", e);
			}
		}
		return sb.toString();
	}

}
