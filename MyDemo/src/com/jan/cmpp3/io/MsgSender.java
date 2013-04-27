/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����01:53:30</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgSubmit;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class MsgSender{

	public static void sendSubmit(String msgContent, String mobileId){
		ThreadPoolHolder.getPoolInstance().execute(new MsgSubmitSender(msgContent, mobileId));
	}
	
	public static void sendData(byte[] data){
		ThreadPoolHolder.getPoolInstance().execute(new DataSender(data));
	}

	public static void respMsg(byte[] data){
		ThreadPoolHolder.getPoolInstance().execute(new DataResp(data));
	}
	
}

class MsgSubmitSender implements Runnable{
	private static Logger logger = Logger.getLogger(MsgSubmitSender.class);
	private String msgContent;
	private String mobileId;
	
	public MsgSubmitSender(String msgContent, String mobileId){
		this.msgContent = msgContent;
		this.mobileId = mobileId;
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		MsgSubmit submit = MsgHelper.getSubmitMsg(msgContent, mobileId);
		try{
			Cmpp3Sender sender = new Cmpp3Sender(SocketHolder.getDin(), SocketHolder.getDout(), submit.toByteArry());
			byte[] resData = sender.send();
			boolean flag = RespHandler.handleResp(resData);
			logger.info("�����ŷ��ͽ����   "+flag);
		}catch (IOException e) {
			logger.error("���ͱ��Ķ����쳣",e);
		}
	}
}

class DataSender implements Runnable{
	private static Logger logger = Logger.getLogger(DataSender.class);

	private byte[] data;
	/**
	 * Zhang Wensheng 2013-4-19 ����02:36:32
	 * @param data
	 */
	public DataSender(byte[] data) {
		this.data = data;
	}

	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try{
			Cmpp3Sender sender = new Cmpp3Sender(SocketHolder.getDin(), SocketHolder.getDout(), data);
			byte[] resData = sender.send();
			boolean flag = RespHandler.handleResp(resData);
			logger.info("�����ŷ��ͽ����   "+flag);
		}catch (IOException e) {
			logger.error("���ͱ��Ķ����쳣",e);
		}
	}
}

class DataResp implements Runnable{
	
	private static Logger logger = Logger.getLogger(DataResp.class);
	private byte[] data;

	/**
	 * Zhang Wensheng 2013-4-19 ����04:04:46
	 * @param data
	 */
	public DataResp(byte[] data) {
		this.data = data;
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try{
//			Cmpp3Sender sender = new Cmpp3Sender(SocketHolder.getDin(), SocketHolder.getDout(), data);
//			sender.send();
			DataOutputStream dout = SocketHolder.getDout();
			dout.write(data);
			dout.flush();
		}catch (IOException e) {
			logger.error("���ͱ��Ķ����쳣",e);
		}
		
	}
}
