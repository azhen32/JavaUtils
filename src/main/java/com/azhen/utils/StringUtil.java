package com.azhen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @version 1.1
 */
public class StringUtil {

	public static boolean isBlank(String str) {
		boolean event = false;
		if ("".equals(str) || str == null)
			event = true;
		return event;
	}

	public static boolean isNotBlank(String str) {
		boolean event = false;
		if ((!"".equals(str)) && str != null)
			event = true;
		return event;
	}
	
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\b|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
