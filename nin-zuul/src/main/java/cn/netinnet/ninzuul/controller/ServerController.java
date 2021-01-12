package cn.netinnet.ninzuul.controller;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.ninzuul.utils.ShiroUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/getCurrUser")
    public UserInfo getCurrUser() {
        return ShiroUtils.getUser();
    }

}
