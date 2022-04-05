package cn.study.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:59
 * @Description
 */
@Data
@ConfigurationProperties("ly.auth")
public class ClientProperties {
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端秘钥
     */
    private String secret;


    /**
     * 拦截器拦截路径
     */
    private List<String> includeFilterPaths;
    /**
     * 拦截器放行路径
     */
    private List<String> excludeFilterPaths;
}
