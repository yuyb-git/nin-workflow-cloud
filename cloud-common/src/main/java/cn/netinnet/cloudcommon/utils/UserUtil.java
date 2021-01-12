package cn.netinnet.cloudcommon.utils;


import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.ExamInfo;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.feign.NinZuulFeign;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;


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

    /**  方法描述
     * @Description 获取用户的批次或者考试信息
     * @Author yuyb
     * @Date 10:10 2020/6/16
     * @param session
     * @return cn.netinnet.workflow.sys.domain.dto.ExamInfo
     **/
    public static ExamInfo getExamInfo(HttpSession session){
        ExamInfo examInfo = SessionUtil.getSessionAttr(session, GlobalConstant.SESSION_EXAM, new ExamInfo());
        // 教师出题questionId不为0， 学生首页登录examId不为0， 学生考试平台跳转examId和questionId都不为0
        if (examInfo.getExamId() == 0L && examInfo.getQuestionId() == 0L) {
            throw new AuthenticationException("session过期!");
        }
        return examInfo;
    }


}
