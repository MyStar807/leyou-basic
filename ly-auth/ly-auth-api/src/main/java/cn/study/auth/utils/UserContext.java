package cn.study.auth.utils;

import cn.study.auth.dto.UserDetail;

/**
 * @Author Meteor
 * @Date 2022/3/31 23:50
 * @Description
 */
public class UserContext {
    private static final ThreadLocal<UserDetail> TL = new ThreadLocal<>();

    /**
     * 存储一个用户到当前线程
     * @param user 用户信息
     */
    public static void setUser(UserDetail user) {
        TL.set(user);
    }

    /**
     * 从当前线程获取一个用户
     * @return 用户信息
     */
    public static UserDetail getUser() {
        return TL.get();
    }

    /**
     * 从当前线程移除用户
     */
    public static void removeUser() {
        TL.remove();
    }
}
