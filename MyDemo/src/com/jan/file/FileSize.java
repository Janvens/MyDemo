/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-4-22����02:40:16</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.file;

import java.io.File;
import java.io.FileInputStream;

/** 
 * desc:
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-4-22 </p>
 * @version V1.0  
 */
public class FileSize{

	public static void main(String[] args) {
		File file = new File("E:\\temp\\ccc.txt");
		long s;
		try {
			s = getFileSizes(file);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static long getFileSizes(File f) throws Exception {// ȡ���ļ���С
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("�ļ�������");
		}
		return s;
	}
	
}
