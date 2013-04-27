package com.jan.cmpp3.io;

import java.util.ResourceBundle;

public class MsgConfig{
	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("MsgConfig");
	public static String get(String key){
		return resourceBundle.getString(key);
	}
	/**
	 * ��ȡ��������������IP
	 * @return
	 */
	public static String getIsmgIp(){
		return MsgConfig.get("ismgIp");
	}
	/**
	 * ��ȡ�������������ض˿ں�
	 * @return
	 */
	public static int getIsmgPort(){
		return Integer.parseInt(MsgConfig.get("ismgPort"));
	}
	/**
	 * ��ȡsp��ҵ����
	 * @return
	 */
	public static String getSpId(){
		return MsgConfig.get("spId");
	}
	/**
	 * ��ȡsp�·����ź���
	 * @return
	 */
	public static String getSpCode(){
		return MsgConfig.get("spCode");
	}
	/**
	 * ��ȡsp sharedSecret
	 * @return
	 */
	public static String getSpSharedSecret(){
		return MsgConfig.get("sharedSecret");
	}
	/**
	 * ��ȡ���ӵĴ���
	 * @return
	 */
	public static int getConnectCount(){
		return Integer.parseInt(MsgConfig.get("connectCount"));
	}
	/**
	 * ��ȡ���ӵĳ�ʱʱ��
	 * @return
	 */
	public static int getTimeOut(){
		return Integer.parseInt(MsgConfig.get("timeOut"));
	}
}