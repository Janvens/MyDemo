/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-19����01:44:32</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-19 </p>
 * @version V1.0  
 */
public class ThreadPoolHolder{
	
	private ThreadPoolHolder(){}
	
	public static ThreadPoolExecutor getPoolInstance(){
		return THolder.taskPool;
	}
	
	private static class THolder{
		static ThreadPoolExecutor taskPool = new ThreadPoolExecutor(1, 20, 10,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20),new ThreadPoolExecutor.DiscardOldestPolicy());
	}
	
}
