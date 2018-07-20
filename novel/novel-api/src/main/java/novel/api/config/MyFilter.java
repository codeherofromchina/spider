package novel.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wangxiaodan on 2018/7/16.
 */
@Configuration
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = request.getRemoteAddr();
        String url = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()           //端口号
                + httpRequest.getContextPath()      //项目名称
                + httpRequest.getServletPath()      //请求页面或其他地址
                + "?" + (httpRequest.getQueryString()); //参数
        System.out.println("用户访问tomcat02:" + ip + ";URL:" + url);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
