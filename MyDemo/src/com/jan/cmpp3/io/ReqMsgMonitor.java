/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19下午02:16:59</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class ReqMsgMonitor{

	public static void monitor(){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new MonitorTask(), 0, 2, TimeUnit.SECONDS);
	}
}

class MonitorTask implements Runnable{

	private static Logger logger=Logger.getLogger(MonitorTask.class);
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		int activeThreadCount = ThreadPoolHolder.getPoolInstance().getActiveCount();
		if(activeThreadCount > 0){
			return ;
		}
		try{
			DataInputStream in = SocketHolder.getDin();
			if(in != null){
				int len=in.readInt();
				if(null!=in && len !=0){
					byte[] reqData =new byte[len-4];
					in.read(reqData);
					ReqHandler.handleReq(reqData);
					logger.info(">MonitorTask< 响应完成");
				}
			}
		}catch (IOException e) {
			logger.error("处理ISMG 请求异常",e);
		}
	}
	
}
