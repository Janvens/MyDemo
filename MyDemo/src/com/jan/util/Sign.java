/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2012-12-27����05:09:07</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.util;

import com.umpay.SignEnc;
import com.umpay.SignEncException;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2012-12-27 </p>
 * @version V1.0  
 */
public class Sign{

	public static void main(String[] args) {
		
		String value = "";
		
		String plain = "funCode=1001&merId=50020001&mobileId=13878112208&smsContent=�������̲��Զ���&version=1.0";
		String sign = "";
		try {
			sign = SignEnc.sign(plain);
		} catch (SignEncException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sign);
		
	}
	
}
