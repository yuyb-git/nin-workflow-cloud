/*
 * WfStaffMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-09 Created
 */
package cn.netinnet.processcenter.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.processcenter.domain.WfStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface WfStaffMapper extends BaseMapper<WfStaff> {

    /**  方法描述
     * @Description 查询员工必要信息
     * @Author yuyb
     * @Date 14:41 2020/4/24
     * @param staffId
     * @return cn.netinnet.processcenter.domain.WfStaff
     **/
    WfStaff getStaffById(@Param("staffId") long staffId);

    /**  方法描述
     * @Description 查询部门员工
     * @Author yuyb
     * @Date 17:25 2020/8/31
     * @param deptId
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> staffList(@Param("deptId") Long deptId);

    /** 方法描述
     * @description 员工列表
     * @param deptId
     * @param searchItem
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author Caicm
     * @date 2020/4/13 14:30
     */
    List<Map<String,Object>> queryStaffList(@Param("deptId") Long deptId, @Param("searchItem") String searchItem);

    /** 方法描述
     * @description 修改启用状态
     * @param enable
     * @param staffId
     * @return void
     * @author Caicm
     * @date 2020/4/13 14:31
     */
    void changeStaffEnableStatus(@Param("enable") Integer enable, @Param("staffId") Long staffId);

    /**  方法描述
     * @Description 查询岗位下的所有员工
     * @Author yuyb
     * @Date 10:51 2020/7/30
     * @param positionId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
     **/
    List<WfStaff> getStaffByPositionId(@Param("positionId") long positionId);

    /**  方法描述
     * @Description 查询岗位下的所有员工id
     * @Author yuyb
     * @Date 15:52 2020/4/17
     * @param positionIds
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> getStaffIdListByPositionId(@Param("positionIds") List<Long> positionIds, @Param("enable") Integer enable);

    /**  方法描述
     * @Description 查询部门=deptId岗位=id下的所有员工id
     * @Author yuyb
     * @Date 17:01 2020/7/29
     * @param deptIds
     * @param positionId
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> getStaffIdListByDeptAndPositionId(@Param("deptIds") List<Long> deptIds,
                                                 @Param("positionId") long positionId);

    /**  方法描述
     * @Description 查询员工的岗位id
     * @Author yuyb
     * @Date 10:19 2020/4/20
     * @param staffId
     * @return java.lang.Long
     **/
    Long getPositionIdByStaff(@Param("staffId") long staffId);

    /**
     * 查询用户名和工号
     * @param staffIds  用户id
     * @author ousp
     * @date 2020/4/24
     * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
     **/
    List<WfStaff> queryStaffNameAndJobNum(@Param("staffIds") Set<Long> staffIds);

    /** 方法描述
     * @description 获取公司下的所有员工
     * @param companyId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
     * @author Caicm
     * @date 2020/5/8 13:39
     */ 
    List<WfStaff> getAllStaffByCompanyId(@Param("companyId") Long companyId);

    /**
    * 查询企业下的员工
    * @param companyIds
    * @author ousp
    * @date 2020/5/20
    * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
    */
    List<WfStaff> queryStaffByCompanyIds(@Param("companyIds") List<Long> companyIds);

    /** 方法描述
     * @description 获取某个部门下的员工id
     * @param deptIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/2 11:01
     */
    List<Long> queryStaffIdsByDeptId(@Param("deptId") Long deptIds);

    /** 方法描述
     * @description 根据企业id获取员工id
     * @param companyId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/4 15:53
     */
    List<Long> queryStaffIdsByCompanyId(@Param("companyId") Long companyId);

    /** 方法描述
     * @description 获取企业中存在的相同工号
     * @param companyId
     * @param jobNumberList
     * @return java.util.List<java.lang.String>
     * @author Caicm
     * @date 2020/7/7 17:53
     */
    List<String> checkJobNumberExist(@Param("companyId") Long companyId, @Param("jobNumberList") List<String> jobNumberList);

    /** 方法描述
     * @description  查询同名的员工
     * @param jobNumber
     * @param companyId
     * @param staffId
     * @return java.util.List<java.lang.Integer>
     * @author Caicm
     * @date 2020/6/5 18:09
     */
    Integer queryExistStaff(@Param("jobNumber") String jobNumber, @Param("companyId") Long companyId, @Param("staffId") Long staffId);

    /**
    * 查询企业下的所有员工并取别名
    * @param companyId  企业id
    * @author ousp
    * @date 2020/7/23
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> getAllStaffByCompanyIdUseAlias(@Param("companyId") Long companyId);

    /**
    * 查询企业下员工个数
    * @param companyId
    * @author ousp
    * @date 2020/7/30
    * @return java.lang.Integer
    */
    Integer queryStaffCount(@Param("companyId") Long companyId);


    /** 方法描述
     * @description  获取企业下的员工信息
     * @param companyIds
     * @return java.util.List<cn.netinnet.processcenter.domain.WfStaff>
     * @author Caicm
     * @date 2020/8/12 15:02
     */
    List<WfStaff> queryStaffInfoByCompanyIds(@Param("companyIds") List<Long> companyIds);
}