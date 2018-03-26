package com.wxd.toutiao.comm;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxiaodan on 2018/3/23.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://www.toutiao.com/api/pc/feed/?category=%E7%BB%84%E5%9B%BE&utm_source=toutiao&max_behot_time={max_behot_time}&as={as}&cp={cp}&_signature={signature}";

        String initSignature = "XsrS8gAABHLE9ksQ9zwbxl7K0u";
        String initMax_behot_time = "1522050415";
        Map<String,String> asAndCp = getAsAndCp();
        String initAs = asAndCp.get("as");
        String initCp = asAndCp.get("cp");
        String trueUrl = url.replace("{max_behot_time}",initMax_behot_time).replace("{as}",initAs).replace("{cp}",initCp).replace("{signature}",initSignature);

        System.out.println(trueUrl);
        String s = HttpUtils.get(trueUrl,getHeader());

       System.out.println(s);
    }


    /**
     * 获取请求头
     * @return
     */
    public static Map<String,String> getHeader() {
        Map<String,String> header = new HashMap<String, String>();
        header.put("Accept","text/javascript, text/html, application/xml, text/xml, */*");
        header.put("Accept-Encoding","gzip, deflate, br");
        header.put("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        header.put("Connection","keep-alive");
        header.put("Content-Type","application/x-www-form-urlencoded");
        header.put("Cookie","uuid=\"w:440309b23e3b442c91e25c6a80e29bc2\"; tt_webid=6537115410453382670; tt_webid=6537115410453382670; UM_distinctid=16260b36b7069c-0c3cf17293b80b8-495860-fa000-16260b36b7253d; CNZZDATA1259612802=1100292544-1522037638-%7C1522053935; __tasessionId=wiydaqbkk1522052264980");
        header.put("Host","www.toutiao.com");
        header.put("Referer","https://www.toutiao.com/ch/news_image/");
        header.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:59.0) Gecko/20100101 Firefox/59.0");
        header.put("X-Requested-With","XMLHttpRequest");
        return header;
    }


    public static Map<String, String> getAsAndCp() {
        Map<String, String> result = new HashMap<String, String>();
        long l = System.currentTimeMillis();
        long t = l / 1000;
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
