/**
 * 
 */
package com.zhuanmibao.mail;

import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import android.os.Looper;

import com.zhuanmibao.utils.ZLog;

/**
 * @author cainli  2014年1月4日下午7:18:48
 *
 */
public class CrashReportEmailSender implements ReportSender {
	private static final String TAG = CrashReportEmailSender.class.getSimpleName();

	/* (non-Javadoc)
	 * @see org.acra.sender.ReportSender#send(org.acra.collector.CrashReportData)
	 */
	@Override
	public void send(CrashReportData data) throws ReportSenderException {
		  ZLog.e(TAG, "UI Thread :" + (Looper.myLooper() != Looper.getMainLooper()));
			StringBuffer sb = new StringBuffer();
			sb.append("Package Name:").append(data.getProperty(ReportField.PACKAGE_NAME)).append("\n");
			sb.append("App Version Name:").append(data.getProperty(ReportField.APP_VERSION_NAME)).append("\n");
			sb.append("App Version Code:").append(data.getProperty(ReportField.APP_VERSION_CODE)).append("\n");
			sb.append("Android Version:").append(data.getProperty(ReportField.ANDROID_VERSION)).append("\n");
			sb.append("Brand:").append(data.getProperty(ReportField.BRAND)).append(" ").append(data.getProperty(ReportField.PHONE_MODEL)).append("\n");
			sb.append("Emial:").append(data.getProperty(ReportField.USER_EMAIL)).append("\n");
//			sb.append("Custom Data:").append(data.getProperty(ReportField.CUSTOM_DATA)).append("\n");
			sb.append("Stack Trace:").append(data.getProperty(ReportField.STACK_TRACE)).append("\n");
			sb.append("Logcat").append(data.getProperty(ReportField.LOGCAT)).append("\n");
			sb.append("User Crash Date :").append(data.getProperty(ReportField.USER_CRASH_DATE)).append("\n");
			sb.append("App start Date:").append(data.getProperty(ReportField.USER_APP_START_DATE)).append("\n");
			sb.append("Total Mem Size:").append(data.getProperty(ReportField.TOTAL_MEM_SIZE)).append("\n");
			sb.append("Available Mem Size:").append(data.getProperty(ReportField.AVAILABLE_MEM_SIZE)).append("\n");
			sb.append("Dumpsys Info:").append(data.getProperty(ReportField.DUMPSYS_MEMINFO)).append("\n");
			sb.append("Build:").append("\n").append(data.getProperty(ReportField.BUILD)).append("\n");
//			sb.append("User Comment:").append(data.getProperty(ReportField.USER_COMMENT)).append("\n");			
			try {
				NeteaseMailSender sender = new NeteaseMailSender();
				sender.sendMail(sb.toString());
			} catch (Exception e) {
				ZLog.e(TAG, "",e);
			}
			ZLog.v(TAG, "had send mail");
	}

}
