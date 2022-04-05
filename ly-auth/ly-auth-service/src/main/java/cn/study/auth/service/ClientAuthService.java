package cn.study.auth.service;

import cn.study.auth.model.ClientInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Meteor
 * @Date 2022/4/2 14:34
 * @Description
 */
public interface ClientAuthService extends IService<ClientInfo> {
    String getKey(String clientId, String secret);
}
