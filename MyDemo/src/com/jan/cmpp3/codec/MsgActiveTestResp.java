package com.jan.cmpp3.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

public class MsgActiveTestResp extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgActiveTestResp.class);
	private byte reserved;//
	
	public MsgActiveTestResp(){
		super();
	}
	
	public MsgActiveTestResp(byte[] data){
		if(data.length==8+1){
			ByteArrayInputStream bins=new ByteArrayInputStream(data);
			DataInputStream dins=new DataInputStream(bins);
			try {
				this.setTotalLength(data.length+4);
				this.setCommandId(dins.readInt());
				this.setSequenceId(dins.readInt());
				this.reserved=dins.readByte();
				dins.close();
				bins.close();
			} catch (IOException e){}
		}else{
			logger.info("链路检查,解析数据包出错，包长度不一致。长度为:"+data.length);
		}
	}
	
	public byte[] toByteArry(){
		ByteArrayOutputStream bous=new ByteArrayOutputStream();
		DataOutputStream dous=new DataOutputStream(bous);
		try {
			dous.writeInt(this.getTotalLength());
			dous.writeInt(this.getCommandId());
			dous.writeInt(this.getSequenceId());
			dous.write(reserved);
			dous.close();
		}catch (IOException e) {
			logger.error("封装二进制数组失败",e);
		}
		return bous.toByteArray();
	}
	
	public byte getReserved() {
		return reserved;
	}

	public void setReserved(byte reserved) {
		this.reserved = reserved;
	}
}
