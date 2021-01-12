package cn.netinnet.educationcenter.controller;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.constant.ErrorMsgConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseController;
import cn.netinnet.common.util.httpclient.RequestUtil;
import cn.netinnet.common.util.httpclient.SessionUtil;
import cn.netinnet.educationcenter.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @ClassName LoginController
 * @Description
 * @Author yuyb
 * @Date 2020/3/12 10:50
 */
@RestController
@RequestMapping("/")
public class LoginController extends BaseController {

    @Resource
    SysUserService sysUserService;

    /** 方法描述
     * @description 登录
     * @param info
     * @return [userLogin, userPwd, type]
     * @author wanghy
     * @date 2020/4/7 15:39
     */
    @PostMapping(value="login")
    @PreventRepeatSubmit
    @LogMark("登录")
    public HttpResultEntry login(@RequestParam("info") String info, HttpSession session, HttpServletRequest request) {
        if(StringUtils.isBlank(info) || info.length() < 4){
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        SessionUtil.setSession(session, GlobalConstant.STU_QUERY_ANSWER_PERMISSION, null);
        SessionUtil.setSession(session, GlobalConstant.IS_TEACHER_VIEW, null);
        ExamInfo examInfo = new ExamInfo();
        SessionUtil.setSession(GlobalConstant.SESSION_EXAM, examInfo);
        String ip = RequestUtil.getRemoteAddr(request);
        return sysUserService.login(info, ip);
    }

    @GetMapping(value="getUser")
    public HttpResultEntry getUser(HttpServletRequest request){
        UserInfo userInfo = UserUtil.getUser();
        return HttpResultEntry.ok("操作成功", userInfo);
    }

    @GetMapping(value="/logout")
    public HttpResultEntry logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String sessionId = session.getId();
        String sessionKey = "spring:session:sessions:"+sessionId;
        if(RedisUtil.hasKey(sessionKey)){
            RedisUtil.del(sessionKey);
        }
        return HttpResultEntry.ok("操作成功");
    }

    @PostMapping("changePwd")
    public HttpResultEntry changePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
        //参数异常
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        //密码格式检查
        if (newPwd.length() < 6 || newPwd.length() > 16) {
            return HttpResultEntry.error(ErrorMsgConstant.USER_PWD_LENGTH);
        }
        //重复密码检查
        if (oldPwd.equals(newPwd)) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "新密码不能与旧密码相同");
        }
        return sysUserService.changePwd(oldPwd, newPwd);
    }


}
