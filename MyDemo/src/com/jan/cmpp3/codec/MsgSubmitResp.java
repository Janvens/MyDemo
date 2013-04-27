package com.jan.cmpp3.codec;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
public class MsgSubmitResp extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgSubmitResp.class);
	private long msgId;
	private int result;//��� 0����ȷ 1����Ϣ�ṹ�� 2�������ִ� 3����Ϣ����ظ� 4����Ϣ���ȴ� 5���ʷѴ���� 6�����������Ϣ�� 7��ҵ������ 8���������ƴ� 9�������ز��������˼ƷѺ��� 10��Src_Id���� 11��Msg_src���� 12��Fee_terminal_Id���� 13��Dest_terminal_Id����
	public MsgSubmitResp(byte[] data){
		if(data.length==8+8+4){
			ByteArrayInputStream bins=new ByteArrayInputStream(data);
			DataInputStream dins=new DataInputStream(bins);
			try {
				this.setTotalLength(data.length+4);
				this.setCommandId(dins.readInt());
				this.setSequenceId(dins.readInt());
				this.msgId=dins.readLong();
				this.result=dins.readInt();
				dins.close();
				bins.close();
			} catch (IOException e){}
		}else{
			logger.info("���Ͷ���IMSP�ظ�,�������ݰ����������Ȳ�һ�¡�����Ϊ:"+data.length);
		}
	}
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
