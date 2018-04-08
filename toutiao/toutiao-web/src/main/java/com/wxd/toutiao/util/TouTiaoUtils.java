package com.wxd.toutiao.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangxiaodan on 2018/3/26.
 */
public final class TouTiaoUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(TouTiaoUtils.class);
    private final static String IMAGE_URL = "https://www.toutiao.com/api/pc/feed/";
    private final static String TOUTIAO_URL = "https://www.toutiao.com/";
    private final static Pattern imageDetailPattern = Pattern.compile("JSON\\.parse\\(\"(.*)\"\\),\\s*siblingList");
    private final static Pattern recommendPattern = Pattern.compile("siblingList: (.*),\\s*publish_time:");

    public static String fetchImage(String category, String timestamp, String sign, Map<String, String> header) {
        Map<String, String> params = getAsAndCp();
        if (StringUtils.isNotBlank(category)) {
            params.put("category", category);
        }
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


    public static JSONObject fetchImageDetail(String itemId, Map<String, String> header) {
        String reqUrl = TOUTIAO_URL + "a" + itemId;
        JSONObject result = new JSONObject();
        try {
            String content = HttpUtils.get(reqUrl, header);
            Matcher imageDetailMatcher = imageDetailPattern.matcher(content);
            if (imageDetailMatcher.find()) {
                String group = imageDetailMatcher.group(1);
                group = group.replace("\\", "");
                JSONObject imageDetailJson = JSONObject.parseObject(group);
                // 处理副标题
                JSONArray subTitles = handleUnicodeArray(imageDetailJson.getJSONArray("sub_titles"));
                imageDetailJson.put("sub_titles", subTitles);
                // 处理说明
                JSONArray subAbstracts = handleUnicodeArray(imageDetailJson.getJSONArray("sub_abstracts"));
                imageDetailJson.put("sub_abstracts", subAbstracts);

                result.put("detail", imageDetailJson);
            } else {
                return null;
            }

            Matcher recommendMatcher = recommendPattern.matcher(content);
            if (recommendMatcher.find()) {
                String group = recommendMatcher.group(1);
                JSONArray recommendArray = JSONObject.parseArray(group);
                result.put("recommend", recommendArray);
            }
            return result;
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


    /**
     * unicode转中文
     *
     * @param dataStr
     * @return 中文
     */
    public static String UnicodeTochinese(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        while (index < dataStr.length()) {
            if (!"\\u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));
                index++;
                continue;
            }
            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            index += 6;
        }
        return buffer.toString();
    }

    /**
     * 将unicode的字符串数组处理成中文数组
     *
     * @param array
     * @return
     */
    private static JSONArray handleUnicodeArray(JSONArray array) {
        JSONArray result = new JSONArray();
        if (array != null && array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                String string = array.getString(i);
                if (StringUtils.isNotBlank(string)) {
                    string = TouTiaoUtils.UnicodeTochinese(string.replace("u", "\\u"));
                }
                result.add(string);
            }
        }
        return result;
    }

}
