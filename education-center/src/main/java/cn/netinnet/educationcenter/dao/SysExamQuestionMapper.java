/*
 * SysExamQuestionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-12 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysExamQuestion;
import cn.netinnet.educationcenter.domain.dto.ExamQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface SysExamQuestionMapper extends BaseMapper<SysExamQuestion> {

    /**  方法描述
     * @Description 查询考试已添加的试题信息
     * @Author yuyb
     * @Date 15:12 2020/8/3
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.ExamQuestion>
     **/
    List<ExamQuestion> getExamQuestionList(@Param("sessionId") Long sessionId,
                                           @Param("questionTitle") String questionTitle);

    /**  方法描述
     * @Description 查询未被考试试用的试题信息
     * @Author yuyb
     * @Date 15:32 2020/8/3
     * @param questionIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.dto.ExamQuestion>
     **/
    List<ExamQuestion> getExamQuestionListNotAdd(@Param("questionIds") List<Long> questionIds,
                                                 @Param("questionTitle") String questionTitle,
                                                 @Param("userType") int userType,
                                                 @Param("userId") long userId);

    /**  方法描述
     * @Description 查询场次试题信息
     * @Author yuyb
     * @Date 9:08 2020/8/3
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamQuestion>
     **/
    List<SysExamQuestion> getExamQuestionBySessionId(@Param("sessionId") Long sessionId);
    /**
    * 通过sessionId查询场次信息
    * @param sessionIds
    * @author ousp
    * @date 2020/6/15
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamQuestion>
    */
    List<SysExamQuestion> queryExamInfo(@Param("sessionIds") Set<Long> sessionIds);

    /**
    * 通过sessionId删除
    * @param sessionIds
    * @author ousp
    * @date 2020/6/17
    * @return void
    */
    void delBySessionIds(@Param("sessionIds") long[] sessionIds);

    /** 方法描述
     * @description 获取已经被使用的题目ID
     * @param questionIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/7/13 9:28
     */
    List<Long> queryUsedQuestionIds(@Param("questionIds") List<Long> questionIds);

    /**  方法描述
     * @Description 查询考试场次的题目id
     * @Author yuyb
     * @Date 9:12 2020/8/3
     * @param sessionId
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> getQuestionIdsBySessionId(@Param("sessionId") Long sessionId);

    /**  方法描述
     * @Description 根据题目id查询主键id
     * @Author yuyb
     * @Date 11:35 2020/8/4
     * @param questionIds
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> getIdsByQuestionIds(@Param("questionIds") List<Long> questionIds, @Param("sessionId") long sessionId);

    /**
    * 查询场次下的题目
    * @param sessionId
    * @author ousp
    * @date 2020/8/4
    * @return java.util.List<java.lang.Long>
    */
    List<Long> getQuestionsBySessionId(@Param("sessionId") long sessionId);

    /** 方法描述
     * @description  获取考试题目信息
     * @param sessionId
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamQuestion>
     * @author Caicm
     * @date 2020/8/10 9:52
     */
    List<SysExamQuestion> queryExamQuestionBySessionId(@Param("sessionId") Long sessionId);

    /**  方法描述
     * @Description 查询考试单个题目
     * @Author yuyb
     * @Date 15:26 2020/8/21
     * @param sessionId
     * @param questionId
     * @return cn.netinnet.workflow.sys.domain.SysExamQuestion
     **/
    SysExamQuestion getExamQuestionBySessionQuesId(@Param("sessionId") Long sessionId, @Param("questionId") Long questionId);

    /***
    * 查询场次下题目信息
    * @param sessionId
    * @author ousp
    * @date 2020/8/26
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> queryQuestionDetail(@Param("sessionId") long sessionId);

    /**  方法描述
     * @Description 查询场次下题目计数-分组
     * @Author yuyb
     * @Date 9:43 2020/8/27
     * @param sessionIds
     * @return java.util.Map<java.lang.Long,java.lang.Long>
     **/
    List<Map<Long, Long>> countBySessionId(@Param("sessionIds") Set<Long> sessionIds);

    /***
     * 查询场次下题目分数
     * @param sessionId  场次id
     * @author ousp
     * @date 2020/8/27
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysExamQuestion>
     */
    List<SysExamQuestion> getAllQuestionScore(@Param("sessionId") long sessionId);

    /***
    * 查询题目分值
    * @param questionId 题目id
    * @param sessionId  场次id
    * @author ousp
    * @date 2020/8/28
    * @return java.lang.Integer
    */
    Integer queryQuestionScore(@Param("questionId") long questionId, @Param("sessionId") long sessionId);
}
