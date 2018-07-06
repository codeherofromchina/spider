package novel.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public final class HttpUtils {
    private static final int DEFAULT_TIMEOUT = 5000; // 默认过期时间为5秒

    /**
     * 发送get请求到指定url
     * @param url
     * @return
     */
    public static String get(String url) {
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_TIMEOUT)   //设置连接超时时间
                .setConnectionRequestTimeout(DEFAULT_TIMEOUT) // 设置请求超时时间
                .setSocketTimeout(DEFAULT_TIMEOUT)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        httpGet.setConfig(requestConfig);

        String strResult = "";
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
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
        return strResult;
    }


    /**
     * 检查url格式是否正确
     *  TODO 这里待完善
     * @param url
     * @return
     */
    private static boolean checkUrl(String url) {

        return true;
    }
}
