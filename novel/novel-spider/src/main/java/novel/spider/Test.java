package novel.spider;

import novel.spider.qidian.QiDianPageConfigBuilder;

import java.io.IOException;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class Test {

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        PageConfigBuilder pageConfigBuilder = new QiDianPageConfigBuilder();
        PageConfig pageConfig = pageConfigBuilder.build();

        Spider spider = new Spider(pageConfig);
        spider.spider();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

    }
}
