/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����11:17:44</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class Cmpp3Sender{
	
	private static Logger logger=Logger.getLogger(Cmpp3Sender.class);

	private DataInputStream in;
	private DataOutputStream out;
	private byte[] data;
	/**
	 * Zhang Wensheng 2013-4-19 ����11:18:34
	 * @param in
	 * @param out
	 * @param data
	 */
	public Cmpp3Sender(DataInputStream in, DataOutputStream out, byte[] data) {
		super();
		this.in = in;
		this.out = out;
		this.data = data;
	}
	
	
	/**
	 * desc: ��������
	 * <p>�����ˣ�Zhang Wensheng , 2013-4-19 ����11:22:10</p>
	 * @return
	 * @throws IOException
	 */
	public byte[] send() throws IOException{
		byte[] resData = null;
		if(out != null){
			out.write(data);
			out.flush();
		}
		if(in != null){
			int len=in.readInt();
			 if(null!=in && 0!=len){
				 resData=new byte[len-4];
				 in.read(resData);
			 }
		}
		return resData;
	}
	
}
