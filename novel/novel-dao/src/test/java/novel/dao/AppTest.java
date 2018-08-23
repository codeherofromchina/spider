package novel.dao;

import com.alibaba.fastjson.JSON;
import novel.dao.mapper.LabelMapper;
import novel.dao.mapper.SpiderWebMapper;
import novel.dao.model.Label;
import novel.dao.model.SpiderWeb;
import novel.dao.oss.OSSHelper;
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
    private SpiderWebMapper spiderWebMapper;
    @Autowired
    private OSSHelper ossHelper;

    @Test
    public void findOne() throws Exception {
        SpiderWeb web = spiderWebMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(web));
        Assert.assertEquals("起点中文网", web.getWebName());
    }


}
