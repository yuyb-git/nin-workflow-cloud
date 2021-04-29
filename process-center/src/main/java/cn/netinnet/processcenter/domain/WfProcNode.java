/*
 * WfProcNode.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-07-02 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-07-02
 **/
public class WfProcNode implements Serializable {
    private Long procNodeId;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 节点自定义id
     */
    private String flowElementId;

    /**
     * 节点名称
     */
    private String procNodeName;

    /**
     * 节点类型：1 开始， 2 普通任务， 3 抄送， 4 转填 ，5 结束
     */
    private Integer procNodeType;

    /**
     * 发起/执行/抄送人类型：1 指定用户， 2 指定角色
     */
    private Integer assigneeType;

    /**
     * 操作表单
     */
    private Long formId;

    /**
     * 1|2|3表示[上一步|下一步|发起人]全部通知
     */
    private String noticeType;

    /**
     * 处理方式：0 普通，1 抢占， 2 会签 
     */
    private Integer handleType;

    /**
     * 回退方式： 0 无回退， 1 上一步 ， 2 发起人
     */
    private Integer backType;

    /**
     * 不满足时： 0 无操作， 1 结束， 2 退回发起
     */
    private Integer misMatch;

    private static final long serialVersionUID = 1971681530968980480L;

    /**
     * @return proc_node_id
     */
    public Long getProcNodeId() {
        return procNodeId;
    }

    /**
     * @param procNodeId
     */
    public void setProcNodeId(Long procNodeId) {
        this.procNodeId = procNodeId;
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
     * 获取节点自定义id
     *
     * @return flow_element_id - 节点自定义id
     */
    public String getFlowElementId() {
        return flowElementId;
    }

    /**
     * 设置节点自定义id
     *
     * @param flowElementId 节点自定义id
     */
    public void setFlowElementId(String flowElementId) {
        this.flowElementId = flowElementId;
    }

    /**
     * 获取节点名称
     *
     * @return proc_node_name - 节点名称
     */
    public String getProcNodeName() {
        return procNodeName;
    }

    /**
     * 设置节点名称
     *
     * @param procNodeName 节点名称
     */
    public void setProcNodeName(String procNodeName) {
        this.procNodeName = procNodeName;
    }

    /**
     * 获取节点类型：1 开始， 2 普通任务， 3 抄送， 4 转填 ，5 结束
     *
     * @return proc_node_type - 节点类型：1 开始， 2 普通任务， 3 抄送， 4 转填 ，5 结束
     */
    public Integer getProcNodeType() {
        return procNodeType;
    }

    /**
     * 设置节点类型：1 开始， 2 普通任务， 3 抄送， 4 转填 ，5 结束
     *
     * @param procNodeType 节点类型：1 开始， 2 普通任务， 3 抄送， 4 转填 ，5 结束
     */
    public void setProcNodeType(Integer procNodeType) {
        this.procNodeType = procNodeType;
    }

    /**
     * 获取发起/执行/抄送人类型：1 指定用户， 2 指定角色
     *
     * @return assignee_type - 发起/执行/抄送人类型：1 指定用户， 2 指定角色
     */
    public Integer getAssigneeType() {
        return assigneeType;
    }

    /**
     * 设置发起/执行/抄送人类型：1 指定用户， 2 指定角色
     *
     * @param assigneeType 发起/执行/抄送人类型：1 指定用户， 2 指定角色
     */
    public void setAssigneeType(Integer assigneeType) {
        this.assigneeType = assigneeType;
    }

    /**
     * 获取操作表单
     *
     * @return form_id - 操作表单
     */
    public Long getFormId() {
        return formId;
    }

    /**
     * 设置操作表单
     *
     * @param formId 操作表单
     */
    public void setFormId(Long formId) {
        this.formId = formId;
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
     * 获取回退方式： 0 无回退， 1 上一步 ， 2 发起人
     *
     * @return back_type - 回退方式： 0 无回退， 1 上一步 ， 2 发起人
     */
    public Integer getBackType() {
        return backType;
    }

    /**
     * 设置回退方式： 0 无回退， 1 上一步 ， 2 发起人
     *
     * @param backType 回退方式： 0 无回退， 1 上一步 ， 2 发起人
     */
    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    /**
     * 获取不满足时： 0 无操作， 1 结束， 2 退回发起
     *
     * @return mis_match - 不满足时： 0 无操作， 1 结束， 2 退回发起
     */
    public Integer getMisMatch() {
        return misMatch;
    }

    /**
     * 设置不满足时： 0 无操作， 1 结束， 2 退回发起
     *
     * @param misMatch 不满足时： 0 无操作， 1 结束， 2 退回发起
     */
    public void setMisMatch(Integer misMatch) {
        this.misMatch = misMatch;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", flowElementId=").append(flowElementId);
        sb.append(", procNodeName=").append(procNodeName);
        sb.append(", procNodeType=").append(procNodeType);
        sb.append(", assigneeType=").append(assigneeType);
        sb.append(", formId=").append(formId);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", handleType=").append(handleType);
        sb.append(", backType=").append(backType);
        sb.append(", misMatch=").append(misMatch);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}