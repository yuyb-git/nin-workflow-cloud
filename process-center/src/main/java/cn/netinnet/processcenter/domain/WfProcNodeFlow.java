/*
 * WfProcNodeFlow.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-07-23 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-07-23
 **/
public class WfProcNodeFlow implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 节点分支流
     */
    private String nodeFlow;

    private static final long serialVersionUID = 703397726214331392L;

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
     * 获取节点分支流
     *
     * @return node_flow - 节点分支流
     */
    public String getNodeFlow() {
        return nodeFlow;
    }

    /**
     * 设置节点分支流
     *
     * @param nodeFlow 节点分支流
     */
    public void setNodeFlow(String nodeFlow) {
        this.nodeFlow = nodeFlow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", nodeFlow=").append(nodeFlow);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}