package com.jan.cmpp3.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * �����������Ϣͷ<br/>
 * totalLength ��Ϣ�ܳ���<br/>
 * commandId ��������<br/>
 * sequenceId ��Ϣ��ˮ��,˳���ۼ�,����Ϊ1,ѭ��ʹ�ã�һ�������Ӧ����Ϣ����ˮ�ű�����ͬ��<br/>
 * Unsigned Integer  	�޷�������<br/>
 * Integer	��������Ϊ������������������<br/>
 * Octet String	�����ַ�����λ������ʱ�������0��ASCII��ʾ��������䣬����Ҳ�0�򲹶����Ƶ����Ա�ʾ�ַ����Ľ�����
 * @author �ſ�ΰ
 * 2011-08-22 11:44 
 */
public class MsgHead {
	private Logger logger=Logger.getLogger(MsgHead.class);
	private int totalLength;//Unsigned Integer ��Ϣ�ܳ���
	private int commandId;//Unsigned Integer ��������
	private int sequenceId;//Unsigned Integer ��Ϣ��ˮ��,˳���ۼ�,����Ϊ1,ѭ��ʹ�ã�һ�������Ӧ����Ϣ����ˮ�ű�����ͬ��
	public byte[] toByteArry(){
		ByteArrayOutputStream bous=new ByteArrayOutputStream();
		DataOutputStream dous=new DataOutputStream(bous);
		try {
			dous.writeInt(this.getTotalLength());
			dous.writeInt(this.getCommandId());
			dous.writeInt(this.getSequenceId());
			dous.close();
		} catch (IOException e) {
			logger.error("��װCMPP��Ϣͷ����������ʧ�ܡ�");
		}
		return bous.toByteArray();
	}
	public MsgHead(byte[] data){
		ByteArrayInputStream bins=new ByteArrayInputStream(data);
		DataInputStream dins=new DataInputStream(bins);
		try {
			this.setTotalLength(data.length+4);
			this.setCommandId(dins.readInt());
			this.setSequenceId(dins.readInt());			
			dins.close();
			bins.close();
		} catch (IOException e){}
	}
	public MsgHead(){
		super();
	}
	public int getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}
	public int getCommandId() {
		return commandId;
	}
	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}
	public int getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
}
