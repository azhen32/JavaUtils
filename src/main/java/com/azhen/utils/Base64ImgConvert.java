package com.azhen.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.derby.tools.sysinfo;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.alibaba.fastjson.JSONObject;

import cc.messcat.entity.Product;
import cc.messcat.web.qcmr.form.bean.ProductForm;
import cc.messcat.web.qcmr.vo.basic.Image;
import cc.messcat.web.qcmr.vo.basic.Result;
import cc.messcat.wechat.weixin.popular.bean.User;
import cc.messcat.wechat.weixin.popular.client.LocalHttpClient;
import edu.emory.mathcs.backport.java.util.Arrays;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ImgConvert {
	 //图片转化成base64字符串  
    public static String GetImageStr(String imgFile)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        //String imgFile = "..//test.jpg";//待处理的图片  
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
    
    public static void main(String[] args) {
    	
    	String str = GetImageStr("/home/azhen/Desktop/1568510217.jpg");
    	HttpUriRequest httpUriRequest = RequestBuilder.post()
    			//.setUri("http://localhost:8080:/qcmr/uploadProductImgAction!uploadImg.action")
    			.setUri("http://qcmr.junrenwl.com:8080:/qcmr/uploadProductImgAction!uploadImg.action")
    			.addParameter("images[0].base64FileString",str)
    			.addParameter("images[0].fileName","111.jpg")
    			.addParameter("images[1].base64FileString",str)
    			.addParameter("images[1].fileName","111.jpg")
    			.addParameter("productId", "124")
    			.build();
    	Result result = LocalHttpClient.executeJsonResult(httpUriRequest, Result.class);
    	System.out.println(JSONObject.toJSONString(result));
    	
	}
}
