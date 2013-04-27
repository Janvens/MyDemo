/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午04:26:41</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class Cmpp3Sender{

	private byte[] data;
	private Channel channel;
	/**
	 * Zhang Wensheng 2013-4-24 下午04:27:31
	 * @param data
	 * @param channel
	 */
	public Cmpp3Sender(byte[] data, Channel channel) {
		super();
		this.data = data;
		this.channel = channel;
	}
	
	public void send(){
		ChannelBuffer buffer = ChannelBuffers.buffer(data.length);
		buffer.writeBytes(data);
		channel.write(buffer);
	}
	
}
