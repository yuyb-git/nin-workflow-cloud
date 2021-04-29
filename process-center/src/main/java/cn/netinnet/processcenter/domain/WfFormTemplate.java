/*
 * WfFormTemplate.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-12 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date   2020-05-12
 **/
public class WfFormTemplate implements Serializable {
    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 表单数量
     */
    private Integer formNum;

    /**
     * 备注信息
     */
    private String description;

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

    /**
     * 模板选择的表单
     */
    private List<WfFormTemplateItem> formTemplateItems;

    private static final long serialVersionUID = 1845820226386064384L;

    /**
     * 获取模板id
     *
     * @return template_id - 模板id
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板id
     *
     * @param templateId 模板id
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取模板名称
     *
     * @return template_name - 模板名称
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置模板名称
     *
     * @param templateName 模板名称
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * 获取表单数量
     *
     * @return form_num - 表单数量
     */
    public Integer getFormNum() {
        return formNum;
    }

    /**
     * 设置表单数量
     *
     * @param formNum 表单数量
     */
    public void setFormNum(Integer formNum) {
        this.formNum = formNum;
    }

    /**
     * 获取备注信息
     *
     * @return description - 备注信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注信息
     *
     * @param description 备注信息
     */
    public void setDescription(String description) {
        this.description = description;
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

    public List<WfFormTemplateItem> getFormTemplateItems() {
        return formTemplateItems;
    }

    public void setFormTemplateItems(List<WfFormTemplateItem> formTemplateItems) {
        this.formTemplateItems = formTemplateItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", templateId=").append(templateId);
        sb.append(", templateName=").append(templateName);
        sb.append(", formNum=").append(formNum);
        sb.append(", description=").append(description);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}