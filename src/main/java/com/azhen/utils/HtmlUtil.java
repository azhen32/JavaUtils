package com.azhen.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("unchecked")
public class HtmlUtil {
	/**
	 * 对html中img的src进行转码
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static List getImgsUrl(String html) throws Exception {
		List imgsList = new ArrayList();
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.getElementsByTag("img");
		if (imgs != null) {
			for (Iterator it = imgs.iterator(); it.hasNext();) {
				Element element = (Element) it.next();
				String imgUrl = element.attr("src");
				if (StringUtil.isNotBlank(imgUrl)) {
					try {
						String imgUrl2 = java.net.URLDecoder.decode(imgUrl, "utf-8");
						imgsList.add(imgUrl2);
					} catch (Exception e) {
						throw new Exception("URL转码出错，被转码的字符串：" + imgUrl);
					}
				}
			}
		}
		return imgsList;
	}

	public static void main(String[] args) {
		String temp = "<img src=\"/zxl/userfiles/image/%E6%8D%95%E8%8E%B7.PNG\" width=\"452\" height=\"321\" alt=\"\" />sadfasfd<img src=\"/zxl1/userfiles1/image1/%E6%8D%95%E8%8E%B7.PNG\" width=\"452\" height=\"321\" alt=\"\" />sdaf";
		try {
			List list = getImgsUrl(temp);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
