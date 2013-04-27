/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-24����07:00:55</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgActiveTestResp;
import com.jan.cmpp3.codec.MsgCommand;
import com.jan.cmpp3.codec.MsgConnectResp;
import com.jan.cmpp3.codec.MsgDeliver;
import com.jan.cmpp3.codec.MsgDeliverResp;
import com.jan.cmpp3.codec.MsgHead;
import com.jan.cmpp3.codec.MsgSubmitResp;
import com.jan.cmpp3.io.MsgHelper;
import com.jan.cmpp3.io.MsgSender;
import com.jan.cmpp3.io.MsgUtil;
import com.jan.cmpp3.io.RespHandler;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-24 </p>
 * @version V1.0  
 */
public class ISMGMsgHandler{
	
	private static Logger logger=Logger.getLogger(ISMGMsgHandler.class);

	public static boolean handle(byte[] data){
		logger.info("��ӦISMG ������  >> "+MsgUtil.bytesToHexString(data));
		boolean flag = false;
		Cmpp3Sender sender = null;
		if(data !=null && data.length>=8){
			MsgHead head=new MsgHead(data);
			System.out.println("------------- "+head.getCommandId());
			switch (head.getCommandId()) {
				case MsgCommand.CMPP_CONNECT_RESP:
					MsgConnectResp connectionResp = new MsgConnectResp(data);
					logger.info("��������ISMG��Ӧ,״̬:"+ connectionResp.getStatusStr() + " ���кţ�"+ connectionResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_ACTIVE_TEST_RESP:
					MsgActiveTestResp activeResp = new MsgActiveTestResp(data);
					logger.info("��������ISMG���Ӽ����Ӧ, ���кţ�" + activeResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_SUBMIT_RESP:
					MsgSubmitResp submitResp = new MsgSubmitResp(data);
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
					
				case MsgCommand.CMPP_ACTIVE_TEST:
					logger.info("���յ�ISMG����·��������,���к�: " + head.getSequenceId());
					MsgActiveTestResp resp = new MsgActiveTestResp();
					resp.setTotalLength(12+1);
					resp.setCommandId(MsgCommand.CMPP_ACTIVE_TEST_RESP);
					resp.setSequenceId(head.getSequenceId());
					resp.setReserved((byte)0x00);
//					MsgSender.respMsg(resp.toByteArry());
					sender = new Cmpp3Sender(resp.toByteArry(), MsgNettySender.getChannelInstance());
					sender.send();
					break;
				case MsgCommand.CMPP_DELIVER:
					logger.info("���յ�ISMG����������,���к�: " + head.getSequenceId());
					MsgDeliver msgDeliver=new MsgDeliver(data);
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
//					MsgSender.respMsg(msgDeliverResp.toByteArry());
					sender = new Cmpp3Sender(msgDeliverResp.toByteArry(), MsgNettySender.getChannelInstance());
					sender.send();
					break;
				case MsgCommand.CMPP_TERMINATE:
					logger.info("���յ�ISMG�Ĳ����·����,���к�: " + head.getSequenceId());
					head.setCommandId(MsgCommand.CMPP_TERMINATE_RESP);
//					MsgSender.respMsg(head.toByteArry());
					sender = new Cmpp3Sender(head.toByteArry(), MsgNettySender.getChannelInstance());
					sender.send();
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
