package com.wxd.toutiao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 项目入口
 * Created by wangxiaodan on 2018/3/23.
 */
@RestController
@EnableAutoConfiguration
public class App {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
    @RequestMapping("/")
    String home() {
    	String boo = stringRedisTemplate.opsForValue().get("boo");
    	
        return "Hello World!" + boo;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
