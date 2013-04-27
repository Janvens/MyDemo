/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19下午05:12:42</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import com.jan.cmpp3.codec.MsgHead;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class MsgActiveTester{
	
	public static void test(){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new ISMGTest(), 0, 2, TimeUnit.SECONDS);
	}

}

class ISMGTest implements Runnable{
	
	private static Logger logger = Logger.getLogger(ISMGTest.class);
	
	/* 
	 * desc:
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		MsgHead test = MsgHelper.getActivityTest();
		try{
			DataOutputStream dout = SocketHolder.getDout();
			dout.write(test.toByteArry());
			dout.flush();
		}catch (IOException e) {
			logger.error("请求测试ISMG异常 ",e);
		}
	}
}
