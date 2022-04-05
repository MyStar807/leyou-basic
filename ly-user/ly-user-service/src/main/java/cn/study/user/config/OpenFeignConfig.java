package cn.study.user.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Meteor
 * @Date 2022/3/30 18:42
 * @Description
 */
@Configuration
@EnableFeignClients(basePackages = "cn.study")
public class OpenFeignConfig {
}
