package novel.service.comm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import novel.dao.mapper.SpiderListMapper;
import novel.dao.mapper.SpiderWebMapper;
import novel.dao.model.SpiderList;
import novel.dao.model.SpiderListExample;
import novel.dao.model.SpiderWeb;
import novel.dao.model.SpiderWebExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/8/9.
 */
@Service
public class SpiderListService {
    @Autowired
    private SpiderListMapper spiderListMapper;
    @Autowired
    private SpiderWebMapper spiderWebMapper;

    /**
     * 分页查询列表爬虫信息
     *
     * @param pageNum
     * @param pageSize
     * @param listName
     * @param runStatus
     * @return
     */
    public PageInfo<SpiderList> findPageByNameAndStatus(Integer pageNum, Integer pageSize, String listName, Boolean runStatus) {
        PageHelper.startPage(pageNum, pageSize);
        SpiderListExample example = new SpiderListExample();
        SpiderListExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(listName)) {
            criteria.andListNameLike("%" + listName + "%");
        }
        if (runStatus != null) {
            criteria.andRunStatusEqualTo(runStatus);
        }
        List<SpiderList> list = spiderListMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 新增列表爬虫
     *
     * @param spiderList
     * @return
     */
    public boolean insert(SpiderList spiderList) {
        Integer spiderWebId = spiderList.getSpiderWebId();
        if (spiderWebId != null) {
            SpiderWeb spiderWeb = spiderWebMapper.selectByPrimaryKey(spiderWebId);
            if (spiderWeb != null) {
                spiderList.setSpiderWebName(spiderWeb.getWebName());
            }
        }
        int insert = spiderListMapper.insert(spiderList);
        return insert > 0;
    }

    /**
     * 完成给定页的采集，并返回是否继续的运行状态
     *
     * @param id
     * @param currentPage
     * @return
     */
    public boolean doneOnePage(Integer id, int currentPage) {
        SpiderList spiderList = spiderListMapper.selectByPrimaryKey(id);
        if (spiderList != null) {
            spiderList.setCurrentPage(currentPage);
            spiderListMapper.updateByPrimaryKeySelective(spiderList);
            Boolean runStatus = spiderList.getRunStatus();
            return runStatus == null ? false : runStatus;
        }
        return false;
    }

    public void stopSpider() {
        SpiderListExample example = new SpiderListExample();
        example.createCriteria().andRunStatusEqualTo(true);
        SpiderList record = new SpiderList();
        record.setRunStatus(false);
        spiderListMapper.updateByExampleSelective(record, example);
    }

    public void stopSpider(Integer spiderListId) {
        SpiderList record = new SpiderList();
        record.setId(spiderListId);
        record.setRunStatus(false);
        record.setSpiderTime(new Date());
        spiderListMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 开始采集，设置采集时间和状态
     *
     * @param id
     */
    public void startRunSpider(Integer id) {
        SpiderList record = new SpiderList();
        record.setId(id);
        record.setRunStatus(true);
        record.setSpiderTime(new Date());
        spiderListMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查找待爬取列表
     * （全部运行只有一个任务，如果有在运行中则返回空，否则返回最长时间未运行的列表爬虫）
     *
     * @return
     */
    public SpiderList findWaitExecuteSpider() {
        SpiderListExample example = new SpiderListExample();
        example.createCriteria().andRunStatusEqualTo(true);
        List<SpiderList> spiderLists = spiderListMapper.selectByExample(example);
        if (spiderLists != null && spiderLists.size() > 0) {
            return null;
        }
        PageHelper.startPage(1, 1);
        SpiderListExample example02 = new SpiderListExample();
        example02.setOrderByClause("spider_time asc,id asc");
        example02.createCriteria().andRunStatusEqualTo(false);// 未运行状态
        List<SpiderList> spiderLists02 = spiderListMapper.selectByExample(example02);
        if (spiderLists02 != null && spiderLists02.size() > 0) {
            return spiderLists02.get(0);
        }
        return null;
    }
}
