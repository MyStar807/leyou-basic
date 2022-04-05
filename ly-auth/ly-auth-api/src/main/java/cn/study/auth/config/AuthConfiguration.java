package cn.study.auth.config;

import cn.study.auth.client.AuthClient;
import cn.study.auth.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:56
 * @Description
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "ly.auth", name = {"clientId", "secret"})
@EnableConfigurationProperties(ClientProperties.class)
public class AuthConfiguration {
    private AuthClient authClient;
    private ClientProperties properties;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 参数自动传入赋值给成员遍历
    public AuthConfiguration(AuthClient authClient, ClientProperties properties) {
        System.err.println("authClient = " + authClient);
        System.err.println("properties = " + properties);
        this.authClient = authClient;
        this.properties = properties;
    }

    @Bean
    @Primary
    public JwtUtils jwtUtils(){
        try {
            // 获得密钥
            String key = authClient.getKey(properties.getClientId(), properties.getSecret());
            // 创建JwtUtils
            JwtUtils jwtUtils = new JwtUtils(key,redisTemplate);
            log.info("秘钥加载成功。");
            return jwtUtils;
        } catch (Exception e) {
            log.error("初始化JwtUtils失败，{}", e.getMessage());
            throw e;
        }
    }
}
