package cn.study.user.controller;

import cn.study.auth.dto.UserDetail;
import cn.study.auth.utils.UserContext;
import cn.study.user.dto.UserDTO;
import cn.study.user.model.User;
import cn.study.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Meteor
 * @Date 2022/3/30 19:34
 * @Description
 */
@RestController
@RequestMapping("/info")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 检查用户名或手机号是否唯一
     *
     * @param data 用户名或手机号
     * @param type 1 = 用户名，2 = 手机号
     */
    @GetMapping("/exists/{data}/{type}")
    public ResponseEntity<Boolean> checkDataExist(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        return ResponseEntity.ok(this.userService.checkDataExist(data, type));
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone") String phone) {
        this.userService.sendVerifyCode(phone);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid User user, @RequestParam String code) {
        this.userService.createUser(user, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<UserDTO> queryUserByNameAndPass(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok(this.userService.queryUserByNameAndPass(username, password));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetail> me(){
        UserDetail user = UserContext.getUser();
        System.out.println("user = " + user);
        return ResponseEntity.ok(user);
    }
}
