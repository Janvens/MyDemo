/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24上午10:34:07</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.netty;

import java.util.concurrent.TimeUnit;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.queue.BufferedWriteHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class MyNettyClientPipelineFactory implements ChannelPipelineFactory{
	private final Timer timer = new HashedWheelTimer();
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		
		pipeline.addLast("timeout", new MyTimeoutHandler(timer, 10, 20, 20, TimeUnit.SECONDS));
		pipeline.addLast("buffer", new Cmpp3handler());
		return pipeline;
	}

}
