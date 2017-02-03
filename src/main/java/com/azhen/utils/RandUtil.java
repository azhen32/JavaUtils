package com.azhen.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 随机工具
 * @author Jesse
 *
 */
public class RandUtil {
	private static Logger log = LoggerFactory.getLogger(RandUtil.class); 
	/**
	 * 随机生成指定长度的数字串
	 * @param count
	 * @return
	 */
	public static String getRandomStr(int count) { 
		StringBuffer str = new StringBuffer("");
		if (count > 0) {
			Random rand = new Random();
			for (int i = 0; i< count;i++ ) {
				str.append(rand.nextInt(10));
			}
		}
		return str.toString();
	}
	
	
	public static void main(String[] args) {
		log.info(getRandomStr(4));
	}
}
