package novel.spider.biduo;

import novel.spider.Parser;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
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


/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class BiduoParser implements Parser {
    private static final String BIDUO_DOMAIN = "https://www.biduo.cc";


    public static void main(String[] args) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/wangxiaodan/tmp/dd/ddd.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        reader.close();
        BiduoParser parser = new BiduoParser();
//        // 解析首页图书列表测试
//        NovelList novelList = parser.parseList(stringBuffer.toString());
//        System.out.println(novelList);

//        Novel novel = parser.parseBookInfo(stringBuffer.toString());
//        System.out.println(novel);

        List<NovelCatalog> novelCatalogs = parser.parseBookCatalog(stringBuffer.toString());
        for (NovelCatalog catalog : novelCatalogs) {
            System.out.println(catalog);
        }

//        String s = parser.parseBookContent(stringBuffer.toString());
//        System.out.println(s);
    }

    @Override
    public NovelList parseList(String content) {
        Document doc = Jsoup.parse(content);
        Elements elements = doc.getElementsByClass("s2");
        List<String> urls = new ArrayList<>();
        elements.forEach(element -> {
            Element a = element.getElementsByTag("a").first();
            if (a != null) {
                urls.add(BIDUO_DOMAIN + a.attr("href"));
            }
        });
        NovelList novelList = new NovelList();
        novelList.setTotalPages(1);// 没有分页，只有一页
        novelList.setBookInfoUrls(urls);
        return novelList;
    }

    @Override
    public Novel parseBookInfo(String content) {
        Document doc = Jsoup.parse(content);

        String parentTypes = doc.selectFirst("meta[property=og:novel:category]").attr("content");
        String author = doc.selectFirst("meta[property=og:novel:author]").attr("content");
        String bookName = doc.selectFirst("meta[property=og:novel:book_name]").attr("content");
        String statusName = doc.selectFirst("meta[property=og:novel:status]").attr("content");
        boolean stat = "连载中".equals(statusName);
        Element element = doc.selectFirst("meta[property=og:url]");
        String sources = element.attr("content");
        int la = sources.lastIndexOf("/");
        String sourceId = sources.substring(0, la);
        la = sourceId.lastIndexOf("/");
        sourceId = sourceId.substring(la + 1);
        String desc = doc.getElementById("intro").html();
        String coverPhoto = doc.getElementById("fmimg").child(0).attr("src");

        Novel novel = new Novel();
        novel.setName(bookName);
        novel.setAlias(bookName);
        novel.setAuthor(author);
        novel.setCoverPhoto(coverPhoto);
        novel.setDesc(desc);
        novel.setParentTypes(parentTypes);
        novel.setStat(stat);
        novel.setSources(sources);
        novel.setSourceId(sourceId);
        novel.setUuid(UUID.randomUUID().toString());
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
        String content = doc.getElementById("content").html();
        int i = content.indexOf("<br>");
        content = content.substring(i + 4);
        return content;
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
        Element element = doc.selectFirst("meta[property=og:url]");
        String bookSources = element.attr("content");

        Elements elements = doc.getElementsByTag("dd");


        List<NovelCatalog> result = new ArrayList<>();
        elements.forEach(vo -> {
            Elements aEle = vo.getElementsByTag("a");
            String name = aEle.html();
            String sourceId = bookSources + aEle.attr("href");
            NovelCatalog catalog = new NovelCatalog();
            catalog.setName(name);
            catalog.setShowName(reviseName(name));
            catalog.setSourceId(sourceId);
            catalog.setVip(false);
            catalog.setUuid(UUID.randomUUID().toString());
            result.add(catalog);
        });


        return result;
    }


    private static final Pattern CATALOG_WORD_COUNT = Pattern.compile("首发时间：([0-9:\\- ]*) 章节字数：(\\d+)");
}
