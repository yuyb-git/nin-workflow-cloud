/*
 * WfCompany.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-16 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-16
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
    private String companyName;

    /**
     * 企业归类（0：系统；1：学生自建；2：答案自建；3：教师）
     */
    private Integer companyCategory;

    /**
     * 行业分类id
     */
    private Long industry;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 企业状态（0：启用；1：禁用）
     */
    private Integer companyStatus;

    /**
     * 表单模板id
     */
    private Long templateId;

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

    private static final long serialVersionUID = 7253903873890609152L;

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
     * 获取行业分类id
     *
     * @return industry - 行业分类id
     */
    public Long getIndustry() {
        return industry;
    }

    /**
     * 设置行业分类id
     *
     * @param industry 行业分类id
     */
    public void setIndustry(Long industry) {
        this.industry = industry;
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
     * 获取表单模板id
     *
     * @return template_id - 表单模板id
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置表单模板id
     *
     * @param templateId 表单模板id
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
        sb.append(", industry=").append(industry);
        sb.append(", remarks=").append(remarks);
        sb.append(", companyStatus=").append(companyStatus);
        sb.append(", templateId=").append(templateId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}