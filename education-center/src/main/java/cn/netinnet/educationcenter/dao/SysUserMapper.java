/*
 * SysUserMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-07 Created
 */
package cn.netinnet.educationcenter.dao;


import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /** 方法描述
     * @description queryUserRoleByUserId
     * @param userId
     * @return [userId]
     * @author wanghy
     * @date 2020/4/3 16:39
     */
    String queryUserRoleByUserId(@Param("userId") Long userId);

    /** 方法描述
     * @description 根据用户名查询用户信息
     * @param login
     * @return [login]
     * @author wanghy
     * @date 2020/4/7 13:52
     */
    SysUser queryUserByLogin(@Param("login") String login);

    /** 方法描述
     * @description queryPwdById
     * @param userId
     * @return [userId]
     * @author wanghy
     * @date 2020/4/7 15:35
     */
    String queryPwdById(@Param("userId") Long userId);

    /** 方法描述
     * @description 根据用户id修改密码
     * @param userId
     * @param password
     * @return [userId, password]
     * @author wanghy
     * @date 2020/4/7 15:37
     */
    void updatePwdById(@Param("userId") Long userId, @Param("userPwd") String password);

    /** 方法描述
     * @description 查询用户信息
     * @param sysUser
     * @return [sysUser]
     * @author wanghy
     * @date 2020/4/8 9:24
     */
    List<SysUser> queryList(SysUser sysUser);

    /** 方法描述
     * @description 统计有效记录数
     * @param login
     * @return [login]
     * @author wanghy
     * @date 2020/4/8 9:32
     */
    Integer countNumByCondition(@Param("login") String login, @Param("classId") Long classId);

    /** 方法描述
     * @description 传入login列表查询其中已存在的login
     * @param loginList
     * @return [loginList]
     * @author wanghy
     * @date 2020/4/8 9:54
     */
    List<String> checkLoginsExist(@Param("list") List<String> loginList);

    /** 方法描述
     * @description 根据账号修改密码和用户名
     * @param userLogin
     * @param userPwd
     * @param userName
     * @return [userLogin, userPwd, userName]
     * @author wanghy
     * @date 2020/4/9 10:40
     */
    void updatePwdAndNameByLogin(@Param("userLogin") String userLogin,
                                 @Param("userPwd") String userPwd,
                                 @Param("userName") String userName);

    /** 方法描述
     * @description 根据账号修改用户名
     * @param userLogin
     * @param userName
     * @return [userLogin, userName]
     * @author wanghy
     * @date 2020/4/9 10:42
     */
    void updateNameByLogin(@Param("userLogin") String userLogin, @Param("userName") String userName);

    /**
     * @Author wangyt
     * @Date 2019/12/9 17:50
     * @Description 根据用户类型, 学校id, 组织id, 查询用户idList
     */
    List<Long> queryIdListByUserSchoolOrg(SysUser sysUser);


    /** 方法描述
     * @description 根据schoolId, userType获取userId
     * @param schoolId
     * @param userType
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/4 14:44
     */
    List<Long> queryUserIdsBySchoolId(Long schoolId, Integer userType);


    /** 方法描述
     * @description  获取已经被删除的学生Id
     * @param   endDate
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/5 10:23
     */
    List<Long> queryDeletedStudentIds(@Param("endDate") String endDate);

    /** 方法描述
     * @description 获取对应组织下的学生id
     * @param schoolOrgId
     * @param column
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/5 13:31
     */
    List<Long> queryStudentIdsByOrgId(@Param("schoolOrgId") Long schoolOrgId, @Param("column") String column);

    /** 方法描述
     * @description 查询 学生用户信息
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysUser>
     * @author Caicm
     * @date 2020/6/19 13:34
     */
    List<SysUser> queryUserInfo(@Param("schoolId") Long schoolId,
                                @Param("className") String className,
                                @Param("studentIds") List<Long> studetnIds,
                                @Param("userId") Long userId);

    /**
    * 插入或更新用户
    * @author ousp
    * @date 2020/7/29
    * @return void
    */
    void insertOrUpdate(@Param("userId") long userId,
                        @Param("userType") int userType,
                        @Param("roleCode") String roleCode,
                        @Param("userLogin") String userLogin,
                        @Param("userName") String userName,
                        @Param("userPwd") String userPwd,
                        @Param("schoolId") long schoolId,
                        @Param("createUserId") long createUserId);

    /** 方法描述
     * @description 批量逻辑删除学生
     * @param studentIds
     * @return void
     * @author Caicm
     * @date 2020/7/29 13:56
     */
    void batchLogicalDelete(@Param("studentIds") List<Long> studentIds);

    /**
    * 获取存在的用户
    * @param userIds
    * @author ousp
    * @date 2020/8/6
    * @return java.util.List<java.lang.Long>
    */
    List<Long> queryExistUser(@Param("userIds") List<Long> userIds);

    /**
    * 查询用户名
    * @param userIds
    * @author ousp
    * @date 2020/8/14
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysUser>
    */
    List<SysUser> queryUserName(@Param("userIds") Set<Long> userIds);

    /***
    * 通过第三方id更新用户状态
    * @author ousp
    * @date 2020/10/20
    * @return void
    */
    void changeUserStatusByIndividualityId(@Param("individualityIds") List<Long> individualityId, int status);

    /***
    * 通过第三方关字段查询用户关系
    * @author ousp
    * @date 2020/10/20
    */
    List<SysUser> queryRelByIndividualityKey(@Param("schoolId") Long schoolId,
                                             @Param("classId") Long classId,
                                             @Param("individualityIds") List<Long> individualityIds);

    /***
    * 更新学校名或者班级名
    * @author ousp
    * @date 2020/10/22
    * @return void
    */
    void updateSchoolAndClassName(@Param("userIds") List<Long> userIds, @Param("schoolName") String schoolName, @Param("className") String className);

    /***
    * 通过第三方id查询用户
    * @author ousp
    * @date 2020/10/22
    * @return java.util.List<java.lang.Long>
    */
    List<Long> queryUserIdByIndividualityId(@Param("individualityId") long individualityId);

    /**  方法描述
     * @Description 通过第三方id查询用户
     * @Author yuyb
     * @Date 17:44 2020/10/22
     * @param individualityIds
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> queryUserIdByIndividualityIds(@Param("individualityIds") List<Long> individualityIds);

    List<SysUser> queryUserInfoByIndividual(@Param("schoolId") Long schoolId,
                                            @Param("className") String className,
                                            @Param("individualIds") List<Long> individualIds);

}
