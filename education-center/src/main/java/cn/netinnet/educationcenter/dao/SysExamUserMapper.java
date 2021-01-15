/*
 * SysExamUserMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-12 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysBatchStudent;
import cn.netinnet.educationcenter.domain.SysExamUser;
import cn.netinnet.educationcenter.domain.dto.SessionCompleteDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface SysExamUserMapper extends BaseMapper<SysExamUser> {

    /**
    * 查询用户信息
    * @param sysExamUsers
    * @author ousp
    * @date 2020/6/15
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamUser>
    */
    List<SysExamUser> queryUserInfo(@Param("userList") List<SysExamUser> sysExamUsers);

    /**
    * 更新提交状态
    * @param sessionId
    * @param userIds
    * @param submitState
    * @author ousp
    * @date 2020/6/15
    * @return void
    */
    void updateSubmitState(@Param("sessionId") long sessionId,
                           @Param("userIds") Set<Long> userIds,
                           @Param("submitState") int submitState);

    /**
    * 重置考试
    * @param examIds
    * @param scoreList
    * @author ousp
    * @date 2020/6/15
    * @return void
    */
    void resetExam(@Param("examIds") List<Long> examIds, @Param("scoreList") List<Long> scoreList);

    /**
    * 删除考试用户
    * @param examIds
    * @param scoreList
    * @author ousp
    * @date 2020/6/15
    * @return void
    */
    void delExamUser(@Param("examIds") List<Long> examIds, @Param("scoreList") List<Long> scoreList);

    /**
    * 查询考试用户信息
    * @param sessionId
    * @author ousp
    * @date 2020/6/16
    * @return cn.netinnet.workflow.sys.domain.SysExamUser
    */
    SysExamUser queryBySessionIdAndUserId(@Param("sessionId") Long sessionId, @Param("userId") long userId);

    /**
    * 获取考试用户
    * @Param sessionIds
    * @author ousp
    * @date 2020/6/17
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamUser>
    */
    List<SysExamUser> querySessionIdAndUserId(@Param("sessionIds") Set<Long> sessionIds);

    /**  方法描述
     * @Description 获取考试或者批次用户的examId
     * @Author yuyb
     * @Date 13:47 2020/6/29
     * @return long
     **/
    SysExamUser getExamUser(@Param("sessionId") Long sessionId,
                            @Param("batchId") Long batchId,
                            @Param("userId") long userId);

    /**  方法描述
     * @Description 查询场次下是否有学生
     * @Author yuyb
     * @Date 8:17 2020/8/3
     * @param sessionId
     * @return java.lang.Integer
     **/
    Integer existUserBySessionId(@Param("sessionId") Long sessionId);

    /**  方法描述
     * @Description 查询考试下的学生id
     * @Author yuyb
     * @Date 8:34 2020/8/3
     * @param sessionId
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> queryUserIdBySessionId(@Param("sessionId") Long sessionId);

    /**  方法描述
     * @Description 根据考试用户id查询用户id
     * @Author yuyb
     * @Date 10:22 2020/8/3
     * @param examUserIdList
     * @return java.util.List<java.lang.Long>
     **/
    List<SysExamUser> queryExamUserByExamUserId(@Param("examUserIdList") List<Long> examUserIdList);

    /**  方法描述
     * @Description 查询考试学生列表
     * @Author yuyb
     * @Date 14:20 2020/8/4
     * @param sessionId
     * @param userLogin
     * @param userName
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> queryExamStudentList(@Param("sessionId") Long sessionId,
                                                   @Param("userLogin") String userLogin,
                                                   @Param("userName") String userName);

    /** 方法描述
     * @description 获取存在的考试用户
     * @param userIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/3 14:22
     */
    List<Long> queryExistExamUser(@Param("userIds") List<Long> userIds, @Param("sessionId") long sessionId);

    /** 方法描述
     * @description 获取考试用户成绩
     * @param sessionId
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author Caicm
     * @date 2020/8/3 15:57
     */
    List<Map<String, Object>> queryExamUsersBySessionId(@Param("sessionId") Long sessionId);

    /**
    * 更新成绩
    * @param examId
    * @author ousp
    * @date 2020/8/3
    * @return void
    */
    void updateScoreAndstate(@Param("examId") Long examId, @Param("score") BigDecimal score, @Param("state") Integer state);


    /** 方法描述
     * @description  根据sessionId获取examId
     * @param sessionIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/4 16:14
     */
    List<Long> queryExamUserBySessionIds(@Param("sessionIds") List<Long> sessionIds);

    /**
    * 更新提交状态
    * @param examIds id
    * @param state   状态
    * @author ousp
    * @date 2020/8/5
    * @return void
    */
    void updateSubmitStateByIds(@Param("examIds") List<Long> examIds, @Param("state") int state);

    /**
    * 查询examid
    * @param sessionId
    * @param userIds
    * @author ousp
    * @date 2020/8/11
    * @return java.util.List<java.lang.Long>
    */
    List<SysExamUser> queryIdBySessionIdAndUser(@Param("sessionId") long sessionId, @Param("userIds") List<Long> userIds);


    /** 方法描述
     * @description  获取examId
     * @param batchId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/18 15:22
     */
    List<SysExamUser> queryExamUserByBatchId(@Param("batchId") Long batchId);

    /** 方法描述
     * @description   通过 批次学生获取examId
     * @param studentList
     * @author Caicm
     * @date 2020/8/18 15:36
     */
    List<SysExamUser> queryExamUserByBatchStudent(@Param("studentList") List<SysBatchStudent> studentList);

    /****
    * 重置考试
    * @param examUserIds
    * @param sessionId
    * @param userIds
    * @author ousp
    * @date 2020/8/20
    * @return void
    */
    void batchResetUserExam(@Param("examUserIds") List<Long> examUserIds, @Param("sessionId") long sessionId,
                            @Param("userIds") List<Long> userIds);

    /****
    * 查询用户数据
    * @param sessionIds
    * @param batchId
    * @param userId
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamUser>
    */
    List<SysExamUser> queryUserExamData(@Param("sessionIds") List<Long> sessionIds,
                                        @Param("batchId") long batchId, @Param("userId") long userId);

    /**  方法描述
     * @Description 查询开始、作业、练习下用户的考试情况
     * @Author yuyb
     * @Date 16:04 2020/8/27
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.SessionCompleteDetail>
     **/
    List<SessionCompleteDetail> getSessionUserDeatil(@Param("sessionId") long sessionId,
                                                     @Param("classId") Long classId,
                                                     @Param("submitStatus") Integer submitStatus,
                                                     @Param("userName") String userName,
                                                     @Param("batchId") long batchId);

    /** 方法描述
     * @description 查询单个场次学生
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamUser>
     * @author Caicm
     * @date 2020/8/31 13:56
     */
    List<SysExamUser> querySessionUser(@Param("sessionId") Long sessionId);

    /**  方法描述
     * @Description 查询用户题目是否提交
     * @Author yuyb
     * @Date 8:59 2020/8/27
     * @param sessionIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
     **/
    List<Long> getUserSubmitList(@Param("sessionIds") Set<Long> sessionIds);

    /***
    * 查询examId
    * @author ousp
    * @date 2020/9/18
    * @return java.lang.Long
    */
    Long getExamId(@Param("userId") long userId, @Param("sessionId") long sessionId);

    /***
    * 取消考试提交
    * @author ousp
    * @date 2020/9/24
    * @return void
    */
    void cancelSubmitExam(@Param("examId") long examId, @Param("sessionId") long sessionId, @Param("userId") long userId);

    /***
    * 延时
    * @author ousp
    * @date 2020/11/11
    * @return void
    */
    void addExtraTime(@Param("sessionId") long sessionId, @Param("extraEndTime") Date extraEndTime,
                      @Param("submitState") int submitState, @Param("userIds") List<Long> userIds);

    /***
    * 取消延时
    * @author ousp
    * @date 2020/11/11
    * @return void
    */
    void delExtraTime(@Param("sessionId") long sessionId, @Param("userIds") List<Long> userIds);

    /***
    * 查询学生延时
    * @author ousp
    * @date 2020/11/11
    * @return java.util.Date
    */
    Date queryExtraTimeById(@Param("examId") long exam_id);

    /***
    * 提交状态
    * @author ousp
    * @date 2020/11/18
    * @return int
    */
    int getSubmitStateById(@Param("examId") long exam_id);

    /***
    * 查询考试学生
    * @author ousp
    * @date 2020/11/29
    * @return java.util.List<java.lang.Long>
    */
    List<Long> queryAllByUserId(@Param("userId") long userId);
}
