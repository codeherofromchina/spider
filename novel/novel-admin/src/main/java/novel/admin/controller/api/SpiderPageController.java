package novel.admin.controller.api;

import com.github.pagehelper.PageInfo;
import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.SpiderList;
import novel.dao.model.SpiderPage;
import novel.service.comm.SpiderPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiaodan on 2018/8/10.
 */
@RestController
@RequestMapping("api/spiderPage")
public class SpiderPageController {
    @Autowired
    private SpiderPageService spiderPageService;



    /**
     * 查询列表爬虫页面
     *
     * @return
     */
    @RequestMapping("list")
    public Result<PageInfo> list(Integer page, Integer rows, String pageName, Integer runStatus) {

        page = (page == null || page < 1) ? 1 : page;
        rows = (rows == null || rows < 1) ? 10 : rows;
        PageInfo<SpiderPage> pageInfo = spiderPageService.findPageByNameAndStatus(page, rows, pageName, runStatus);

        return new Result<>(pageInfo);
    }

    /**
     * 新增单页面爬虫信息
     *
     * @param spiderPage
     * @return
     */
    @RequestMapping("add")
    public Result add(SpiderPage spiderPage) {
        Result result = new Result();
        boolean flag = spiderPageService.insert(spiderPage);
        if (!flag) {
            result.setStatus(ResultStatusEnum.FAIL);
        }
        return result;
    }

}
