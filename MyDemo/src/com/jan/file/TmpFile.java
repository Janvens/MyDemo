/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-3-18上午10:04:43</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import org.springframework.util.FileCopyUtils;


/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-3-18 </p>
 * @version V1.0  
 */
public class TmpFile{

	
	public static void main(String[] args) throws Exception {
		File file = new File("C:/Users/admin/Pictures/壁纸/1358989991558.jpg");
		InputStream is = new FileInputStream(file);
		
		
		byte[] b = new byte[(int) file.length()];
		int i = 0;
		int ti = 0;
		while((ti=is.read()) != -1){
			b[i] = (byte) ti;
			i++;
		}
		is.close();
		File temF = File.createTempFile("tmp","",new File("C:/Users/admin/AppData/Local/Temp/"));
		if(temF.exists()){
			System.out.println("ok");
		}
		FileCopyUtils.copy(b, temF);
		
		System.out.println(temF.getPath());
		System.out.println(temF.getAbsolutePath());
	}
}
