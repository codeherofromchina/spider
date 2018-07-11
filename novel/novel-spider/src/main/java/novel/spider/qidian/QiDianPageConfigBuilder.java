package novel.spider.qidian;

import novel.spider.PageConfig;
import novel.spider.PageConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 起点网页面资源配置构造器
 * Created by wangxiaodan on 2018/7/5.
 */
@Component
public class QiDianPageConfigBuilder implements PageConfigBuilder {

    @Bean(name="qiDianConfig")
    @Override
    public PageConfig build() {
        PageConfig pageConfig = new PageConfig(QiDianEnv.START_PATE);
        pageConfig.setListUrlPattern(QiDianEnv.URL_LIST_PATTERN);
        pageConfig.setCatalogUrlPattern(QiDianEnv.URL_CATALOG_PATTERN);
        pageConfig.setContentUrlPattern(QiDianEnv.URL_CONTENT_PATTERN);
        pageConfig.setParser(new QiDianParser());
        return pageConfig;
    }
}
