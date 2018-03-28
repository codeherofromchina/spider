package com.wxd.toutiao.util;

import com.wxd.toutiao.dao.impl.AbstractTouTiaoDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxiaodan on 2018/3/26.
 */
public final class TouTiaoUtils {
    private final static String IMAGE_URL = "https://www.toutiao.com/api/pc/feed/";

    public static String fetchImage(String category ,String timestamp ,String sign){

        return null;
    }



    /**
     * 获取请求中的as和cp的参数值
     * @return
     */
    public static Map<String, String> getAsAndCp() {
        Map<String, String> result = new HashMap<String, String>();
        long t = System.currentTimeMillis()/1000;
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
