/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午04:12:24</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class Cmpp3PipelineFactory implements ChannelPipelineFactory{

	private final Timer timer = new HashedWheelTimer();
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("wTimeout", new Cmpp3WriteTimeoutHandler(timer, 5));
		pipeline.addLast("rTimeout", new Cmpp3ReadTimeoutHandler(timer,10));
		pipeline.addLast("IdleState", new IdleStateAwareChannelHandler());
		pipeline.addLast("cmpp3", new Cmpp3handler());
		return pipeline;
	}

}
