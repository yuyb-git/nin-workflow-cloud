/*
 * SysQuestionMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-12 Created
 */
package cn.netinnet.educationcenter.dao;

import cn.netinnet.common.base.BaseMapper;
import cn.netinnet.educationcenter.domain.SysQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface SysQuestionMapper extends BaseMapper<SysQuestion> {

    /**  方法描述
     * @Description 查询试题信息
     * @Author yuyb
     * @Date 9:18 2020/8/3
     * @param questionIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysQuestion>
     **/
    List<SysQuestion> getQuestionByIds(@Param("questionIds") List<Long> questionIds);

    /** 方法描述
     * @description 查询问题列表
     * @param questiontatus
     * @param questionType
     * @param questionLabel
     * @param questionTitle
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysQuestion>
     * @author Caicm
     * @date 2020/5/12 17:10
     */
    List<Map<String,Object>> querySysQuestionList(@Param("questionStatus") Integer questiontatus,
                                                  @Param("questionType") String questionType,
                                                  @Param("questionLabel") String questionLabel,
                                                  @Param("questionTitle") String questionTitle,
                                                  @Param("userType") int userType,
                                                  @Param("userId") long userId);

    /** 方法描述
     * @description 获取问题详情
     * @param questionId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Caicm
     * @date 2020/5/14 17:59
     */
    Map<String, Object> queryQuestionDetail(@Param("questionId") Long questionId);

    /** 方法描述
     * @description 修改问题状态
     * @param questionIds
     * @param status
     * @return java.lang.Integer
     * @author Caicm
     * @date 2020/5/15 11:25
     */
    Integer changeQuestionStatus(@Param("questionIds") List<Long> questionIds, @Param("status") Integer status);

    /**
    * 检查企业是否存在题目中
    * @param companyIds 企业id
    * @author ousp
    * @date 2020/6/10
    * @return java.util.List<java.lang.Long>
    */
    List<Long> checkCompanyInQuestion(@Param("companyIds") List<Long> companyIds);

    /**
    * 查询题目下的企业
    * @param questionId
    * @author ousp
    * @date 2020/6/10
    * @return java.lang.Long
    */
    Long queryCompanyId(@Param("questionId") long questionId);

    /**
    * 获取系统启用的题目
    * @param questionTitle
    * @author ousp
    * @date 2020/6/17
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String, Object>> queryOnSysQuestions(@Param("questionTitle") String questionTitle, List<Long> noTaskIds);

    /** 方法描述
     * @description 获取指定企业的题目
     * @param questionIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/7/13 9:54
     */
    List<Long> querySelectedCompanyQuestion(@Param("questionIds") List<Long> questionIds);


    /** 方法描述
     * @description  获取试题信息
     * @param questionIds
     * @return java.util.List<cn.netinnet.workflow.sys.domain.SysQuestion>
     * @author Caicm
     * @date 2020/8/12 9:48
     */
    List<SysQuestion> queryQuestionByQuestionIds(@Param("questionIds") List<Long> questionIds);

    /***
    * 根据题目类型获取题目
    * @author ousp
    * @date 2020/9/16
    * @return java.util.List<cn.netinnet.workflow.sys.domain.SysQuestion>
    */
    List<SysQuestion> getQuestionByType(@Param("list") List<Long> itemIdList);

    /***
    * 查询教师的类型是否在教师题目中使用
    * @author ousp
    * @date 2020/9/16
    * @return java.lang.Long
    */
    Long isTeacherQuestinTypeUsed(@Param("list") List<Long> itemIdList, @Param("userId") long userId);

    /***
    * 检查是否包含系统题目
    * @author ousp
    * @date 2020/9/17
    * @return java.lang.Long
    */
    Long containsSysQuestion(@Param("list") List<Long> ids);
}
