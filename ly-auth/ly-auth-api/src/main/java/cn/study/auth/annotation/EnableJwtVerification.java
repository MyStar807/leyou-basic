package cn.study.auth.annotation;

import cn.study.auth.config.MvcConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:51
 * @Description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MvcConfiguration.class)
@Documented
@Inherited
public @interface EnableJwtVerification {
}
