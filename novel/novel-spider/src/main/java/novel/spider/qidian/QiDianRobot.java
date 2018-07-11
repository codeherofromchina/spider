package novel.spider.qidian;

import novel.spider.PageConfig;
import novel.spider.Robot;
import novel.spider.Spider;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/7.
 */
@Component
public class QiDianRobot implements Robot {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiDianRobot.class);

    @Autowired
    private Spider spider;
    @Resource(name = "qiDianConfig")
    private PageConfig pageConfig;

    // 爬取全部数据
    public void start() {
        long start = System.currentTimeMillis();
        LOGGER.info("起点网采集开始[{}]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT));
        spider.spider(pageConfig);
        LOGGER.info("起点网采集结束[{}][{}ms]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT), (System.currentTimeMillis() - start));
    }

    // 爬取固定图书内容
    public void start2() {
        long start = System.currentTimeMillis();
        LOGGER.info("起点网采集开始[{}]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT));
        String bookUrl = "https://book.qidian.com/info/1209977";
        spider.spiderSimpleBook(bookUrl,pageConfig);
        LOGGER.info("起点网采集结束[{}][{}ms]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT), (System.currentTimeMillis() - start));
    }


}
