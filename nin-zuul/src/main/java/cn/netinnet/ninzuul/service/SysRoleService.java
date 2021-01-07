package cn.netinnet.ninzuul.service;

import cn.netinnet.ninzuul.domain.SysRole;
import cn.netinnet.cloudcommon.base.Service;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;

import java.util.List;

/**
 * @author yuyb
 * @date   2020-04-03
 */
public interface SysRoleService extends Service<SysRole> {

    /**查询对应角色的权限列表（包含权限的选择状态） **/
    HttpResultEntry queryPermission(Long roleId, List<Integer> projectTypes);

    /** 批量删除角色 **/
    HttpResultEntry batchDeleteRole(List<Long> roleIds);

    /** 删除角色 **/
    HttpResultEntry deleteRole(Long roleId);

    /** 删除角色 **/
    HttpResultEntry editRole(SysRole sysRole, String rolePermissionJson);

    /** 新增角色 **/
    HttpResultEntry addRole(SysRole sysRole, String rolePermissionJson);
}
