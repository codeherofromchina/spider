package novel.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxiaodan on 2018/7/31.
 */
@Controller
@RequestMapping("spider")
public class SpiderController {

    /**
     * 爬虫网站列表页面
     * @return
     */
    @RequestMapping("web")
    public String web() {
        return "spider/web";
    }

    /**
     * 解析器页面
     * @return
     */
    @RequestMapping("parser")
    public String parser() {
        return "spider/parser";
    }

    /**
     * 分类映射页面
     * @return
     */
    @RequestMapping("typesMapping")
    public String typesMapping() {
        return "spider/typesMapping";
    }


    /**
     * 列表爬虫管理页面
     * @return
     */
    @RequestMapping("listSpider")
    public String listSpider(HttpServletRequest request){
        int maxThreadNum = Runtime.getRuntime().availableProcessors();
        request.setAttribute("maxThreadNum",maxThreadNum);
        return "spider/listSpider";
    }


    /**
     * 单页面爬虫管理页面
     * @return
     */
    @RequestMapping("pageSpider")
    public String pageSpider(HttpServletRequest request){
        return "spider/pageSpider";
    }
}
