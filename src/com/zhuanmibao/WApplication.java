/**
 * 
 */
package com.zhuanmibao;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.baidu.frontia.FrontiaApplication;
import com.zhuanmibao.mail.CrashReportEmailSender;
import com.zhuanmibao.pojo.DeviceInfo;
import com.zhuanmibao.ui.SplashActivity;
import com.zhuanmibao.utils.Utils;
import com.zhuanmibao.utils.WLog;

/**
 * @author cainli
 * 2013-12-21下午1:40:08
 */

//@ReportsCrashes(
//		formKey = "dGVacG0ydVHnaNHjRjVTUTEtb3FPWGc6MQ", 
//		formUri = "http://www.backendofyourchoice.com/reportpath", 
//		mode = ReportingInteractionMode.DIALOG, 
//		resToastText = R.string.crash_toast_text, 
//		resDialogText = R.string.crash_dialog_text, 
//		resDialogIcon = android.R.drawable.ic_dialog_info, 
//		resDialogTitle = R.string.crash_dialog_title, 
//		resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, 
//		resDialogOkToast = R.string.crash_dialog_ok_toast,
//		customReportContent = {
//		        ReportField.BUILD, ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE,
//		        ReportField.USER_EMAIL, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION,
//		        ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE,
//		        ReportField.LOGCAT,
//		}
//)
@ReportsCrashes(formKey = "", // will not be used
	customReportContent = {
		ReportField.BUILD, 
		ReportField.USER_APP_START_DATE, 
		ReportField.USER_CRASH_DATE,
		ReportField.USER_EMAIL, 
		ReportField.PACKAGE_NAME, 
		ReportField.APP_VERSION_NAME, 
		ReportField.APP_VERSION_CODE, 
		ReportField.ANDROID_VERSION,
		ReportField.PHONE_MODEL, 
		ReportField.CUSTOM_DATA, 
		ReportField.STACK_TRACE,
		ReportField.DUMPSYS_MEMINFO,
		ReportField.AVAILABLE_MEM_SIZE,
		ReportField.TOTAL_MEM_SIZE,
		ReportField.STACK_TRACE,
		ReportField.USER_COMMENT,
		ReportField.BRAND,
		ReportField.LOGCAT
	},
	mode = ReportingInteractionMode.TOAST,
	resToastText = R.string.crash_toast_text)
public class WApplication extends FrontiaApplication{

	private static final String TAG = WApplication.class.getSimpleName();

	public static Context context;
	
	public static final Handler uiHandler = new Handler();
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@SuppressLint("NewApi")
	@Override
	public void onCreate() {    
        ACRA.init(this);
        CrashReportEmailSender sender = new CrashReportEmailSender();
        ACRA.getErrorReporter().setReportSender(sender);
        super.onCreate();
        
		DeviceInfo deviceInfo = Utils.getPhoneNum(this);
		if(deviceInfo.tel!=null && !deviceInfo.tel.equals("")){			
			init(deviceInfo);
		}else{
			//没有电话，这里提示不让使用
			showDialog();
		}
		context = this;
		
		
//		if(Constants.IS_DEV){
//			new AsyncTask<Void, Void, String[]>() {
//				@Override
//				protected String[] doInBackground(Void... params) {
//					byte[] bytes = "18bea08af654f46cc58e705da89cd279".getBytes();
//					byte[] aest = ZJni.crypto(bytes, true);
//					String encodeToString = Base64.encodeToString(aest,Base64.DEFAULT);
////					Log.e("Test", "encode:"+encodeToString);
//					byte[] decode = Base64.decode(encodeToString,Base64.DEFAULT);
//					byte[] daest = ZJni.crypto(decode, false);
////					Log.e("Test", "decode:"+new String(daest));
//					return new String[]{encodeToString,new String(daest)};
//				}
//				
//				@Override
//				protected void onPostExecute(String[] result) {
//					super.onPostExecute(result);
//					Toast.makeText(getApplicationContext(), 
//							result[0]+"\n"+result[1], Toast.LENGTH_SHORT).show();
//				}
//				
//				
//				
//			}.execute();
//		}
	}
	/**
	 * @param deviceInfo
	 */
	private void init(DeviceInfo deviceInfo) {
		Constants.AppConfig.MOIBLE = deviceInfo.tel;
		Constants.AppConfig.IMEI = deviceInfo.imei;			
		PackageManager manager = this.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			Constants.AppConfig.APP_VERSION_CODE = info.versionCode; 
			Constants.AppConfig.APP_VERSION_NAME = info.versionName; 
		} catch (NameNotFoundException e) {
			WLog.v(TAG, "",e);
		}
		//启动闪屏
		Intent intent = new Intent(this,SplashActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
	}
	/**
	 * 
	 */
	private void showDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("请插入SIM卡!");
		builder.setTitle("提示")
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.exit(0);
				}
			});
	}
	
	public static void runOnUi(Runnable runnable){
		uiHandler.post(runnable);
	}
}
