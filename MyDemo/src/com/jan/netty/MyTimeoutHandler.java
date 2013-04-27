/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-26下午02:40:28</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.netty;

import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.Timer;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-26 </p>
 * @version V1.0  
 */
public class MyTimeoutHandler extends IdleStateHandler{

	/**
	 * Zhang Wensheng 2013-4-26 下午02:41:28
	 * @param timer
	 * @param readerIdleTime
	 * @param writerIdleTime
	 * @param allIdleTime
	 * @param unit
	 */
	public MyTimeoutHandler(Timer timer, long readerIdleTime,
			long writerIdleTime, long allIdleTime, TimeUnit unit) {
		// TODO Auto-generated constructor stubs
		super(timer, readerIdleTime, writerIdleTime, allIdleTime, unit);
	}
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.IdleStateHandler#channelIdle(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.handler.timeout.IdleState, long)
	 */
	@Override
	protected void channelIdle(ChannelHandlerContext ctx, IdleState state,
			long lastActivityTimeMillis) throws Exception {
		// TODO Auto-generated method stubs
		super.channelIdle(ctx, state, lastActivityTimeMillis);
		
		System.out.println(">>>>>  "+lastActivityTimeMillis);
	}

}
