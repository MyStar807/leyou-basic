package cn.study;

import cn.study.auth.annotation.EnableJwtVerification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Meteor
 * @Date 2022/3/30 13:11
 * @Description
 */
@SpringBootApplication
//用户拦截器开关，
@EnableJwtVerification
public class LyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyUserApplication.class, args);
    }

}
