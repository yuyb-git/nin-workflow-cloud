package cn.netinnet.ninzuul.authentication;

import cn.netinnet.ninzuul.dao.SysRolePermissionMapper;
import cn.netinnet.ninzuul.utils.RedisUtil;
import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.RoleConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.exception.ExpiredAccountException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Linjj
 * @Date 2019/8/15 9:17
 * @Description 自定义实现 ShiroRealm，包含认证和授权两大模块
 */
public class ShiroRealm extends AuthorizingRealm {

    private final static Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的
        String token = String.valueOf(authenticationToken.getCredentials());
        if (StringUtils.isBlank(token)) {
            throw new IncorrectCredentialsException("token为空，未通过认证");
        }
        // 进行token校验
        if (!JWTUtil.verify(token)) {
            throw new IncorrectCredentialsException("token校验不通过");
        }
        // 获取jwt中的用户信息
        UserInfo user = JWTUtil.getUser(token);
        if (user == null) {
            throw new IncorrectCredentialsException("获取认证的用户信息为null");
        }
        // 校验jwt中用户在redis对应的token信息
        String userIdTokenKey = RedisUtil.getKey(CacheConstant.R_USERID_TOKEN, user.getUserId());
        String latestRefreshToken = RedisUtil.hasKey(userIdTokenKey)?RedisUtil.get(userIdTokenKey).toString():"";
        // 若redis对应的refreshToken信息为空，说明登录已失效（被强制退出，用户信息变化）
        if (StringUtils.isBlank(latestRefreshToken)) {
            throw new ExpiredAccountException("登录已失效");
        }
        String tokenCacheKey= RedisUtil.getKey(CacheConstant.R_TOKEN_REFRSHTOKEN, token);
        String currentRefreshToken = RedisUtil.hasKey(tokenCacheKey)?RedisUtil.get(tokenCacheKey).toString():"";
        // 若redis对应的token对应的refreshToken的信息为空，说明token过期了，则重新刷新token----这种情况一般不会出现，token过期在JWTUtil.verify(token)中就进行判断了
        if(StringUtils.isBlank(currentRefreshToken)){
            throw new ExpiredCredentialsException("token out of date");
        }
        // 若redis中userId对应的refreshToken信息与客户端发送的token从redis中取出的refreshToken不相符，说明已被他人登录-----但对于系统管理员，不作异地登录限制
        boolean isStudent = RoleConstant.STUDENT.equals(user.getRoleCode());
        if (!latestRefreshToken.equals(currentRefreshToken) && isStudent) {
            throw new ConcurrentAccessException("异地登录");
        }
        return new SimpleAuthenticationInfo(user, token, "workflow_realm");
    }

    /**
     * 授权模块，获取用户角色和权限
     *
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        UserInfo userInfo = (UserInfo) principal.getPrimaryPrincipal();
        String roleCode = userInfo.getRoleCode();
        // 获取用户角色集(本项目用户和角色是一对一，所以就不去数据库查询了)
        Set<String> roleSet = new HashSet<>();
        roleSet.add(roleCode);
        simpleAuthorizationInfo.setRoles(roleSet);
        // 获取用户权限集(查询角色权限表)
        List<String> rolePermission = sysRolePermissionMapper.queryPermissionByRoleCode(roleCode);
        Set<String> permissionSet = rolePermission.stream()
                // 过滤空字符的权限
                .filter(StringUtils :: isNotBlank)
                .collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
}
