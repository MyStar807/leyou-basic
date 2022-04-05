package cn.study.auth.controller;

import cn.study.auth.mapper.ClientInfoMapper;
import cn.study.auth.model.ClientInfo;
import cn.study.auth.service.ClientAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Meteor
 * @Date 2022/4/2 14:33
 * @Description
 */
@RestController
@RequestMapping("/client")
public class ClientAuthController {

    @Autowired
    private ClientAuthService clientAuthService;


    /**
     * 根据clientId和secret的校验结果，获取jwtUtils专用key
     * @param clientId
     * @param secret
     * @return
     */
    @GetMapping("/key")
    public ResponseEntity<String> getKey(
            @RequestParam("clientId")String clientId,
            @RequestParam("secret")String secret){

        return ResponseEntity.ok(this.clientAuthService.getKey(clientId,secret));
    }
}
