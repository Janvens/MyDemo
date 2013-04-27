/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午07:00:55</p>
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
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class ISMGMsgHandler{
	
	private static Logger logger=Logger.getLogger(ISMGMsgHandler.class);

	public static boolean handle(byte[] data){
		logger.info("响应ISMG 的数据  >> "+MsgUtil.bytesToHexString(data));
		boolean flag = false;
		Cmpp3Sender sender = null;
		if(data !=null && data.length>=8){
			MsgHead head=new MsgHead(data);
			System.out.println("------------- "+head.getCommandId());
			switch (head.getCommandId()) {
				case MsgCommand.CMPP_CONNECT_RESP:
					MsgConnectResp connectionResp = new MsgConnectResp(data);
					logger.info("请求连接ISMG响应,状态:"+ connectionResp.getStatusStr() + " 序列号："+ connectionResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_ACTIVE_TEST_RESP:
					MsgActiveTestResp activeResp = new MsgActiveTestResp(data);
					logger.info("请求连接ISMG链接检查响应, 序列号：" + activeResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_SUBMIT_RESP:
					MsgSubmitResp submitResp = new MsgSubmitResp(data);
					logger.info("请求ISMF下发短信响应，状态码:"+ submitResp.getResult() + " 序列号："+ submitResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_TERMINATE_RESP:
					logger.info("请求拆除与ISMG链接响应" + " 序列号：" + head.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_CANCEL_RESP:
					logger.info("请求ISMG删除短信响应， 序列号：" + head.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_QUERY_RESP:
					logger.info("请求ISMG查询短信响应， 序列号：" + head.getSequenceId());
					flag = true;
					break;
					
				case MsgCommand.CMPP_ACTIVE_TEST:
					logger.info("接收到ISMG的链路测试请求,序列号: " + head.getSequenceId());
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
					logger.info("接收到ISMG的上行请求,序列号: " + head.getSequenceId());
					MsgDeliver msgDeliver=new MsgDeliver(data);
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
//					MsgSender.respMsg(msgDeliverResp.toByteArry());
					sender = new Cmpp3Sender(msgDeliverResp.toByteArry(), MsgNettySender.getChannelInstance());
					sender.send();
					break;
				case MsgCommand.CMPP_TERMINATE:
					logger.info("接收到ISMG的拆除链路请求,序列号: " + head.getSequenceId());
					head.setCommandId(MsgCommand.CMPP_TERMINATE_RESP);
//					MsgSender.respMsg(head.toByteArry());
					sender = new Cmpp3Sender(head.toByteArry(), MsgNettySender.getChannelInstance());
					sender.send();
					break;
				default:
					logger.error("无法解析IMSP返回的包结构：包长度为" + head.getTotalLength());
					flag = false;
					break;
			}
		}
		return flag;
	}
	
}
