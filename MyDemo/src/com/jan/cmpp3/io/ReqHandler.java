/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19下午02:26:49</p>
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
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class ReqHandler{

	private static Logger logger=Logger.getLogger(ReqHandler.class);
	
	public static void handleReq(byte[] reqData){
		logger.info("接收到ISMG请求的数据>> "+MsgUtil.bytesToHexString(reqData));
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
						logger.info("CMPP_DELIVER 序列号："+head.getSequenceId()+"，是否消息回复"+(msgDeliver.getRegistered_Delivery()==0?"不是,消息内容："+msgDeliver.getMsg_Content():"是，目的手机号："+msgDeliver.getDest_terminal_Id()));
					}else{
						logger.info("CMPP_DELIVER 序列号："+head.getSequenceId());
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
					logger.error("无法解析IMSP返回的包结构：包长度为"+head.getTotalLength());
					break;
			}
		}
		
		
	}
	
}
