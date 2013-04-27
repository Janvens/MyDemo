/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2013-2-18下午02:43:58</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.img;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2013-2-18 </p>
 * @version V1.0  
 */
public class ImgMini{

	public static void main(String[] args) {
		try {
//			File fi = new File("E:/temp/1.jpg"); //大图文件
//			File fo = new File("E:/temp/2.jpg"); //将要转换出的小图文件
//			
//			AffineTransform transform = new AffineTransform();
//			BufferedImage bis = ImageIO.read(fi);
//			int w = bis.getWidth();
//			int h = bis.getHeight();
//			
//			
//			double scale = w/h;
//			
//			int nw = 120;
//            int nh = (nw * h) / w;
//            if(nh>120) {
//                nh = 120;
//                nw = (nh * w) / h;
//            }
//            
//            double sx = (double)nw / w;
//            double sy = (double)nh / h;
//            transform.setToScale(sx,sy);
//            
//            AffineTransformOp ato = new AffineTransformOp(transform, null);
//            BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
//            ato.filter(bis,bid);
//            ImageIO.write(bid, "jpeg", fo);
			
			ImgMini mi = new ImgMini();
			mi.transFile("E:/temp/1.jpg", "E:/temp/2.jpg", 100, 180);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * desc:
	 * <p>创建人：Zhang Wensheng , 2013-2-18 下午02:58:01</p>
	 * @param iFile 输入图片路径
	 * @param oFile 输出图片路径
	 * @param nWidth 输出图片宽度 单位：像素
	 * @param nHeight 输出图片高度 单位：像素
	 * @throws IOException 
	 */
	public void transFile(String iFile, String oFile, int nWidth, int nHeight) throws IOException{
		File fi = new File(iFile); //大图文件
		File fo = new File(oFile); //将要转换出的小图文件
		
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi);
		int w = bis.getWidth();//获取原图像的宽度
		int h = bis.getHeight();//获取原图像的高度
		
		double sx = (double)nWidth / w; //缩略比
        double sy = (double)nHeight / h; //缩略比
        
        transform.setToScale(sx,sy);
        
        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage bid = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_3BYTE_BGR);
        ato.filter(bis,bid);
        ImageIO.write(bid, "jpeg", fo);//输出图像格式为jpg 
	}
	
}
