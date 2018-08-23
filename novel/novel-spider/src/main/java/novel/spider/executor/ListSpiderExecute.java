package novel.spider.executor;

import novel.dao.model.SpiderList;
import novel.dao.model.SpiderPage;
import novel.dao.model.SpiderParser;
import novel.service.comm.SpiderParserService;
import novel.spider.Spider;
import novel.spider.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangxiaodan on 2018/8/11.
 */
@Component
public class ListSpiderExecute {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Autowired
    private Spider spider;
    @Autowired
    private SpiderParserService spiderParserService;

    /**
     * 提交爬取单页面的任务
     *
     * @param spiderList
     */
    public void execute(SpiderList spiderList) {
        System.out.println("开始采集列表页面任务**********************");
        Integer spiderParserId = spiderList.getSpiderParserId();
        SpiderParser spiderParser = spiderParserService.findById(spiderParserId);
        ScriptEngine engine = null;
        try {
            engine = SpiderUtils.groovyEngine(spiderParser.getParserContent(), null);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        if (engine == null) {
            // 将状态设置为未采集
            spider.spiderListBookDone(spiderList.getId());
        } else {
            executorService.execute(new ListSpiderRunner(spider, spiderList, engine));
        }
        System.out.println("采集列表页面任务提交完成*******************");
    }

    private static class ListSpiderRunner implements Runnable {

        private Spider spider;
        private SpiderList spiderList;
        private ScriptEngine engine;

        public ListSpiderRunner(Spider spider, SpiderList spiderList, ScriptEngine engine) {
            this.spider = spider;
            this.spiderList = spiderList;
            this.engine = engine;
        }

        @Override
        public void run() {
            spider.spider(spiderList, engine);
            spider.spiderListBookDone(spiderList.getId());
        }
    }

    /**
     * 结束提交任务的工作
     */
    public void shutDown() {
        executorService.shutdown();
        spider.stopListPageSpider();
    }


}