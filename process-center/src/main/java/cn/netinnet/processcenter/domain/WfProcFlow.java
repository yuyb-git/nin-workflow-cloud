/*
 * WfProcFlow.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-27 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-27
 **/
public class WfProcFlow implements Serializable {
    private Long procFlowId;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 节点自定义id
     */
    private String flowElementId;

    /**
     * 源节点，wf_proc_node的主键
     */
    private Long sourceRef;

    /**
     * 目标节点，wf_proc_node的主键
     */
    private Long targetRef;

    /**
     * 条件表达式
     */
    private String conditionExpression;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 4503755480397228032L;

    /**
     * @return proc_flow_id
     */
    public Long getProcFlowId() {
        return procFlowId;
    }

    /**
     * @param procFlowId
     */
    public void setProcFlowId(Long procFlowId) {
        this.procFlowId = procFlowId;
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
     * 获取源节点，wf_proc_node的主键
     *
     * @return source_ref - 源节点，wf_proc_node的主键
     */
    public Long getSourceRef() {
        return sourceRef;
    }

    /**
     * 设置源节点，wf_proc_node的主键
     *
     * @param sourceRef 源节点，wf_proc_node的主键
     */
    public void setSourceRef(Long sourceRef) {
        this.sourceRef = sourceRef;
    }

    /**
     * 获取目标节点，wf_proc_node的主键
     *
     * @return target_ref - 目标节点，wf_proc_node的主键
     */
    public Long getTargetRef() {
        return targetRef;
    }

    /**
     * 设置目标节点，wf_proc_node的主键
     *
     * @param targetRef 目标节点，wf_proc_node的主键
     */
    public void setTargetRef(Long targetRef) {
        this.targetRef = targetRef;
    }

    /**
     * 获取条件表达式
     *
     * @return condition_expression - 条件表达式
     */
    public String getConditionExpression() {
        return conditionExpression;
    }

    /**
     * 设置条件表达式
     *
     * @param conditionExpression 条件表达式
     */
    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", procFlowId=").append(procFlowId);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", flowElementId=").append(flowElementId);
        sb.append(", sourceRef=").append(sourceRef);
        sb.append(", targetRef=").append(targetRef);
        sb.append(", conditionExpression=").append(conditionExpression);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}