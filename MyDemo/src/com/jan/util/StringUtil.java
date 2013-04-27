/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-10下午06:36:01</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.util;

import java.util.UUID;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2012-12-10 </p>
 * @version V1.0  
 */
public class StringUtil{

	
	public static String trim(Object obj){
		if(null==obj){
			return "";
		}else{
			return obj.toString().trim();
		}
	}
	
	public static void main(String[] args) {
		
		int a = 4;
		int b = 2;
		int c = 3;
		System.out.println(System.currentTimeMillis());
		
	}
	
	
	
	
	
}
