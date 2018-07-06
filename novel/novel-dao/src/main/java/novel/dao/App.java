package novel.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
@SpringBootApplication
@MapperScan("novel.dao.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
