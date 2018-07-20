package novel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxiaodan on 2018/7/20.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        System.out.println("这里是首页abc");
        return "index";
    }

}
