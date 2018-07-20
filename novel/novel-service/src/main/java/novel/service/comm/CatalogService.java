package novel.service.comm;

import novel.dao.mapper.CatalogMapper;
import novel.dao.model.Catalog;
import novel.dao.model.CatalogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/8.
 */
@Service
public class CatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    /**
     * 插入目录信息
     *
     * @param catalog
     * @return
     */
    public boolean insert(Catalog catalog) {
        int insert = catalogMapper.insert(catalog);
        return insert > 0;
    }

    /**
     * 通过uuid查找目录
     *
     * @param catalogUuid
     * @return
     */
    public Catalog findByUuid(String catalogUuid) {
        CatalogExample example = new CatalogExample();
        CatalogExample.Criteria criteria = example.createCriteria();
        criteria.andUuidEqualTo(catalogUuid);
        List<Catalog> catalogs = catalogMapper.selectByExample(example);
        if (catalogs != null && catalogs.size() > 0) {
            return catalogs.get(0);
        }
        return null;
    }

    /**
     * 设置目录为已爬取，并设置爬取时间
     *
     * @param catalogId
     */
    public void updateSpiderMarkAndDate(Integer catalogId) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        catalog.setMark(true);
        catalog.setSpiderDate(new Date());
        catalogMapper.updateByPrimaryKeySelective(catalog);
    }

    /**
     * 根据图书和目录名称查找唯一目录
     *
     * @param bookId
     * @param catalogName
     * @param catalogShowName
     * @return
     */
    public Catalog findByBookIdAndName(Integer bookId, String catalogName, String catalogShowName) {
        CatalogExample example = new CatalogExample();
        CatalogExample.Criteria criteria = example.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        criteria.andNameEqualTo(catalogName);
        criteria.andShowNameEqualTo(catalogShowName);

        List<Catalog> catalogs = catalogMapper.selectByExample(example);
        if (catalogs != null && catalogs.size() > 0) {
            return catalogs.get(0);
        }
        return null;
    }

    /**
     * 查询图书目录
     *
     * @param bookId
     * @return
     */
    public List<Catalog> findByBookId(Integer bookId) {
        CatalogExample example = new CatalogExample();
        CatalogExample.Criteria criteria = example.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        List<Catalog> catalogs = catalogMapper.selectByExample(example);
        if (catalogs == null) {
            catalogs = new ArrayList<>();
        }
        return catalogs;
    }
}
