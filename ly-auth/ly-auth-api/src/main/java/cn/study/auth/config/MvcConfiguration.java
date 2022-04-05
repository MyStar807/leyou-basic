package cn.study.auth.config;

import cn.study.auth.interceptors.LoginInterceptor;
import cn.study.auth.utils.JwtUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:54
 * @Description
 */

public class MvcConfiguration implements WebMvcConfigurer {
    // ioc如果有值会自动注入进来
    private JwtUtils jwtUtils;
    private ClientProperties properties;

    public MvcConfiguration(@Lazy JwtUtils jwtUtils, ClientProperties properties) {
        this.jwtUtils = jwtUtils;
        this.properties = properties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor(jwtUtils));
        if (!CollectionUtils.isEmpty(properties.getIncludeFilterPaths())) {
            registration.addPathPatterns(properties.getIncludeFilterPaths());
        }
        if (!CollectionUtils.isEmpty(properties.getExcludeFilterPaths())) {
            registration.excludePathPatterns(properties.getExcludeFilterPaths());
        }
    }
}
