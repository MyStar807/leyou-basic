package cn.study.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Meteor
 * @Date 2022/3/30 18:41
 * @Description
 */
@Configuration
@MapperScan("cn.study.user.mapper")
public class MybatisConfig {
}
