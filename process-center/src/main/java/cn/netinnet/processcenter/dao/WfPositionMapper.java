/*
 * WfPositionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-10 Created
 */
package cn.netinnet.processcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.processcenter.domain.WfPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public interface WfPositionMapper extends BaseMapper<WfPosition> {

    /**  方法描述
     * @Description 查询指定字段
     * @Author yuyb
     * @Date 10:36 2020/7/30
     * @param positionId
     * @param columns
     * @return cn.netinnet.processcenter.domain.WfPosition
     **/
    WfPosition selectColumnsById(@Param("positionId") long positionId, @Param("columns") String columns);

    /** 方法描述
     * @description 获取岗位
     * @param companyId
     * @return java.util.List<java.lang.String>
     * @author Caicm
     * @date 2020/4/14 16:40
     */
    List<String> getPositionNameByCompanyId(@Param("companyId") Long companyId);

    /**  方法描述
     * @Description 根据当前岗位id获取父岗位id
     * @Author yuyb
     * @Date 10:03 2020/4/20
     * @param positionId
     * @return long
     **/
    long getParentPositionId(@Param("positionId") long positionId);


    /** 方法描述
     * @description 获取第一级岗位
     * @param companyId
     * @return cn.netinnet.processcenter.domain.WfPosition
     * @author Caicm
     * @date 2020/4/20 10:53
     */
    WfPosition getRootPostion(@Param("companyId") Long companyId);

    
    /** 方法描述
     * @description 根据企业id查询企业下所有岗位 
     * @param companyId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfPosition>
     * @author Caicm
     * @date 2020/5/9 17:28
     */ 
    List<WfPosition>  getAllPositionByCompanyId(@Param("companyId") Long companyId);

    /** 方法描述
     * @description 根据父岗位ID获取子岗位ID
     * @param parentId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/5/9 17:40
     */ 
    List<Long> getPositionIdByParent(@Param("parent") Long parentId);

    /**
    *  通过企业id查询
    * @param companyIds
    * @author ousp
    * @date 2020/5/20
    * @return java.util.List<cn.netinnet.processcenter.domain.WfPosition>
    */
    List<WfPosition> queryByCompanyIds(@Param("companyIds") List<Long> companyIds);

    /**  方法描述
     * @Description 查询岗位名称
     * @Author yuyb
     * @Date 9:01 2020/5/21
     * @param positionId
     * @return java.lang.String
     **/
    String getPositionName(@Param("positionId") long positionId);

    /** 方法描述
     * @description 根据companyId获取positionId
     * @param companyId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/4 15:33
     */
    List<Long> queryPositionIdsByCompanyId(@Param("companyId") Long companyId);

    /** 方法描述
     * @description 获取企业下一级岗位个数
     * @param companyId
     * @return java.lang.Integer
     * @author Caicm
     * @date 2020/6/10 17:40
     */
    Integer countFirstPositionExist(@Param("companyId") Long companyId);

    /** 方法描述
     * @description 获取企业下同名岗位个数
     * @param companyId
     * @param positionName
     * @param positionId
     * @return java.lang.Integer
     * @author Caicm
     * @date 2020/6/10 17:40
     */
    Integer countPositionNameExist(@Param("companyId") Long companyId, @Param("positionName") String positionName, @Param("positionId") Long positionId);

    /** 方法描述
     * @description 获取企业下相同岗位编码岗位个数
     * @param companyId
     * @param positionCode
     * @param positionId
     * @return java.lang.Integer
     * @author Caicm
     * @date 2020/6/10 17:40
     */
    Integer countPositionCodeExist(@Param("companyId") Long companyId, @Param("positionCode") String positionCode, @Param("positionId") Long positionId);

    /** 方法描述
     * @description  获取企业下的岗位
     * @param companyIds
     * @return java.util.List<cn.netinnet.processcenter.domain.WfPosition>
     * @author Caicm
     * @date 2020/8/12 15:16
     */
    List<WfPosition>  queryPositionInfoByCompanyIds(@Param("companyIds") List<Long> companyIds);

    /***
    *   查询岗位名称
    * @param ids 岗位id
    * @author ousp
    * @date 2020/9/11
    * @return java.util.List<cn.netinnet.processcenter.domain.WfPosition>
    */
    List<WfPosition> queryPositionNameByIds(@Param("ids") Set<Long> ids);

    /***
    * 查询岗位个数
    * @author ousp
    * @date 2020/9/17
    * @return java.lang.Integer
    */
    Integer queryPositionCount(@Param("companyId") long companyId);
}