package novel.admin.controller.api;

import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.SpiderTypesMapping;
import novel.dao.model.SpiderWeb;
import novel.service.comm.SpiderTypesMappingService;
import novel.service.comm.SpiderWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangxiaodan on 2018/8/9.
 */
@RestController
@RequestMapping("api/spiderTypesMapping")
public class SpiderTypesMappingController {
    @Autowired
    private SpiderWebService spiderWebService;
    @Autowired
    private SpiderTypesMappingService spiderTypesMappingService;

    /**
     * 映射的第一行网站列表类
     * @return
     */
    @RequestMapping("webList")
    public Result<List<SpiderTypesMapping>> webList() {
        List<SpiderWeb> spiderWebList = spiderWebService.listAll();
        List<SpiderTypesMapping> data = spiderWebList.stream().map(spiderWeb -> {
            SpiderTypesMapping spiderTypesMapping = new SpiderTypesMapping();
            Integer spiderWebId = spiderWeb.getId();
            spiderTypesMapping.setId(-spiderWebId);
            spiderTypesMapping.setSpiderWebId(spiderWebId);
            spiderTypesMapping.setOriginalTypes("<a target='_blank' href='" + spiderWeb.getIndexPage() + "'>" + spiderWeb.getWebName() + "</a>");
            spiderTypesMapping.setState("closed");
            return spiderTypesMapping;
        }).collect(Collectors.toList());
        return new Result<>(data);
    }

    /**
     * 获取某站点下的分类树信息
     * @param webId
     * @return
     */
    @RequestMapping("tree")
    public Result<List<SpiderTypesMapping>> tree(Integer webId) {
        List<SpiderTypesMapping> treeData = spiderTypesMappingService.findTreeByWebId(webId);
        return new Result<>(treeData);
    }

    /**
     * 保存分类映射信息
     * @param spiderTypesMapping
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result<SpiderTypesMapping> save(SpiderTypesMapping spiderTypesMapping) {
        Integer id = spiderTypesMapping.getId();
        if (id == null) {
            spiderTypesMapping = spiderTypesMappingService.insert(spiderTypesMapping);
        } else {
            spiderTypesMapping = spiderTypesMappingService.update(spiderTypesMapping);
        }
        return new Result<>(spiderTypesMapping);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Result delete(Integer id){
        // 0:成功  1：失败  2：非叶子节点  3：不存在
        int flag = spiderTypesMappingService.deleteLeafNode(id);
        Result result = new Result();
        switch (flag) {
            case 0:
                break;
            case 2:
                result.setStatus(ResultStatusEnum.NOT_LEAF_NODE_ERROR);
                break;
            case 3:
                result.setStatus(ResultStatusEnum.OBJECT_NOT_EXIST);
                break;
                default:
                    result.setStatus(ResultStatusEnum.FAIL);
        }
        return result;
    }
}
