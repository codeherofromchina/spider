package novel.service.comm;

import novel.dao.mapper.BookTypesMapper;
import novel.dao.mapper.SpiderTypesMappingMapper;
import novel.dao.model.BookTypes;
import novel.dao.model.SpiderTypesMapping;
import novel.dao.model.SpiderTypesMappingExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类型映射服务类
 * Created by wangxiaodan on 2018/8/9.
 */
@Service
public class SpiderTypesMappingService {
    @Autowired
    private BookTypesMapper bookTypesMapper;
    @Autowired
    private SpiderTypesMappingMapper spiderTypesMappingMapper;

    /**
     * 查找站点下的分类树信息
     *
     * @param webId
     * @return
     */
    public List<SpiderTypesMapping> findTreeByWebId(Integer webId) {
        SpiderTypesMappingExample example = new SpiderTypesMappingExample();
        example.createCriteria().andSpiderWebIdEqualTo(webId);
        example.setOrderByClause("seq desc,id asc");
        List<SpiderTypesMapping> spiderTypesMappingList = spiderTypesMappingMapper.selectByExample(example);
        Map<Integer, SpiderTypesMapping> spiderTypesMappingMap = spiderTypesMappingList.parallelStream().collect(Collectors.toMap(SpiderTypesMapping::getId, vo -> vo));

        List<SpiderTypesMapping> result = new ArrayList<>();
        for (SpiderTypesMapping spiderTypesMapping : spiderTypesMappingList) {
            Integer parentId = spiderTypesMapping.getParentId();
            if (parentId == null) {
                result.add(spiderTypesMapping);
            } else {
                SpiderTypesMapping parentSpiderTypesMapping = spiderTypesMappingMap.get(parentId);
                parentSpiderTypesMapping.addChildren(spiderTypesMapping);
            }
        }

        return result;
    }

    /**
     * 插入映射分类
     *
     * @param spiderTypesMapping
     * @return
     */
    public SpiderTypesMapping insert(SpiderTypesMapping spiderTypesMapping) {
        Integer bookTypesId = spiderTypesMapping.getBookTypesId();
        if (bookTypesId != null) {
            BookTypes bookTypes = bookTypesMapper.selectByPrimaryKey(bookTypesId);
            spiderTypesMapping.setBookTypesName(bookTypes.getName());
        }
        int insert = spiderTypesMappingMapper.insert(spiderTypesMapping);
        if (insert > 0) {
            Integer parentId = spiderTypesMapping.getParentId();
            if (parentId != null) {
                setStateClose(parentId);
            }
            return spiderTypesMapping;
        }
        return null;
    }


    public void setStateClose(Integer id) {
        SpiderTypesMapping spiderTypesMapping = new SpiderTypesMapping();
        spiderTypesMapping.setId(id);
        spiderTypesMapping.setState("closed");
        spiderTypesMappingMapper.updateByPrimaryKeySelective(spiderTypesMapping);
    }

    /**
     * 更新映射分类
     *
     * @param spiderTypesMapping
     * @return
     */
    public SpiderTypesMapping update(SpiderTypesMapping spiderTypesMapping) {
        Integer id = spiderTypesMapping.getId();
        SpiderTypesMapping spiderTypesMapping02 = spiderTypesMappingMapper.selectByPrimaryKey(id);
        if (spiderTypesMapping02 == null) {
            return null;
        }
        Integer bookTypesId = spiderTypesMapping.getBookTypesId();
        Integer bookTypesId02 = spiderTypesMapping02.getBookTypesId();
        if (!StringUtils.equals(String.valueOf(bookTypesId), String.valueOf(bookTypesId02))) {
            if (bookTypesId != null) {
                BookTypes bookTypes = bookTypesMapper.selectByPrimaryKey(bookTypesId);
                spiderTypesMapping02.setBookTypesName(bookTypes.getName());
                spiderTypesMapping02.setBookTypesId(bookTypesId);
            } else {
                spiderTypesMapping02.setBookTypesName(null);
                spiderTypesMapping02.setBookTypesId(null);
            }
        }
        int i = spiderTypesMappingMapper.updateByPrimaryKey(spiderTypesMapping02);
        if (i > 0) {
            return spiderTypesMapping02;
        }
        return null;
    }

