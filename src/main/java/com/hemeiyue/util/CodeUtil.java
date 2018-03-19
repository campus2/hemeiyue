package com.hemeiyue.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CodeUtil {

	static int BACKK =  0xff000000;
	static final int WHITE = 0xFFFFFFFF;
	
	//定义二维码的样式  
    static final int width = 300;  
    static final int height = 300;  
    static final String format = "png";  
	
	public static void main(String[] args) {
		createQRImg(null,"createQRImg(HttpServletResponse response, String contents)","这是附加信息");
	}
	
	@SuppressWarnings("all")
	public static void createQRImg(HttpServletResponse response, String contents,String desc) {
        //定义二维码的参数  
		HashMap hints = new HashMap();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//设置二维码的容错等级  
        hints.put(EncodeHintType.MARGIN, 2);//边距  
          
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);  
            //
            ServletOutputStream stream = response.getOutputStream();
            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
          //获取二维码的宽高度
            int width = img.getWidth();
            int hight = img.getHeight();
            //使用Graphics2D画图
            Graphics2D gd = img.createGraphics();
            gd.setColor(Color.RED);
            Font font=new Font("宋体",Font.PLAIN,24);  
            gd.setFont(font); 
            //计算文字长度，设置居中
            FontMetrics fm = gd.getFontMetrics(font);
            int textWidh = fm.stringWidth(desc);
            int widthX = (width-textWidh)/2;
            gd.drawString(desc, widthX, hight+1);
            gd.dispose();
            
            ImageIO.write(img, format, stream);
//            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);  
        } catch (Exception e) {  
            e.printStackTrace();
            System.out.println("二维码生成失败");
        }  
	}
	
	@SuppressWarnings("all")
	public static void createQRImg(String path) throws IOException, WriterException {
        String contents = "http://blog.csdn.net/qq_30507287";//扫描二维码时产生的连接  
       
        //定义二维码的参数  
		HashMap hints = new HashMap();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//设置二维码的容错等级  
        hints.put(EncodeHintType.MARGIN, 2);//边距  
          
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);  
            Path file = new File("/media/cedo/cedo/img.png").toPath();//保存的路径  
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 二维码解码
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static String decode(File file) throws IOException, NotFoundException {
		BufferedImage image;
		image = ImageIO.read(file);
		if(image == null) {
			return null;
		}
		 LuminanceSource source = new BufferedImageLuminanceSource(image);
		 BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		 Result result;
		 Hashtable hints = new Hashtable();
		 hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		 result = new MultiFormatReader().decode(bitmap, hints);
		 String resultStr = result.getText();
		 return resultStr;
	}
}
