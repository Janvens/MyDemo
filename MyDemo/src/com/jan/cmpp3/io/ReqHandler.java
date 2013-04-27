/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����02:26:49</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgActiveTestResp;
import com.jan.cmpp3.codec.MsgCommand;
import com.jan.cmpp3.codec.MsgDeliver;
import com.jan.cmpp3.codec.MsgDeliverResp;
import com.jan.cmpp3.codec.MsgHead;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class ReqHandler{

	private static Logger logger=Logger.getLogger(ReqHandler.class);
	
	public static void handleReq(byte[] reqData){
		logger.info("���յ�ISMG���������>> "+MsgUtil.bytesToHexString(reqData));
		if(reqData !=null && reqData.length>=8){
			MsgHead head=new MsgHead(reqData);
			switch (head.getCommandId()) {
				case MsgCommand.CMPP_ACTIVE_TEST:
					MsgActiveTestResp resp = new MsgActiveTestResp();
					resp.setTotalLength(12+1);
					resp.setCommandId(MsgCommand.CMPP_ACTIVE_TEST_RESP);
					resp.setSequenceId(head.getSequenceId());
					resp.setReserved((byte)0x00);
					MsgSender.respMsg(resp.toByteArry());
					break;
				case MsgCommand.CMPP_DELIVER:
					MsgDeliver msgDeliver=new MsgDeliver(reqData);
					if(msgDeliver.getResult()==0){
						logger.info("CMPP_DELIVER ���кţ�"+head.getSequenceId()+"���Ƿ���Ϣ�ظ�"+(msgDeliver.getRegistered_Delivery()==0?"����,��Ϣ���ݣ�"+msgDeliver.getMsg_Content():"�ǣ�Ŀ���ֻ��ţ�"+msgDeliver.getDest_terminal_Id()));
					}else{
						logger.info("CMPP_DELIVER ���кţ�"+head.getSequenceId());
					}
					MsgDeliverResp msgDeliverResp=new MsgDeliverResp();
					msgDeliverResp.setTotalLength(12+8+4);
					msgDeliverResp.setCommandId(MsgCommand.CMPP_DELIVER_RESP);
					msgDeliverResp.setSequenceId(msgDeliver.getSequenceId());
					msgDeliverResp.setMsg_Id(msgDeliver.getMsg_Id());
					msgDeliverResp.setResult(msgDeliver.getResult());
					MsgSender.respMsg(msgDeliverResp.toByteArry());
					break;
				case MsgCommand.CMPP_TERMINATE:
					head.setCommandId(MsgCommand.CMPP_TERMINATE_RESP);
					MsgSender.respMsg(head.toByteArry());
					break;

				default:
					logger.error("�޷�����IMSP���صİ��ṹ��������Ϊ"+head.getTotalLength());
					break;
			}
		}
		
		
	}
	
}
