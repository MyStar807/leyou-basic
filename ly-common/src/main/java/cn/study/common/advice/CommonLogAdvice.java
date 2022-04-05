package cn.study.common.advice;

import cn.study.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author Meteor
 * @Date 2022/3/30 14:22
 * @Description 日志切面
 */
@Slf4j
@Aspect
@Component
public class CommonLogAdvice {

    // 匹配@Service注解和IService类和子类中执行
    @Around("within(@org.springframework.stereotype.Service *)||within(com.baomidou.mybatisplus.extension.service.IService+)")
    public Object handlerExceptionLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取被增强的方法相关信息
        Signature signature = joinPoint.getSignature();
        try {
            log.debug("{}方法准备调用，参数: {}", signature, Arrays.toString(joinPoint.getArgs()));
            long start = System.currentTimeMillis();
            // 执行目标方法
            Object result = joinPoint.proceed();
            log.debug("{}方法调用执行成功，执行耗时: {}", signature, System.currentTimeMillis() - start);
            return result;
        } catch (Throwable throwable) {
            log.error("{}方法执行失败，原因：{}", signature, throwable.getMessage(), throwable);
            // 判断是否为自定义异常
            if (throwable instanceof LyException) {
                // 自定义异常直接抛出
                throw throwable;
            } else {
                // 转成LyException
                throw new LyException(500, throwable);
            }
        }
    }

}
