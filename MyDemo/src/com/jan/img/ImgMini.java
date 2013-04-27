/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-2-18����02:43:58</p>
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
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2013-2-18 </p>
 * @version V1.0  
 */
public class ImgMini{

	public static void main(String[] args) {
		try {
//			File fi = new File("E:/temp/1.jpg"); //��ͼ�ļ�
//			File fo = new File("E:/temp/2.jpg"); //��Ҫת������Сͼ�ļ�
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
	 * <p>�����ˣ�Zhang Wensheng , 2013-2-18 ����02:58:01</p>
	 * @param iFile ����ͼƬ·��
	 * @param oFile ���ͼƬ·��
	 * @param nWidth ���ͼƬ��� ��λ������
	 * @param nHeight ���ͼƬ�߶� ��λ������
	 * @throws IOException 
	 */
	public void transFile(String iFile, String oFile, int nWidth, int nHeight) throws IOException{
		File fi = new File(iFile); //��ͼ�ļ�
		File fo = new File(oFile); //��Ҫת������Сͼ�ļ�
		
		AffineTransform transform = new AffineTransform();
		BufferedImage bis = ImageIO.read(fi);
		int w = bis.getWidth();//��ȡԭͼ��Ŀ��
		int h = bis.getHeight();//��ȡԭͼ��ĸ߶�
		
		double sx = (double)nWidth / w; //���Ա�
        double sy = (double)nHeight / h; //���Ա�
        
        transform.setToScale(sx,sy);
        
        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage bid = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_3BYTE_BGR);
        ato.filter(bis,bid);
        ImageIO.write(bid, "jpeg", fo);//���ͼ���ʽΪjpg 
	}
	
}
