package novel.service.comm;


import novel.dao.mapper.LabelMapper;
import novel.dao.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标签业务类
 * Created by wangxiaodan on 2018/7/6.
 */
@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    public Label findById(Integer id) {
        return labelMapper.selectByPrimaryKey(id);
    }

}
