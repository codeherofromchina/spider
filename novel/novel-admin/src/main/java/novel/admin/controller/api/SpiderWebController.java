package novel.admin.controller.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.SpiderWeb;
import novel.service.comm.SpiderWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wangxiaodan on 2018/7/31.
 */
@RestController
@RequestMapping("api/spiderWeb")
public class SpiderWebController {

    @Autowired
    private SpiderWebService spiderWebService;

    /**
     * 查询爬虫网站列表
     *
     * @return
     */
    @RequestMapping("list")
    public Result<PageInfo> list(Integer page, Integer rows, String webName) {

        page = (page == null || page < 1) ? 1 : page;
        rows = (rows == null || rows < 1) ? 10 : rows;
        PageInfo<SpiderWeb> pageInfo = spiderWebService.findPageByWebName(page, rows, webName);

        return new Result<>(pageInfo);
    }


    /**
     * 查询爬虫网站全部列表
     *
     * @return
     */
    @RequestMapping("listAll")
    public Result<List<SpiderWeb>> listAll() {
        List<SpiderWeb> spiderWebList = spiderWebService.listAll();
        return new Result<>(spiderWebList);
    }

    /**
     * 添加爬虫站点
     *
     * @param spiderWeb
     * @return
     */
    @RequestMapping("add")
    public Result add(SpiderWeb spiderWeb) {
        int flag = spiderWebService.add(spiderWeb);
        Result result = new Result();
        switch (flag) {
            case 1:
                result.setStatus(ResultStatusEnum.WEB_NAME_REPEAT_ERROR);
                break;
            case -1:
                result.setStatus(ResultStatusEnum.FAIL);
                break;
        }
        return result;
    }
}
