package novel.service.comm;

import novel.dao.mapper.BookTypesMapper;
import novel.dao.model.BookTypes;
import novel.dao.model.BookTypesExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/7.
 */
@Service
public class BookTypesService {
    @Autowired
    private BookTypesMapper bookTypesMapper;

    /**
     * 通过分类名称查找图书分类
     *
     * @param typesName
     * @return
     */
    public BookTypes findByTypesNameAndParentId(String typesName, Integer parentId) {
        BookTypesExample example = new BookTypesExample();
        BookTypesExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(typesName);
        criteria.andParentIdEqualTo(parentId);
        List<BookTypes> bookTypesList = bookTypesMapper.selectByExample(example);
        if (bookTypesList != null && bookTypesList.size() > 0) {
            return bookTypesList.get(0);
        }
        return null;
    }


    /**
     * 通过分类名称查找顶级图书分类
     *
     * @param typesName
     * @return
     */
    public BookTypes findTopTypesByTypesName(String typesName) {
        BookTypesExample example = new BookTypesExample();
        BookTypesExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(typesName);
        criteria.andParentIdIsNull();
        List<BookTypes> bookTypesList = bookTypesMapper.selectByExample(example);
        if (bookTypesList != null && bookTypesList.size() > 0) {
            return bookTypesList.get(0);
        }
        return null;
    }

    /**
     * 插入图书分类业务
     * 检查父级分类和子分类并分别插入并返回id
     *
     * @param parentTypesName
     * @param sonTypesName
     * @return
     */
    public Integer insertBookTypes(String parentTypesName, String sonTypesName) {
        BookTypes parentBookTypes = findTopTypesByTypesName(parentTypesName);
        Date now = new Date();
        if (parentBookTypes == null) {
            parentBookTypes = new BookTypes();
            parentBookTypes.setCreateTime(now);
            parentBookTypes.setName(parentTypesName);
            if (bookTypesMapper.insert(parentBookTypes) <= 0) {
                return null;
            }

        }

        if (StringUtils.isBlank(sonTypesName)) {
            if (parentBookTypes == null) {
                return null;
            }
            return parentBookTypes.getId();
        }
        BookTypes sonBookTypes = findByTypesNameAndParentId(sonTypesName, parentBookTypes.getId());
        if (sonBookTypes == null) {
            sonBookTypes = new BookTypes();
            sonBookTypes.setCreateTime(now);
            sonBookTypes.setName(sonTypesName);
            sonBookTypes.setParentId(parentBookTypes.getId());
            bookTypesMapper.insert(sonBookTypes);
        }
        if (sonBookTypes == null) {
            return null;
        }
        return sonBookTypes.getId();
    }
}
