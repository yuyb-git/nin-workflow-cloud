/*
 * SysSchoolMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-09 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysSchool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
public interface SysSchoolMapper extends BaseMapper<SysSchool> {
    /** 方法描述
     * @description 根据学校id查询管理员账号
     * @param schoolId
     * @return [schoolId]
     * @author wanghy
     * @date 2020/4/9 10:33
     */
    String queryAdminLoginBySchoolId(Long schoolId);

    /** 方法描述
     * @description 查询学校列表
     * @param sysSchool
     * @return [sysSchool]
     * @author wanghy
     * @date 2020/4/9 10:54
     */
    List<SysSchool> queryList(SysSchool sysSchool);

    /**
     * @Author wangyt
     * @Date 2019/8/5 17:00
     * @Description 根据学校类型查询学校idNameList
     */
    List<SysSchool> queryListByType(Integer schoolType);

    /**  方法描述
     * @Description 查询学校状态
     * @Author yuyb
     * @Date 10:33 2020/5/29
     * @param schoolId
     * @return java.lang.Integer
     **/
    @Select("select school_status from sys_school where school_id = #{schoolId} and del_flag = 0")
    Integer querySchoolStatusById(@Param("schoolId") Long schoolId);


    /**  方法描述
     * @Description 获取被删除的学校id
     * @param endDate
     * @return List<java.lang.Long>
     * @Author Caicm
     * @Date 2020/5/29 14:44
     **/
    List<Long> queryDeletedSchoolIds(@Param("endDate") String endDate);

    /***
    * 查询学校名称
    * @author ousp
    * @date 2020/10/19
    * @return java.lang.Integer
    */
    String querySchoolNameById(@Param("schoolId") Long schoolId);
}
