package novel.service.comm;

import novel.dao.mapper.ContentMapper;
import novel.dao.model.Content;
import novel.dao.model.ContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/8.
 */
@Service
public class ContentService {
    @Autowired
    private ContentMapper contentMapper;


    /**
     * 插入图书内容
     *
     * @param content
     * @return
     */
    public boolean insert(Content content) {
        return contentMapper.insert(content) > 0;
    }

    /**
     * 查询图书目录的具体文章内容
     *
     * @param catalogId
     * @return
     */
    public Content findByCatalog(Integer catalogId) {
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCatalogIdEqualTo(catalogId);
        List<Content> contents = contentMapper.selectByExampleWithBLOBs(example);
        if (contents != null && contents.size() > 0) {
            return contents.get(0);
        }
        return null;
    }
}
