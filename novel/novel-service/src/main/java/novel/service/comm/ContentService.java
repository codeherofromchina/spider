package novel.service.comm;

import novel.dao.mapper.CatalogMapper;
import novel.dao.model.Catalog;
import novel.dao.model.Content;
import novel.dao.oss.OSSHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by wangxiaodan on 2018/7/8.
 */
@Service
public class ContentService {
    @Autowired
    private OSSHelper ossHelper;
    @Autowired
    private CatalogMapper catalogMapper;

    /**
     * 插入内容到阿里云SSO对象存储中
     *
     * @param content
     * @return
     */
    public boolean insert(Content content) {
        boolean insertFlag = ossHelper.putObject(content.getCatalogUUID(), content.getText());
        return insertFlag;
    }

    // 查询目录下的内容
    public Content findByCatalog(Integer catalogId) {
        Catalog catalog = catalogMapper.selectByPrimaryKey(catalogId);
        String object = ossHelper.getObject(catalog.getUuid());
        Content content = new Content();
        content.setText(object);
        content.setCatalog(String.valueOf(catalog.getBookId()));
        content.setCatalogUUID(catalog.getUuid());

        return content;
    }
}
