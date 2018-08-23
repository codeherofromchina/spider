import com.alibaba.fastjson.JSON;
import novel.spider.Spider;
import novel.spider.domain.NovelCatalog;
import novel.spider.util.SpiderUtils;

import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/8/13.
 */
public class SpiderTest {

    public static void main(String[] args) throws IOException, ScriptException {
        BufferedReader reader01 = new BufferedReader(new FileReader("/Users/wangxiaodan/tmp/dd/groove.txt"));
        StringBuilder scriptBuilder = new StringBuilder();
        String line;
        while ((line = reader01.readLine()) != null) {
            scriptBuilder.append(line);
        }
        reader01.close();


        BufferedReader reader02 = new BufferedReader(new FileReader("/Users/wangxiaodan/tmp/dd/webResp.html"));
        StringBuilder bookInfoResponse = new StringBuilder();
        while ((line = reader02.readLine()) != null) {
            bookInfoResponse.append(line);
        }
        reader02.close();


        Spider spider = new Spider();
        List<String> novelCatalogs = spider.spiderBookList("https://www.qidian.com/free/all?page=3", SpiderUtils.groovyEngine(scriptBuilder.toString(), null), Charset.forName("utf-8"));

        String s = JSON.toJSONString(novelCatalogs);
        System.out.println(s);
    }
}
