/*
 * WfProcType.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-09-14 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-09-14
 **/
public class WfProcType implements Serializable {
    private Long id;

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 试题id
     */
    private Long questionId;

    /**
     * 数据归类（0：答案，1：学生）
     */
    private Integer typeCategory;

    /**
     * 子分类
     */
    private String dataItem;

    /**
     * 所属企业id
     */
    private Long companyId;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

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

    private static final long serialVersionUID = 2377292044995757056L;

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
     * 获取试题id
     *
     * @return question_id - 试题id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置试题id
     *
     * @param questionId 试题id
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取数据归类（0：答案，1：学生）
     *
     * @return type_category - 数据归类（0：答案，1：学生）
     */
    public Integer getTypeCategory() {
        return typeCategory;
    }

    /**
     * 设置数据归类（0：答案，1：学生）
     *
     * @param typeCategory 数据归类（0：答案，1：学生）
     */
    public void setTypeCategory(Integer typeCategory) {
        this.typeCategory = typeCategory;
    }

    /**
     * 获取子分类
     *
     * @return data_item - 子分类
     */
    public String getDataItem() {
        return dataItem;
    }

    /**
     * 设置子分类
     *
     * @param dataItem 子分类
     */
    public void setDataItem(String dataItem) {
        this.dataItem = dataItem;
    }

    /**
     * 获取所属企业id
     *
     * @return company_id - 所属企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属企业id
     *
     * @param companyId 所属企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取逻辑删除：0表示正常； 1表示删除
     *
     * @return del_flag - 逻辑删除：0表示正常； 1表示删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除：0表示正常； 1表示删除
     *
     * @param delFlag 逻辑删除：0表示正常； 1表示删除
     */
    public void setDelFlag(Integer delFlag) {
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
        sb.append(", id=").append(id);
        sb.append(", examId=").append(examId);
        sb.append(", questionId=").append(questionId);
        sb.append(", typeCategory=").append(typeCategory);
        sb.append(", dataItem=").append(dataItem);
        sb.append(", companyId=").append(companyId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}