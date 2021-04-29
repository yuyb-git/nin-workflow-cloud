/*
 * WfPositionGrade.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-07 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-07
 **/
public class WfPositionGrade implements Serializable {
    /**
     * id
     */
    private Long gradeId;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 职级名称
     */
    private String gradeName;

    /**
     * 级次
     */
    private Integer gradeLevel;

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

    private static final long serialVersionUID = 8250620378571712512L;

    /**
     * 获取id
     *
     * @return grade_id - id
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * 设置id
     *
     * @param gradeId id
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

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
     * 获取职级名称
     *
     * @return grade_name - 职级名称
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 设置职级名称
     *
     * @param gradeName 职级名称
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取级次
     *
     * @return grade_level - 级次
     */
    public Integer getGradeLevel() {
        return gradeLevel;
    }

    /**
     * 设置级次
     *
     * @param gradeLevel 级次
     */
    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
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
        sb.append(", gradeId=").append(gradeId);
        sb.append(", companyId=").append(companyId);
        sb.append(", gradeName=").append(gradeName);
        sb.append(", gradeLevel=").append(gradeLevel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
