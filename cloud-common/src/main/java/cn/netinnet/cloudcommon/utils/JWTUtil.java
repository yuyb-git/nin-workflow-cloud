package cn.netinnet.cloudcommon.utils;


import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class JWTUtil {
    private final static Logger LOG = LoggerFactory.getLogger(JWTUtil.class);
    /**
     * jwt存在header的key名
     */
    public static final String ACCESS_TOKEN_KEY = "token";
    public static final String REFRESH_TOKEN_KEY = "refreshToken";
    /**
     * jwt加密的秘钥
     */
    public static String SECRET_KEY = "nin-workflow:(^*$^(*%#W^&&*)(%$^*!@#$%";
    public void setSecretKey(String secretKey) {
        JWTUtil.SECRET_KEY = secretKey;
    }

    /**
     * jwt签发有效期，默认一小时
     */
    public static long EXPIRE_TIME = 3600;

    /**
     * 静态变量通过set方法注入(乘以1000，将其转为秒单位)
     */
    public void setExpireTime(long expireTime) {
        JWTUtil.EXPIRE_TIME = expireTime * 1000;
    }

    /**
     * jwt刷新有效期，默认7天
     */
    public static long REFRESH_TIME = 3;

    /**
     * 静态变量通过set方法注入(乘以1000*60*60*24，将其转为小时)
     */
    public void setRefreshTime(long freshDay) {
        JWTUtil.REFRESH_TIME = freshDay * 1000 * 60 * 60;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 10:35
     * @Description JWT生成
     */
    public static String sign(UserInfo user, Date expiresAt) {
        try {
            // 加密——>算法+秘钥
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withClaim("user", JSON.toJSONString(user))
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (Exception e) {
            LOG.error("JWT生成失败:", e);
            throw new CustomException(e);
        }
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 15:00
     * @Description 验证JWT
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            throw new ExpiredCredentialsException(e.getMessage());
        } catch (Exception e) {
            LOG.debug("token校验失败:", e);
            return false;
        }
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 17:38
     * @Description 获取jwt中的用户信息
     */
    public static UserInfo getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return JSON.parseObject(jwt.getClaim("user").asString(), UserInfo.class);
        } catch (JWTDecodeException e) {
            LOG.error("获取jwt中的用户信息失败：", e);
            return null;
        }
    }

    /**
    * token解析工具
    * @param secretKey  秘钥
    * @param token      token
    * @author ousp
    * @date 2020/6/12
    * @return java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>
    */
    public static Map<String, Claim> parseToken(String secretKey, String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (TokenExpiredException e) {
            throw new ExpiredCredentialsException(e.getMessage());
        } catch (Exception e) {
            throw new IncorrectCredentialsException("token校验不通过");
        }
    }

    /**
    * token验证
    * @param secretKey
    * @param token
    * @author ousp
    * @date 2020/6/23
    * @return boolean
    */
    public static boolean verify(String secretKey, String token) {
        try {
            System.out.println(token);
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            throw new ExpiredCredentialsException(e.getMessage());
        } catch (Exception e) {
            LOG.debug("token校验失败:", e);
            return false;
        }
    }
}
