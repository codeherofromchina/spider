package novel.spider.biduo;

import novel.spider.PageConfig;
import novel.spider.Robot;
import novel.spider.Spider;
import novel.spider.qidian.QiDianRobot;
import novel.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/11.
 */
@Component
public class BiduoRobot implements Robot {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiDianRobot.class);
    @Autowired
    private Spider spider;
    @Resource(name = "biduoConfig")
    private PageConfig pageConfig;

    // 爬取固定图书内容
    public void start() {
        long start = System.currentTimeMillis();
        LOGGER.info("笔趣阁采集开始[{}]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT));
        spider.spider(pageConfig);
        LOGGER.info("笔趣阁采集结束[{}][{}ms]", DateUtil.format(new Date(), DateUtil.LONG_FORMAT), (System.currentTimeMillis() - start));
    }
}
