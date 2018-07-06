package novel.dao;

import novel.dao.mapper.LabelMapper;
import novel.dao.model.Label;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private LabelMapper labelMapper;

    @Test
    public void findOne() throws Exception {
        Label label = labelMapper.selectByPrimaryKey(1);
        System.out.println(label);
        Assert.assertEquals("暴力", label.getName());
    }
}
