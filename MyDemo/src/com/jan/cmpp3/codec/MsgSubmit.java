package com.jan.cmpp3.codec;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.jan.cmpp3.io.MsgUtil;
public class MsgSubmit extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgSubmit.class);
	private long msgId = 0;
	private byte pkTotal = 0x01;
	private byte pkNumber = 0x01;
	private byte registeredDelivery = 0x00;
	private byte msgLevel = 0x01;
	private String serviceId = "";
	private byte feeUserType = 0x00;// ˭���գ���˭�ķ�
	private String feeTerminalId = "";
	private byte feeTerminalType = 0x00;
	private byte tpPId = 0x00;
	private byte tpUdhi = 0x00;
	private byte msgFmt = 0x0f;
	private String msgSrc;
	// 01���ԡ��Ʒ��û����롱��ѣ�
	// 02���ԡ��Ʒ��û����롱��������Ϣ�ѣ�
	// 03���ԡ��Ʒ��û����롱��������ȡ��Ϣ��

	private String feeType = "01";// Ĭ��Ϊ����
	private String feeCode = "000000";
	private String valIdTime = "";// �ݲ�֧��
	private String atTime = "";// �ݲ�֧��
	// SP�ķ�������ǰ׺Ϊ�������ĳ�����,
	// ���ؽ��ú����������SMPPЭ��Submit_SM��Ϣ��Ӧ��source_addr�ֶΣ��ú����������û��ֻ�����ʾΪ����Ϣ�����к��롣
	private String srcId;
	private byte destUsrTl = 0x01;// ��֧��Ⱥ��
	private String destTerminalId;// �����ֻ����룬
	private byte destTerminalType = 0x00;// ��ʵ����
	private byte msgLength;
	private byte[] msgContent;
	// �㲥ҵ��ʹ�õ�LinkID���ǵ㲥��ҵ���MT���̲�ʹ�ø��ֶ�
	private String linkID = "";
	
	public byte[] toByteArry(){
		ByteArrayOutputStream bous=new ByteArrayOutputStream();
		DataOutputStream dous=new DataOutputStream(bous);
		try {
			dous.writeInt(this.getTotalLength());
			dous.writeInt(this.getCommandId());
			dous.writeInt(this.getSequenceId());
			dous.writeLong(this.msgId);//Msg_Id ��Ϣ��ʶ����SP����Ķ������ر���������������
			dous.writeByte(this.pkTotal);//Pk_total ��ͬMsg_Id����Ϣ������
			dous.writeByte(this.pkNumber);//Pk_number ��ͬMsg_Id����Ϣ��ţ���1��ʼ
			dous.writeByte(this.registeredDelivery);//Registered_Delivery �Ƿ�Ҫ�󷵻�״̬ȷ�ϱ���
			dous.writeByte(this.msgLevel);//Msg_level ��Ϣ����
			MsgUtil.writeString(dous,this.serviceId,10);//Service_Id ҵ���ʶ�������֡���ĸ�ͷ��ŵ���ϡ�
			dous.writeByte(this.feeUserType);//Fee_UserType �Ʒ��û������ֶ� 0����Ŀ���ն�MSISDN�Ʒѣ�1����Դ�ն�MSISDN�Ʒѣ�2����SP�Ʒ�;3����ʾ���ֶ���Ч����˭�ƷѲμ�Fee_terminal_Id�ֶΡ�
			MsgUtil.writeString(dous,this.feeTerminalId,32);//Fee_terminal_Id ���Ʒ��û��ĺ���
			dous.writeByte(this.feeTerminalType);//Fee_terminal_type ���Ʒ��û��ĺ������ͣ�0����ʵ���룻1��α��
			dous.writeByte(this.tpPId);//TP_pId
			dous.writeByte(this.tpUdhi);//TP_udhi
			dous.writeByte(this.msgFmt);//Msg_Fmt
			MsgUtil.writeString(dous,this.msgSrc,6);//Msg_src ��Ϣ������Դ(SP_Id)
			MsgUtil.writeString(dous,this.feeType,2);//FeeType �ʷ����
			MsgUtil.writeString(dous,this.feeCode,6);//FeeCode
			MsgUtil.writeString(dous,this.valIdTime,17);//�����Ч��
			MsgUtil.writeString(dous,this.atTime,17);//��ʱ����ʱ��
			MsgUtil.writeString(dous,this.srcId,21);//Src_Id spCode
			dous.writeByte(this.destUsrTl);//DestUsr_tl
			MsgUtil.writeString(dous,this.destTerminalId,32);//Dest_terminal_Id
			dous.writeByte(this.destTerminalType);//Dest_terminal_type ���ն��ŵ��û��ĺ������ͣ�0����ʵ���룻1��α��
			dous.writeByte(this.msgLength);//Msg_Length
			dous.write(this.msgContent);//��Ϣ����			
			MsgUtil.writeString(dous,this.linkID,20);//�㲥ҵ��ʹ�õ�LinkID
			dous.close();
		} catch (IOException e) {
			logger.error("��װ���ŷ��Ͷ���������ʧ�ܡ�");
		}
		return bous.toByteArray();
	}
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public byte getPkTotal() {
		return pkTotal;
	}
	public void setPkTotal(byte pkTotal) {
		this.pkTotal = pkTotal;
	}
	public byte getPkNumber() {
		return pkNumber;
	}
	public void setPkNumber(byte pkNumber) {
		this.pkNumber = pkNumber;
	}
	public byte getRegisteredDelivery() {
		return registeredDelivery;
	}
	public void setRegisteredDelivery(byte registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}
	public byte getMsgLevel() {
		return msgLevel;
	}
	public void setMsgLevel(byte msgLevel) {
		this.msgLevel = msgLevel;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public byte getFeeUserType() {
		return feeUserType;
	}
	public void setFeeUserType(byte feeUserType) {
		this.feeUserType = feeUserType;
	}
	public String getFeeTerminalId() {
		return feeTerminalId;
	}
	public void setFeeTerminalId(String feeTerminalId) {
		this.feeTerminalId = feeTerminalId;
	}
	public byte getFeeTerminalType() {
		return feeTerminalType;
	}
	public void setFeeTerminalType(byte feeTerminalType) {
		this.feeTerminalType = feeTerminalType;
	}
	public byte getTpPId() {
		return tpPId;
	}
	public void setTpPId(byte tpPId) {
		this.tpPId = tpPId;
	}
	public byte getTpUdhi() {
		return tpUdhi;
	}
	public void setTpUdhi(byte tpUdhi) {
		this.tpUdhi = tpUdhi;
	}
	public byte getMsgFmt() {
		return msgFmt;
	}
	public void setMsgFmt(byte msgFmt) {
		this.msgFmt = msgFmt;
	}
	public String getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(String msgSrc) {
		this.msgSrc = msgSrc;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getValIdTime() {
		return valIdTime;
	}
	public void setValIdTime(String valIdTime) {
		this.valIdTime = valIdTime;
	}
	public String getAtTime() {
		return atTime;
	}
	public void setAtTime(String atTime) {
		this.atTime = atTime;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public byte getDestUsrTl() {
		return destUsrTl;
	}
	public void setDestUsrTl(byte destUsrTl) {
		this.destUsrTl = destUsrTl;
	}
	public String getDestTerminalId() {
		return destTerminalId;
	}
	public void setDestTerminalId(String destTerminalId) {
		this.destTerminalId = destTerminalId;
	}
	public byte getDestTerminalType() {
		return destTerminalType;
	}
	public void setDestTerminalType(byte destTerminalType) {
		this.destTerminalType = destTerminalType;
	}
	public byte getMsgLength() {
		return msgLength;
	}
	public void setMsgLength(byte msgLength) {
		this.msgLength = msgLength;
	}
	public byte[] getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(byte[] msgContent) {
		this.msgContent = msgContent;
	}
	public String getLinkID() {
		return linkID;
	}
	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}
}