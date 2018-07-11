package novel.spider;

import novel.spider.biduo.BiduoRobot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
@SpringBootApplication
@ComponentScans({@ComponentScan("novel.service"), @ComponentScan("novel.dao")})
@MapperScan("novel.dao.mapper")
public class App implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 程序启动
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        Map<String, Robot> robots = applicationContext.getBeansOfType(Robot.class);
//        for (Map.Entry<String, Robot> entry : robots.entrySet()) {
//            entry.getValue().start();
//        }

        BiduoRobot robot = applicationContext.getBean(BiduoRobot.class);
        robot.start();
    }
}
