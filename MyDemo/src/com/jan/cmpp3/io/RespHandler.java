/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����11:23:01</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgActiveTestResp;
import com.jan.cmpp3.codec.MsgCommand;
import com.jan.cmpp3.codec.MsgConnectResp;
import com.jan.cmpp3.codec.MsgHead;
import com.jan.cmpp3.codec.MsgSubmitResp;

/** 
 * desc: �������յ�ISMG����Ӧ
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class RespHandler{
	
	private static Logger logger=Logger.getLogger(RespHandler.class);

	public static boolean handleResp(byte[] resData){
		logger.info("��ӦISMG ������  >> "+MsgUtil.bytesToHexString(resData));
		boolean flag = false;
		if(resData !=null && resData.length>=8){
			MsgHead head=new MsgHead(resData);
			switch (head.getCommandId()) {
				case MsgCommand.CMPP_CONNECT_RESP:
					MsgConnectResp connectionResp = new MsgConnectResp(resData);
					logger.info("��������ISMG��Ӧ,״̬:"+ connectionResp.getStatusStr() + " ���кţ�"+ connectionResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_ACTIVE_TEST_RESP:
					MsgActiveTestResp activeResp = new MsgActiveTestResp(resData);
					logger.info("��������ISMG���Ӽ����Ӧ, ���кţ�" + activeResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_SUBMIT_RESP:
					MsgSubmitResp submitResp = new MsgSubmitResp(resData);
					logger.info("����ISMF�·�������Ӧ��״̬��:"+ submitResp.getResult() + " ���кţ�"+ submitResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_TERMINATE_RESP:
					logger.info("��������ISMG������Ӧ" + " ���кţ�" + head.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_CANCEL_RESP:
					logger.info("����ISMGɾ��������Ӧ�� ���кţ�" + head.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_QUERY_RESP:
					logger.info("����ISMG��ѯ������Ӧ�� ���кţ�" + head.getSequenceId());
					flag = true;
					break;
				default:
					logger.error("�޷�����IMSP���صİ��ṹ��������Ϊ" + head.getTotalLength());
					flag = false;
					break;
			}
		}
		return flag;
	}
	
}
