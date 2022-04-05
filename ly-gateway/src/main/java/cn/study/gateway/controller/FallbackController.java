package cn.study.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Meteor
 * @Date 2022/3/30 11:32
 * @Description 服务熔断
 */
@Slf4j
@RestController
public class FallbackController {
    @GetMapping("/hystrix/fallback")
    public ResponseEntity<String> fallback(ServerWebExchange exchange) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 99999);
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        result.put("data", null);
        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
        log.error("接口调用失败，URL={}", delegate.getRequest().getURI(), exception);
        if (exception instanceof HystrixTimeoutException) {
            result.put("msg", "接口调用超时");
        } else if (exception != null && exception.getMessage() != null) {
            result.put("msg", "接口调用失败: " + exception.getMessage());
        } else {
            result.put("msg", "接口调用失败");
        }
        //return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("网关超时");
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(JSON.toJSONString(result));
    }
}
