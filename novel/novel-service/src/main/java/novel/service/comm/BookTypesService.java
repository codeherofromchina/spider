package novel.service.comm;

import com.alibaba.fastjson.JSONArray;
import novel.dao.mapper.BookTypesMapper;
import novel.dao.model.Book;
import novel.dao.model.BookTypes;
import novel.dao.model.BookTypesExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (parentId == null) {
            criteria.andParentIdIsNull();
        } else {
            criteria.andParentIdEqualTo(parentId);
        }
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
     * 查找所有分类并组织成树结构
     *
     * @return
     */
    public List<BookTypes> tree() {
        BookTypesExample example = new BookTypesExample();
        example.setOrderByClause("seq desc,id asc");
        List<BookTypes> bookTypesList = bookTypesMapper.selectByExample(example);
        // 如果存在数据，则将结构组织成树状结构
        Map<Integer, BookTypes> bookTypesMap = bookTypesList.parallelStream().collect(Collectors.toMap(BookTypes::getId, vo -> vo));
        List<BookTypes> tree = new ArrayList<>();
        for (BookTypes bookTypes : bookTypesList) {
            Integer parentId = bookTypes.getParentId();
            if (parentId == null) {// 父级节点
                tree.add(bookTypes);
            } else {
                BookTypes parentBookTypes = bookTypesMap.get(parentId);
                parentBookTypes.addChildren(bookTypes);
            }
        }
        return tree;
    }

    /**
     * 更新图书分类信息
     *
     * @param bookTypes
     * @return 0:成功  1：失败  2：图书分类不存在 3：分类名称重复
     */
    public int update(BookTypes bookTypes) {
        BookTypes bookTypes02 = bookTypesMapper.selectByPrimaryKey(bookTypes.getId());
        if (bookTypes02 == null) {
            return 2; // 图书分类不存在
        }
        // 判断名称是否重复
        if (!bookTypes02.getName().equals(bookTypes.getName())) {
            BookTypes bookTypes03 = findByTypesNameAndParentId(bookTypes.getName(), bookTypes02.getParentId());
            if (bookTypes03 != null) {
                return 3; // 分类名称重复
            }
        }
        bookTypes02.setName(bookTypes.getName());
        bookTypes02.setSeq(bookTypes.getSeq());
        bookTypes02.setPic(bookTypes.getPic());
        bookTypes02.setClazz(bookTypes.getClazz());
        int updateNum = bookTypesMapper.updateByPrimaryKey(bookTypes02);
        if (updateNum < 1) {
            return 1;
        }
        return 0;
    }

    /**
     * 添加图书分类
     *
     * @param bookTypes
     * @return 0:成功  1：失败  3：分类名称重复
     */
    public int add(BookTypes bookTypes) {
        BookTypes bookTypes02 = findByTypesNameAndParentId(bookTypes.getName(), bookTypes.getParentId());
        if (bookTypes02 != null) {
            return 3; // 图书分类名称重复
        }
        bookTypes.setId(null);
        bookTypes.setCreateTime(new Date());
        int insert = bookTypesMapper.insert(bookTypes);
        if (insert < 1) {
            return 1;
        }
        return 0;
    }

    /**
     * 删除分类及其所有子分类
     *
     * @param id
     * @return
     */
    public int delete(Integer id) {
        BookTypes bookTypes = bookTypesMapper.selectByPrimaryKey(id);
        if (bookTypes == null) {
            return 2;
        }
        if (deleteAllSonTypes(id) == 0 && deleteById(id)) {
            return 0;
        }
        return 1;
    }

    /**
     * 删除给定分类的所有子分类
     *
     * @param parentId
     * @return 0 成功 1：失败
     */
    public int deleteAllSonTypes(Integer parentId) {
        BookTypesExample example = new BookTypesExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<BookTypes> bookTypesList = bookTypesMapper.selectByExample(example);
        if (bookTypesList != null && bookTypesList.size() > 0) {
            for (BookTypes bookTypes : bookTypesList) {
                Integer id = bookTypes.getId();
                if (!(deleteAllSonTypes(id) == 0 && deleteById(id))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 根据ID删除分类信息
     *
     * @param id
     * @return
     */
    private boolean deleteById(Integer id) {
        return bookTypesMapper.deleteByPrimaryKey(id) > 0;
    }
}
