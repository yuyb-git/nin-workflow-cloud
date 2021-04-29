/*
 * WfNodeAssignee.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-19 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-06-19
 **/
public class WfNodeAssignee implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 流程节点id
     */
    private Long procNodeId;

    /**
     * 执行id
     */
    private Long assignee;

    private static final long serialVersionUID = 2319660669053408256L;

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
     * 获取执行id
     *
     * @return assignee - 执行id
     */
    public Long getAssignee() {
        return assignee;
    }

    /**
     * 设置执行id
     *
     * @param assignee 执行id
     */
    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", assignee=").append(assignee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}