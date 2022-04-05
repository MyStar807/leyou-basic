package cn.study.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author Meteor
 * @Date 2022/3/30 11:38
 * @Description 令牌桶算法 KeyResolver
 */
@Configuration
@Slf4j
public class RateLimitConfig {

    @Bean
    public KeyResolver ipKeyResolver(){
        return exchange -> {
            // 获取访问的主机ip进行限流，限流使用的是Redis中的令牌桶算法
            String hostName = exchange.getRequest().getRemoteAddress().getHostName();
            log.info("当前请求者的host地址：{}",hostName);
            return Mono.just(hostName);};
    }

}
