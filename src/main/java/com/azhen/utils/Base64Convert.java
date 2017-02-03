package com.azhen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 图片文件base64编码转换
 * @author azhen
 *
 */
public class Base64Convert {
	
	public static void main(String args[]){
		//String imageBase64 = GetImageStr("D://banner-1.png");
		//WriteStringToFile5("D:\\imageBase64.txt", imageBase64);
		
		String imageBase64 = readTxtFile("..\\app.txt");
		//System.out.println(imageBase64);
		GenerateImage(imageBase64, "../upload/");
	}
	
	public static void WriteStringToFile5(String filePath, String str) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static String readTxtFile(String filePath) {
		StringBuffer str = new StringBuffer();
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					//System.out.println(lineTxt);
					str.append(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
			
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return str.toString();
	}
	
	 //图片转化成base64字符串  
    public static String GetImageStr(String imagePath)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = imagePath;//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片并上传到某个文件夹中  
    public static String GenerateImage(String imgStr, String upLoadPath)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  UUID.randomUUID().toString() +
            String name = UUID.randomUUID().toString() + ".jpg";
            String imgFilePath = upLoadPath + name;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return name;  
        }
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return null;  
        }  
    }
    
  //base64字符串转化图片字节数组  
    public static byte[] GenerateImage2Byte(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            
            return b;
        }
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return null;  
        }  
    }  
    
    //将BASE64编码字符串转成字节数组
    public static byte[] decodeBase64(String input) throws Exception{
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(input);
    }
	
    //将字节数组转成BASE64编码字符串
	public static String encodeBase64(byte[]input) throws Exception{  
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(input);
	}
}
