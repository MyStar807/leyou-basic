package cn.study.user.client;

import cn.study.user.dto.AddressDTO;
import cn.study.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Meteor
 * @Date 2022/3/30 18:04
 * @Description 供其他服务调用
 */
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/info")
    UserDTO queryUserByNameAndPass(@RequestParam String username,@RequestParam String password);

    @GetMapping("/address")
    AddressDTO queryUserAddress(@RequestParam Long userId, @RequestParam Long id);
}
