package novel.spider.serialization;

import com.alibaba.fastjson.JSONObject;
import novel.dao.model.Book;
import novel.dao.model.Catalog;
import novel.dao.model.Content;
import novel.dao.model.SpiderPage;
import novel.service.comm.*;
import novel.spider.SerializationService;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/7.
 */
@Component("dbSerialization")
public class DbSerializationService implements SerializationService {
    @Autowired
    private BookService bookService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private SpiderPageService spiderPageService;
    @Autowired
    private SpiderListService spiderListService;
    @Autowired
    private SpiderTypesMappingService spiderTypesMappingService;

    /**
     * 持久化小说信息
     *
     * @param novel
     * @return ture 保存成功  false:数据库中已经存在且近期采集过
     */
    public boolean novelSerialize(Novel novel) {
        try {
            // 准备工作
            Integer typesId = spiderTypesMappingService.findMapping(novel.getSpiderWebId(), novel.getParentTypes(), novel.getSonTypes());
            String uuid;
            Date now = new Date();
            do {
                uuid = SpiderUtils.uuid();
            } while (bookService.existUUID(uuid));

            // 持久化图书
            Book book = bookService.findByNameAndAuthor(novel.getName(), novel.getAuthor());
            if (book == null) {
                novel.setUuid(uuid);
                // 如果不存在图书，则插入分类
                book = new Book();
                novelSet2Book(book, novel);
                book.setCreateTime(now);
                book.setBookTypesId(typesId);
                book.setSpiderDate(now);
                if (!bookService.insert(book)) {
                    return false;
                }
            } else {
                novel.setUuid(book.getUuid());
                // 检查采集时间，并做相应更新
                novelSet2Book(book, novel);
                book.setBookTypesId(typesId);
                book.setSpiderDate(now);
                if (!bookService.update(book)) {
                    return false;
                }
            }
            // 持久化标签信息
            List<String> labelList = novel.getLabelList();
            if (labelList != null && labelList.size() > 0) {
                labelService.insertLabels(labelList, book.getId());
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 序列化目录到数据库中
     *
     * @param novelCatalog
     * @return
     */
    @Override
    public boolean catalogSerialize(NovelCatalog novelCatalog) {
        try {
            // 获取book
            String bookUuid = novelCatalog.getBookUuid();
            Book book = bookService.findByUuid(bookUuid);
            if (book == null) {
                return false;
            }
            Integer bookId = book.getId();
            // 查看目录是否存在且被爬取过去
            Catalog catalog = catalogService.findByBookIdAndName(bookId, novelCatalog.getName(), novelCatalog.getShowName());
            if (catalog != null) {
                // 目录存在
                Boolean mark = catalog.getMark();
                if (mark != null && mark) {
                    return false; // 不用重新爬取
                }
            } else {
                String uuid;
                do {
                    uuid = SpiderUtils.uuid();
                } while (catalogService.existUUID(uuid));
                // 目录不存在，新建目录
                catalog = new Catalog();
                catalog.setName(novelCatalog.getName());
                catalog.setShowName(novelCatalog.getShowName());
                catalog.setVolumeName(novelCatalog.getVolumeName());
                catalog.setVolumeNum(novelCatalog.getVolumeNum());
                catalog.setBookId(bookId);
                catalog.setWordCount(novelCatalog.getWordCount());
                catalog.setCatalogNum(novelCatalog.getCatalogNum());
                catalog.setPubTime(novelCatalog.getPubTime());
                catalog.setMark(false);
                catalog.setDelFlag(false);
                catalog.setSourcesId(novelCatalog.getSourceId());
                catalog.setContentUrl(novelCatalog.getSourceUrl());
                catalog.setUuid(uuid);
                catalog.setCreateTime(new Date());
                boolean insertFlag = catalogService.insert(catalog);
                if (!insertFlag) {
                    return false;
                }
            }
            novelCatalog.setUuid(catalog.getUuid());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * 序列化小说内容到数据库
     *
     * @param text
     * @param catalogUuid
     * @return
     */
    @Override
    public boolean contentSerialize(String text, String catalogUuid) {
        try {
            // 获取目录信息
            Catalog catalog = catalogService.findByUuid(catalogUuid);
            if (catalog == null) {
                return false;
            }
            Integer catalogId = catalog.getId();
            // 保存内容
            Content content = new Content();
            content.setCatalog(String.valueOf(catalog.getBookId()));
            content.setCatalogUUID(catalogUuid);
            content.setText(text);

            boolean insertFlag = contentService.insert(content);
            if (!insertFlag) {
                return false;
            }
            // 设置book为已爬取
            catalogService.updateSpiderMarkAndDate(catalogId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }



    @Override
    public void spiderSimpleBookDone(boolean flag, Integer spiderPageId) {
        spiderPageService.updateRunStatus(flag ? SpiderPage.RunStatusEnum.DONE : SpiderPage.RunStatusEnum.WAIT, spiderPageId);
    }

    @Override
    public void stopSinglePageSpider() {
        spiderPageService.stopSpider();
    }

    @Override
    public void spiderListBookDone(Integer spiderListId) {
        spiderListService.stopSpider(spiderListId);
    }

    @Override
    public void stopListPageSpider() {
        spiderListService.stopSpider();
    }

    @Override
    public boolean spiderListCurrentPageDone(Integer spiderListId, int currentPage) {
        return spiderListService.doneOnePage(spiderListId, currentPage);
    }


    /**
     * 将小说信息设置到图书中
     *
     * @param book
     * @param novel
     */
    private void novelSet2Book(Book book, Novel novel) {
        book.setName(novel.getName());
        book.setAuthor(novel.getAuthor());
        book.setSources(novel.getSources());
        book.setSourcesId(novel.getSourceId());
        book.setIntro(novel.getIntro());
        book.setDesc(novel.getDesc());
        book.setCoverPhoto(novel.getCoverPhoto());
        book.setSources(novel.getSources());
        book.setUuid(novel.getUuid());
        book.setWordCount(novel.getWordCount());
        book.setStat(novel.isStat());
        JSONObject remark = new JSONObject();
        remark.put("parentTypes", novel.getParentTypes());
        remark.put("sonTypes", novel.getSonTypes());
        book.setRemarks(remark.toJSONString());
    }
}
