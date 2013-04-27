/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-10下午06:09:34</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import com.jan.util.StringUtil;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2012-12-10 </p>
 * @version V1.0  
 */
public class IphoneMsg{

	public static void main(String[] args) {
		File file = new File("E:\\temp\\msm\\smsHistory.txt");
//		File file = new File("E:\\temp\\msm\\test.txt");
		File osFile = new File("E:\\temp\\msm\\sms2.csv");
		BufferedReader reader = null;
		
		try {
			if(!osFile.exists()){
				osFile.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(osFile,true); 
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder sb = new StringBuilder();
			out.write("PhoneNumber,DateCreated,Content,Type,State".getBytes());
			out.write("\n".getBytes());
			int count = 0;
			while((line=reader.readLine())!=null){
				if(line.indexOf("-> 我")>0){
					sb.append(StringUtil.trim(line.substring(0, line.indexOf("->")-1))).append(",");
					sb.append(StringUtil.trim(line.subSequence(line.indexOf("(")+1, line.indexOf(")")))).append(",");
					line = reader.readLine();
					sb.append("\"").append(StringUtil.trim(line)).append("\"");
					sb.append(",recv,read");
					sb.append("\n");
				}
				if(line.indexOf("我 ->")>-1){
					sb.append(StringUtil.trim(line.substring(line.indexOf("->")+2,line.indexOf("(")))).append(",");
					sb.append(StringUtil.trim(line.subSequence(line.indexOf("(")+1, line.indexOf(")")))).append(",");
					line = reader.readLine();
					sb.append("\"").append(StringUtil.trim(line)).append("\"");
					sb.append(",send,unread");
					sb.append("\n");
				}
				out.write(sb.toString().getBytes());
				sb = new StringBuilder();
				count++;
			}
			System.out.println("sms count "+count);
			out.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
}
