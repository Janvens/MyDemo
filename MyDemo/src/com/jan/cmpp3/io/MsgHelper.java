package com.jan.cmpp3.io;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.jan.cmpp3.codec.MsgActiveTestResp;
import com.jan.cmpp3.codec.MsgCommand;
import com.jan.cmpp3.codec.MsgConnect;
import com.jan.cmpp3.codec.MsgHead;
import com.jan.cmpp3.codec.MsgSubmit;

public class MsgHelper {

	public static MsgConnect getConnectMsg(){
		MsgConnect connect=new MsgConnect();
		connect.setTotalLength(12+6+16+1+4);//��Ϣ�ܳ��ȣ������ֽ���:4+4+4(��Ϣͷ)+6+16+1+4(��Ϣ����)
		connect.setCommandId(MsgCommand.CMPP_CONNECT);//��ʶ��������
		connect.setSequenceId(MsgUtil.getSequence());//���У�������ָ��
		connect.setSourceAddr(MsgConfig.getSpId());//���ǵ���ҵ����
		connect.setAuthenticatorSource(MsgUtil.getAuthenticatorSource(MsgConfig.getSpId(),MsgConfig.getSpSharedSecret()));//md5(��ҵ����+�ܳ�+ʱ���)
		connect.setTimestamp(Integer.parseInt(MsgUtil.getTimestamp()));//ʱ���(MMDDHHMMSS)
		connect.setVersion((byte)0x30);//�汾�� ��4bitΪ3����4λΪ0
		return connect;
	}
	
	public static MsgSubmit getSubmitMsg(String msgContent,String mobileId){
		int seq=MsgUtil.getSequence();
		MsgSubmit submit=new MsgSubmit();
		submit.setTotalLength(12+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+msgContent.length()*2+20);
		submit.setCommandId(MsgCommand.CMPP_SUBMIT);
		submit.setSequenceId(seq);			
		submit.setPkTotal((byte)0x01);
		submit.setPkNumber((byte)0x01);			
		submit.setRegisteredDelivery((byte)0x00);
		submit.setMsgLevel((byte)0x01);
		submit.setFeeUserType((byte)0x00);
		submit.setFeeTerminalId("");			
		submit.setFeeTerminalType((byte)0x00);			
		submit.setTpPId((byte)0x00);
		submit.setTpUdhi((byte)0x00);
		submit.setMsgFmt((byte)0x0f);
		submit.setMsgSrc(MsgConfig.getSpId());
		submit.setSrcId(MsgConfig.getSpCode());
		submit.setDestTerminalId(mobileId);
		submit.setMsgLength((byte)(msgContent.length()*2));
		try {
			submit.setMsgContent(msgContent.getBytes("gb2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return submit;
	}
	
	public static List<MsgSubmit> getSubmitMsgList(String msgContent,String mobileId){
		byte[] allByte = null;
		try {
			allByte = msgContent.getBytes("UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		List<MsgSubmit> dataList=new ArrayList<MsgSubmit>();
		int msgLength=allByte.length;
		int maxLength=140;
		int msgSendCount=msgLength%(maxLength-6)==0?msgLength/(maxLength-6):msgLength/(maxLength-6)+1;
		//����Ϣ����ͷƴ��
		byte[] msgHead=new byte[6];
		msgHead[0]=0x05;
		msgHead[1]=0x00;
		msgHead[2]=0x03;
		msgHead[3]=(byte)MsgUtil.getSequence();
		msgHead[4]=(byte)msgSendCount;
		msgHead[5]=0x01;
		int seqId=MsgUtil.getSequence();
		Cmpp3Sender sender = null;
		for(int i=0;i<msgSendCount;i++){
			//msgHead[3]=(byte)MsgUtil.getSequence();
			msgHead[5]=(byte)(i+1);
			byte[] needMsg=null;
			//��Ϣͷ+��Ϣ���ݲ��
			if(i!=msgSendCount-1){
				int start=(maxLength-6)*i;
				int end=(maxLength-6)*(i+1);
				needMsg=MsgUtil.getMsgBytes(allByte,start,end);
			}else{
				int start=(maxLength-6)*i;
				int end=allByte.length;
				needMsg=MsgUtil.getMsgBytes(allByte,start,end);
			}
			int subLength=needMsg.length+msgHead.length;
			byte[] sendMsg=new byte[needMsg.length+msgHead.length];
			System.arraycopy(msgHead,0,sendMsg,0,6);
			System.arraycopy(needMsg,0,sendMsg,6,needMsg.length);
			MsgSubmit submit=new MsgSubmit();
			submit.setTotalLength(12+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+subLength+20);
			submit.setCommandId(MsgCommand.CMPP_SUBMIT);
			submit.setSequenceId(seqId);			
			submit.setPkTotal((byte)msgSendCount);
			submit.setPkNumber((byte)(i+1));		
			submit.setRegisteredDelivery((byte)0x00);
			submit.setMsgLevel((byte)0x01);
			submit.setFeeUserType((byte)0x00);
			submit.setFeeTerminalId("");			
			submit.setFeeTerminalType((byte)0x00);			
			submit.setTpPId((byte)0x00);
			submit.setTpUdhi((byte)0x01);
			submit.setMsgFmt((byte)0x08);
			submit.setMsgSrc(MsgConfig.getSpId());
			submit.setSrcId(MsgConfig.getSpCode());
			submit.setDestTerminalId(mobileId);
			submit.setMsgLength((byte)subLength);
			submit.setMsgContent(sendMsg);
			dataList.add(submit);
		}
		return dataList;
	}
	
	public static MsgHead getCancelMsg(){
		MsgHead cancelMsg=new MsgHead();
		cancelMsg.setTotalLength(12);//��Ϣ�ܳ��ȣ������ֽ���:4+4+4(��Ϣͷ)+6+16+1+4(��Ϣ����)
		cancelMsg.setCommandId(MsgCommand.CMPP_TERMINATE);//��ʶ��������
		cancelMsg.setSequenceId(MsgUtil.getSequence());//���У�������ָ��
		return cancelMsg;
	}
	
	public static MsgHead getActivityTest(){
		MsgHead testMsg=new MsgHead();
		testMsg.setTotalLength(12);//��Ϣ�ܳ��ȣ������ֽ���:4+4+4(��Ϣͷ)+6+16+1+4(��Ϣ����)
		testMsg.setCommandId(MsgCommand.CMPP_ACTIVE_TEST);//��ʶ��������
		testMsg.setSequenceId(MsgUtil.getSequence());//���У�������ָ��
		return testMsg;
	}
	
	public static MsgActiveTestResp getMsgActiveTestResp(byte[] data){
		MsgHead head = new MsgHead(data);
		MsgActiveTestResp resp = new MsgActiveTestResp();
		resp.setTotalLength(12+1);
		resp.setCommandId(MsgCommand.CMPP_ACTIVE_TEST_RESP);
		resp.setSequenceId(head.getSequenceId());
		resp.setReserved((byte)0x00);
		return resp;
		
	}
	
}
