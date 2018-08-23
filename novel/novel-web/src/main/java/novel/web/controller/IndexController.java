package novel.web.controller;

import novel.web.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangxiaodan on 2018/7/20.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpServletResponse response) {
        System.out.println("设置www.peer1.com的cookie");

        CookieUtil.setCookie (response, "eruitoken","peer1.com.token","peer1.com",0);

        return "index";
    }


    @RequestMapping("/type")
    public String types(HttpServletResponse response) {
        System.out.println("设置bate.peer1.com的cookie");
        CookieUtil.setCookie (response, "eruitoken","bate.peer1.com.token","bate.peer1.com",0);

        return "type";
    }


    @RequestMapping("/search")
    public String search(HttpServletRequest request) {
        System.out.println("打印cookie");
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            System.out.println(cookie.getName() + "\t" + cookie.getValue() + "\t" + cookie.getDomain());
        }
        return "search";
    }

    @RequestMapping("/ranking")
    public String ranking() {
        return "ranking";
    }
}
