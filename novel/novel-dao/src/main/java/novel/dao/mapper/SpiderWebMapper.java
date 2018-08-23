package novel.dao.mapper;

import java.util.List;
import novel.dao.model.SpiderWeb;
import novel.dao.model.SpiderWebExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderWebMapper {
    int countByExample(SpiderWebExample example);

    int deleteByExample(SpiderWebExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderWeb record);

    int insertSelective(SpiderWeb record);

    List<SpiderWeb> selectByExample(SpiderWebExample example);

    SpiderWeb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderWeb record, @Param("example") SpiderWebExample example);

    int updateByExample(@Param("record") SpiderWeb record, @Param("example") SpiderWebExample example);

    int updateByPrimaryKeySelective(SpiderWeb record);

    int updateByPrimaryKey(SpiderWeb record);
}