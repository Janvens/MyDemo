/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19下午12:52:09</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgConnect;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class ISMGConnect{
	
	private static Logger logger=Logger.getLogger(Cmpp3Sender.class);
	private static boolean isConnected = false;

	public static void connect(){
		MsgConnect connect = MsgHelper.getConnectMsg();
		try{
			Cmpp3Sender sender = new Cmpp3Sender(SocketHolder.getDin(), SocketHolder.getDout(), connect.toByteArry());
			byte[] resData = sender.send();
			boolean result = RespHandler.handleResp(resData);
			int counter = 0;//MsgConfig.getConnectCount();
			if(!result&&counter<=MsgConfig.getConnectCount()){
				resData = sender.send();
				result = RespHandler.handleResp(resData);
				counter = counter + 1;
			}
			isConnected = true;
		}catch (Exception e) {
			logger.error("连接ISMG异常",e);
		}
	}
	
	public static boolean isISMGConnected(){
		return isConnected;
	}
	
	public static synchronized void setISMGConnected(boolean _isConnected){
		isConnected = _isConnected;
	}
	
}
