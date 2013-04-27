/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24上午11:36:28</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import com.jan.cmpp3.io.MsgUtil;
import com.jan.cmpp3.io.RespHandler;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class Cmpp3handler extends SimpleChannelUpstreamHandler{

	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
		System.out.println("messageReceived     >> "+e.getMessage());
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		ByteBuffer bBuffer = buffer.toByteBuffer();
		
		
		ByteArrayInputStream bins=new ByteArrayInputStream(bBuffer.array());
		DataInputStream dins=new DataInputStream(bins);
		System.out.println("s16 1> "+MsgUtil.bytesToHexString(bBuffer.array()));
		int c = dins.readInt();
		byte[] b = new byte[c-4];
		
		dins.read(b);
		System.out.println("s16 2> "+MsgUtil.bytesToHexString(b));
		System.out.println("length > "+b.length);
//		RespHandler.handleResp(b);
		ISMGMsgHandler.handle(b);
		dins.close();
		
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#channelConnected(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ChannelStateEvent)
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stubs
		super.channelConnected(ctx, e);
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ExceptionEvent)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stubs
		super.exceptionCaught(ctx, e);
	}
	
}
