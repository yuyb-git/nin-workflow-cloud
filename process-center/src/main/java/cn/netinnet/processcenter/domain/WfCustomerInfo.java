/*
 * WfCustomerInfo.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-20 Created
 */
package cn.netinnet.processcenter.domain;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-20
 **/
public class WfCustomerInfo implements Serializable {
    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 企业名称
     */
    @NotEmpty(message = "企业名称不能为空")
    @Size(max = 30, message = "企业名称长度不能超过30")
    private String companyName;

    /**
     * 法人代表
     */
    @NotEmpty(message = "法人代表不能为空")
    @Size(max = 20, message = "法人代表长度不能超过20")
    private String representative;

    /**
     * 注册资本
     */
    @NotNull(message = "注册资本不能为空")
    @Min(value = 0L, message = "注册资本金必须在0~999999999999999999之间")
    @Max(value = 999999999999999999L, message = "注册资本金必须在0~999999999999999999之间")
    private BigDecimal registerCapital;

    /**
     * 统一社会信用代码
     */
    @NotEmpty(message = "统一社会信用代码不能为空")
    @Size(max = 20, message = "统一社会信用代码长度不能超过20")
    private String socialCreditCode;

    /**
     * 银行账号
     */
    @NotEmpty(message = "银行账号不能为空")
    @Size(max = 20, message = "银行账号长度不能超过20")
    private String bankAccount;

    /**
     * 开户银行
     */
    @NotEmpty(message = "开户银行不能为空")
    @Size(max = 20, message = "开户银行长度不能超过20")
    private String depositBank;

    /**
     * 公司地址
     */
    @NotEmpty(message = "公司地址不能为空")
    @Size(max = 50, message = "公司地址长度不能超过50")
    private String companyAddress;

    /**
     * 公司电话
     */
    @NotEmpty(message = "公司电话能为空")
    @Pattern(regexp = "[0-9-()（）]{7,18}", message = "电话格式不正确")
    private String companyTel;

    /**
     * 信用评级
     */
    @NotNull(message = "信用评级不能为空")
    @Min(value = 1L, message = "信用评级必须为在1~5之间的整数")
    @Max(value = 5L,  message = "信用评级必须为在1~5之间的整数")
    private Integer creditRating;

    /**
     * 可赊销金额上限
     */
    @NotNull(message = "可赊销金额上限不能为空")
    @Min(value = 0L, message = "可赊销金额上限必须在0~999999999999999999之间")
    @Max(value = 999999999999999999L, message = "可赊销金额上限必须在0~999999999999999999之间")
    private BigDecimal creditLimit;

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

    private static final long serialVersionUID = 6383610576120931328L;

    /**
     * 获取客户id
     *
     * @return customer_id - 客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
     * 获取企业名称
     *
     * @return company_name - 企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置企业名称
     *
     * @param companyName 企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取法人代表
     *
     * @return representative - 法人代表
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * 设置法人代表
     *
     * @param representative 法人代表
     */
    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    /**
     * 获取注册资本
     *
     * @return register_capital - 注册资本
     */
    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    /**
     * 设置注册资本
     *
     * @param registerCapital 注册资本
     */
    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return social_credit_code - 统一社会信用代码
     */
    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param socialCreditCode 统一社会信用代码
     */
    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    /**
     * 获取银行账号
     *
     * @return bank_account - 银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置银行账号
     *
     * @param bankAccount 银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取开户银行
     *
     * @return deposit_bank - 开户银行
     */
    public String getDepositBank() {
        return depositBank;
    }

    /**
     * 设置开户银行
     *
     * @param depositBank 开户银行
     */
    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    /**
     * 获取公司地址
     *
     * @return company_address - 公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddress 公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取公司电话
     *
     * @return company_tel - 公司电话
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 设置公司电话
     *
     * @param companyTel 公司电话
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    /**
     * 获取信用评级
     *
     * @return credit_rating - 信用评级
     */
    public Integer getCreditRating() {
        return creditRating;
    }

    /**
     * 设置信用评级
     *
     * @param creditRating 信用评级
     */
    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * 获取可赊销金额上限
     *
     * @return credit_limit - 可赊销金额上限
     */
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    /**
     * 设置可赊销金额上限
     *
     * @param creditLimit 可赊销金额上限
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
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
        sb.append(", customerId=").append(customerId);
        sb.append(", asId=").append(asId);
        sb.append(", companyName=").append(companyName);
        sb.append(", representative=").append(representative);
        sb.append(", registerCapital=").append(registerCapital);
        sb.append(", socialCreditCode=").append(socialCreditCode);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", depositBank=").append(depositBank);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", companyTel=").append(companyTel);
        sb.append(", creditRating=").append(creditRating);
        sb.append(", creditLimit=").append(creditLimit);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
