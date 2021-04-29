/*
 * WfProcInst.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-14 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-14
 **/
public class WfProcInst implements Serializable {
    private Long procInstId;

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
     * 流程名称
     */
    private String procDefName;

    /**
     * 流程归类（0：答案，1：学生）
     */
    private Integer procCategory;

    /**
     * 发起员工id
     */
    private Long staffId;

    /**
     * 发起员工名称
     */
    private String staffName;

    /**
     * 所属企业id
     */
    private Long companyId;

    /**
     * 流程分类
     */
    private Long procTypeId;

    /**
     * 状态: 0 审批中，1 审批完成， 2 已撤销
     */
    private Integer procInstState;

    /**
     * 流程完成时间
     */
    private Date completeTime;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private static final long serialVersionUID = 4997794572570141696L;

    /**
     * @return proc_inst_id
     */
    public Long getProcInstId() {
        return procInstId;
    }

    /**
     * @param procInstId
     */
    public void setProcInstId(Long procInstId) {
        this.procInstId = procInstId;
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
     * 获取流程名称
     *
     * @return proc_def_name - 流程名称
     */
    public String getProcDefName() {
        return procDefName;
    }

    /**
     * 设置流程名称
     *
     * @param procDefName 流程名称
     */
    public void setProcDefName(String procDefName) {
        this.procDefName = procDefName;
    }

    /**
     * 获取流程归类（0：答案，1：学生）
     *
     * @return proc_category - 流程归类（0：答案，1：学生）
     */
    public Integer getProcCategory() {
        return procCategory;
    }

    /**
     * 设置流程归类（0：答案，1：学生）
     *
     * @param procCategory 流程归类（0：答案，1：学生）
     */
    public void setProcCategory(Integer procCategory) {
        this.procCategory = procCategory;
    }

    /**
     * 获取发起员工id
     *
     * @return staff_id - 发起员工id
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置发起员工id
     *
     * @param staffId 发起员工id
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取发起员工名称
     *
     * @return staff_name - 发起员工名称
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置发起员工名称
     *
     * @param staffName 发起员工名称
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取所属企业id
     *
     * @return company_id - 所属企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属企业id
     *
     * @param companyId 所属企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取流程分类
     *
     * @return proc_type_id - 流程分类
     */
    public Long getProcTypeId() {
        return procTypeId;
    }

    /**
     * 设置流程分类
     *
     * @param procTypeId 流程分类
     */
    public void setProcTypeId(Long procTypeId) {
        this.procTypeId = procTypeId;
    }

    /**
     * 获取状态: 0 审批中，1 审批完成， 2 已撤销
     *
     * @return proc_inst_state - 状态: 0 审批中，1 审批完成， 2 已撤销
     */
    public Integer getProcInstState() {
        return procInstState;
    }

    /**
     * 设置状态: 0 审批中，1 审批完成， 2 已撤销
     *
     * @param procInstState 状态: 0 审批中，1 审批完成， 2 已撤销
     */
    public void setProcInstState(Integer procInstState) {
        this.procInstState = procInstState;
    }

    /**
     * 获取流程完成时间
     *
     * @return complete_time - 流程完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置流程完成时间
     *
     * @param completeTime 流程完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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
        sb.append(", procInstId=").append(procInstId);
        sb.append(", examId=").append(examId);
        sb.append(", questionId=").append(questionId);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procDefName=").append(procDefName);
        sb.append(", procCategory=").append(procCategory);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffName=").append(staffName);
        sb.append(", companyId=").append(companyId);
        sb.append(", procTypeId=").append(procTypeId);
        sb.append(", procInstState=").append(procInstState);
        sb.append(", completeTime=").append(completeTime);
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