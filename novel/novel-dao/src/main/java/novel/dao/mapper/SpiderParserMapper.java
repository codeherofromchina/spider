package novel.dao.mapper;

import java.util.List;
import novel.dao.model.SpiderParser;
import novel.dao.model.SpiderParserExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderParserMapper {
    int countByExample(SpiderParserExample example);

    int deleteByExample(SpiderParserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderParser record);

    int insertSelective(SpiderParser record);

    List<SpiderParser> selectByExampleWithBLOBs(SpiderParserExample example);

    List<SpiderParser> selectByExample(SpiderParserExample example);

    SpiderParser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderParser record, @Param("example") SpiderParserExample example);

    int updateByExampleWithBLOBs(@Param("record") SpiderParser record, @Param("example") SpiderParserExample example);

    int updateByExample(@Param("record") SpiderParser record, @Param("example") SpiderParserExample example);

    int updateByPrimaryKeySelective(SpiderParser record);

    int updateByPrimaryKeyWithBLOBs(SpiderParser record);

    int updateByPrimaryKey(SpiderParser record);
}