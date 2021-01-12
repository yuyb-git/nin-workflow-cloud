package cn.netinnet.cloudcommon.utils;


import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.feign.NinZuulFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 \* @Author: Linjj
 \* @Date: 2019/8/15 11:27
 \* @Description: 用户工具类
 \*/
public class UserUtil {
    private static final Logger LOG = LoggerFactory.getLogger(UserUtil.class);

    private volatile static NinZuulFeign ninZuulFeign = null;

    private static void setNinZuulClient() {
        if (ninZuulFeign == null) {
            synchronized (UserUtil.class) {
                ninZuulFeign = SpringUtil.getBean(NinZuulFeign.class);
            }
        }
    }

    /**
     * @Author Linjj
     * @Date 2019/8/15 11:40
     * @Description 获取用户信息错误
     */
    public static UserInfo getUser() {
        if (ninZuulFeign == null) {
            setNinZuulClient();
        }
        Object user = ninZuulFeign.getCurrUser();
        if (!(user instanceof UserInfo)) {
            throw new CustomException("获取用户信息错误");
        }
        return (UserInfo) user;
    }
}
