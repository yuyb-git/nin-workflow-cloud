package cn.netinnet.cloudcommon.utils;


import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 \* @Author: Linjj
 \* @Date: 2019/8/15 11:27
 \* @Description: 用户工具类
 \*/
public class UserUtil {
    private static final Logger LOG = LoggerFactory.getLogger(UserUtil.class);

    /**
     * @Author Linjj
     * @Date 2019/8/15 11:40
     * @Description 获取用户信息错误
     */
    public static UserInfo getUser() {
        Object user = SecurityUtils.getSubject().getPrincipal();
        if (!(user instanceof UserInfo)) {
            throw new CustomException("获取用户信息错误");
        }
        return (UserInfo) user;
    }

}