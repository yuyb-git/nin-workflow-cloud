/*
 * SysBatchMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-26 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysBatch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface SysBatchMapper extends BaseMapper<SysBatch> {

   /**  方法描述
    * @Description 查询批次列表
    * @Author yuyb
    * @Date 14:56 2020/5/26
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysBatch>
    **/
    List<SysBatch> queryList(@Param("batchStatus") Integer batchStatus, @Param("batchName") String batchName,
                             @Param("schoolId") Long schoolId, @Param("userId") Long userId);

    /**  方法描述
     * @Description 根据学校ID查询批次列表
     * @Author yuyb
     * @Date 14:56 2020/5/26
     * @param schoolId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysBatch>
     **/
    @Select("select batch_id from  sys_batch where school_id = #{schoolId} and del_flag = 0")
    List<SysBatch> queryListBySchoolId(@Param("schoolId") Long schoolId);

    /**  方法描述
     * @Description 根据学校id和学生id获取其所在批次列表
     * @Author yuyb
     * @Date 14:56 2020/5/26
     * @param userIds
     * @param schoolId
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> queryBatchByUserIdAndSchoolId(@Param("userIds") List<Long> userIds, @Param("schoolId") Long schoolId, @Param("now") Date now, Integer batchStatus);

    /** 方法描述
     * @description  根据schoolId获取批次Id
     * @param schoolId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/5/29 16:59
     */
    List<Long> queryBatchIdsBySchoolId(@Param("schoolId") Long schoolId);


    /** 方法描述
     * @description  获取已经被删除的批次Id
     * @param endDate
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/5/29 17:09
     */
    List<Long> queryDeletedBatchIds(@Param("endDate") String endDate);

    /** 方法描述
     * @description 逻辑删除批次
     * @param batchId
     * @return void
     * @author Caicm
     * @date 2020/7/29 15:17
     */
    void  logicalDelete(@Param("batchId") Long batchId);

    /**  方法描述
     * @Description 查询教师批次
     * @Author yuyb
     * @Date 15:52 2020/10/22
     * @param schoolId
     * @param userId
     * @param now
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> batchList(@Param("schoolId") Long schoolId,
                                       @Param("userId") Long userId,
                                       @Param("batchStatus") Integer batchStatus,
                                       @Param("batchName") String batchName,
                                       @Param("now") Date now);

    Integer existBatch(@Param("batchId") Long batchId, @Param("userId") Long userId, @Param("batchName") String batchName);

}
