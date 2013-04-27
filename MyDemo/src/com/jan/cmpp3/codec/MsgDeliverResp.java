package com.jan.cmpp3.codec;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
public class MsgDeliverResp extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgDeliverResp.class);
	private long msg_Id;//��Ϣ��ʶ��CMPP_DELIVER�е�Msg_Id�ֶΣ�
	private int result;//��� 0����ȷ 1����Ϣ�ṹ�� 2�������ִ� 3����Ϣ����ظ� 4����Ϣ���ȴ� 5���ʷѴ���� 6�����������Ϣ�� 7��ҵ������8: �������ƴ�9~ ����������
	public byte[] toByteArry(){
		ByteArrayOutputStream bous=new ByteArrayOutputStream();
		DataOutputStream dous=new DataOutputStream(bous);
		try {
			dous.writeInt(this.getTotalLength());
			dous.writeInt(this.getCommandId());
			dous.writeInt(this.getSequenceId());
			dous.writeLong(this.msg_Id);
			dous.writeInt(this.result);
			dous.close();
		} catch (IOException e) {
			logger.error("��װ���Ӷ���������ʧ�ܡ�");
		}
		return bous.toByteArray();
	}
	public long getMsg_Id() {
		return msg_Id;
	}

	public void setMsg_Id(long msg_Id) {
		this.msg_Id = msg_Id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
