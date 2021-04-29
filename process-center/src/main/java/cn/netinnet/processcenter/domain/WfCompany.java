/*
 * WfCompany.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-14 Created
 */
package cn.netinnet.processcenter.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-14
 **/
public class WfCompany implements Serializable {
    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 企业名称
     */
    @NotEmpty(message = "企业名称不能为空")
    @Size(max = 30, message = "企业名称长度不能超过30")
    private String companyName;

    /**
     * 企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     */
    private Integer companyCategory;

    /**
     * 统一社会信用代码
     */
    @NotEmpty(message = "统一社会信用代码不能为空")
    @Size(max = 50, message = "统一社会信用代码长度不能超过50")
    private String creditCode;

    /**
     * 法人代表
     */
    @NotEmpty(message = "法人代表不能为空")
    @Size(max = 20, message = "法人代表长度不能超过20")
    private String legalRepresent;

    /**
     * 成立日期
     */
    @NotNull(message = "成立日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 运营周期
     */
    @NotNull(message = "运营周期不能为空")
    @Pattern(regexp = "^\\d{6}$", message = "运营周期格式不正确")
    private String runPeriod;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    @NotNull(message = "电话不能为空")
    @Pattern(regexp = "[0-9-()（）]{7,18}", message = "电话格式不正确")
    private String phone;

    /**
     * 开户银行
     */
    @NotEmpty(message = "开户银行不能为空")
    @Size(max = 50, message = "法人代表长度不能超过50")
    private String depositBank;

    /**
     * 银行账号
     */
    @NotEmpty(message = "银行账号不能为空")
    @Size(max = 30, message = "银行账号长度不能超过30")
    private String bankAccount;

    /**
     * 银行资金
     */
    @NotNull(message = "银行资金不能为空")
    @Max(value = 999999999999L, message = "银行资金不能超过999999999999")
    private BigDecimal bankFund;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 企业状态（0：启用；1：禁用）
     */
    private Integer companyStatus;

    /**
     * 逻辑删除标识
     */
    private Long delFlag;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 5109439180771248128L;

    /**
     * 获取企业id
     *
     * @return company_id - 企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业id
     *
     * @param companyId 企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
     * 获取企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     *
     * @return company_category - 企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     */
    public Integer getCompanyCategory() {
        return companyCategory;
    }

    /**
     * 设置企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     *
     * @param companyCategory 企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     */
    public void setCompanyCategory(Integer companyCategory) {
        this.companyCategory = companyCategory;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return credit_code - 统一社会信用代码
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param creditCode 统一社会信用代码
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    /**
     * 获取法人代表
     *
     * @return legal_represent - 法人代表
     */
    public String getLegalRepresent() {
        return legalRepresent;
    }

    /**
     * 设置法人代表
     *
     * @param legalRepresent 法人代表
     */
    public void setLegalRepresent(String legalRepresent) {
        this.legalRepresent = legalRepresent;
    }

    /**
     * 获取成立日期
     *
     * @return create_date - 成立日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置成立日期
     *
     * @param createDate 成立日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取运营周期
     *
     * @return run_period - 运营周期
     */
    public String getRunPeriod() {
        return runPeriod;
    }

    /**
     * 设置运营周期
     *
     * @param runPeriod 运营周期
     */
    public void setRunPeriod(String runPeriod) {
        this.runPeriod = runPeriod;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取银行资金
     *
     * @return bank_fund - 银行资金
     */
    public BigDecimal getBankFund() {
        return bankFund;
    }

    /**
     * 设置银行资金
     *
     * @param bankFund 银行资金
     */
    public void setBankFund(BigDecimal bankFund) {
        this.bankFund = bankFund;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取企业状态（0：启用；1：禁用）
     *
     * @return company_status - 企业状态（0：启用；1：禁用）
     */
    public Integer getCompanyStatus() {
        return companyStatus;
    }

    /**
     * 设置企业状态（0：启用；1：禁用）
     *
     * @param companyStatus 企业状态（0：启用；1：禁用）
     */
    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * 获取逻辑删除标识
     *
     * @return del_flag - 逻辑删除标识
     */
    public Long getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除标识
     *
     * @param delFlag 逻辑删除标识
     */
    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
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
        sb.append(", companyId=").append(companyId);
        sb.append(", examId=").append(examId);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyCategory=").append(companyCategory);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", legalRepresent=").append(legalRepresent);
        sb.append(", createDate=").append(createDate);
        sb.append(", runPeriod=").append(runPeriod);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", depositBank=").append(depositBank);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankFund=").append(bankFund);
        sb.append(", remarks=").append(remarks);
        sb.append(", companyStatus=").append(companyStatus);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
