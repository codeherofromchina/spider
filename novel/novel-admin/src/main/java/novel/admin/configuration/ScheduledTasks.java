package novel.admin.configuration;

import novel.dao.model.SpiderList;
import novel.dao.model.SpiderPage;
import novel.service.comm.SpiderListService;
import novel.service.comm.SpiderPageService;
import novel.spider.executor.ListSpiderExecute;
import novel.spider.executor.SinglePageSpiderExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务
 * Created by wangxiaodan on 2018/8/11.
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private SpiderPageService spiderPageService;
    @Autowired
    private SpiderListService spiderListService;
    @Autowired
    private SinglePageSpiderExecute singlePageExecute;
    @Autowired
    private ListSpiderExecute listSpiderExecute;

    /**
     * 单页面爬虫的数据调度，将带爬取数据的信息放入到线程池队列待执行
     * 项目开启后1分钟执行,每5分钟执行一次
     */
    @Scheduled(initialDelay = 1000 * 20, fixedDelay = 1000 * 60 * 5)
    public void singlePageSpiderTask() {
        List<SpiderPage> pageSpiderList = spiderPageService.findWaitExecuteSpider(5);
        if (pageSpiderList.size() > 0) {
            // 提交爬虫任务
            singlePageExecute.execute(pageSpiderList);
            // 设置为运行中状态
            List<Integer> ids = pageSpiderList.parallelStream().map(SpiderPage::getId).collect(Collectors.toList());
            spiderPageService.updateRunStatus(SpiderPage.RunStatusEnum.RUNING, ids);
        }
    }

    /**
     * 列表页面爬虫的数据调度，将带爬取数据的信息放入到线程池队列待执行
     * 项目开启后2分钟执行,每30分钟执行一次
     */
    @Scheduled(initialDelay = 1000 * 60 * 2, fixedDelay = 1000 * 60 * 5)
    public void listPageSpiderTask() {
        SpiderList spiderList = spiderListService.findWaitExecuteSpider();
        if (spiderList != null) {
            // 设置为运行中状态
            spiderListService.startRunSpider(spiderList.getId());
            // 提交爬虫任务
            listSpiderExecute.execute(spiderList);
        }
    }



    /**
     * 爬虫的数据重置任务
     */
    @PreDestroy
    public void destroy() {
        System.out.println("定时任务中的收尾工作*******************************");
        singlePageExecute.shutDown();
        listSpiderExecute.shutDown();
    }
}
