/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午04:16:55</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import com.jan.netty.MyNettyClientPipelineFactory;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class MsgNettySender{

	private static ClientBootstrap bootstrap;
	private static Channel channel;
	
	public static Channel getChannelInstance(){
		if(channel != null && channel.isConnected()){
			return channel;
		}else{
			bootstrap = new ClientBootstrap(
					new NioClientSocketChannelFactory(
							Executors.newCachedThreadPool(),
							Executors.newCachedThreadPool()));
			
			// Configure the pipeline factory.
			bootstrap.setPipelineFactory(new Cmpp3PipelineFactory());
			
			// Start the connection attempt.
			ChannelFuture future = bootstrap.connect(new InetSocketAddress("192.168.1.199", 7890));
			
			future.awaitUninterruptibly();
			if(future.isSuccess()){
				channel =  future.getChannel();
			}else{
				future.getCause().printStackTrace();
				bootstrap.releaseExternalResources();
			}
//			bootstrap.setOption("keepAlive", true);
//			bootstrap.setOption("tcpNoDelay", true);
//			bootstrap.releaseExternalResources();
		}
        return channel;
	}
}
