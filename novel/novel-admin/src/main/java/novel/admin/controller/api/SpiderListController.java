package novel.admin.controller.api;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.SpiderList;
import novel.service.comm.SpiderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiaodan on 2018/8/9.
 */
@RestController
@RequestMapping("api/spiderList")
public class SpiderListController {

    @Autowired
    private SpiderListService spiderListService;

    /**
     * 查询列表爬虫页面
     *
     * @return
     */
    @RequestMapping("list")
    public Result<PageInfo> list(Integer page, Integer rows, String listName, Boolean runStatus) {

        page = (page == null || page < 1) ? 1 : page;
        rows = (rows == null || rows < 1) ? 10 : rows;
        PageInfo<SpiderList> pageInfo = spiderListService.findPageByNameAndStatus(page, rows, listName, runStatus);

        return new Result<>(pageInfo);
    }

    /**
     * 新增列表爬虫信息
     *
     * @param spiderList
     * @return
     */
    @RequestMapping("add")
    public Result add(SpiderList spiderList) {
        Result result = new Result();
        boolean flag = spiderListService.insert(spiderList);
        if (!flag) {
            result.setStatus(ResultStatusEnum.FAIL);
        }
        return result;
    }

}
