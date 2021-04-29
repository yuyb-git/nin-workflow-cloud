/*
 * WfRunTask.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-27 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-27
 **/
public class WfRunTask implements Serializable {
    private Long runTaskId;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 流程实例id
     */
    private Long procInstId;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 运行节点id
     */
    private Long procNodeId;

    /**
     * 节点类型
     */
    private Integer procNodeType;

    /**
     * 1|2|3表示[上一步|下一步|发起人]全部通知
     */
    private String noticeType;

    /**
     * 处理方式：0 普通，1 抢占， 2 会签
     */
    private Integer handleType;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 执行人
     */
    private Long assignee;

    /**
     * 表单id
     */
    private Long formId;

    /**
     * 关联业务id
     */
    private Long businessId;

    /**
     * 状态：0 默认，1 正在进行， 2 已完成
     */
    private Integer taskState;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private static final long serialVersionUID = 7977975210830148608L;

    /**
     * @return run_task_id
     */
    public Long getRunTaskId() {
        return runTaskId;
    }

    /**
     * @param runTaskId
     */
    public void setRunTaskId(Long runTaskId) {
        this.runTaskId = runTaskId;
    }

    /**
     * 获取考试id
     *
     * @return exam_id - 考试id
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * 设置考试id
     *
     * @param examId 考试id
     */
    public void setExamId(Long examId) {
        this.examId = examId;
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
     * 获取流程实例id
     *
     * @return proc_inst_id - 流程实例id
     */
    public Long getProcInstId() {
        return procInstId;
    }

    /**
     * 设置流程实例id
     *
     * @param procInstId 流程实例id
     */
    public void setProcInstId(Long procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取流程定义id
     *
     * @return proc_def_id - 流程定义id
     */
    public Long getProcDefId() {
        return procDefId;
    }

    /**
     * 设置流程定义id
     *
     * @param procDefId 流程定义id
     */
    public void setProcDefId(Long procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取运行节点id
     *
     * @return proc_node_id - 运行节点id
     */
    public Long getProcNodeId() {
        return procNodeId;
    }

    /**
     * 设置运行节点id
     *
     * @param procNodeId 运行节点id
     */
    public void setProcNodeId(Long procNodeId) {
        this.procNodeId = procNodeId;
    }

    /**
     * 获取节点类型
     *
     * @return proc_node_type - 节点类型
     */
    public Integer getProcNodeType() {
        return procNodeType;
    }

    /**
     * 设置节点类型
     *
     * @param procNodeType 节点类型
     */
    public void setProcNodeType(Integer procNodeType) {
        this.procNodeType = procNodeType;
    }

    /**
     * 获取1|2|3表示[上一步|下一步|发起人]全部通知
     *
     * @return notice_type - 1|2|3表示[上一步|下一步|发起人]全部通知
     */
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * 设置1|2|3表示[上一步|下一步|发起人]全部通知
     *
     * @param noticeType 1|2|3表示[上一步|下一步|发起人]全部通知
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * 获取处理方式：0 普通，1 抢占， 2 会签
     *
     * @return handle_type - 处理方式：0 普通，1 抢占， 2 会签
     */
    public Integer getHandleType() {
        return handleType;
    }

    /**
     * 设置处理方式：0 普通，1 抢占， 2 会签
     *
     * @param handleType 处理方式：0 普通，1 抢占， 2 会签
     */
    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }

    /**
     * 获取任务名称
     *
     * @return task_name - 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置任务名称
     *
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取执行人
     *
     * @return assignee - 执行人
     */
    public Long getAssignee() {
        return assignee;
    }

    /**
     * 设置执行人
     *
     * @param assignee 执行人
     */
    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    /**
     * 获取表单id
     *
     * @return form_id - 表单id
     */
    public Long getFormId() {
        return formId;
    }

    /**
     * 设置表单id
     *
     * @param formId 表单id
     */
    public void setFormId(Long formId) {
        this.formId = formId;
    }

    /**
     * 获取关联业务id
     *
     * @return business_id - 关联业务id
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * 设置关联业务id
     *
     * @param businessId 关联业务id
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取状态：0 默认，1 正在进行， 2 已完成
     *
     * @return task_state - 状态：0 默认，1 正在进行， 2 已完成
     */
    public Integer getTaskState() {
        return taskState;
    }

    /**
     * 设置状态：0 默认，1 正在进行， 2 已完成
     *
     * @param taskState 状态：0 默认，1 正在进行， 2 已完成
     */
    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    /**
     * @return create_user_id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modify_user_id
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * @param modifyUserId
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
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
        sb.append(", runTaskId=").append(runTaskId);
        sb.append(", examId=").append(examId);
        sb.append(", questionId=").append(questionId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", procNodeType=").append(procNodeType);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", handleType=").append(handleType);
        sb.append(", taskName=").append(taskName);
        sb.append(", assignee=").append(assignee);
        sb.append(", formId=").append(formId);
        sb.append(", businessId=").append(businessId);
        sb.append(", taskState=").append(taskState);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}