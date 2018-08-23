package novel.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 图书管理
 * Created by wangxiaodan on 2018/8/8.
 */
@Controller
@RequestMapping("book")
public class BookController {

    /**
     * 图书分类管理页面
     * @return
     */
    @RequestMapping("types")
    public String types() {
        return "/book/types";
    }


}
