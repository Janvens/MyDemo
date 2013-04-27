/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24上午10:33:16</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import com.jan.cmpp3.codec.MsgConnect;
import com.jan.cmpp3.codec.MsgSubmit;
import com.jan.cmpp3.io.MsgHelper;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class MyNettyClient{

	public static void main(String[] args) {
		// Configure the client.
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        
     // Configure the pipeline factory.
        bootstrap.setPipelineFactory(new MyNettyClientPipelineFactory());
        
     // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("192.168.1.199", 7890));
        
        future.awaitUninterruptibly();
        
     // Wait until the connection attempt succeeds or fails.
        if (!future.isSuccess()){
            future.getCause().printStackTrace();
            bootstrap.releaseExternalResources();
            return;
        }
        if(future.getChannel().isConnected()){
        	
        	System.out.println(">> send reqData ... ");
        	MsgSubmit submit = MsgHelper.getSubmitMsg("test", "13802981106");
        	byte[] reqData = submit.toByteArry();
        	ChannelBuffer buffer = ChannelBuffers.buffer(reqData.length);
        	buffer.writeBytes(reqData);
        	future.getChannel().write(buffer);
        }
        System.out.println(System.currentTimeMillis());
        
	}
	
}
