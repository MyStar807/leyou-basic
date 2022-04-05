package cn.study.user.service;

import cn.study.user.dto.UserDTO;
import cn.study.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Meteor
 * @Date 2022/3/30 19:16
 * @Description
 */
public interface UserService extends IService<User> {
    Boolean checkDataExist(String data, Integer type);

    void sendVerifyCode(String phone);

    void createUser(User user, String code);

    UserDTO queryUserByNameAndPass(String username, String password);
}