    /**
     * 删除叶子节点
     * @param id
     * @return  0:成功  1：失败  2：非叶子节点  3：不存在
     */
    public int deleteLeafNode(Integer id) {
        SpiderTypesMapping spiderTypesMapping = spiderTypesMappingMapper.selectByPrimaryKey(id);
        if (spiderTypesMapping == null) {
            return 3;
        }
        List<SpiderTypesMapping> sonMappingList = findByParentId(id);
        if (sonMappingList.size() > 0) {
            return 2;
        }
        int delNum = spiderTypesMappingMapper.deleteByPrimaryKey(id);
        if (delNum > 0) {
            return 0;
        }
        return 1;
    }


    /**
     * 根据父ID查找所有子映射项
     * @param parentId
     * @return
     */
    public List<SpiderTypesMapping> findByParentId(int parentId) {
        SpiderTypesMappingExample example = new SpiderTypesMappingExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("seq desc,id asc");
        List<SpiderTypesMapping> spiderTypesMappingList = spiderTypesMappingMapper.selectByExample(example);
        if (spiderTypesMappingList == null) {
            spiderTypesMappingList = new ArrayList<>();
        }
        return spiderTypesMappingList;
    }

    /**
     * 查询原始网站分类对应的本站分类
     * @param spiderWebId
     * @param parentTypes
     * @param sonTypes
     * @return
     */
    public Integer findMapping(Integer spiderWebId, String parentTypes, String sonTypes) {
        if (spiderWebId == null || StringUtils.isBlank(parentTypes)) {
            return null;
        }
        SpiderTypesMappingExample example01 = new SpiderTypesMappingExample();
        example01.createCriteria().andSpiderWebIdEqualTo(spiderWebId).andOriginalTypesEqualTo(parentTypes).andParentIdIsNull();
        List<SpiderTypesMapping> parentList = spiderTypesMappingMapper.selectByExample(example01);
        if(parentList == null || parentList.size() == 0) {
            // 插入父、子分类
            SpiderTypesMapping pSpiderTypesMapping = new SpiderTypesMapping();
            pSpiderTypesMapping.setSpiderWebId(spiderWebId);
            pSpiderTypesMapping.setOriginalTypes(parentTypes);
            pSpiderTypesMapping.setParentId(null);
            pSpiderTypesMapping.setSeq(100);
            insert(pSpiderTypesMapping);
            if (StringUtils.isNotBlank(sonTypes)) {
                SpiderTypesMapping sSpiderTypesMapping = new SpiderTypesMapping();
                sSpiderTypesMapping.setSpiderWebId(spiderWebId);
                sSpiderTypesMapping.setOriginalTypes(parentTypes);
                sSpiderTypesMapping.setParentId(pSpiderTypesMapping.getId());
                sSpiderTypesMapping.setSeq(100);
                insert(sSpiderTypesMapping);
            }
            return null;
        }
        SpiderTypesMapping parent = parentList.get(0);
        if (StringUtils.isBlank(sonTypes)) {
            return parent.getBookTypesId();
        }
        // 查找子分类
        SpiderTypesMappingExample example02 = new SpiderTypesMappingExample();
        example02.createCriteria().andSpiderWebIdEqualTo(spiderWebId).andOriginalTypesEqualTo(sonTypes).andParentIdEqualTo(parent.getId());
        List<SpiderTypesMapping> sonList = spiderTypesMappingMapper.selectByExample(example02);
        if(sonList == null || sonList.size() == 0) {
            SpiderTypesMapping sSpiderTypesMapping = new SpiderTypesMapping();
            sSpiderTypesMapping.setSpiderWebId(spiderWebId);
            sSpiderTypesMapping.setOriginalTypes(parentTypes);
            sSpiderTypesMapping.setParentId(parent.getId());
            sSpiderTypesMapping.setSeq(100);
            insert(sSpiderTypesMapping);
            return null;
        }
        SpiderTypesMapping son = sonList.get(0);
        return son.getBookTypesId();
    }
}
