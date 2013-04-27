package com.jan.cmpp3.codec;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.jan.cmpp3.io.MsgUtil;

/**
 * SP�������ӵ�ISMG��Ϣ�嶨��CMPP_CONNECT������Ŀ����SP��ISMGע����Ϊһ���Ϸ�SP��ݣ�
 * ��ע��ɹ��󼴽�����Ӧ�ò�����ӣ��˺�SP����ͨ����ISMG���պͷ��Ͷ��š�<br/>
 * Source_Addr:Octet String	Դ��ַ���˴�ΪSP_Id����SP����ҵ���롣<br/>
 * AuthenticatorSource:Octet String	���ڼ���Դ��ַ����ֵͨ������MD5 hash����ó�����ʾ���£�
 * AuthenticatorSource =MD5��Source_Addr+9 �ֽڵ�0 +shared secret+timestamp��
 * Shared secret ���й��ƶ���Դ��ַʵ�������̶���timestamp��ʽΪ��MMDDHHMMSS��������ʱ���룬10λ��<br/>
 * Version:Unsigned Integer	˫��Э�̵İ汾��(��λ4bit��ʾ���汾��,��λ4bit��ʾ�ΰ汾��)������3.0�İ汾����4bitΪ3����4λΪ0<br/>
 * Timestamp:Unsigned Integer	ʱ���������,�ɿͻ��˲���,��ʽΪMMDDHHMMSS��������ʱ���룬10λ���ֵ����ͣ��Ҷ��� ��<br/>
 */
public class MsgConnect extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgConnect.class);
	private String sourceAddr;//Դ��ַ���˴�ΪSP_Id����SP����ҵ���롣
	private byte[] authenticatorSource;//���ڼ���Դ��ַ����ֵͨ������MD5 hash����ó�����ʾ���£�AuthenticatorSource = MD5��Source_Addr+9 �ֽڵ�0 +shared secret+timestamp�� Shared secret ���й��ƶ���Դ��ַʵ�������̶���timestamp��ʽΪ��MMDDHHMMSS��������ʱ���룬10λ��
	private byte version;//˫��Э�̵İ汾��(��λ4bit��ʾ���汾��,��λ4bit��ʾ�ΰ汾��)������3.0�İ汾����4bitΪ3����4λΪ0
	private int timestamp;//ʱ���������,�ɿͻ��˲���,��ʽΪMMDDHHMMSS��������ʱ���룬10λ���ֵ����ͣ��Ҷ��� ��
	
	public byte[] toByteArry(){
		ByteArrayOutputStream bous=new ByteArrayOutputStream();
		DataOutputStream dous=new DataOutputStream(bous);
		try {
			dous.writeInt(this.getTotalLength());
			dous.writeInt(this.getCommandId());
			dous.writeInt(this.getSequenceId());
			MsgUtil.writeString(dous,this.sourceAddr,6);
			dous.write(authenticatorSource);
			dous.writeByte(0x30);
			dous.writeInt(Integer.parseInt(MsgUtil.getTimestamp()));
			dous.close();
		} catch (IOException e) {
			logger.error("��װ���Ӷ���������ʧ�ܡ�");
		}
		return bous.toByteArray();
	}
	public String getSourceAddr() {
		return sourceAddr;
	}
	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}
	public byte[] getAuthenticatorSource() {
		return authenticatorSource;
	}
	public void setAuthenticatorSource(byte[] authenticatorSource) {
		this.authenticatorSource = authenticatorSource;
	}
	public byte getVersion() {
		return version;
	}
	public void setVersion(byte version) {
		this.version = version;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
}
