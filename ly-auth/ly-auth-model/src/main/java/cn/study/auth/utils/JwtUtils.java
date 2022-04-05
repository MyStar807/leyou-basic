package cn.study.auth.utils;

import cn.study.auth.dto.Payload;
import cn.study.auth.dto.UserDetail;
import cn.study.common.exception.LyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.study.auth.constants.JwtConstants.KEY_PREFIX;

/**
 * @Author Meteor
 * @Date 2022/3/31 23:03
 * @Description
 */
public class JwtUtils {
    /**
     * JWT解析器
     */
    private final JwtParser jwtParser;
    /**
     * 秘钥
     */
    private final SecretKey secretKey;

    private StringRedisTemplate redisTemplate;

    private final static ObjectMapper mapper = new ObjectMapper();

    public JwtUtils(String key, StringRedisTemplate redisTemplate) {
        // 生成密钥
        secretKey = Keys.hmacShaKeyFor(key.getBytes(Charset.forName("UTF-8")));
        // Jwt解析器 [注意加密解密用同一个键保证可逆加密]
        this.jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        this.redisTemplate = redisTemplate;
    }

    // 生成jwt token 自己指定jtl
    public String createJwt(UserDetail userDetail) {
        String jti = createJti();
        try {
            // 生成token
            String token = Jwts.builder().signWith(secretKey).setId(jti).claim("user", mapper.writeValueAsString(userDetail)).compact();
            this.redisTemplate.opsForValue().set(KEY_PREFIX + userDetail.getId(), jti, 30, TimeUnit.SECONDS);
            return token;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析jwt，并将用户信息转为指定的Class类型
     *
     * @param jwt token
     * @return 载荷，包含JTI和用户信息
     */
    public Payload parseJwt(String jwt) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);
            Claims claims = claimsJws.getBody();

            Payload payload = new Payload();
            // 唯一表示token的id
            payload.setJti(claims.getId());
            // 用户信息
            payload.setUserDetail(mapper.readValue(claims.get("user", String.class), UserDetail.class));

            String key = KEY_PREFIX + payload.getUserDetail().getId();

            // 获取redis中的唯一表示与解析后的结果进行判断
            if (this.redisTemplate.hasKey(key) && claims.getId().equals(redisTemplate.opsForValue().get(key))) {
                return payload;
            }
            throw new LyException(401, "用户未登录，或token非法");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createJti() {
        return StringUtils.replace(UUID.randomUUID().toString(), "_", "");
    }
}
