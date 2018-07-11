package novel.service.comm;


import novel.dao.mapper.BookLabelMapper;
import novel.dao.mapper.LabelMapper;
import novel.dao.model.BookLabelKey;
import novel.dao.model.Label;
import novel.dao.model.LabelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 标签业务类
 * Created by wangxiaodan on 2018/7/6.
 */
@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private BookLabelMapper bookLabelMapper;

    public Label findById(Integer id) {
        return labelMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入图书标签
     *
     * @param labelList
     * @param bookId
     */
    public void insertLabels(List<String> labelList, Integer bookId) {
        if (labelList == null || labelList.size() == 0) {
            return;
        }
        // 查询所有数据库中存在的标签
        LabelExample example = new LabelExample();
        LabelExample.Criteria criteria = example.createCriteria();
        criteria.andNameIn(labelList);
        List<Label> labels = labelMapper.selectByExample(example);
        // 过滤出未在数据库中的标签
        Set<String> existsLabel = labels.parallelStream().map(vo -> vo.getName()).collect(Collectors.toSet());
        Date now = new Date();
        List<Label> noExistsLabel = labelList.parallelStream().filter(vo -> !existsLabel.contains(vo)).map(vo -> {
            Label label = new Label();
            label.setName(vo);
            label.setSeq(0);
            label.setBookNum(0);
            label.setCreateTime(now);
            return label;
        }).collect(Collectors.toList());
        // 将未在数据库中的标签插入数据库
        for (Label label : noExistsLabel) {
            labelMapper.insert(label);
        }
        labels.addAll(noExistsLabel);
        if (bookId != null) {
            // 将标签和给定图书关联
            for (Label label : labels) {
                BookLabelKey key = new BookLabelKey();
                key.setBookId(bookId);
                key.setLabelId(label.getId());
                if (bookLabelMapper.countByPrimaryKey(key) > 0) {
                    continue;
                }
                if (bookLabelMapper.insert(key) > 0) {
                    labelMapper.incBookNum(label.getId());
                }
            }
        }
    }
}
