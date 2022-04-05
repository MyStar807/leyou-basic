package cn.study.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Meteor
 * @Date 2022/3/30 13:06
 * @Description 测试
 */
@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Meteor!";
    }

}
