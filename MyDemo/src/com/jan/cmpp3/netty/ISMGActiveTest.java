/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-24下午07:19:39</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.netty;

import com.jan.cmpp3.codec.MsgHead;
import com.jan.cmpp3.io.MsgHelper;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-24 </p>
 * @version V1.0  
 */
public class ISMGActiveTest{

	public static void test(){
		MsgHead test = MsgHelper.getActivityTest();
		Cmpp3Sender sender = new Cmpp3Sender(test.toByteArry(), MsgNettySender.getChannelInstance());
		sender.send();
	}
	
}
