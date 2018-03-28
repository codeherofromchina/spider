package com.wxd.toutiao.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangxiaodan on 2018/3/26.
 */
public final class TouTiaoUtils {
	private final static Logger LOGGER = LoggerFactory.getLogger(TouTiaoUtils.class);
	private final static String IMAGE_URL = "https://www.toutiao.com/api/pc/feed/";

	public static String fetchImage(String category, String timestamp, String sign, Map<String, String> header) {
		Map<String, String> params = getAsAndCp();
		params.put("category", category);
		params.put("utm_source", "toutiao");
		params.put("max_behot_time", timestamp);
		params.put("_signature", sign);

		String reqUrl = joinOfGetUrl(IMAGE_URL, params);
		try {
			return HttpUtils.get(reqUrl, header);
		} catch (IOException e) {
			LOGGER.error("异常", e, reqUrl, header);
		}
		return null;
	}

	public static String joinOfGetUrl(String url, Map<String, String> params) {
		StringBuffer sBuffer = new StringBuffer(url);
		if (params != null && params.size() > 0) {
			sBuffer.append("?");
			for (Entry<String, String> entry : params.entrySet()) {
				try {
					sBuffer.append(entry.getKey()).append("=")
							.append(URLEncoder.encode(entry.getValue(), HttpUtils.DEFAULT_CHARSET)).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			sBuffer.setLength(sBuffer.length() - 1);
		}
		return sBuffer.toString();
	}

	/**
	 * 获取请求中的as和cp的参数值
	 * 
	 * @return
	 */
	public static Map<String, String> getAsAndCp() {
		Map<String, String> result = new HashMap<String, String>();
		long t = System.currentTimeMillis() / 1000;
		String e = Long.toHexString(t).toUpperCase();
		String i = Md5Utils.md5(String.valueOf(t)).toUpperCase();
		if (e.length() == 8) {
			StringBuffer sBuffer = new StringBuffer();
			StringBuffer rBuffer = new StringBuffer();
			for (int n = 0; n < 5; n++) {
				sBuffer.append(i.substring(n, n + 1) + e.substring(n, n + 1));
				int tmp = 5 - n;
				rBuffer.append(e.substring(n + 3, n + 4)).append(i.substring(i.length() - tmp, i.length() - tmp + 1));
			}
			result.put("as", "A1" + sBuffer.toString() + e.substring(e.length() - 3));
			result.put("cp", e.substring(0, 3) + rBuffer.toString() + "E1");

		} else {
			result.put("as", "479BB4B7254C150");
			result.put("cp", "7E0AC8874BB0985");
		}
		return result;
	}

}
