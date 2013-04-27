/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-4-19下午01:43:43</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.cmpp3.test;

import com.jan.cmpp3.io.MsgSender;


/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-4-19 </p>
 * @version V1.0  
 */
public class Cmpp3Test{

	public static void main(String[] args) {
		MsgSender.sendSubmit("测试短信", "13802981106");
		
	}
	
}
