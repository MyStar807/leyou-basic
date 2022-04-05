package cn.study.auth.config;

import cn.study.auth.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Meteor
 * @Date 2022/3/31 23:55
 * @Description
 */
@Configuration
public class JwtConfig {
    @Value("${ly.jwt.key}")
    private String key;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils(key, redisTemplate);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
