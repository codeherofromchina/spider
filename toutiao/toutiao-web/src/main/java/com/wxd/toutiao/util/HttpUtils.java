package com.wxd.toutiao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by wangxiaodan on 2018/3/23.
 */
public final class HttpUtils {
    private static String DEFAULT_CHARSET = "UTF-8";


    /**
     * http的get请求指定url地址
     *
     * @param url
     * @return 状态200返回页面字符串，否则返回null
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }


    public static String originGet(String url, Map<String, String> header) throws IOException {
        StringBuffer result = new StringBuffer();
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(),entry.getValue());
            }
        }
        conn.setDoOutput(true);
        conn.connect();


        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),DEFAULT_CHARSET));
        String line = null;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        conn.disconnect();



        return result.toString();
    }

    /**
     * http的get请求指定url地址
     *
     * @param url
     * @param header 请求header
     * @return 状态200返回页面字符串，否则返回null
     */
    public static String get(String url, Map<String, String> header) throws IOException {
        if (StringUtils.isNotBlank(url)) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = null;
            try {
                if (header != null && header.size() > 0) {
                    for (Map.Entry<String, String> entry : header.entrySet()) {
                        httpGet.setHeader(entry.getKey(),entry.getValue());
                    }
                }
                response = httpclient.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    HttpEntity entity = response.getEntity();
                    byte[] contentBytes = EntityUtils.toByteArray(entity);
                    String charset = getCharSetByBody(new String(contentBytes));
                    charset = StringUtils.defaultString(charset, DEFAULT_CHARSET);
                    return new String(contentBytes, charset);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        }
        return null;
    }


    /**
     * 根据页面body获取字符编码
     *
     * @param html
     * @return
     */
    private static String getCharSetByBody(String html) {
        String charset = null;
        Document document = Jsoup.parse(html);
        Elements elements = document.select("meta");
        for (Element metaElement : elements) {
            if (metaElement != null && StringUtils.isNotBlank(metaElement.attr("http-equiv")) && metaElement.attr("http-equiv").toLowerCase().equals("content-type")) {
                String content = metaElement.attr("content");
                charset = getCharSet(content);
                break;
            }
        }
        return charset;
    }


    /**
     * 正则获取字符编码
     *
     * @param content
     * @return
     */
    private static String getCharSet(String content) {
        String regex = ".*charset=([^;]*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
