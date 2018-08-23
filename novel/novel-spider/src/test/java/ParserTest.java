import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import novel.comm.util.DateUtil;
import novel.spider.domain.NovelCatalog;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/8/13.
 */
public class ParserTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/wangxiaodan/tmp/dd/webResp.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        List<NovelCatalog> novelCatalogs = parseBookCatalog(stringBuilder.toString());
        System.out.println(JSON.toJSONString(novelCatalogs));


    }

    public static List<NovelCatalog> parseBookCatalog(String catalogContent) {
        List<NovelCatalog> result = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(catalogContent);
            Elements volume = doc.getElementsByClass("volume");
            int size = volume.size();
            for (int i = 0; i < size; i++) {
                Element element = volume.get(i);
                Element subscri = element.getElementsByTag("h3").first();
                String text = subscri.text();
                String volumeName = text.substring(text.indexOf(" ") + 1, text.indexOf("·"));
                if ("作品相关".equals(volumeName)) {
                    continue;
                };
                Elements liEle = element.getElementsByTag("li");
                int size02 = liEle.size();
                for (int n = 0; n < size02; n++) {
                    Element catalogEle = liEle.get(n);
                    Element a = catalogEle.getElementsByTag("a").first();
                    //Elements sS = catalogEle.getElementsByTag("em");
                    String dataRid = catalogEle.attr("data-rid");
                    String catalogName = a.text();
                    String sourcesUrl = a.attr("href");
                    String timeAndWordCount = a.attr("title");
                    String time = timeAndWordCount.substring(timeAndWordCount.indexOf("首发时间：") + 5, timeAndWordCount.indexOf(" 章节字数："));
                    String wordCount = timeAndWordCount.substring(timeAndWordCount.indexOf(" 章节字数：") + 6);
                    NovelCatalog novelCatalog = new NovelCatalog();
                    novelCatalog.setName(catalogName);
                    novelCatalog.setShowName(catalogName);
                    novelCatalog.setVolumeName(volumeName);
                    novelCatalog.setVolumeNum(i);
                    novelCatalog.setSourceUrl(sourcesUrl);
                    if (StringUtils.isNumeric(wordCount)) {
                        novelCatalog.setWordCount(Integer.parseInt(wordCount));
                    };
                    if (StringUtils.isNumeric(dataRid)) {
                        novelCatalog.setCatalogNum(Integer.parseInt(dataRid));
                    };
                    //novelCatalog.setVip(sS.size() > 0);
                    novelCatalog.setPubTime(DateUtil.parse(time, DateUtil.LONG_FORMAT));
                    result.add(novelCatalog);
                };
            };
        } catch (Exception ex) {
            ex.printStackTrace();
        };
        return result;
    }

    public static List<NovelCatalog> parseBookCatalog2(String catalogContent) {
        List<NovelCatalog> result = new ArrayList<>();
        try {
            JSONObject jsonContent = JSON.parseObject(catalogContent);
            int code = jsonContent.getIntValue("code");
            if (0 != code) {
                return result;
            }
            ;
            JSONArray volumeArr = jsonContent.getJSONObject("data").getJSONArray("vs");
            int volumeSize = volumeArr.size();
            for (int i = 0; i < volumeSize; i++) {
                JSONObject volume = volumeArr.getJSONObject(i);
                String volumeName = volume.getString("vN");
                JSONArray catalogArr = volume.getJSONArray("cs");
                int catalogSize = catalogArr.size();
                for (int n = 0; n < catalogSize; n++) {
                    JSONObject catalogObj = catalogArr.getJSONObject(n);
                    NovelCatalog novelCatalog = new NovelCatalog();
                    String catalogName = catalogObj.getString("cN");
                    novelCatalog.setName(catalogName);
                    novelCatalog.setShowName(catalogName);
                    novelCatalog.setVolumeName(volumeName);
                    novelCatalog.setSourceId(catalogObj.getString("cU"));
                    novelCatalog.setWordCount(catalogObj.getIntValue("cnt"));
                    novelCatalog.setVip(catalogObj.getIntValue("sS") == 0);
                    novelCatalog.setPubTime(DateUtil.parse(catalogObj.getString("uT"), DateUtil.LONG_FORMAT));
                    result.add(novelCatalog);
                }
                ;
            }
            ;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ;
        return result;
    }

    ;

}
