package novel.spider.qidian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import novel.spider.Parser;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
import novel.util.DateUtil;
import novel.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class QiDianParser implements Parser {


    public static void main(String[] args) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/wangxiaodan/tmp/dd/ddd.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        reader.close();
        QiDianParser parser = new QiDianParser();

        List<NovelCatalog> novelCatalogs = parser.parseBookCatalog(stringBuffer.toString());
        for (NovelCatalog catalog : novelCatalogs) {
            System.out.println(catalog);
        }
    }

    @Override
    public NovelList parseList(String content) {
        Document doc = Jsoup.parse(content);
        // 解析所有图书链接
        Elements elements = doc.getElementsByAttributeValue("data-eid", "qd_B58");
        if (elements == null) {
            elements = doc.getElementsByAttributeValue("data-eid", "qd_E05");
        }
        List<String> hrefs = elements.eachAttr("href");
        hrefs = hrefs.parallelStream().map(href -> PROTOCOL + href).collect(Collectors.toList());
        // 解析总页码
        int totalPages = 1;
        Elements elementsByClass = doc.getElementsByClass("lbf-pagination-page");
        if (elementsByClass != null || elementsByClass.size() > 0) {
            Element last = elementsByClass.last();
            String html = last.html();
            if (StringUtils.isNumericSpace(html)) {
                totalPages = Integer.parseInt(html.trim());
            }
        }

        NovelList novelList = new NovelList();
        novelList.setBookInfoUrls(hrefs);
        novelList.setTotalPages(totalPages);
        return novelList;
    }

    @Override
    public Novel parseBookInfo(String content) {
        Novel novel = new Novel();

        Document doc = Jsoup.parse(content);
        Element bookInfo = doc.getElementsByClass("book-info").first();
        // 查找图书名称
        Element name = bookInfo.getElementsByTag("em").first();
        novel.setName(name.html());
        // 简短介绍
        Element intro = bookInfo.getElementsByClass("intro").first();
        novel.setIntro(intro.html());
        // 作者
        Element author = bookInfo.getElementsByAttributeValue("data-eid", "qd_G08").first();
        novel.setAuthor(author.html());
        // 图书描述
        Element desc = doc.getElementsByClass("book-intro").first();
        novel.setDesc(desc.html());
        // 是否连载
        Element lianzai = bookInfo.getElementsByClass("blue").first();
        novel.setStat("连载".equals(lianzai.html()));
        // 封面图
        Element coverPhoto = doc.getElementsByAttributeValue("data-eid", "qd_G09").first().child(0);
        novel.setCoverPhoto(PROTOCOL + coverPhoto.attr("src"));
        // 父级分类
        Elements pTypes = doc.getElementsByAttributeValue("data-eid", "qd_G10");
        novel.setParentTypes(pTypes.html());
        // 子分类
        Elements sTypes = bookInfo.getElementsByAttributeValue("data-eid", "qd_G11");
        novel.setSonTypes(sTypes.html());
        // 获取sourceId
        Element sourcesId = doc.getElementById("bookImg");
        novel.setSourceId(sourcesId.attr("data-bid"));
        // 获取source
        Elements sources = bookInfo.getElementsByAttributeValue("data-eid", "qd_G18");
        novel.setSources(sources.attr("href"));
        // 生成唯一标识uuid
        novel.setUuid(UUID.randomUUID().toString());
        // 解析标签
        Elements labelEles = doc.getElementsByAttributeValue("data-eid", "qd_G70");
        if (labelEles != null && labelEles.size() > 0) {
            List<String> labels = new ArrayList<>();
            labelEles.forEach(vo -> {
                labels.add(vo.html());
            });
            novel.setLabelList(labels);
        }

        return novel;
    }

    /**
     * 解析阅读内容
     *
     * @param bookInfo
     * @return
     */
    @Override
    public String parseBookContent(String bookInfo) {
        Document doc = Jsoup.parse(bookInfo);
        Element readContentEle = doc.getElementsByClass("read-content").first();
        return readContentEle.html();
    }

    /**
     * 解析图书目录
     *
     * @param catalogContent
     * @return
     */
    @Override
    public List<NovelCatalog> parseBookCatalog(String catalogContent) {
        // 编码接口数据为utf-8
        byte[] bytes = catalogContent.getBytes(HTTP.DEF_CONTENT_CHARSET);
        catalogContent = new String(bytes, HttpUtils.UTF8_CHARSET);
        List<NovelCatalog> result = new ArrayList<>();
        try {
            JSONObject jsonContent = JSON.parseObject(catalogContent);
            int code = jsonContent.getIntValue("code");
            if (0 != code) {
                return result;
            }
            JSONArray volumeArr = jsonContent.getJSONObject("data").getJSONArray("vs");
            int volumeSize = volumeArr.size();
            // 解析卷
            for (int i = 0; i < volumeSize; i++) {
                JSONObject volume = volumeArr.getJSONObject(i);
                String volumeName = volume.getString("vN");
                // 解析目录
                JSONArray catalogArr = volume.getJSONArray("cs");
                int catalogSize = catalogArr.size();
                for (int n = 0; n < catalogSize; n++) {
                    JSONObject catalogObj = catalogArr.getJSONObject(n);
                    NovelCatalog novelCatalog = new NovelCatalog();
                    // 设置目录名称
                    String catalogName = catalogObj.getString("cN");
                    novelCatalog.setName(catalogName);
                    novelCatalog.setShowName(reviseName(catalogName));
                    // 设置所在卷宗名称
                    novelCatalog.setVolumeName(volumeName);
                    // 设置目录的唯一标识
                    novelCatalog.setSourceId(catalogObj.getString("cU"));
                    // 设置目录总字数
                    novelCatalog.setWordCount(catalogObj.getIntValue("cnt"));
                    // 是否是vip
                    novelCatalog.setVip(catalogObj.getIntValue("sS") == 0);
                    //设置发布时间
                    novelCatalog.setPubTime(DateUtil.parse(catalogObj.getString("uT"), DateUtil.LONG_FORMAT));
                    // 设置唯一标识
                    novelCatalog.setUuid(UUID.randomUUID().toString());
                    result.add(novelCatalog);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    private static final Pattern CATALOG_WORD_COUNT = Pattern.compile("首发时间：([0-9:\\- ]*) 章节字数：(\\d+)");
}
