package novel.admin.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxiaodan on 2018/7/30.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public String doLogin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (StringUtils.equals("admin", name) && StringUtils.equals("admin", password)) {
            return "index";
        }
        return "login";
    }

}
