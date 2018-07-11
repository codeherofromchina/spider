package novel.service.comm;

import novel.dao.mapper.ContentMapper;
import novel.dao.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
