package novel.dao.mapper;

import java.util.List;
import novel.dao.model.SpiderTypesMapping;
import novel.dao.model.SpiderTypesMappingExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderTypesMappingMapper {
    int countByExample(SpiderTypesMappingExample example);

    int deleteByExample(SpiderTypesMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderTypesMapping record);

    int insertSelective(SpiderTypesMapping record);

    List<SpiderTypesMapping> selectByExample(SpiderTypesMappingExample example);

    SpiderTypesMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderTypesMapping record, @Param("example") SpiderTypesMappingExample example);

    int updateByExample(@Param("record") SpiderTypesMapping record, @Param("example") SpiderTypesMappingExample example);

    int updateByPrimaryKeySelective(SpiderTypesMapping record);

    int updateByPrimaryKey(SpiderTypesMapping record);
}