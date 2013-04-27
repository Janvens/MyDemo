/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����09:40:18</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class MsgUtil{

	private static int sequenceId = 0;// ���б��

	/**
	 * ���� ����
	 */
	public static int getSequence() {
		++sequenceId;
		if (sequenceId > 255) {
			sequenceId = 0;
		}
		return sequenceId;
	}

	/**
	 * ʱ���������,�ɿͻ��˲���,��ʽΪMMDDHHMMSS��������ʱ���룬10λ���ֵ����ͣ��Ҷ��� ��
	 */
	public static String getTimestamp() {
		DateFormat format = new SimpleDateFormat("MMddhhmmss");
		return format.format(new Date());
	}

	/**
	 * ���ڼ���Դ��ַ����ֵͨ������MD5 hash����ó�����ʾ���£�
	 * AuthenticatorSource =
	 * MD5��Source_Addr+9 �ֽڵ�0 +shared secret+timestamp��
	 * Shared secret ���й��ƶ���Դ��ַʵ�������̶���timestamp��ʽΪ��MMDDHHMMSS��������ʱ���룬10λ��
	 * 
	 * @return
	 */
	public static byte[] getAuthenticatorSource(String spId, String secret) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] data = (spId + "\0\0\0\0\0\0\0\0\0" + secret + getTimestamp()).getBytes();
			return md5.digest(data);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SP���ӵ�ISMGƴ��AuthenticatorSourceʧ�ܣ�"
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * ������д��ָ���ֽڳ��ȵ��ַ���������ʱ��0
	 * 
	 * @param dous
	 *            :Ҫд���������
	 * @param s
	 *            :Ҫд����ַ���
	 * @param len
	 *            :д�볤��,���㲹0
	 */
	public static void writeString(DataOutputStream dous, String s, int len) {

		try {
			byte[] data = s.getBytes("gb2312");
			if (data.length > len) {
				System.out.println("������д����ַ���������Ҫд" + len + " �ַ�����:" + s);
			}
			int srcLen = data.length;
			dous.write(data);
			while (srcLen < len) {
				dous.write('\0');
				srcLen++;
			}
		} catch (IOException e) {
			System.out.println("������д��ָ���ֽڳ��ȵ��ַ���ʧ�ܣ�" + e.getMessage());
		}
	}

	/**
	 * �����ж�ȡָ�����ȵ��ֽڣ�ת���ַ�������
	 * 
	 * @param ins
	 *            :Ҫ��ȡ��������
	 * @param len
	 *            :Ҫ��ȡ���ַ�������
	 * @return:��ȡ�����ַ���
	 */
	public static String readString(java.io.DataInputStream ins, int len) {
		byte[] b = new byte[len];
		try {
			ins.read(b);
			String s = new String(b);
			s = s.trim();
			return s;
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * ��ȡ�ֽ�
	 * 
	 * @param msg
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] getMsgBytes(byte[] msg, int start, int end) {
		byte[] msgByte = new byte[end - start];
		int j = 0;
		for (int i = start; i < end; i++) {
			msgByte[j] = msg[i];
			j++;
		}
		return msgByte;
	}

	/**
	 * UCS2����
	 * 
	 * @param src
	 *            UCS2 Դ��
	 * @return ������UTF-16BE�ַ���
	 */
	public static String DecodeUCS2(String src) {
		byte[] bytes = new byte[src.length() / 2];
		for (int i = 0; i < src.length(); i += 2) {
			bytes[i / 2] = (byte) (Integer
					.parseInt(src.substring(i, i + 2), 16));
		}
		String reValue = "";
		try {
			reValue = new String(bytes, "UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			reValue = "";
		}
		return reValue;

	}

	/**
	 * UCS2����
	 * 
	 * @param src
	 *            UTF-16BE�����Դ��
	 * @return ������UCS2��
	 */
	public static String EncodeUCS2(String src) {
		byte[] bytes;
		try {
			bytes = src.getBytes("UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			bytes = new byte[0];
		}
		StringBuffer reValue = new StringBuffer();
		StringBuffer tem = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			tem.delete(0, tem.length());
			tem.append(Integer.toHexString(bytes[i] & 0xFF));
			if (tem.length() == 1) {
				tem.insert(0, '0');
			}
			reValue.append(tem);
		}
		return reValue.toString().toUpperCase();
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
