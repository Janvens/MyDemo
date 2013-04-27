/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午04:23:28</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import com.jan.cmpp3.codec.MsgConnect;
import com.jan.cmpp3.io.MsgHelper;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class ISMGNettyConnect{

	private static boolean isConnected = false;
	
	public static void connect(){
		MsgConnect connect = MsgHelper.getConnectMsg();
		Cmpp3Sender sender = new Cmpp3Sender(connect.toByteArry(), MsgNettySender.getChannelInstance());
		sender.send();
	}

	/**
	 * @return the isConnected
	 */
	public static boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected the isConnected to set
	 */
	public static synchronized void setConnected(boolean isConnected) {
		ISMGNettyConnect.isConnected = isConnected;
	}
	
}
