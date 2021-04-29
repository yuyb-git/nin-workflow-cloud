/*
 * WfProcTransaction.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-22 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2021-04-22
 **/
public class WfProcTransaction implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 事务id
     */
    private Long transactionId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 流程任务id
     */
    private Long runTaskId;

    /**
     * 表单id
     */
    private Long formId;

    /**
     * 表单名
     */
    private String tableName;

    /**
     * 表单字段
     */
    private String formColumn;

    /**
     * 字段变更值
     */
    private String columnValue;

    /**
     * 操作:-1减，1加
     */
    private Boolean operation;

    private static final long serialVersionUID = 5142647291637744640L;

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
     * 获取事务id
     *
     * @return transaction_id - 事务id
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * 设置事务id
     *
     * @param transactionId 事务id
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取账套id
     *
     * @return as_id - 账套id
     */
    public Long getAsId() {
        return asId;
    }

    /**
     * 设置账套id
     *
     * @param asId 账套id
     */
    public void setAsId(Long asId) {
        this.asId = asId;
    }

    /**
     * 获取流程任务id
     *
     * @return run_task_id - 流程任务id
     */
    public Long getRunTaskId() {
        return runTaskId;
    }

    /**
     * 设置流程任务id
     *
     * @param runTaskId 流程任务id
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
     * 获取表单名
     *
     * @return table_name - 表单名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置表单名
     *
     * @param tableName 表单名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取表单字段
     *
     * @return form_column - 表单字段
     */
    public String getFormColumn() {
        return formColumn;
    }

    /**
     * 设置表单字段
     *
     * @param formColumn 表单字段
     */
    public void setFormColumn(String formColumn) {
        this.formColumn = formColumn;
    }

    /**
     * 获取字段变更值
     *
     * @return column_value - 字段变更值
     */
    public String getColumnValue() {
        return columnValue;
    }

    /**
     * 设置字段变更值
     *
     * @param columnValue 字段变更值
     */
    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    /**
     * 获取操作:-1减，1加
     *
     * @return operation - 操作:-1减，1加
     */
    public Boolean getOperation() {
        return operation;
    }

    /**
     * 设置操作:-1减，1加
     *
     * @param operation 操作:-1减，1加
     */
    public void setOperation(Boolean operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", asId=").append(asId);
        sb.append(", runTaskId=").append(runTaskId);
        sb.append(", formId=").append(formId);
        sb.append(", tableName=").append(tableName);
        sb.append(", formColumn=").append(formColumn);
        sb.append(", columnValue=").append(columnValue);
        sb.append(", operation=").append(operation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}