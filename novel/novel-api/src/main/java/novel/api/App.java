package novel.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
@SpringBootApplication
@ComponentScans({@ComponentScan("novel.service"),@ComponentScan("novel.dao")})
@MapperScan("novel.dao.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
