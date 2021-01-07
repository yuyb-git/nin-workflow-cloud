/*
 * WorkflowRoleMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-03 Created
 */
package cn.netinnet.ninzuul.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.ninzuul.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /** 方法描述
     * @description 查询所有角色code和name
     * @return []
     * @author wanghy
     * @date 2020/4/7 16:10
     */
    List<SysRole> queryAllCodeName();

    /** 方法描述
     * @description 根据角色id查找对应的用户id列表
     * @param roleId
     * @return [roleId]
     * @author wanghy
     * @date 2020/4/7 16:38
     */
    List<Long> queryUserIdsByRoleId(@Param("roleId") Long roleId);

    /** 方法描述
     * @description 校验code是否已存在(排除自身那条)
     * @param roleCode
     * @param roleId
     * @return [roleCode, roleId]
     * @author wanghy
     * @date 2020/4/7 16:43
     */
    Integer countRoleCodeAndId(@Param("roleCode") String roleCode, @Param("roleId") Long roleId);

    /** 方法描述
     * @description 查询角色信息列表
     * @param sysRole
     * @return [sysRole]
     * @author wanghy
     * @date 2020/4/7 16:50
     */
    List<SysRole> queryList(SysRole sysRole);

}