/*
 * SysExamScoreMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-15 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysExamScore;
import cn.netinnet.educationcenter.domain.dto.QuestionDoneDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface SysExamScoreMapper extends BaseMapper<SysExamScore> {
    /**
    * 查询用户成绩
    * @param sessionId
    * @param userIds
    * @author ousp
    * @date 2020/6/15
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
    */
    List<SysExamScore> queryUserSocre(@Param("sessionId") Long sessionId,
                                      @Param("userIds") Set<Long> userIds);

    /**
    * 查询用户题目id
    * @param sessionIds
    * @author ousp
    * @date 2020/6/15
    * @return java.util.List<java.lang.Long>
    */
    List<SysExamScore> queryUserBySessions(@Param("sessionIds") Set<Long> sessionIds);

    /**
    * 更新用户成绩和状态
    * @param sessionId      场次id
    * @param questionId     题目id
    * @param userId         用户id
    * @param score          得分率
    * @param orgSocre       部门分
    * @param positionSocre  岗位分
    * @param procDefSocre   定义分
    * @param procRunScore   实例分
    * @param state          状态
    * @author ousp
    * @date 2020/6/22
    */
    void updateScoreAndState(@Param("sessionId") long sessionId,
                             @Param("qustionId") long questionId,
                             @Param("userId") long userId,
                             @Param("score") BigDecimal score,
                             @Param("orgSocre") BigDecimal orgSocre,
                             @Param("positionSocre") BigDecimal positionSocre,
                             @Param("procDefSocre") BigDecimal procDefSocre,
                             @Param("procRunScore") BigDecimal procRunScore,
                             @Param("state") int state);

    /**  方法描述
     * @Description 删除场次下指定用户的成绩记录
     * @Author yuyb
     * @Date 10:25 2020/8/3
     * @param sessionId
     * @param userIds
     * @return void
     **/
    void delBySessionAndUser(@Param("sessionId") long sessionId, @Param("userIds") List<Long> userIds);

    /**  方法描述
     * @Description 查询场次成绩
     * @Author yuyb
     * @Date 11:13 2020/8/5
     * @param sessionId
     * @param userName
     * @param userLogin
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getResultsList(@Param("sessionId") long sessionId,
                                             @Param("userName") String userName,
                                             @Param("userLogin") String userLogin);

    /** 方法描述
     * @description  获取场次成绩
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
     * @author Caicm
     * @date 2020/8/10 10:02
     */
    List<SysExamScore> queryExamScoreBySessionId(@Param("sessionId") Long sessionId);

    /***
    * 查询成绩明细
    * @param sessionId
    * @param questionId
    * @param userId
    * @description
    * @author ousp
    * @date 2020/8/20
    * @return cn.netinnet.workflow.sys.domain.SysExamScore
    */
    SysExamScore queryQuestionScoreDetail(@Param("sessionId") long sessionId,
                                          @Param("questionId") long questionId,
                                          @Param("userId") long userId);

    /***
    * 查询题目状态
    * @param sessionId
    * @param userId
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
    */
    List<SysExamScore> queryQuestionState(@Param("sessionId") long sessionId, @Param("userId") long userId);

    /***
    * 查询做题情况
    * @param sessionId
    * @param state
    * @param questionTitle
    * @param userId
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<QuestionDoneDetail> userExamDetail(@Param("sessionId") long sessionId,
                                            @Param("state") Integer state,
                                            @Param("questionTitle") String questionTitle,
                                            @Param("userId") long userId);

    /****
    * 查询用户做题情况
    * @param sessionId  场次id
    * @param userId     用户id
    * @author ousp
    * @date 2020/8/27
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
    */
    List<SysExamScore> queryUserQuestionStateAndScore(@Param("sessionId") long sessionId, @Param("userId") long userId);

    /**  方法描述
     * @Description 查询场次下某道题是不是已经被初始化做题
     * @Author yuyb
     * @Date 15:03 2020/8/28
     * @param sessionId
     * @param questionId
     * @return java.lang.Integer
     **/
    Integer existQuestionId(@Param("sessionId") long sessionId, @Param("questionId") long questionId);

    /***
    * 获取考试结果
    * @author ousp
    * @date 2020/9/23
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> getExamResultsList(@Param("sessionId") long sessionId,
                                                 @Param("userName") String userName,
                                                 @Param("userLogin") String userLogin);
    /***
    * 重置成绩和状态
    * @author ousp
    * @date 2020/9/24
    * @return void
    */
    void resetExamQuestion(@Param("examSocreId") long examSocreId,
                           @Param("examId") long examId,
                           @Param("singleSocre") BigDecimal singleSocre);

    /***
    * 查询题目提交状态
    * @author ousp
    * @date 2020/9/29
    * @return java.lang.Integer
    */
    Integer queryQestionState(@Param("sessionId") long sessionId, @Param("userId") long userId, @Param("questionId") long questionId);

    /***
    * 更新题目提交状态
    * @author ousp
    * @date 2020/9/29
    * @return void
    */
    void updateQuestionState(@Param("sessionId") long sessionId,
                             @Param("userId") long userId,
                             @Param("questionId") long questionId,
                             @Param("state") int state);

    /**  方法描述
     * @Description 某道题是否被学生答题
     * @Author yuyb
     * @Date 9:29 2020/10/10
     * @param questionId
     * @return java.lang.Integer
     **/
    Integer ifQuestionUsed(@Param("questionId") long questionId);

    /***
    * 查询丢失sys_exam_score的记录
    * @author ousp
    * @date 2020/10/22
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamScore>
    */
    List<SysExamScore> getexamScoreMissList();

    /***
    * 查询考试学生
    * @author ousp
    * @date 2020/11/29
    * @return java.util.List<java.lang.Long>
    */
    List<Long> queryAllByUserId(@Param("userId") long userId);
}
