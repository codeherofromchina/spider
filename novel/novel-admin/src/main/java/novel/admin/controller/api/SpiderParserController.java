package novel.admin.controller.api;

import com.github.pagehelper.PageInfo;
import novel.comm.result.Result;
import novel.comm.result.ResultStatusEnum;
import novel.dao.model.SpiderParser;
import novel.service.comm.SpiderParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

/**
 * Created by wangxiaodan on 2018/8/1.
 */
@RestController
@RequestMapping("api/spiderParser")
public class SpiderParserController {

    @Autowired
    private SpiderParserService spiderParserService;

    /**
     * 获取解析器列表
     *
     * @return
     */
    @RequestMapping("list")
    public Result<PageInfo> list(Integer page, Integer rows, String parserName) {
        page = (page == null || page < 1) ? 1 : page;
        rows = (rows == null || rows < 1) ? 10 : rows;
        System.out.println(page + "  " + rows + "  " + parserName);
        PageInfo<SpiderParser> pageInfo = spiderParserService.findPageByParserName(page, rows, parserName);
        return new Result<>(pageInfo);
    }


    /**
     * 获取解析器所有列表
     *
     * @return
     */
    @RequestMapping("listAll")
    public Result<List<SpiderParser>> listAll() {
        List<SpiderParser> data = spiderParserService.listAll();
        return new Result<>(data);
    }

    /**
     * 添加解析器
     *
     * @param spiderParser
     * @return
     */
    @RequestMapping("add")
    public Result add(SpiderParser spiderParser) {
        Result result = new Result();
        // 将内容插入到数据库
        boolean addFlag = spiderParserService.add(spiderParser);
        if (!addFlag) {
            result.setStatus(ResultStatusEnum.FAIL);
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("59.46.0.31", 1080));
        URL url = new URL("https://www.baidu.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);
        urlConnection.setRequestMethod("GET");

        InputStream in = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        urlConnection.disconnect();


    }
}
