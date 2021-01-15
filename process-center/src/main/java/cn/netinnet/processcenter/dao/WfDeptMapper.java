/*
 * WfDeptMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-10 Created
 */
package cn.netinnet.processcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.processcenter.domain.WfDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface WfDeptMapper extends BaseMapper<WfDept> {
    /** 方法描述
     * @description 根据企业ID获取企业下所有部门
     * @param companyId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfDept>
     * @author Caicm
     * @date 2020/5/9 17:08
     */
    List<WfDept> getAllDeptByCompanyId(@Param("companyId") Long companyId);


    /** 方法描述
     * @description 根据父部门id获取子部门
     * @param parent
     * @return java.util.List<cn.netinnet.processcenter.domain.WfDept>
     * @author Caicm
     * @date 2020/5/9 17:09
     */ 
    List<WfDept> getChildrenDept(@Param("parent") Long parent);

    /**
    * 根据企业id查询
    * @param companyIds
    * @author ousp
    * @date 2020/5/20
    * @return java.util.List<cn.netinnet.processcenter.domain.WfDept>
    */
    List<WfDept> queryDeptByCompanyIds(@Param("companyIds") List<Long> companyIds);

    /**  方法描述
     * @Description 查询部门名称
     * @Author yuyb
     * @Date 8:58 2020/5/21
     * @param deptId
     * @return java.lang.String
     **/
    String getDeptNameById(@Param("deptId") long deptId);

    /** 方法描述 根据企业Id获取部门Id
     * @description
     * @param companyId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/4 15:41
     */
    List<Long> queryDeptIdsByCompanyId(@Param("companyId") Long companyId);

    /** 方法描述
     * @description  查询是否存在同一企业同级同名部门
     * @param companyId
     * @param deptId
     * @param deptName
     * @param parent
     * @return java.lang.Integer
     * @author Caicm
     * @date 2020/7/23 9:56
     */
    Integer countDeptExist(@Param("companyId") Long companyId, @Param("deptId") Long deptId,
                           @Param("deptName") String deptName, @Param("parent") Long parent);
    /**
    * 查询企业下的所有部门并取别名
    * @param companyId  企业id
    * @author ousp
    * @date 2020/7/23
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> getAllDeptByCompanyIdUseAlias(@Param("companyId") Long companyId);

    /**  方法描述
     * @Description 查询当前企业的部门
     * @Author yuyb
     * @Date 9:01 2020/7/30
     * @param companyId
     * @return java.lang.Long
     **/
    List<WfDept> getParentDept(@Param("companyId") Long companyId);

    /** 方法描述
     * @description  获取企业下的部门
     * @param companyIds
     * @return java.util.List<cn.netinnet.processcenter.domain.WfDept>
     * @author Caicm
     * @date 2020/8/12 15:13
     */
    List<WfDept> queryDeptInfoByCompanyIds(@Param("companyIds") List<Long> companyIds);

}