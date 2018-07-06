package novel.spider.qidian;

import novel.spider.Parser;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
import novel.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
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
        novelList.setPageSize(elements.size());
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
        // 评分
        Element score1 = doc.getElementById("score1");
        Element score2 = doc.getElementById("score2");
        float fScore = Integer.parseInt(score1.html()) + Integer.parseInt(score2.html()) / 10.0F;
        novel.setScore(fScore);
        // 父级分类
        Elements pTypes = doc.getElementsByAttributeValue("data-eid", "qd_G10");
        novel.setParentTypes(pTypes.html());
        // 子分类
        Elements sTypes = bookInfo.getElementsByAttributeValue("data-eid", "qd_G11");
        novel.setSonTypes(sTypes.html());
        // 获取sourceId
        Element sourcesId = doc.getElementById("bookImg");
        novel.setSourcesId(sourcesId.attr("data-bid"));
        // 获取source
        Elements sources = bookInfo.getElementsByAttributeValue("data-eid", "qd_G18");
        novel.setSources(sources.attr("href"));
        // 图书总推荐
        // 图书周推荐
        //图书总字数
        // 设置采集时间为当前时间
        novel.setSpiderDate(new Date());

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
        Document doc = Jsoup.parse(catalogContent);
        Elements catalogs = doc.getElementsByAttributeValue("data-eid", "qd_G55");

        List<NovelCatalog> result = new ArrayList<>();
        catalogs.forEach(vo -> {
            NovelCatalog catalog = new NovelCatalog();
            // 设置目录名称
            String name = vo.html();
            catalog.setName(name);
            catalog.setShowName(reviseName(name));
            // 设置目录的内容链接
            String href = vo.attr("href");
            catalog.setContentUrl(PROTOCOL_SSL + href);
            // 设置字数和发布时间
            String title = vo.attr("title");
            Matcher matcher = CATALOG_WORD_COUNT.matcher(title);
            if (matcher.matches()) {
                String timeStr = matcher.group(1);
                String wordCount = matcher.group(2);
                catalog.setPubTime(DateUtil.parse(timeStr, DateUtil.LONG_FORMAT));
                if (StringUtils.isNumeric(wordCount)) {
                    catalog.setWordCount(Integer.parseInt(wordCount));
                }
            }
            // 设置是否是vip
            Element nextEle = vo.nextElementSibling();
            if (nextEle == null) {
                catalog.setSpiderDate(new Date());
                catalog.setMark(true);
            } else {
                catalog.setVip(true);
            }
            result.add(catalog);
        });


        return result;
    }


    private static final Pattern CATALOG_WORD_COUNT = Pattern.compile("首发时间：([0-9:\\- ]*) 章节字数：(\\d+)");
}
