package novel.spider;

import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 内容解析器
 * Created by wangxiaodan on 2018/7/5.
 */
public interface Parser {
    static final String PROTOCOL_SSL = "https:";
    static final String PROTOCOL = "http:";

    /**
     * 解析列表页面
     *
     * @param content
     */
    public NovelList parseList(String content);

    /**
     * 解析小说介绍
     *
     * @param bookInfo
     * @return
     */
    public Novel parseBookInfo(String bookInfo);

    /**
     * 解析小说内容
     *
     * @param bookInfo
     * @return
     */
    public String parseBookContent(String bookInfo);

    /**
     * 解析小说目录信息
     * @param catalogContent
     * @return
     */
    public List<NovelCatalog> parseBookCatalog(String catalogContent);



    /**
     * 修正图书目录名称
     * TODO 稍后实现
     * @param name
     * @return
     */
    default String reviseName(String name) {
        if(StringUtils.isBlank(name)) {
            return name;
        }
        // 将后面括号中不是数字的内容去除

        return name;
    }

}
