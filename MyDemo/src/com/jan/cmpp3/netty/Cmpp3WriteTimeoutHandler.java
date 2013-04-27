/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午09:02:34</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.timeout.WriteTimeoutHandler;
import org.jboss.netty.util.Timer;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class Cmpp3WriteTimeoutHandler extends WriteTimeoutHandler{

	private Timer timer;
	private int seconds;
	
	/**
	 * Zhang Wensheng 2013-4-24 下午09:02:52
	 * @param timer
	 * @param timeoutSeconds
	 */
	public Cmpp3WriteTimeoutHandler(Timer timer, int timeoutSeconds) {
		// TODO Auto-generated constructor stubs
		super(timer, timeoutSeconds);
		this.timer = timer;
		this.seconds = timeoutSeconds;
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.WriteTimeoutHandler#writeTimedOut(org.jboss.netty.channel.ChannelHandlerContext)
	 */
	@Override
	protected void writeTimedOut(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stubs
		super.writeTimedOut(ctx);
		System.out.println("-----------writeTimedOut-----");
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelDownstreamHandler#connectRequested(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ChannelStateEvent)
	 */
	@Override
	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stubs
		super.connectRequested(ctx, e);
		System.out.println("-------connectRequested--------- ");
	}

}
