/*
 * WfRunTaskPartner.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-15 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-06-15
 **/
public class WfRunTaskPartner implements Serializable {
    /**
     * 变量id
     */
    private Long partnerId;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 流程实例id
     */
    private Long procInstId;

    /**
     * 流程节点id
     */
    private Long procNodeId;

    /**
     * 运行时任务id
     */
    private Long runTaskId;

    /**
     * 参与者员工id
     */
    private Long staffId;

    private static final long serialVersionUID = 7240803135932894208L;

    /**
     * 获取变量id
     *
     * @return partner_id - 变量id
     */
    public Long getPartnerId() {
        return partnerId;
    }

    /**
     * 设置变量id
     *
     * @param partnerId 变量id
     */
    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
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
     * 获取流程节点id
     *
     * @return proc_node_id - 流程节点id
     */
    public Long getProcNodeId() {
        return procNodeId;
    }

    /**
     * 设置流程节点id
     *
     * @param procNodeId 流程节点id
     */
    public void setProcNodeId(Long procNodeId) {
        this.procNodeId = procNodeId;
    }

    /**
     * 获取运行时任务id
     *
     * @return run_task_id - 运行时任务id
     */
    public Long getRunTaskId() {
        return runTaskId;
    }

    /**
     * 设置运行时任务id
     *
     * @param runTaskId 运行时任务id
     */
    public void setRunTaskId(Long runTaskId) {
        this.runTaskId = runTaskId;
    }

    /**
     * 获取参与者员工id
     *
     * @return staff_id - 参与者员工id
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置参与者员工id
     *
     * @param staffId 参与者员工id
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", partnerId=").append(partnerId);
        sb.append(", examId=").append(examId);
        sb.append(", questionId=").append(questionId);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", runTaskId=").append(runTaskId);
        sb.append(", staffId=").append(staffId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}