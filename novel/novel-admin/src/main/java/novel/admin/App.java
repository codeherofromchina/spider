package novel.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 配置项目管理
 * Created by wangxiaodan on 2018/7/30.
 */
@SpringBootApplication
@ComponentScans({@ComponentScan("novel.service"), @ComponentScan("novel.dao"), @ComponentScan("novel.spider")})
@MapperScan("novel.dao.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}


