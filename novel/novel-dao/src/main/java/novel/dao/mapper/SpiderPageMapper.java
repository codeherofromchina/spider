package novel.dao.mapper;

import java.util.List;
import novel.dao.model.SpiderPage;
import novel.dao.model.SpiderPageExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderPageMapper {
    int countByExample(SpiderPageExample example);

    int deleteByExample(SpiderPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderPage record);

    int insertSelective(SpiderPage record);

    List<SpiderPage> selectByExample(SpiderPageExample example);

    SpiderPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderPage record, @Param("example") SpiderPageExample example);

    int updateByExample(@Param("record") SpiderPage record, @Param("example") SpiderPageExample example);

    int updateByPrimaryKeySelective(SpiderPage record);

    int updateByPrimaryKey(SpiderPage record);
}