/*
 * SysExamUser.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-12 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2020-06-12
 **/
public class SysExamUser implements Serializable {
    /**
     * ID
     */
    private Long examId;

    /**
     * 场次ID或批次任务id
     */
    private Long sessionId;

    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 考试提交状态（0：未提交，1：已提交）
     */
    private Integer submitState;


    /**
     * 总成绩
     */
    private BigDecimal totalScore;

    /**
     * 补时结束时间
     */
    private Date extraEndTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 8724737199434388480L;

    /**
     * 获取ID
     *
     * @return exam_id - ID
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * 设置ID
     *
     * @param examId ID
     */
    public void setExamId(Long examId) {
        this.examId = examId;
    }

    /**
     * 获取场次ID或批次任务id
     *
     * @return session_id - 场次ID或批次任务id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 设置场次ID或批次任务id
     *
     * @param sessionId 场次ID或批次任务id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取批次id
     *
     * @return batch_id - 批次id
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * 设置批次id
     *
     * @param batchId 批次id
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
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
     * 获取考试提交状态（0：未提交，1：已提交）
     *
     * @return submit_state - 考试提交状态（0：未提交，1：已提交）
     */
    public Integer getSubmitState() {
        return submitState;
    }

    /**
     * 设置考试提交状态（0：未提交，1：已提交）
     *
     * @param submitState 考试提交状态（0：未提交，1：已提交）
     */
    public void setSubmitState(Integer submitState) {
        this.submitState = submitState;
    }

    /**
     * 获取总成绩
     *
     * @return total_score - 总成绩
     */
    public BigDecimal getTotalScore() {
        return totalScore;
    }

    /**
     * 设置总成绩
     *
     * @param totalScore 总成绩
     */
    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取补时结束时间
     *
     * @return extra_end_time - 补时结束时间
     */
    public Date getExtraEndTime() {
        return extraEndTime;
    }

    /**
     * 设置补时结束时间
     *
     * @param extraEndTime 补时结束时间
     */
    public void setExtraEndTime(Date extraEndTime) {
        this.extraEndTime = extraEndTime;
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
        sb.append(", examId=").append(examId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", batchId=").append(batchId);
        sb.append(", userId=").append(userId);
        sb.append(", submitState=").append(submitState);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", extraEndTime=").append(extraEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
