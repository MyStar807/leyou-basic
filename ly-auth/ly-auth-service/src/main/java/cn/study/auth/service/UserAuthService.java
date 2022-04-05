package cn.study.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meteor
 * @Date 2022/4/1 0:07
 * @Description
 */
public interface UserAuthService {
    void userLogin(String username, String password, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
