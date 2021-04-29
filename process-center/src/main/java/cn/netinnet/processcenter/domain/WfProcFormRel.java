/*
 * WfProcFormRel.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-11-23 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-11-23
 **/
public class WfProcFormRel implements Serializable {
    private Long id;

    /**
     * 流程定义id
     */
    private Long procDefId;

    /**
     * 上一节点id
     */
    private Long preNodeId;

    /**
     * 节点id
     */
    private Long procNodeId;

    /**
     * 源表单id
     */
    private Long sourceForm;

    /**
     * 目标表单id
     */
    private Long targetForm;

    /**
     * 映射关系
     */
    private String rel;

    private static final long serialVersionUID = 5264464036663121920L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
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
     * 获取上一节点id
     *
     * @return pre_node_id - 上一节点id
     */
    public Long getPreNodeId() {
        return preNodeId;
    }

    /**
     * 设置上一节点id
     *
     * @param preNodeId 上一节点id
     */
    public void setPreNodeId(Long preNodeId) {
        this.preNodeId = preNodeId;
    }

    /**
     * 获取节点id
     *
     * @return proc_node_id - 节点id
     */
    public Long getProcNodeId() {
        return procNodeId;
    }

    /**
     * 设置节点id
     *
     * @param procNodeId 节点id
     */
    public void setProcNodeId(Long procNodeId) {
        this.procNodeId = procNodeId;
    }

    /**
     * 获取源表单id
     *
     * @return source_form - 源表单id
     */
    public Long getSourceForm() {
        return sourceForm;
    }

    /**
     * 设置源表单id
     *
     * @param sourceForm 源表单id
     */
    public void setSourceForm(Long sourceForm) {
        this.sourceForm = sourceForm;
    }

    /**
     * 获取目标表单id
     *
     * @return target_form - 目标表单id
     */
    public Long getTargetForm() {
        return targetForm;
    }

    /**
     * 设置目标表单id
     *
     * @param targetForm 目标表单id
     */
    public void setTargetForm(Long targetForm) {
        this.targetForm = targetForm;
    }

    /**
     * 获取映射关系
     *
     * @return rel - 映射关系
     */
    public String getRel() {
        return rel;
    }

    /**
     * 设置映射关系
     *
     * @param rel 映射关系
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", preNodeId=").append(preNodeId);
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", sourceForm=").append(sourceForm);
        sb.append(", targetForm=").append(targetForm);
        sb.append(", rel=").append(rel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}