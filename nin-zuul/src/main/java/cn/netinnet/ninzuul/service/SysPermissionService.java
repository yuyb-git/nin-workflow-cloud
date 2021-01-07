package cn.netinnet.ninzuul.service;


import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.common.base.Service;
import cn.netinnet.ninzuul.domain.SysPermission;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yuyb
 * @date   2020-04-03
 */
public interface SysPermissionService extends Service<SysPermission> {

    /** 获取用户权限路由 **/
    String getRouters(String roleCode, Integer projectType);

    /** 新增/编辑权限 **/
    HttpResultEntry addEditPermission(SysPermission sysPermission);

    /** 删除权限（物理删除） **/
    HttpResultEntry deletePermission(Long permissionId);

    /** 可选上级权限列表查询 **/
    HttpResultEntry queryParentPermissions(Integer projectType, @RequestParam(defaultValue = "0") Long permissionId);

    /** 后台查询权限列表树 **/
    HttpResultEntry queryPermissionTree(Integer projectType);

    /** 根据角色获取权限 **/
    List<String> getPermissionByRoleCode(String roleCode);

}
