package com.hemeiyue.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMailUtil extends Thread{
	
	//发件人qq邮箱的SMTP服务器
	private String qqHost;
	
	//发件人账号
	private String userName;
	
	//发件人的密码
	private String password;
	
	//发件人邮箱
	private String from;
	
	//收件人邮箱
	private String to;
	
	//邮件正文
	private String message;
	
	//邮件主题
	private String subject;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQqHost() {
		return qqHost;
	}

	/**
	 * 发送邮件
	 */
	public void send() {
		//创建邮件属性
		Properties p = new Properties();
		p.put("mail.smtp.host", qqHost);
		p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.fallback", "false");
		p.put("mail.smtp.auth", "true");
		Session mailSession = Session.getDefaultInstance(p);
		mailSession.setDebug(true);
		//创建一个消息
		Message msg = new MimeMessage(mailSession);
		try {
			//发件人地址
			InternetAddress fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			//收件人的地址
			InternetAddress toAddr = new InternetAddress(to);
			msg.setRecipient(Message.RecipientType.TO, toAddr);
			//添加邮件日期
			msg.setSentDate(new Date());
			msg.setSubject(subject);
			//添加内容
			if(message != null && message.trim().length() > 0) {
				msg.setText(message);
			}else {
				msg.setText("No message to be sent!");
			}
			msg.saveChanges();
			
			int nMailPort = 465;
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(qqHost, nMailPort, userName, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("邮件发送成功");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		send();
	}
	
	/**
	 * @param to 收件人邮箱
	 * @param subject 邮件标题
	 * @param message 邮件正文
	 */
	public SendMailUtil(String to,String subject,String message) {
		Properties pro = new Properties();
		InputStream in;
		try {
			in = SendMailUtil.class.getClassLoader().getResourceAsStream("email.properties");
//			in = new FileInputStream("D:\\GIT\\Warehouse\\hemeiyue\\src\\main\\resources\\email.properties");
			in = new FileInputStream(SendMailUtil.class.getClassLoader().getResource("email.properties").getFile());
			pro.load(in);
			in.close();
			qqHost = pro.getProperty("qqHost");
			System.out.println(qqHost);
			userName = pro.getProperty("userName");
			password = pro.getProperty("password");
			from = pro.getProperty("from");
			this.to = to;
			this.subject = subject;
			this.message = message;
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SendMailUtil s = new SendMailUtil("2338314741@qq.com", "test", "test");
	}
}
