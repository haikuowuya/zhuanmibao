/**
 * 
 */
package com.zhuanmibao.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author cainli  2014年1月4日下午7:12:40
 *
 */
public class NeteaseMailSender{   

	public synchronized void sendMail(String body) throws Exception {
		Properties props=new Properties();
		props.put("mail.smtp.host","smtp.163.com");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.port","25");
		props.put("mail.transport.protocol","smtp");
		Session session=Session.getInstance(props);
		session.setDebug(true);
		Message mess = new MimeMessage(session);
		mess.setFrom(new InternetAddress("s_crashreport@163.com"));
		mess.setRecipient(RecipientType.TO, new InternetAddress("r_crashreport@163.com"));
		mess.setSentDate(new Date());
		mess.setSubject("CrashReport");
		mess.setText(new String(body.getBytes(), "UTF-8"));
		// 指定邮件的优先级 1：紧急 3:普通 5：缓慢
		mess.setHeader("X-Priority", "1");
		// 创建一个传输对象
		Transport trans = session.getTransport("smtp");
		// 连接SMTP服务器
		trans.connect("smtp.163.com","s_crashreport", "1qazxsw2");
		trans.sendMessage(mess, mess.getAllRecipients());
		trans.close();
	}
   
}  