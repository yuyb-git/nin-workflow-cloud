package cn.netinnet.cloudcommon.aspect;

import cn.netinnet.cloudcommon.annotation.RequiresPermission;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.feign.NinAuthorizationClient;
import cn.netinnet.cloudcommon.utils.UserUtil;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PermissionAspect
 * @Description
 * @Author yuyb
 * @Date 2021/1/14 14:31
 */
@Aspect
@Component
@Slf4j
@Order(10)
public class PermissionAspect {

    private final static Logger LOG = LoggerFactory.getLogger(PermissionAspect.class);

    @Resource
    NinAuthorizationClient ninAuthorizationClient;

    @Pointcut("@annotation(cn.netinnet.cloudcommon.annotation.RequiresPermission)")
    public void pointCut() {
    }

    /**  方法描述
     * @Description 进入方法前确定是否有权限
     * @Author yuyb
     * @Date 14:34 2021/1/14
     * @param requiresPermission
     * @return void
     **/
    @Before("pointCut() && @annotation(requiresPermission)")
    public void before(RequiresPermission requiresPermission){
        if(requiresPermission != null){
            UserInfo userInfo = UserUtil.getUser();
            String roleCode = userInfo.getRoleCode();
            List<String> rolePermission = ninAuthorizationClient.getPermissionByRoleCode(roleCode);
            String requirePermission = requiresPermission.value();
            if(!rolePermission.contains(requirePermission)){
                throw new CustomException("没有该操作权限！");
            }
        }

    }

}
