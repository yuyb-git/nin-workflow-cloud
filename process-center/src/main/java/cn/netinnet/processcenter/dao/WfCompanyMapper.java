/*
 * WfCompanyMapper.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-04-14 Created
 */
package cn.netinnet.processcenter.dao;

import cn.netinnet.cloudcommon.base.BaseMapper;
import cn.netinnet.processcenter.domain.WfCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface WfCompanyMapper extends BaseMapper<WfCompany> {

    /**  方法描述
     * @Description 查询指定的字段
     * @Author yuyb
     * @Date 17:11 2020/6/1
     * @param companyId
     * @param columns
     * @return cn.netinnet.processcenter.domain.WfCompany
     **/
    WfCompany selectColumnsById(@Param("companyId") long companyId, @Param("columns") String columns);

    /**  方法描述
     * @Description 查询我的企业列表
     * @Author yuyb
     * @Date 10:18 2020/4/27
     * @param examId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     **/
    List<WfCompany> getMyCompanyList(@Param("examId") long examId);

    /**
    * @description              搜索企业
    * @param examId             不传表示后台查询
    * @param companyCategory    企业类型
    * @param  enable            启用状态
    * @param  companyName       企业名
    * @author ousp
    * @date 2020/4/14
    * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
    */
    List<WfCompany> seachCompanyList(@Param("examId") long examId,
                                     @Param("companyCategory") Integer companyCategory,
                                     @Param("enable") Integer enable,
                                     @Param("companyName") String companyName);

    /**  方法描述
     * @Description 查询教师企业
     * @Author yuyb
     * @Date 19:06 2020/9/17
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     **/
    List<WfCompany> seachTeacherCompanyList(@Param("userId") long userId,
                                            @Param("enable") Integer enable,
                                            @Param("companyName") String companyName);

    /**
    * @description 检查是否存在相同的企业名
    * @param companyId  企业id
    * @param  companyName   企业名
    * @param  examId        不传表示后台检查
    * @author ousp
    * @date 2020/4/14
    * @return java.lang.Integer
    */
    Integer checkExist(@Param("companyId") Long companyId,
                       @Param("companyCategory") int companyCategory,
                       @Param("companyName") String companyName,
                       @Param("examId") Long examId);

    /**  方法描述
     * @Description 查询教师企业是否存在同名的
     * @Author yuyb
     * @Date 18:48 2020/9/17
     * @return java.lang.Integer
     **/
    Integer checkTeacherCompanyExist(@Param("companyId") Long companyId,
                                     @Param("companyCategory") int companyCategory,
                                     @Param("companyName") String companyName,
                                     @Param("userId") Long userId);

    /**  方法描述
     * @Description 教师单道题目内，企业名称不重复
     * @Date 16:28 2020/8/21
     * @param questionId
     * @param companyCategory
     * @return java.lang.Integer
     **/
    Integer checkSingleQuestionExist(@Param("questionId") Long questionId,
                                     @Param("companyCategory") int companyCategory);

    /**  方法描述
     * @Description 查询学生自荐企业是否存在
     * @Author yuyb
     * @Date 9:27 2020/9/2
     * @return java.lang.Integer
     **/
    Integer checkUserQuesCompExist(@Param("examId") Long examId,
                                   @Param("questionId") Long questionId,
                                   @Param("companyCategory") int companyCategory);

    /** 方法描述
     * @description 获取企业下拉框的数据
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     * @author Caicm
     * @date 2020/5/14 10:20
     */
    List<WfCompany> queryCompanyOptions(@Param("questionCategory") int questionCategory, @Param("userId") Long userId);

    /** 方法描述
     * @description 启用（禁用）公司状态
     * @param companyIds
     * @param status
     * @return void
     * @author Caicm
     * @date 2020/5/26 10:03
     */ 
    void changeCompanyStatus(@Param("companyIds") List<Long> companyIds, @Param("status") Integer status);

    /** 方法描述
     * @description 根据userId获取companyId
     * @param userId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/6/4 15:10
     */
    List<Long> queryCompanyIdsByUserId(@Param("userId") Long userId);

    /**
    * 查询企业名称
    * @param companyIds
    * @description
    * @author ousp
    * @date 2020/6/10
    * @return java.util.List<java.lang.String>
    */
    List<String> queryCompanyNames(@Param("companyIds") List<Long> companyIds);

    /**
    * 查询题目下的企业
    * @param questionId
    * @author ousp
    * @date 2020/6/10
    * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
    */
    List<WfCompany> seachCompanyListInQuestion(@Param("questionId") long questionId,
                                               @Param("companyName") String companyName,
                                               @Param("examId") long examId);

    /** 方法描述
     * @description 查询表单模板是否被使用
     * @param templateId	 
     * @return java.lang.Integer
     * @author Caicm 
     * @date 2020/7/27 17:57      
     */
    Integer queryFormTemplateUsedCompany(@Param("templateId") Long templateId);

    /** 方法描述
     * @description 查询被使用的表单模板
     * @param templateIds
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/7/27 18:05
     */
    List<Long> queryUsedFormTemplate(@Param("templateIds") List<Long> templateIds);


    /** 方法描述
     * @description 通过examId获取companyId
     * @param examId
     * @return java.util.List<java.lang.Long>
     * @author Caicm
     * @date 2020/8/4 16:25
     */
    List<Long> queryCompanyIdByExamId(@Param("examId") Long examId);

    /** 方法描述
     * @description  获取企业信息
     * @param companyIds
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     * @author Caicm
     * @date 2020/8/12 14:49
     */
    List<WfCompany> queryCompanyByCompanyIds(@Param("companyIds") List<Long> companyIds);

    /**  方法描述
     * @Description 根据答案企业名称查询对应的学生企业id
     * @Author yuyb
     * @Date 13:29 2020/8/31
     * @param examId
     * @param questionId
     * @param companyName
     * @return java.lang.Long
     **/
    Long getStuCompanyIdByAnswer(@Param("examId") Long examId,
                                 @Param("questionId") long questionId,
                                 @Param("companyName") String companyName);

    /**  方法描述
     * @Description 查询不指定企业的试题的答案企业列表
     * @Author yuyb
     * @Date 10:39 2020/8/31
     * @param questionId
     * @return java.util.List<cn.netinnet.processcenter.domain.WfCompany>
     **/
    List<WfCompany> getAnswerCompanys(@Param("questionId") Long questionId);

    /**  方法描述
     * @Description 逻辑删除
     * @Author yuyb
     * @Date 11:11 2020/9/16
     * @param companyId
     * @return void
     **/
    void logicalDeleteById(@Param("companyId") long companyId);

    /**  方法描述
     * @Description 批量逻辑删除
     * @Author yuyb
     * @Date 9:49 2020/9/15
     * @param companyIds
     * @return void
     **/
    void logicalDeleteByIdList(@Param("companyIds") List<Long> companyIds);

    /**  方法描述
     * @Description 查询已删除的企业id
     * @Author yuyb
     * @Date 11:22 2020/9/16
     * @param endDate
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> getCompanyIdsByDefFlag(@Param("endDate") String endDate);
}