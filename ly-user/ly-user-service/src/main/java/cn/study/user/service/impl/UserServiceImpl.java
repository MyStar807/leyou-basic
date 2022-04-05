package cn.study.user.service.impl;

import cn.study.common.exception.LyException;
import cn.study.common.utils.RegexUtils;
import cn.study.user.dto.UserDTO;
import cn.study.user.mapper.UserMapper;
import cn.study.user.model.User;
import cn.study.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.study.user.constants.Constants.KEY_PHONE_PREFIX;

/**
 * @Author Meteor
 * @Date 2022/3/30 19:17
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    AmqpTemplate amqpTemplate;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 查询用户名或手机号是否存在
     * @param data 用户名或手机号
     * @param type 1 = 用户名，2 = 手机号
     * @return
     */
    @Override
    public Boolean checkDataExist(String data, Integer type) {
        if ((type != 1 && type != 2) || StringUtils.isEmpty(data) || (type == 2 && !RegexUtils.isPhone(data))) {
            throw new LyException(400, "参数错误");
        }
        String column = null;
        if (type == 1) column = "username";
        else if (type == 2) column = "phone";
        return this.query().eq(column, data).count() == 1;
    }

    @Override
    public void sendVerifyCode(String phone) {
        if (!RegexUtils.isPhone(phone)) {
            throw new LyException(400, "手机号格式错误");
        }

        Map<String, String> msg = new HashMap<>();
        String code = RandomStringUtils.randomNumeric(5);
        msg.put("phone", phone);
        msg.put("code", code);

        // 利用sms发送短信
        // this.amqpTemplate.convertAndSend(SMS_EXCHANGE_NAME, VERIFY_CODE_KEY, msg);

        this.redisTemplate.opsForValue().set(KEY_PHONE_PREFIX + phone, code, 5, TimeUnit.MINUTES);
        System.err.println("phone = " + phone + ",code = " + code);
    }

    @Override
    public void createUser(User user, String code) {
        if (StringUtils.isEmpty(code)) {
            throw new LyException(400, "验证码不能为空");
        }

        // redis key
        String key = KEY_PHONE_PREFIX + user.getPhone();

        // key不存在或验证码不匹配
        if (!redisTemplate.hasKey(key) || !code.equals(redisTemplate.opsForValue().get(key))) {
            throw new LyException(400, "验证码失效");
        }

        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 保存
        this.save(user);
    }

    @Override
    public UserDTO queryUserByNameAndPass(String username, String password) {
        User user = this.query()
                .eq("username", username).one();

        if (null == user) {
            throw new LyException(400, "用户名不存在");
        }

        //验密
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new LyException(400, "密码输入错误");
        }

        return new UserDTO(user);
    }
}
