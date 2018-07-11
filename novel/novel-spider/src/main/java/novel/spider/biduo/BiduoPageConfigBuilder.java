package novel.spider.biduo;

import novel.spider.PageConfig;
import novel.spider.PageConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * 起点网页面资源配置构造器
 * Created by wangxiaodan on 2018/7/5.
 */
@Component
public class BiduoPageConfigBuilder implements PageConfigBuilder {

    @Bean(name = "biduoConfig")
    @Override
    public PageConfig build() {
        PageConfig pageConfig = new PageConfig(BiduoEnv.START_PATE);
        pageConfig.setTotalPages(BiduoEnv.START_PATE);
        pageConfig.setListUrlPattern(BiduoEnv.URL_LIST_PATTERN);
        //pageConfig.setCatalogUrlPattern(BiduoEnv.URL_CATALOG_PATTERN);
        pageConfig.setContentUrlPattern(BiduoEnv.URL_CONTENT_PATTERN);
        pageConfig.setParser(new BiduoParser());
        pageConfig.setDefaultCharset(Charset.forName("GBK"));
        pageConfig.setThreadNum(10);
        return pageConfig;
    }
}
