package cn.study.auth.controller;

import cn.study.auth.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meteor
 * @Date 2022/4/1 0:07
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<Void> userLogin(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
       this.userAuthService.userLogin(username,password,response);
       return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        //1,删除redis中的用户token对应的jti
        //2，删除cookie，覆盖
        this.userAuthService.logout(request,response);
        return ResponseEntity.ok().build();
    }
}
