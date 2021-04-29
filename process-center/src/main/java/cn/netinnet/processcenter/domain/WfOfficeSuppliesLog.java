/*
 * WfOfficeSuppliesLog.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-20 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-20
 **/
public class WfOfficeSuppliesLog implements Serializable {
    /**
     * 记录id
     */
    private Long goodLogId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 实例id
     */
    private Long procInstId;

    /**
     * 办公用品id
     */
    private Long goodId;

    /**
     * 计价单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 操作： I 买入， O售出
     */
    private String operationFlag;

    /**
     * 操作日期
     */
    private String operationDate;

    /**
     * 数量余额
     */
    private Integer balanceNumber;

    /**
     * 金额余额
     */
    private BigDecimal balanceAmount;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 4348897852255854592L;

    /**
     * 获取记录id
     *
     * @return good_log_id - 记录id
     */
    public Long getGoodLogId() {
        return goodLogId;
    }

    /**
     * 设置记录id
     *
     * @param goodLogId 记录id
     */
    public void setGoodLogId(Long goodLogId) {
        this.goodLogId = goodLogId;
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
     * 获取实例id
     *
     * @return proc_inst_id - 实例id
     */
    public Long getProcInstId() {
        return procInstId;
    }

    /**
     * 设置实例id
     *
     * @param procInstId 实例id
     */
    public void setProcInstId(Long procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取办公用品id
     *
     * @return good_id - 办公用品id
     */
    public Long getGoodId() {
        return goodId;
    }

    /**
     * 设置办公用品id
     *
     * @param goodId 办公用品id
     */
    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    /**
     * 获取计价单位
     *
     * @return unit - 计价单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置计价单位
     *
     * @param unit 计价单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取单价
     *
     * @return unit_price - 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单价
     *
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取操作： I 买入， O售出
     *
     * @return operation_flag - 操作： I 买入， O售出
     */
    public String getOperationFlag() {
        return operationFlag;
    }

    /**
     * 设置操作： I 买入， O售出
     *
     * @param operationFlag 操作： I 买入， O售出
     */
    public void setOperationFlag(String operationFlag) {
        this.operationFlag = operationFlag;
    }

    /**
     * 获取操作日期
     *
     * @return operation_date - 操作日期
     */
    public String getOperationDate() {
        return operationDate;
    }

    /**
     * 设置操作日期
     *
     * @param operationDate 操作日期
     */
    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 获取数量余额
     *
     * @return balance_number - 数量余额
     */
    public Integer getBalanceNumber() {
        return balanceNumber;
    }

    /**
     * 设置数量余额
     *
     * @param balanceNumber 数量余额
     */
    public void setBalanceNumber(Integer balanceNumber) {
        this.balanceNumber = balanceNumber;
    }

    /**
     * 获取金额余额
     *
     * @return balance_amount - 金额余额
     */
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * 设置金额余额
     *
     * @param balanceAmount 金额余额
     */
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    /**
     * 获取修改人
     *
     * @return modify_user_id - 修改人
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置修改人
     *
     * @param modifyUserId 修改人
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
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
        sb.append(", goodLogId=").append(goodLogId);
        sb.append(", asId=").append(asId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", goodId=").append(goodId);
        sb.append(", unit=").append(unit);
        sb.append(", number=").append(number);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", amount=").append(amount);
        sb.append(", operationFlag=").append(operationFlag);
        sb.append(", operationDate=").append(operationDate);
        sb.append(", balanceNumber=").append(balanceNumber);
        sb.append(", balanceAmount=").append(balanceAmount);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}