package novel.spider;

import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;

import java.io.Serializable;

/**
 * 持久化服务
 * Created by wangxiaodan on 2018/7/7.
 */
public interface SerializationService {

    /**
     * 持久化小说
     *
     * @param novel
     * @return
     */
    boolean novelSerialize(Novel novel);

    /**
     * 持久化目录
     *
     * @param catalog
     * @return
     */
    boolean catalogSerialize(NovelCatalog catalog);

    /**
     * 持久化内容
     *
     * @param content
     * @param catalogUuid
     * @return
     */
    boolean contentSerialize(String content, String catalogUuid);


    void spiderSimpleBookDone(boolean flag, Integer spiderPageId);

    /**
     * 保存列表爬虫当前页面，并返回是否继续标志
     * @param spiderListId
     * @param currentPage
     * @return
     */
    boolean spiderListCurrentPageDone(Integer spiderListId, int currentPage);

    /**
     * 停止列表爬虫采集
     * @param spiderListId
     */
    void spiderListBookDone(Integer spiderListId);

    /**
     * 停止列表页面信息采集
     */
    void stopListPageSpider();

    /**
     * 停止单页面信息采集
     */
    void stopSinglePageSpider();
}
