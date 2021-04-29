/*
 * WfFormTemplateItem.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-13 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2020-05-13
 **/
public class WfFormTemplateItem implements Serializable {
    /**
     * 主键
     */
    private Long itemId;

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 表单id
     */
    private Long formId;

    /**
     * 表单名称
     */
    private String formName;

    private static final long serialVersionUID = 9105952482039010304L;

    /**
     * 获取主键
     *
     * @return item_id - 主键
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置主键
     *
     * @param itemId 主键
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

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
     * 获取表单名称
     *
     * @return form_name - 表单名称
     */
    public String getFormName() {
        return formName;
    }

    /**
     * 设置表单名称
     *
     * @param formName 表单名称
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", templateId=").append(templateId);
        sb.append(", formId=").append(formId);
        sb.append(", formName=").append(formName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}