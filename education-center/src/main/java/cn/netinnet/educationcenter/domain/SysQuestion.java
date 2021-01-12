/*
 * SysQuestion.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-15 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-15
 **/
public class SysQuestion implements Serializable {
    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目标题
     */
    private String questionTitle;

    /**
     * 题目类型
     */
    private String questionType;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 题目标签
     */
    private String questionLabel;

    /**
     * 题目状态（0:正常；1:禁用）
     */
    private Integer questionStatus;

    /**
     * 资料数量
     */
    private Integer attachCount;

    /**
     * 试题归类（0：系统，1：教师）
     */
    private Integer questionCategory;

    /**
     * 是否开放（0：不开放，1：开放）
     */
    private Integer openSource;

    /**
     * 题目总分
     */
    private BigDecimal questionScore;

    /**
     * 组织架构分数比率
     */
    private Integer orgScoreRate;

    /**
     * 岗位架构分数比率
     */
    private Integer positionScoreRate;

    /**
     * 流程设计分数比率
     */
    private Integer procDesignScoreRate;

    /**
     * 流程执行分数比率
     */
    private Integer procRunScoreRate;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 637259386493130752L;

    /**
     * 获取题目id
     *
     * @return question_id - 题目id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目id
     *
     * @param questionId 题目id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取题目标题
     *
     * @return question_title - 题目标题
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * 设置题目标题
     *
     * @param questionTitle 题目标题
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    /**
     * 获取题目类型
     *
     * @return question_type - 题目类型
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * 设置题目类型
     *
     * @param questionType 题目类型
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取企业id
     *
     * @return company_id - 企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业id
     *
     * @param companyId 企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取题目标签
     *
     * @return question_label - 题目标签
     */
    public String getQuestionLabel() {
        return questionLabel;
    }

    /**
     * 设置题目标签
     *
     * @param questionLabel 题目标签
     */
    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    /**
     * 获取题目状态（0:正常；1:禁用）
     *
     * @return question_status - 题目状态（0:正常；1:禁用）
     */
    public Integer getQuestionStatus() {
        return questionStatus;
    }

    /**
     * 设置题目状态（0:正常；1:禁用）
     *
     * @param questionStatus 题目状态（0:正常；1:禁用）
     */
    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }

    /**
     * 获取资料数量
     *
     * @return attach_count - 资料数量
     */
    public Integer getAttachCount() {
        return attachCount;
    }

    /**
     * 设置资料数量
     *
     * @param attachCount 资料数量
     */
    public void setAttachCount(Integer attachCount) {
        this.attachCount = attachCount;
    }

    /**
     * 获取试题归类（0：系统，1：教师）
     *
     * @return question_category - 试题归类（0：系统，1：教师）
     */
    public Integer getQuestionCategory() {
        return questionCategory;
    }

    /**
     * 设置试题归类（0：系统，1：教师）
     *
     * @param questionCategory 试题归类（0：系统，1：教师）
     */
    public void setQuestionCategory(Integer questionCategory) {
        this.questionCategory = questionCategory;
    }

    /**
     * 获取是否开放（0：不开放，1：开放）
     *
     * @return open_source - 是否开放（0：不开放，1：开放）
     */
    public Integer getOpenSource() {
        return openSource;
    }

    /**
     * 设置是否开放（0：不开放，1：开放）
     *
     * @param openSource 是否开放（0：不开放，1：开放）
     */
    public void setOpenSource(Integer openSource) {
        this.openSource = openSource;
    }

    /**
     * 获取题目总分
     *
     * @return question_score - 题目总分
     */
    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    /**
     * 设置题目总分
     *
     * @param questionScore 题目总分
     */
    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    /**
     * 获取组织架构分数比率
     *
     * @return org_score_rate - 组织架构分数比率
     */
    public Integer getOrgScoreRate() {
        return orgScoreRate;
    }

    /**
     * 设置组织架构分数比率
     *
     * @param orgScoreRate 组织架构分数比率
     */
    public void setOrgScoreRate(Integer orgScoreRate) {
        this.orgScoreRate = orgScoreRate;
    }

    /**
     * 获取岗位架构分数比率
     *
     * @return position_score_rate - 岗位架构分数比率
     */
    public Integer getPositionScoreRate() {
        return positionScoreRate;
    }

    /**
     * 设置岗位架构分数比率
     *
     * @param positionScoreRate 岗位架构分数比率
     */
    public void setPositionScoreRate(Integer positionScoreRate) {
        this.positionScoreRate = positionScoreRate;
    }

    /**
     * 获取流程设计分数比率
     *
     * @return proc_design_score_rate - 流程设计分数比率
     */
    public Integer getProcDesignScoreRate() {
        return procDesignScoreRate;
    }

    /**
     * 设置流程设计分数比率
     *
     * @param procDesignScoreRate 流程设计分数比率
     */
    public void setProcDesignScoreRate(Integer procDesignScoreRate) {
        this.procDesignScoreRate = procDesignScoreRate;
    }

    /**
     * 获取流程执行分数比率
     *
     * @return proc_run_score_rate - 流程执行分数比率
     */
    public Integer getProcRunScoreRate() {
        return procRunScoreRate;
    }

    /**
     * 设置流程执行分数比率
     *
     * @param procRunScoreRate 流程执行分数比率
     */
    public void setProcRunScoreRate(Integer procRunScoreRate) {
        this.procRunScoreRate = procRunScoreRate;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人
     *
     * @return modify_user_id - 修改人
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置修改人
     *
     * @param modifyUserId 修改人
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", questionId=").append(questionId);
        sb.append(", questionTitle=").append(questionTitle);
        sb.append(", questionType=").append(questionType);
        sb.append(", companyId=").append(companyId);
        sb.append(", questionLabel=").append(questionLabel);
        sb.append(", questionStatus=").append(questionStatus);
        sb.append(", attachCount=").append(attachCount);
        sb.append(", questionCategory=").append(questionCategory);
        sb.append(", openSource=").append(openSource);
        sb.append(", questionScore=").append(questionScore);
        sb.append(", orgScoreRate=").append(orgScoreRate);
        sb.append(", positionScoreRate=").append(positionScoreRate);
        sb.append(", procDesignScoreRate=").append(procDesignScoreRate);
        sb.append(", procRunScoreRate=").append(procRunScoreRate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
