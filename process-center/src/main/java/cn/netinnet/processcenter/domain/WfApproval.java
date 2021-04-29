/*
 * WfApproval.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-15 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-05-15
 **/
public class WfApproval implements Serializable {
    /**
     * 审批id
     */
    private Long approvalId;

    /**
     * 流程实例id
     */
    private Long procInstId;

    /**
     * 任务id
     */
    private Long runTaskId;

    /**
     * 表单id
     */
    private Long formId;

    /**
     * 审批类型
     */
    private String approvalType;

    /**
     * 审批人
     */
    private Long approvalUserId;

    /**
     * 审批意见
     */
    private String approvalOpinion;

    /**
     * 审批结果
     */
    private Integer approvalResult;

    /**
     * 审批时间
     */
    private Date approvalTime;

    private static final long serialVersionUID = 5980900074302620672L;

    /**
     * 获取审批id
     *
     * @return approval_id - 审批id
     */
    public Long getApprovalId() {
        return approvalId;
    }

    /**
     * 设置审批id
     *
     * @param approvalId 审批id
     */
    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
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
     * 获取任务id
     *
     * @return run_task_id - 任务id
     */
    public Long getRunTaskId() {
        return runTaskId;
    }

    /**
     * 设置任务id
     *
     * @param runTaskId 任务id
     */
    public void setRunTaskId(Long runTaskId) {
        this.runTaskId = runTaskId;
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
     * 获取审批类型
     *
     * @return approval_type - 审批类型
     */
    public String getApprovalType() {
        return approvalType;
    }

    /**
     * 设置审批类型
     *
     * @param approvalType 审批类型
     */
    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    /**
     * 获取审批人
     *
     * @return approval_user_id - 审批人
     */
    public Long getApprovalUserId() {
        return approvalUserId;
    }

    /**
     * 设置审批人
     *
     * @param approvalUserId 审批人
     */
    public void setApprovalUserId(Long approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    /**
     * 获取审批意见
     *
     * @return approval_opinion - 审批意见
     */
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    /**
     * 设置审批意见
     *
     * @param approvalOpinion 审批意见
     */
    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    /**
     * 获取审批结果
     *
     * @return approval_result - 审批结果
     */
    public Integer getApprovalResult() {
        return approvalResult;
    }

    /**
     * 设置审批结果
     *
     * @param approvalResult 审批结果
     */
    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    /**
     * 获取审批时间
     *
     * @return approval_time - 审批时间
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置审批时间
     *
     * @param approvalTime 审批时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", approvalId=").append(approvalId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", runTaskId=").append(runTaskId);
        sb.append(", formId=").append(formId);
        sb.append(", approvalType=").append(approvalType);
        sb.append(", approvalUserId=").append(approvalUserId);
        sb.append(", approvalOpinion=").append(approvalOpinion);
        sb.append(", approvalResult=").append(approvalResult);
        sb.append(", approvalTime=").append(approvalTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}