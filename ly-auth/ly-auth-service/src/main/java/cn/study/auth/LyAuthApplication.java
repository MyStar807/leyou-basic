package cn.study.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Meteor
 * @Date 2022/4/1 0:03
 * @Description
 */
// auth包下注解扫描不到内容所以要进行指定
@SpringBootApplication(scanBasePackages = "cn.study")
@EnableFeignClients(basePackages = "cn.study")
@MapperScan("cn.study.auth.mapper")
public class LyAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyAuthApplication.class, args);
    }
}
