package com.jan.cmpp3.codec;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
/**
 * ISMG��CMPP_CONNECT_RESP��Ϣ��ӦSP����������<br/>
 * status��Ӧ״̬״̬ 0����ȷ 1����Ϣ�ṹ�� 2���Ƿ�Դ��ַ 3����֤�� 4���汾̫�� 5~ ����������<br/>
 * authenticatorISMG SMG��֤�룬���ڼ���ISMG�� ��ֵͨ������MD5 hash����ó�����ʾ���£� Authent
 * icatorISMG =MD5��Status+AuthenticatorSource+shared secret����Shared secret ���й��ƶ���Դ��ַʵ�������̶���
 * AuthenticatorSourceΪԴ��ַʵ�巢�͸�ISMG�Ķ�Ӧ��ϢCMPP_Connect�е�ֵ�� ��֤����ʱ������Ϊ�ա�<br/>
 * version������֧�ֵ���߰汾�ţ�����3.0�İ汾����4bitΪ3����4λΪ0<br/>
 */
public class MsgConnectResp extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgConnectResp.class);
	private int status;//��Ӧ״̬״̬ 0����ȷ 1����Ϣ�ṹ�� 2���Ƿ�Դ��ַ 3����֤�� 4���汾̫�� 5~ ����������
	private String statusStr;//��Ӧ״̬״̬ 0����ȷ 1����Ϣ�ṹ�� 2���Ƿ�Դ��ַ 3����֤�� 4���汾̫�� 5~ ����������
	private byte[] authenticatorISMG;//	ISMG��֤�룬���ڼ���ISMG�� ��ֵͨ������MD5 hash����ó�����ʾ���£� AuthenticatorISMG =MD5��Status+AuthenticatorSource+shared secret����Shared secret ���й��ƶ���Դ��ַʵ�������̶���AuthenticatorSourceΪԴ��ַʵ�巢�͸�ISMG�Ķ�Ӧ��ϢCMPP_Connect�е�ֵ�� ��֤����ʱ������Ϊ�ա�
	private byte version;//	������֧�ֵ���߰汾�ţ�����3.0�İ汾����4bitΪ3����4λΪ0
	public MsgConnectResp(byte[] data){
		if(data.length==8+4+16+1){
			ByteArrayInputStream bins=new ByteArrayInputStream(data);
			DataInputStream dins=new DataInputStream(bins);
			try {
				this.setTotalLength(data.length+4);
				this.setCommandId(dins.readInt());
				this.setSequenceId(dins.readInt());
				this.setStatus(dins.readInt());
				byte[] aiByte=new byte[16];
				dins.read(aiByte);
				this.authenticatorISMG=aiByte;
				this.version=dins.readByte();
				dins.close();
				bins.close();
			} catch (IOException e){}
		}else{
			logger.info("������IMSP,�������ݰ����������Ȳ�һ�¡�����Ϊ:"+data.length);
		}
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		switch(status){
			case 0 : statusStr="��ȷ";break;
			case 1 : statusStr="��Ϣ�ṹ��";break;
			case 2 : statusStr="�Ƿ�Դ��ַ";break;
			case 3 : statusStr="��֤��";break;
			case 4 : statusStr="�汾̫��";break;
			case 5 : statusStr="��������";break;
			default:statusStr=status+":δ֪";break;
		}
	}
	public byte[] getAuthenticatorISMG() {
		return authenticatorISMG;
	}
	public void setAuthenticatorISMG(byte[] authenticatorISMG) {
		this.authenticatorISMG = authenticatorISMG;
	}
	public byte getVersion() {
		return version;
	}
	public void setVersion(byte version) {
		this.version = version;
	}
	public String getStatusStr() {
		return statusStr;
	}
}

