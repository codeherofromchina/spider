package novel.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public final class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    private static final int DEFAULT_TIMEOUT = 5000; // 默认过期时间为5秒
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8"); // 默认UTF-8编码


    public static void main(String[] args) throws IOException {
        String url = "https://www.biduo.cc/biquge/36_36667/";
        String response = get(url, Charset.forName("GBK"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/wangxiaodan/tmp/dd/ddd.txt")));
        writer.write(response);
        writer.close();
    }


    public static String get(String url) {
        return get(url, null);
    }

    /**
     * 发送get请求到指定url
     *
     * @param url
     * @return
     */
    public static String get(String url, Charset defaultCharset) {
        LOGGER.debug("请求开始[{}]", url);
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_TIMEOUT)   //设置连接超时时间
                .setConnectionRequestTimeout(DEFAULT_TIMEOUT) // 设置请求超时时间
                .setSocketTimeout(DEFAULT_TIMEOUT)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        httpGet.setConfig(requestConfig);

        String strResult = "";
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                if (defaultCharset != null) {
                    strResult = EntityUtils.toString(entity, defaultCharset);//获得返回的结果
                } else {
                    strResult = EntityUtils.toString(entity);//获得返回的结果
                }
            } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                System.out.println("页面不存在");
            } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                System.out.println("服务器端异常");
            } else {
                System.out.println("请求其他异常：【" + httpResponse.getStatusLine() + "】");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.debug("请求结束[{}][{}]", url, strResult);
        return strResult;
    }


    /**
     * 检查url格式是否正确
     * TODO 这里待完善
     *
     * @param url
     * @return
     */
    private static boolean checkUrl(String url) {

        return true;
    }


    /**
     * 获取编码
     *
     * @param entity
     * @return
     */
    private static Charset getCharset(HttpEntity entity) {
        ContentType contentType = ContentType.get(entity);
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
            if (charset == null) {
                ContentType reader = ContentType.getByMimeType(contentType.getMimeType());
                charset = reader != null ? reader.getCharset() : null;
            }
        }
        return charset;
    }
}
