package novel.dao.mapper;

import java.util.List;
import novel.dao.model.SpiderList;
import novel.dao.model.SpiderListExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderListMapper {
    int countByExample(SpiderListExample example);

    int deleteByExample(SpiderListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderList record);

    int insertSelective(SpiderList record);

    List<SpiderList> selectByExample(SpiderListExample example);

    SpiderList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderList record, @Param("example") SpiderListExample example);

    int updateByExample(@Param("record") SpiderList record, @Param("example") SpiderListExample example);

    int updateByPrimaryKeySelective(SpiderList record);

    int updateByPrimaryKey(SpiderList record);
}