package cn.study.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:55
 * @Description
 */
@FeignClient("auth-service")
public interface AuthClient {

    // 根据clientId和secret的校验结果，获取jwtUtils专用key
    @GetMapping("/client/key")
    String getKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);
}
