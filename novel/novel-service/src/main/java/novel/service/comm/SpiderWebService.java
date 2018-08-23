package novel.service.comm;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import novel.dao.mapper.SpiderWebMapper;
import novel.dao.model.SpiderWeb;
import novel.dao.model.SpiderWebExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 爬虫网站服务类
 * Created by wangxiaodan on 2018/8/1.
 */
@Service
public class SpiderWebService {
    @Autowired
    private SpiderWebMapper spiderWebMapper;

    /**
     * 新增爬虫站点
     *
     * @param spiderWeb
     * @return 0:正常添加成功  1:站点名称重复  -1：其他错误
     */
    public int add(SpiderWeb spiderWeb) {
        if (findByWebName(spiderWeb.getWebName()) != null) {
            return 1;
        }
        spiderWeb.setId(null);
        spiderWeb.setCreateTime(new Date());

        int insert = spiderWebMapper.insert(spiderWeb);
        if (insert < 1) {
            return -1;
        }
        return 0;
    }


    /**
     * 通过网站名称唯一查找网站
     *
     * @param webName
     * @return
     */
    public SpiderWeb findByWebName(String webName) {
        SpiderWebExample example = new SpiderWebExample();
        example.createCriteria().andWebNameEqualTo(webName);
        List<SpiderWeb> spiderWebs = spiderWebMapper.selectByExample(example);
        if (spiderWebs != null && spiderWebs.size() > 0) {
            return spiderWebs.get(0);
        }
        return null;
    }

    public PageInfo<SpiderWeb> findPageByWebName(int pageNum, int pageSize, String webName) {
        PageHelper.startPage(pageNum, pageSize);
        SpiderWebExample example = null;
        if (StringUtils.isNotBlank(webName)) {
            example = new SpiderWebExample();
            example.createCriteria().andWebNameLike("%" + webName + "%");
        }
        List<SpiderWeb> list = spiderWebMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 所有爬虫网站列表
     * @return
     */
    public List<SpiderWeb> listAll() {
        SpiderWebExample example = new SpiderWebExample();
        example.setOrderByClause("id asc");
        List<SpiderWeb> spiderWebList = spiderWebMapper.selectByExample(example);
        if (spiderWebList == null) {
            spiderWebList = new ArrayList<>();
        }
        return spiderWebList;
    }
}
