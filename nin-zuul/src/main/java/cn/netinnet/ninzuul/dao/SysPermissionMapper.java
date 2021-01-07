/*
 * WorkflowPermissionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-03 Created
 */
package cn.netinnet.ninzuul.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.ninzuul.domain.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /** 方法描述
     * @description 查询对应项目类型的所有权限列表
     * @return [projectType]
     * @author wanghy
     * @date 2020/4/7 16:01
     */
    List<SysPermission> queryAllPermission(@Param("projectTypes") List<Integer> projectTypes);

    /** 方法描述
     * @description 获取用户路由权限列表
     * @param roleCode
     * @return [roleCode]
     * @author wanghy
     * @date 2020/4/8 13:42
     */
    List<SysPermission> queryUserRouter(@Param("roleCode") String roleCode, @Param("projectType") Integer projectType);

    /** 方法描述
     * @description 根据权限条件统计数量
     * @param permissionCode
     * @param permissionId
     * @param parentId
     * @return [permissionCode, permissionId, parentId]
     * @author wanghy
     * @date 2020/4/8 14:01
     */
    int countPermissionByCondition(@Param("permissionCode") String permissionCode,
                                   @Param("permissionId") Long permissionId,
                                   @Param("parentId") Long parentId);


    /** 方法描述
     * @description 查询对应项目的所有菜单权限列表
     * @param projectType
     * @return [projectType]
     * @author wanghy
     * @date 2020/4/8 14:11
     */
    List<SysPermission> queryAllMenuPermission(@Param("projectType") Integer projectType);

}