/*
 * WorkflowRolePermissionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-03 Created
 */
package cn.netinnet.ninauthorization.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.ninauthorization.domain.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /** 方法描述
     * @description queryPermissionByRoleCode
     * @param roleCode
     * @return [roleCode]
     * @author wanghy
     * @date 2020/4/3 16:37
     */
    List<String> queryPermissionByRoleCode(@Param("roleCode") String roleCode);

    /** 方法描述
     * @description 查询当前角色拥有的权限id列表
     * @param roleId
     * @return [roleId]
     * @author wanghy
     * @date 2020/4/7 16:07
     */
    List<Long> queryPermissionIdsByRoleId(@Param("roleId") Long roleId);

    /** 方法描述
     * @description 批量逻辑删除对应的角色权限
     * @param userId
     * @param roleIds
     * @return [userId, roleIds]
     * @author wanghy
     * @date 2020/4/7 16:17
     */
    void logicDeleteByRoleIds(List<Long> roleIds, Long userId);

    /** 方法描述
     * @description 逻辑删除对应的角色权限
     * @param roleId
     * @param userId
     * @return [roleId, userId]
     * @author wanghy
     * @date 2020/4/7 16:25
     */
    void logicDeleteByRoleId(Long roleId, Long userId);

    /** 方法描述
     * @description 删除原有的角色权限
     * @param roleId
     * @return [roleId]
     * @author wanghy
     * @date 2020/4/7 16:35
     */
    void deletePermissionByRoleId(Long roleId);

    /** 方法描述
     * @description 同步更新权限编码
     * @param permissionCode
     * @param permissionId
     * @return [permissionCode, permissionId]
     * @author wanghy
     * @date 2020/4/8 13:50
     */
    void updatePermissionCodeById(@Param("permissionId") Long permissionId, @Param("permissionCode") String permissionCode);
}