package com.azhen.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jaxen.function.ext.EndsWithFunction;

import com.mysql.jdbc.Field;

import cc.modules.constants.Constants;
import sun.misc.BASE64Decoder;

/**
 * 转换base64上传的图片String，并还原图片
 * @author azhen
 *
 */
public class Base64ImageUpload {

	public static String uploadImage(String imageBase64, String uploadFileName, String savePath) throws Exception {

		String fileName = null;
		if (imageBase64 != null && !"".equals(imageBase64)) {
			OutputStream os = null;
			try {
				byte[] uploadfile = Base64Convert.GenerateImage2Byte(imageBase64);

				String inputFileExt = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

				fileName = (new StringBuffer(String.valueOf((new DateHelper()).getRandomNum()))).append(".")
						.append(inputFileExt).toString();

				String outputFilePath = new StringBuffer(ServletActionContext.getServletContext().getRealPath(savePath))
						.append(Constants.FILE_SEPARATOR).append(fileName).toString();

				File file = new File(outputFilePath);
				if (!file.exists() && file.createNewFile()) {
					os = new FileOutputStream(outputFilePath);
					os.write(uploadfile);
					os.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (os != null)
					os.close();
			}
		}
		return fileName;

	}
}
