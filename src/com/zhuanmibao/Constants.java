package com.zhuanmibao;


/**
 * 
 * @author cainli 2013-12-19下午9:51:21
 */
public interface Constants {
	
	public static boolean IS_DEV = true;
	
	static class AppConfig {
		public static final String API_VERSION = "1.0.0";
		public static int APP_VERSION_CODE = 1;
		public static String APP_VERSION_NAME;
		public static long APP_ID = 1;
		public static String IMEI;
		public static String MOIBLE;
		public static String TOKEN;
	}
	
	static class BizType {
		public static final String LOGIN = "login";// 登录
		public static final String UPDATE_USER_INFO = "register";// 完善资料
		public static final String QUICKLY_REGISTER = "activate";// 一键注册
		public static final String GET_NONCE= "nonce";// 获取任务列表		
		public static final String GET_TASK_LIST = "listTask";// 获取任务列表		
	}

	static class JsonConstants {
		public static final int RESULT_OK = 0;

		public static final String USER_UID = "uid";
		public static final String USER_NICKNAME = "nickName";
		public static final String USER_RANK = "rank";
		public static final String USER_BALANCE = "balance";
	}

	static class NetConstants {
		public static final String URL = "http://115.29.186.31/service";
		public static final int TIME_OUT = 10000;
		public static final String REQ_GET = "GET";
		public static final String REQ_POST = "POST";
		public static final int RESULT_SUCCESS = 1000;
		public static final int RESULT_FAILE = -1000;
	}

}
