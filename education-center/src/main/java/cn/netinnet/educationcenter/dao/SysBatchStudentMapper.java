/*
 * SysBatchStudentMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-26 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysBatchStudent;
import cn.netinnet.educationcenter.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface SysBatchStudentMapper extends BaseMapper<SysBatchStudent> {

    /**  方法描述
     * @Description 查询批次学生列表
     * @Author yuyb
     * @Date 14:54 2020/5/26
     * @param batchId
     * @param classId
     * @param userName
     * @param userLogin
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>>queryBatchStudentList(@Param("batchId") Long batchId,
                                                  @Param("classId") Long classId,
                                                  @Param("userName") String userName,
                                                  @Param("userLogin") String userLogin);

    /**  方法描述
     * @Description 查询该批次下的班级列表数据
     * @Author yuyb
     * @Date 14:55 2020/5/26
     * @param batchId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysUser>
     **/
    List<SysUser> queryStudentClassByBatch(@Param("batchId") Long batchId);

   /**  方法描述
    * @Description 根据批次id查询相关userId
    * @Author yuyb
    * @Date 14:55 2020/5/26
    * @param batchId
    * @return java.util.List<java.lang.Long>
    **/
    @Select("SELECT user_id FROM sys_batch_student WHERE batch_id = #{batchId} AND del_flag = 0")
    List<Long> queryUserIdByBatchId(@Param("batchId") Long batchId);

    /**  方法描述
     * @Description 根据批次id查询批次学生数量
     * @Author yuyb
     * @Date 14:55 2020/5/26
     * @param batchId
     * @return int
     **/
    @Select("select count(0) from sys_batch_student where del_flag = 0 and batch_id = #{batchId}")
    int countByBatchId(@Param("batchId") Long batchId);

    /**  方法描述
     * @Description 根据学生id修改学生姓名
     * @Author yuyb
     * @Date 14:55 2020/5/26
     * @param userName
     * @param userId
     * @return int
     **/
    @Update("update sys_batch_student set user_name=#{userName} where user_id=#{userId}  and del_flag=0")
    int updateUserNameByUserId(@Param("userName") String userName, @Param("userId") Long userId);

    /** 方法描述
     * @description 根据批次Id 获取批次学生
     * @param batchId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/5/29 17:19
     */
    List<Long> queryBatchStudentIdsByBatchId(@Param("batchId") Long batchId);

    /** 方法描述
     * @description 获取已经被删除的批次学生ID
     * @return java.util.List<java.lang.Long>
     * @param  endDate
     * @author Caicm
     * @date 2020/5/29 17:26
     */
    List<Long> queryDeletedBatchStudentIds(@Param("endDate") String endDate);

    /**  方法描述
     * @Description 根据批次id和用户id查询SysBatchStudent
     * @Author yuyb
     * @Date 16:37 2020/6/29
     * @param batchId
     * @param userId
     * @return cn.netinnet.workflow.sys.domain.SysBatchStudent
     **/
    SysBatchStudent selectByBatchAndUser(@Param("batchId") Long batchId, @Param("userId") Long userId);

    /** 方法描述
     * @description     批量逻辑删除批次学生
     * @param studentIds
     * @param batchId
     * @return void
     * @author Caicm
     * @date 2020/7/29 15:09
     */
    void  batchLogicalDelete(@Param("studentIds") List<Long> studentIds, @Param("batchId") Long batchId);

    /** 方法描述
     * @description 查询已经存在批次的学生
     * @param userIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/7/30 10:29
     */
    List<Long> queryExistBatchStudentIds(@Param("userIds") List<Long> userIds);

    /** 方法描述
     * @description 根据batchStudentId 获取studentId (未删除的)
     * @param batchStudentIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/17 14:46
     */
    List<Long> queryStudentIdByBatchStudentIds(@Param("batchStudentIds") List<Long> batchStudentIds);

    /** 方法描述
     * @description  获取批次学生
     * @param batchStudentIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysBatchStudent>
     * @author Caicm
     * @date 2020/8/18 15:01
     */
    List<SysBatchStudent> queryBatchStudentByIds(@Param("batchStudentIds") List<Long> batchStudentIds);

    /***
    * 逻辑删除批次下的学生
    * @author ousp
    * @date 2020/10/12
    * @return void
    */
    void logicDeleteByIdUserId(@Param("userIds") List<Long> userIds);

    /***
    * 统计批次下学生个数
    * @author ousp
    * @date 2020/10/22
    * @return java.util.Map<java.lang.String,java.lang.Integer>
    */
    List<Map<String, Object>> countByBatchIds(@Param("batchIds") List<Long> batchIds);

    /**  方法描述
     * @Description 根据批次id查询相关individuality_id
     * @Author yuyb
     * @Date 17:30 2020/10/22
     * @param batchId
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> queryIndividualIdByBatchIdDif(@Param("batchId") Long batchId);

    /***
    * 删除逻辑删除的数据
    * @author ousp
    * @date 2020/11/5
    * @return void
    */
    void delLogicDeleteData(@Param("batchId") long batchId, @Param("userIds") List<Long> userIds);

    /***
    * 查询批次学生（包含已删除的）
    * @author ousp
    * @date 2020/11/29
    * @return java.util.List<java.lang.Long>
    */
    List<Long> queryAllByUserId(@Param("userId") long userId);
}
