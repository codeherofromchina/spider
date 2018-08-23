package novel.web.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by juzhihai on 17/11/16.
 */
public class CookieUtil {


    /**
     * 删除cookie
     *
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name,String domain) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setDomain(domain);
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     *
     * @param request
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        if(null!=cookies && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 设置cookie值
     * @param response 响应对象
     * @param key 键
     * @param value 值
     */
    public static void setCookie(HttpServletResponse response,String key,String value,String domain,int time)
    {
        Cookie cookie = new Cookie(key,value);
        cookie.setHttpOnly(true);//如果设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法访问该Cookie
        if(time>0)
        {
            cookie.setMaxAge(time);//设置生存期
        }
        cookie.setDomain(domain);//子域，在这个子域下才可以访问该Cookie
        cookie.setPath("/");
        //cookie.setSecure(true);//如果设置了Secure，则只有当使用https协议连接时cookie才可以被页面访问
        response.addCookie(cookie);
    }
    /**
     * 设置cookie值
     * @param response 响应对象
     * @param key 键
     * @param value 值
     */
    public static void setCookie(HttpServletResponse response,String key,String value,String domain,int time,boolean only)
    {
        Cookie cookie = new Cookie(key,value);
        cookie.setHttpOnly(only);//如果设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法访问该Cookie
        if(time>0)
        {
            cookie.setMaxAge(time);//设置生存期
        }
        cookie.setDomain(domain);//子域，在这个子域下才可以访问该Cookie
        cookie.setPath("/");
        //cookie.setSecure(true);//如果设置了Secure，则只有当使用https协议连接时cookie才可以被页面访问
        response.addCookie(cookie);
    }
}
