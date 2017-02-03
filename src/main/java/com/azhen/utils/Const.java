package com.azhen.utils;

/**
 * @author handsomeWS
 * 
 */
public class Const {

	// 防止SQL注入的方法
	public static boolean voidSQL(String str) {

		/*boolean str2 = true;
		for (int i = 0; i < str.trim().length(); i++) {
			String tempStr = new String(str.substring(i, i + 1));
			if (tempStr.equals("=") || tempStr.equals("'") || tempStr.equals(":")) {
				str2 = false;
				break;
			}
		}
		return str2;
	}*/
		boolean str2 = true;
		for (int i = 0; i < str.trim().length(); i++) {
			char c = str.charAt(i);
			if(c == '=' ||c =='\'' || c ==':') {
				str2 = false;
				break;
			}
		}
		return str2;
	}
}
