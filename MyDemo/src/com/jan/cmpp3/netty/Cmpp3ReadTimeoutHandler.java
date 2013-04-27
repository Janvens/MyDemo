/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午07:36:54</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.Timer;
import com.jan.util.DateTimeUtil;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class Cmpp3ReadTimeoutHandler extends ReadTimeoutHandler{

	private Timer timer;
	private int seconds;
	
	/**
	 * Zhang Wensheng 2013-4-24 下午07:37:11
	 * @param timer
	 * @param timeoutSeconds
	 */
	public Cmpp3ReadTimeoutHandler(Timer timer, int timeoutSeconds) {
		super(timer, timeoutSeconds);
		this.timer = timer;
		this.seconds = timeoutSeconds;
		
	}

	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.ReadTimeoutHandler#afterAdd(org.jboss.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void afterAdd(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stubs
		super.afterAdd(ctx);
		System.out.println(">>>>>>>>>>>>>>  afterAdd > "+DateTimeUtil.datetime12());
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.ReadTimeoutHandler#beforeAdd(org.jboss.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void beforeAdd(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stubs
		super.beforeAdd(ctx);
		System.out.println(">>>>>>>>>>>>>>  beforeAdd");
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
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.ReadTimeoutHandler#readTimedOut(org.jboss.netty.channel.ChannelHandlerContext)
	 */
	@Override
	protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stubs
		super.readTimedOut(ctx);
		
	}
}
