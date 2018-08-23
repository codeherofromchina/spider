package novel.dao.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by wangxiaodan on 2018/7/28.
 */
@Repository
public class OSSHelper {
    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");
    private OSSClient[] ossClients = new OSSClient[10];
    private int currentOSSClientIndex = 0;
    private boolean destroyed = false;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public OSSHelper(@Value("${oss.endpoint}") String endpoint, @Value("${oss.accessKeyId}") String accessKeyId,
                     @Value("${oss.accessKeySecret}") String accessKeySecret, @Value("${oss.bucketName}") String bucketName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁OSSClient");
        int length = ossClients.length;
        for (int n = 0; n < length; n++) {
            OSSClient ossClient = ossClients[n];
            if (ossClient != null) {
                ossClient.shutdown();
                ossClients[n] = null;
            }
        }
        destroyed = true;
    }

    @PostConstruct
    public void initOSSClient() {
        System.out.println("初始化OSSClient");
        int length = ossClients.length;
        for (int i = 0; i < length; i++) {
            ClientConfiguration conf = new ClientConfiguration();
            // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
            conf.setMaxConnections(200);
            // 设置Socket层传输数据的超时时间，默认为50000毫秒。
            conf.setSocketTimeout(10000);
            // 设置建立连接的超时时间，默认为50000毫秒。
            conf.setConnectionTimeout(10000);
            // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
            conf.setConnectionRequestTimeout(1000);
            // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
            conf.setIdleConnectionTime(10000);
            ossClients[i] = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        destroyed = false;
    }


    /**
     * 在键中存入指定内容
     *
     * @param key
     * @param content
     * @return
     */
    public boolean putObject(String key, String content) {
        if (StringUtils.isAnyBlank(key, content)) {
            return false;
        }
        boolean result = true;
        OSSClient ossClient = getOSSClient();
        InputStream is = new ByteArrayInputStream(content.getBytes(DEFAULT_CHARSET));
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, key, is);
        return result;
    }

    /**
     * 给定的键是否已经存在，存在返回true，其他返回false
     *
     * @param key
     * @return
     */
    public boolean doesObjectExist(String key) {
        if (StringUtils.isAnyBlank(key)) {
            return false;
        }
        OSSClient ossClient = getOSSClient();
        return ossClient.doesObjectExist(bucketName, key);
    }

    /**
     * 给定键获取内容
     *
     * @param key
     * @return
     */
    public String getObject(String key) {
        if (StringUtils.isAnyBlank(key)) {
            return null;
        }
        OSSClient ossClient = getOSSClient();
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, key);
            InputStream inputStream = ossObject.getObjectContent();
            StringBuilder objectContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_CHARSET));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                objectContent.append(line);
            }
            inputStream.close();
            return objectContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OSSClient getOSSClient() {
        if (destroyed) {
            return null;
        }
        int length = ossClients.length;
        int times = 1;
        OSSClient ossClient = null;
        while (true) {
            ossClient = ossClients[currentOSSClientIndex % length];
            currentOSSClientIndex++;
            if (ossClient != null || ++times > length) {
                break;
            }
        }
        return ossClient;
    }
}
