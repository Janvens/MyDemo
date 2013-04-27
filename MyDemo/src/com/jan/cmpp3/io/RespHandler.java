/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19上午11:23:01</p>
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
 * desc: 解析接收到ISMG的响应
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class RespHandler{
	
	private static Logger logger=Logger.getLogger(RespHandler.class);

	public static boolean handleResp(byte[] resData){
		logger.info("响应ISMG 的数据  >> "+MsgUtil.bytesToHexString(resData));
		boolean flag = false;
		if(resData !=null && resData.length>=8){
			MsgHead head=new MsgHead(resData);
			switch (head.getCommandId()) {
				case MsgCommand.CMPP_CONNECT_RESP:
					MsgConnectResp connectionResp = new MsgConnectResp(resData);
					logger.info("请求连接ISMG响应,状态:"+ connectionResp.getStatusStr() + " 序列号："+ connectionResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_ACTIVE_TEST_RESP:
					MsgActiveTestResp activeResp = new MsgActiveTestResp(resData);
					logger.info("请求连接ISMG链接检查响应, 序列号：" + activeResp.getSequenceId());
					flag = true;
					break;
				case MsgCommand.CMPP_SUBMIT_RESP:
					MsgSubmitResp submitResp = new MsgSubmitResp(resData);
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
				default:
					logger.error("无法解析IMSP返回的包结构：包长度为" + head.getTotalLength());
					flag = false;
					break;
			}
		}
		return flag;
	}
	
}
