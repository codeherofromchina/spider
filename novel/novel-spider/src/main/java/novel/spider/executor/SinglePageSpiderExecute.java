package novel.spider.executor;

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
public class SinglePageSpiderExecute {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Autowired
    private Spider spider;
    @Autowired
    private SpiderParserService spiderParserService;


    /**
     * 提交爬取单页面的任务
     *
     * @param pageSpiderList
     */
    public void execute(List<SpiderPage> pageSpiderList) {
        System.out.println("开始采集单页面任务**********************");
        Map<Integer, ScriptEngine> parserEngine = new HashMap<>();
        for (SpiderPage spiderPage : pageSpiderList) {
            Integer spiderParserId = spiderPage.getSpiderParserId();
            ScriptEngine engine = parserEngine.get(spiderParserId);
            if (engine == null) {
                // 获取解析器脚本引擎
                SpiderParser spiderParser = spiderParserService.findById(spiderParserId);
                if (spiderParser != null) {
                    try {
                        engine = SpiderUtils.groovyEngine(spiderParser.getParserContent(), null);
                    } catch (ScriptException e) {
                        e.printStackTrace();
                    }
                }
                parserEngine.put(spiderParserId, engine);
            }
            if (engine == null) {
                // 将状态设置为未采集
                spider.spiderSimpleBookDone(false,spiderPage.getId());
                continue;
            }
            executorService.execute(new SpiderSinglePageRunner(spider, spiderPage, engine));
        }
        System.out.println("**********************");
    }

    private static class SpiderSinglePageRunner implements Runnable {

        private Spider spider;
        private SpiderPage spiderPage;
        private ScriptEngine engine;

        public SpiderSinglePageRunner(Spider spider, SpiderPage spiderPage, ScriptEngine engine) {
            this.spider = spider;
            this.spiderPage = spiderPage;
            this.engine = engine;
        }

        @Override
        public void run() {
            boolean flag = spider.spiderSimpleBook(spiderPage,engine);
            spider.spiderSimpleBookDone(flag,spiderPage.getId());
        }
    }

    /**
     * 结束提交任务的工作
     */
    public void shutDown() {
        executorService.shutdown();
        spider.stopSinglePageSpider();
    }


}