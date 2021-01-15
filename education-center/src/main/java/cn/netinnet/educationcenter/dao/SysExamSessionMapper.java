/*
 * SysExamSessionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-08-03 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysExamSession;
import cn.netinnet.educationcenter.domain.dto.SessionDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface SysExamSessionMapper extends BaseMapper<SysExamSession> {

    /**  方法描述
     * @Description 查询指定的字段
     * @Author yuyb
     * @Date 18:38 2020/8/20
     * @param sessionId
     * @param columns
     * @return cn.netinnet.workflow.sys.domain.SysExamSession
     **/
    SysExamSession selectColumnsById(@Param("sessionId") long sessionId, @Param("columns") String columns);

    /**  方法描述
     * @Description 查询建套餐人的场次
     * @Author yuyb
     * @Date 14:00 2020/8/28
     * @param userId
     * @param sessionName
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
     **/
    List<SysExamSession> getPackageSessionList(@Param("userId") long userId,
                                               @Param("sessionType") Integer sessionType,
                                               @Param("sessionName") String sessionName);

    /**  方法描述
     * @Description 查询考试场次
     * @Author yuyb
     * @Date 8:23 2020/8/3
     * @param sessionName
     * @param userId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
     **/
    List<Map<String, Object>> queryList(@Param("sessionType") Integer sessionType,
                                        @Param("sessionName") String sessionName,
                                        @Param("roleCode") String roleCode,
                                        @Param("userId") long userId,
                                        @Param("sessionId") long sessionId);

    /**  方法描述
     * @Description 查询考试状态
     * @Author yuyb
     * @Date 10:11 2020/8/3
     * @param sessionId
     * @return int
     **/
    int getSessionStatus(@Param("sessionId") long sessionId);

    /** 方法描述
     * @description 逻辑删除
     * @param sessionId
     * @return void
     * @author Caicm
     * @date 2020/8/3 14:43
     */
    void logicalDeleteById(@Param("sessionId") Long sessionId);

    /**
    * 检查是否存在
    * @param id
    * @author ousp
    * @date 2020/8/3
    * @return java.lang.Integer
    */
    Integer checkExist(@Param("id") Long id);

    /**
    * 更新考试结束时间和状态
    * @param sessionIds
    * @param endTime
    * @param state
    * @author ousp
    * @date 2020/8/4
    * @return void
    */
    void updateStateAndEndTime(@Param("sessionIds") List<Long> sessionIds,
                               @Param("endTime") Date endTime,
                               @Param("state") int state);

    /**
    * 更新考试状态
    * @param sessionId
    * @param endState
    * @param submitState
    * @author ousp
    * @date 2020/8/4
    * @return void
    */
    void updateEndAndSubmitState(@Param("sessionIds") List<Long> sessionId,
                                 @Param("endState") int endState,
                                 @Param("submitState") int submitState);

    /**
     * 查询考试结束时间
     * @param sessionId
     * @author ousp
     * @date 2020/6/12
     * @return java.util.Date
     */
    Date queryEndTime(long sessionId);

    /** 方法描述
     * @description 获取已经被删除的场次
     * @param endDate
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/4 16:09
     */
    Set<Long> queryDeletedExamSession(@Param("endDate") String endDate);

    /***
    * 按分类统计
    * @param batchId 批次id
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> countByType(@Param("batchId") long batchId);

    /***
    * 查询批次列表
    * @param batchId        批次id
    * @param sessionType    类型
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
    */
    List<SessionDetail> queryListByBatchIdAndType(@Param("batchId") long batchId, @Param("sessionType") int sessionType,
                                                  @Param("startData") String startData, @Param("endData") String endData,
                                                  @Param("sessionName") String sessionName, @Param("openStatus") Integer openStatus,
                                                  @Param("now") Date now);

    /**  方法描述
     * @Description 查询考试试题
     * @Author yuyb
     * @Date 11:10 2020/9/28
     * @param sessionId
     * @return long
     **/
    long getSessionQuestionId(@Param("sessionId") long sessionId);

    /***
    * 查询题目
    * @author ousp
    * @date 2020/10/22
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamSession>
    */
    List<SysExamSession> queryQuests(@Param("sessionIds") Set<Long> sessionIds);
}
