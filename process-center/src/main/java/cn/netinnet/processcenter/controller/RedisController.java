package cn.netinnet.processcenter.controller;

import cn.netinnet.cloudcommon.constant.RoleConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RedisController
 * @Description
 * @Author yuyb
 * @Date 2021/1/11 13:49
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @GetMapping(value="/getUser")
    public HttpResultEntry getUser(HttpServletRequest request){
        Object userInfo = RedisUtil.get("adminuser");
        return HttpResultEntry.ok("操作成功", userInfo);
    }

    @GetMapping(value="/setUser")
    public HttpResultEntry setUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(100000000000000000L);
        userInfo.setUserName("admin");
        userInfo.setUserType(UserConstant.ADMIN);
        userInfo.setRoleCode(RoleConstant.ADMIN);
        RedisUtil.set("adminuser", userInfo);
        return HttpResultEntry.ok("操作成功", userInfo);
    }

}
