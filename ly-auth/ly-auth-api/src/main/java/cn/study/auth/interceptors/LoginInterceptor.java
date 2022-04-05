package cn.study.auth.interceptors;

import cn.study.auth.constants.JwtConstants;
import cn.study.auth.dto.Payload;
import cn.study.auth.dto.UserDetail;
import cn.study.auth.utils.JwtUtils;
import cn.study.auth.utils.UserContext;
import cn.study.common.exception.LyException;
import cn.study.common.utils.CookieUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Meteor
 * @Date 2022/3/31 23:40
 * @Description 拦截器获取cookie中用户信息
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    public LoginInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 获取cookie中的jwt
            String jwt = CookieUtils.getCookieValue(request, JwtConstants.COOKIE_NAME);
            // 验证并解析
            Payload payload = jwtUtils.parseJwt(jwt);
            UserDetail userDetail = payload.getUserDetail();
            UserContext.setUser(userDetail);
            log.info("用户{}正在访问。", userDetail.getUsername());
            return true;
        } catch (JwtException e) {
            throw new LyException(401, "JWT无效或过期!", e);
        } catch (IllegalArgumentException e){
            throw new LyException(401, "用户未登录!", e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }
}
