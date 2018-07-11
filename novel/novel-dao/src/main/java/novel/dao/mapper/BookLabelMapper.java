package novel.dao.mapper;

import java.util.List;

import novel.dao.model.BookLabelExample;
import novel.dao.model.BookLabelKey;
import org.apache.ibatis.annotations.Param;

public interface BookLabelMapper {
    int countByExample(BookLabelExample example);

    int deleteByExample(BookLabelExample example);

    int deleteByPrimaryKey(BookLabelKey key);

    int countByPrimaryKey(BookLabelKey key);

    int insert(BookLabelKey record);

    int insertSelective(BookLabelKey record);

    List<BookLabelKey> selectByExample(BookLabelExample example);

    int updateByExampleSelective(@Param("record") BookLabelKey record, @Param("example") BookLabelExample example);

    int updateByExample(@Param("record") BookLabelKey record, @Param("example") BookLabelExample example);
}