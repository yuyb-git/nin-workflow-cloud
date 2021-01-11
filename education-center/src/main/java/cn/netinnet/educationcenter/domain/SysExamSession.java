/*
 * SysExamSession.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-27 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-27
 **/
public class SysExamSession implements Serializable {
    /**
     * 考试id
     */
    private Long sessionId;

    /**
     * 场次来源（0：系统自建；1：考试平台；2：正保云）
     */
    private Integer sessionFrom;

    /**
     * 场次类型（0：练习；1：作业：2：考试）
     */
    private Integer sessionType;

    /**
     * 考试名次
     */
    private String sessionName;

    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 考试状态（0-新建;1-已初始化;2-进行中;3-暂停）
     */
    private Integer sessionStatus;

    /**
     * 是否可以重置（0：不能；1：可以）
     */
    private Integer resetable;

    /**
     * 是否可以查看答案（0：不能；1：边做边看；2：提交后可看；3：结束后可看）
     */
    private Integer viewAnswer;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

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

    private static final long serialVersionUID = 6663954448348864512L;

    /**
     * 获取考试id
     *
     * @return session_id - 考试id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 设置考试id
     *
     * @param sessionId 考试id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取场次来源（0：系统自建；1：考试平台；2：正保云）
     *
     * @return session_from - 场次来源（0：系统自建；1：考试平台；2：正保云）
     */
    public Integer getSessionFrom() {
        return sessionFrom;
    }

    /**
     * 设置场次来源（0：系统自建；1：考试平台；2：正保云）
     *
     * @param sessionFrom 场次来源（0：系统自建；1：考试平台；2：正保云）
     */
    public void setSessionFrom(Integer sessionFrom) {
        this.sessionFrom = sessionFrom;
    }

    /**
     * 获取场次类型（0：练习；1：作业：2：考试）
     *
     * @return session_type - 场次类型（0：练习；1：作业：2：考试）
     */
    public Integer getSessionType() {
        return sessionType;
    }

    /**
     * 设置场次类型（0：练习；1：作业：2：考试）
     *
     * @param sessionType 场次类型（0：练习；1：作业：2：考试）
     */
    public void setSessionType(Integer sessionType) {
        this.sessionType = sessionType;
    }

    /**
     * 获取考试名次
     *
     * @return session_name - 考试名次
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * 设置考试名次
     *
     * @param sessionName 考试名次
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
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
     * 获取试题id
     *
     * @return question_id - 试题id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置试题id
     *
     * @param questionId 试题id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取考试状态（0-新建;1-已初始化;2-进行中;3-暂停）
     *
     * @return session_status - 考试状态（0-新建;1-已初始化;2-进行中;3-暂停）
     */
    public Integer getSessionStatus() {
        return sessionStatus;
    }

    /**
     * 设置考试状态（0-新建;1-已初始化;2-进行中;3-暂停）
     *
     * @param sessionStatus 考试状态（0-新建;1-已初始化;2-进行中;3-暂停）
     */
    public void setSessionStatus(Integer sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * 获取是否可以重置（0：不能；1：可以）
     *
     * @return resetable - 是否可以重置（0：不能；1：可以）
     */
    public Integer getResetable() {
        return resetable;
    }

    /**
     * 设置是否可以重置（0：不能；1：可以）
     *
     * @param resetable 是否可以重置（0：不能；1：可以）
     */
    public void setResetable(Integer resetable) {
        this.resetable = resetable;
    }

    /**
     * 获取是否可以查看答案（0：不能；1：边做边看；2：提交后可看；3：结束后可看）
     *
     * @return view_answer - 是否可以查看答案（0：不能；1：边做边看；2：提交后可看；3：结束后可看）
     */
    public Integer getViewAnswer() {
        return viewAnswer;
    }

    /**
     * 设置是否可以查看答案（0：不能；1：边做边看；2：提交后可看；3：结束后可看）
     *
     * @param viewAnswer 是否可以查看答案（0：不能；1：边做边看；2：提交后可看；3：结束后可看）
     */
    public void setViewAnswer(Integer viewAnswer) {
        this.viewAnswer = viewAnswer;
    }

    /**
     * 获取逻辑删除：0表示正常； 1表示删除
     *
     * @return del_flag - 逻辑删除：0表示正常； 1表示删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除：0表示正常； 1表示删除
     *
     * @param delFlag 逻辑删除：0表示正常； 1表示删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
        sb.append(", sessionId=").append(sessionId);
        sb.append(", sessionFrom=").append(sessionFrom);
        sb.append(", sessionType=").append(sessionType);
        sb.append(", sessionName=").append(sessionName);
        sb.append(", batchId=").append(batchId);
        sb.append(", questionId=").append(questionId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", sessionStatus=").append(sessionStatus);
        sb.append(", resetable=").append(resetable);
        sb.append(", viewAnswer=").append(viewAnswer);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
