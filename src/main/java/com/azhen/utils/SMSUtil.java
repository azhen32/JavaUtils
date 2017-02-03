package com.azhen.utils;

import java.util.Random;

public class SMSUtil {
	/**
	 * 产生短信验证码
	 * @param n
	 * @return
	 */
	public static String getCheckCode(int n){  
		String num = "";
		for (int i = 0 ; i < n ; i ++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
		}
}
