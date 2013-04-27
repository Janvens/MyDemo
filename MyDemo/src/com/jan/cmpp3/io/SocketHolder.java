/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����12:42:58</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class SocketHolder{

	private static Logger logger=Logger.getLogger(SocketHolder.class);
	private static Socket socket = null;
	private static DataInputStream din = null;
	private static DataOutputStream dout = null;
	
	private SocketHolder(){}
	
	public static Socket getSocketInstance(){
		if(null!=socket && !socket.isClosed() && socket.isConnected()){
			return socket;
		}else{
			try {
				socket=new Socket(MsgConfig.getIsmgIp(),MsgConfig.getIsmgPort());
				socket.setKeepAlive(true);
				ISMGConnect.connect();//����ISMG
				ReqMsgMonitor.monitor();//���ISMG������
//				MsgActiveTester.test();//����ISMG������
			} catch (UnknownHostException e) {
				logger.error("Socket���Ӷ������ض˿ںŲ���ȷ��"+e.getMessage());
				//���Ӷ�������
			} catch (IOException e) {
				logger.error("Socket���Ӷ�������ʧ�ܣ�"+e.getMessage());
			}
		}
		return socket;
	}
	
	public static DataInputStream getDin() throws IOException{
		if(din != null && socket != null && socket.isConnected()){
			return din;
		}else{
			return new DataInputStream(getSocketInstance().getInputStream());
		}
	}
	
	public static DataOutputStream getDout() throws IOException{
		if(dout != null && socket != null && socket.isConnected()){
			return dout;
		}else{
			return new DataOutputStream(getSocketInstance().getOutputStream());
		}
	}
	
}
