/*
 * SysExamScore.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-08-26 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2020-08-26
 **/
public class SysExamScore implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 场次或任务id
     */
    private Long sessionId;

    /**
     * 题目id
     */
    private Long qustionId;

    /**
     * 试题状态（0：未完成；1：已完成）
     */
    private Integer questionState;

    /**
     * 流程执行分数
     */
    private BigDecimal procRunScore;

    /**
     * 流程设计分数
     */
    private BigDecimal procDesignScore;

    /**
     * 岗位架构分数
     */
    private BigDecimal positionScore;

    /**
     * 组织架构分数
     */
    private BigDecimal orgScore;

    /**
     * 成绩
     */
    private BigDecimal score;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 7136044752778609664L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取场次或任务id
     *
     * @return session_id - 场次或任务id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 设置场次或任务id
     *
     * @param sessionId 场次或任务id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取题目id
     *
     * @return qustion_id - 题目id
     */
    public Long getQustionId() {
        return qustionId;
    }

    /**
     * 设置题目id
     *
     * @param qustionId 题目id
     */
    public void setQustionId(Long qustionId) {
        this.qustionId = qustionId;
    }

    /**
     * 获取试题状态（0：未完成；1：已完成）
     *
     * @return question_state - 试题状态（0：未完成；1：已完成）
     */
    public Integer getQuestionState() {
        return questionState;
    }

    /**
     * 设置试题状态（0：未完成；1：已完成）
     *
     * @param questionState 试题状态（0：未完成；1：已完成）
     */
    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    /**
     * 获取流程执行分数
     *
     * @return proc_run_score - 流程执行分数
     */
    public BigDecimal getProcRunScore() {
        return procRunScore;
    }

    /**
     * 设置流程执行分数
     *
     * @param procRunScore 流程执行分数
     */
    public void setProcRunScore(BigDecimal procRunScore) {
        this.procRunScore = procRunScore;
    }

    /**
     * 获取流程设计分数
     *
     * @return proc_design_score - 流程设计分数
     */
    public BigDecimal getProcDesignScore() {
        return procDesignScore;
    }

    /**
     * 设置流程设计分数
     *
     * @param procDesignScore 流程设计分数
     */
    public void setProcDesignScore(BigDecimal procDesignScore) {
        this.procDesignScore = procDesignScore;
    }

    /**
     * 获取岗位架构分数
     *
     * @return position_score - 岗位架构分数
     */
    public BigDecimal getPositionScore() {
        return positionScore;
    }

    /**
     * 设置岗位架构分数
     *
     * @param positionScore 岗位架构分数
     */
    public void setPositionScore(BigDecimal positionScore) {
        this.positionScore = positionScore;
    }

    /**
     * 获取组织架构分数
     *
     * @return org_score - 组织架构分数
     */
    public BigDecimal getOrgScore() {
        return orgScore;
    }

    /**
     * 设置组织架构分数
     *
     * @param orgScore 组织架构分数
     */
    public void setOrgScore(BigDecimal orgScore) {
        this.orgScore = orgScore;
    }

    /**
     * 获取成绩
     *
     * @return score - 成绩
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置成绩
     *
     * @param score 成绩
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
        sb.append(", id=").append(id);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", qustionId=").append(qustionId);
        sb.append(", questionState=").append(questionState);
        sb.append(", procRunScore=").append(procRunScore);
        sb.append(", procDesignScore=").append(procDesignScore);
        sb.append(", positionScore=").append(positionScore);
        sb.append(", orgScore=").append(orgScore);
        sb.append(", score=").append(score);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
