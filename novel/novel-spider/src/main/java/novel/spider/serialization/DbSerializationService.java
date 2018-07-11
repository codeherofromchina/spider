package novel.spider.serialization;

import novel.dao.model.Book;
import novel.dao.model.Catalog;
import novel.dao.model.Content;
import novel.service.comm.*;
import novel.spider.SerializationService;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/7.
 */
@Component("dbSerialization")
public class DbSerializationService implements SerializationService {
    // 图书重复采集时间，12小时就可以再次采集一次
    private static final long SPIDER_BOOK_INTERVAL = 5 * 24 * 60 * 60 * 1000;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypesService typesService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ContentService contentService;

    /**
     * 持久化小说信息
     *
     * @param novel
     * @return ture 保存成功  false:数据库中已经存在且近期采集过
     */
    public boolean novelSerialize(Novel novel) {
        try {
            // 持久化分类
            Integer typesId = typesService.insertBookTypes(novel.getParentTypes(), novel.getSonTypes());
            // 持久化图书
            Book book = bookService.findByNameAndAuthor(novel.getName(), novel.getAuthor());
            if (book == null) {
                // 如果不存在图书，则插入分类
                book = new Book();
                book.setCreateTime(new Date());
                novelSet2Book(book, novel);
                book.setBookTypesId(typesId);
                if (!bookService.insert(book)) {
                    return false;
                }
            } else {
                // 检查采集时间，并做相应更新
                Date oldSpiderDate = book.getSpiderDate();
                if (!isPassSpiderTime(oldSpiderDate, new Date(), SPIDER_BOOK_INTERVAL)) {
                    // 数据还新鲜
                    return false;
                }
                novelSet2Book(book, novel);
                book.setBookTypesId(typesId);
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
            Catalog catalog = catalogService.findByBookIdAndName(bookId,novelCatalog.getName(),novelCatalog.getShowName());
            if (catalog != null) {
                // 目录存在
                Boolean mark = catalog.getMark();
                if (mark != null && mark) {
                    return false; // 不用重新爬取
                } else {
                    novelCatalog.setUuid(catalog.getUuid());
                }
            } else {
                // 目录不存在，新建目录
                catalog = new Catalog();
                catalog.setName(novelCatalog.getName());
                catalog.setShowName(novelCatalog.getShowName());
                catalog.setVolumeName(novelCatalog.getVolumeName());
                catalog.setBookId(bookId);
                catalog.setWordCount(novelCatalog.getWordCount());
                catalog.setPubTime(novelCatalog.getPubTime());
                catalog.setMark(false);
                catalog.setDelFlag(false);
                catalog.setSourcesId(novelCatalog.getSourceId());
                catalog.setUuid(novelCatalog.getUuid());
                catalog.setCreateTime(new Date());
                boolean insertFlag = catalogService.insert(catalog);
                if (!insertFlag) {
                    return false;
                }
            }
            // 设置book为已爬取
            bookService.updateSpiderMarkAndDate(bookId);
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
            content.setCatalogId(catalogId);
            content.setTxt(text);

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

    /**
     * 检查是否在再次采集时间外
     *
     * @param oldSpiderDate 老数据采集时间
     * @param newSpiderDate 新数据采集时间
     * @param passTime      间隔多长ms可以重新采集
     * @return
     */
    private boolean isPassSpiderTime(Date oldSpiderDate, Date newSpiderDate, long passTime) {
        if (oldSpiderDate == null) {
            return true;
        }
        if (newSpiderDate == null) {
            newSpiderDate = new Date();
        }
        return (newSpiderDate.getTime() - oldSpiderDate.getTime()) >= passTime;

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
    }

}
