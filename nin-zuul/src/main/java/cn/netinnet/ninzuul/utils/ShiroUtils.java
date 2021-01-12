package cn.netinnet.ninzuul.utils;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Classname ShiroUtils
 * @Description TODO
 * @Date 2021/1/11 17:12
 * @Created by ousp
 */
public class ShiroUtils {
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserInfo getUser() {
        Object user = SecurityUtils.getSubject().getPrincipal();
        if (!(user instanceof UserInfo)) {
            throw new CustomException("获取用户信息错误");
        }
        return (UserInfo) user;
    }
}
