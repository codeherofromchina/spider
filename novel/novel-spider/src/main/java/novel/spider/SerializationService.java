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
}
