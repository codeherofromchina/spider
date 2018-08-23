package novel.service.comm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import novel.dao.mapper.SpiderPageMapper;
import novel.dao.mapper.SpiderWebMapper;
import novel.dao.model.SpiderPage;
import novel.dao.model.SpiderPageExample;
import novel.dao.model.SpiderWeb;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 单页面爬虫服务类
 * Created by wangxiaodan on 2018/8/10.
 */
@Service
public class SpiderPageService {
    @Autowired
    private SpiderPageMapper spiderPageMapper;
    @Autowired
    private SpiderWebMapper spiderWebMapper;

    /**
     * 新增单页面爬虫信息
     *
     * @param spiderPage
     * @return
     */
    public boolean insert(SpiderPage spiderPage) {
        Integer spiderWebId = spiderPage.getSpiderWebId();
        if (spiderWebId != null) {
            SpiderWeb spiderWeb = spiderWebMapper.selectByPrimaryKey(spiderWebId);
            if (spiderWeb != null) {
                spiderPage.setSpiderWebName(spiderWeb.getWebName());
            }
        }
        spiderPage.setRunStatus(0);
        spiderPage.setCreateTime(new Date());
        int insert = spiderPageMapper.insert(spiderPage);
        return insert > 0;
    }

    /**
     * 分页查询单页面爬虫信息
     *
     * @param pageNum
     * @param pageSize
     * @param pageName
     * @param runStatus
     * @return
     */
    public PageInfo<SpiderPage> findPageByNameAndStatus(Integer pageNum, Integer pageSize, String pageName, Integer runStatus) {
        PageHelper.startPage(pageNum, pageSize);
        SpiderPageExample example = new SpiderPageExample();
        SpiderPageExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(pageName)) {
            criteria.andPageNameLike("%" + pageName + "%");
        }
        if (runStatus != null) {
            criteria.andRunStatusEqualTo(runStatus);
        }
        List<SpiderPage> list = spiderPageMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 查找给定数量的待执行的单页面爬虫任务
     *
     * @param num
     * @return
     */
    public List<SpiderPage> findWaitExecuteSpider(int num) {
        PageHelper.startPage(1, num);
        SpiderPageExample example = new SpiderPageExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andRunStatusEqualTo(SpiderPage.RunStatusEnum.WAIT.getState());// 未运行的
        List<SpiderPage> spiderPageList = spiderPageMapper.selectByExample(example);
        if (spiderPageList == null) {
            spiderPageList = new ArrayList<>();
        }
        return spiderPageList;
    }

    /**
     * 更新单页爬虫的运行状态
     *
     * @param runStatusEnum
     * @param ids
     * @return
     */
    public boolean updateRunStatus(SpiderPage.RunStatusEnum runStatusEnum, List<Integer> ids) {
        SpiderPageExample example = new SpiderPageExample();
        example.createCriteria().andIdIn(ids);
        SpiderPage record = new SpiderPage();
        record.setRunStatus(runStatusEnum.getState());
        int updateNum = spiderPageMapper.updateByExampleSelective(record, example);
        // TODO 这里更新不完全可以抛出异常并回滚
        return updateNum == ids.size();
    }

    /**
     * 更新单页爬虫的运行状态
     *
     * @param runStatusEnum
     * @param id
     * @return
     */
    public boolean updateRunStatus(SpiderPage.RunStatusEnum runStatusEnum, Integer id) {
        SpiderPageExample example = new SpiderPageExample();
        example.createCriteria().andIdEqualTo(id);
        SpiderPage record = new SpiderPage();
        record.setRunStatus(runStatusEnum.getState());
        int updateNum = spiderPageMapper.updateByExampleSelective(record, example);
        // TODO 这里更新不完全可以抛出异常并回滚
        return updateNum > 0;
    }

    /**
     * 停止采集，将所有运行中的状态设置为待运行
     */
    public void stopSpider() {
        SpiderPage record = new SpiderPage();
        record.setRunStatus(SpiderPage.RunStatusEnum.WAIT.getState());

        SpiderPageExample example = new SpiderPageExample();
        example.createCriteria().andRunStatusEqualTo(SpiderPage.RunStatusEnum.RUNING.getState());

        spiderPageMapper.updateByExampleSelective(record, example);
    }

}
