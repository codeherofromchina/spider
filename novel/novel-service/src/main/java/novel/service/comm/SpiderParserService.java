package novel.service.comm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import novel.dao.mapper.SpiderParserMapper;
import novel.dao.model.SpiderParser;
import novel.dao.model.SpiderParserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/8/1.
 */
@Service
public class SpiderParserService {
    @Autowired
    private SpiderParserMapper spiderParserMapper;

    /**
     * 分页查找解析器
     *
     * @param pageNum
     * @param pageSize
     * @param parserName
     * @return
     */
    public PageInfo<SpiderParser> findPageByParserName(int pageNum, int pageSize, String parserName) {
        PageHelper.startPage(pageNum, pageSize);
        SpiderParserExample example = null;
        if (StringUtils.isNotBlank(parserName)) {
            example = new SpiderParserExample();
            example.createCriteria().andParserNameLike("%" + parserName + "%");
        }
        List<SpiderParser> parserList = spiderParserMapper.selectByExample(example);
        return new PageInfo(parserList);
    }


    /**
     * 新增解析器
     *
     * @param spiderParser
     * @return
     */
    public boolean add(SpiderParser spiderParser) {
        if (spiderParser.getId() != null) {
            Integer id = spiderParser.getId();
            SpiderParser spiderParser2 = spiderParserMapper.selectByPrimaryKey(id);
            if (spiderParser2 != null) {
                return false;
            }
        }
        spiderParser.setId(null);
        spiderParser.setCreateTime(new Date());
        int insertNum = spiderParserMapper.insert(spiderParser);
        return insertNum == 1;
    }

    /**
     * 所有解析器列表
     * @return
     */
    public List<SpiderParser> listAll() {
        List<SpiderParser> parserList = spiderParserMapper.selectByExample(null);
        return parserList;
    }

    /**
     * 通过id查找唯一解析器
     * @param id
     * @return
     */
    public SpiderParser findById(Integer id) {
        SpiderParser spiderParser = spiderParserMapper.selectByPrimaryKey(id);
        return spiderParser;
    }


}
