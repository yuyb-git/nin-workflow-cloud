/*
 * WfPosition.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-06-29 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-06-29
 **/
public class WfPosition implements Serializable {
    /**
     * 岗位id
     */
    private Long positionId;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 岗位编码
     */
    private String positionCode;

    /**
     * 父岗位
     */
    private Long parent;

    /**
     * 岗位级别
     */
    private Integer level;

    /**
     * 职级
     */
    private Long positionGradeId;

    /**
     * 归属企业id
     */
    private Long companyId;

    /**
     * 创建人
     */
    private Long userId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 对或者错
     */
    private int judgeResult;

    private static final long serialVersionUID = 3679311057835459584L;

    /**
     * 获取岗位id
     *
     * @return position_id - 岗位id
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * 设置岗位id
     *
     * @param positionId 岗位id
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取岗位名称
     *
     * @return position_name - 岗位名称
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 设置岗位名称
     *
     * @param positionName 岗位名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * 获取岗位编码
     *
     * @return position_code - 岗位编码
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * 设置岗位编码
     *
     * @param positionCode 岗位编码
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * 获取父岗位
     *
     * @return parent - 父岗位
     */
    public Long getParent() {
        return parent;
    }

    /**
     * 设置父岗位
     *
     * @param parent 父岗位
     */
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * 获取岗位级别
     *
     * @return level - 岗位级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置岗位级别
     *
     * @param level 岗位级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取职级
     *
     * @return position_grade_id - 职级
     */
    public Long getPositionGradeId() {
        return positionGradeId;
    }

    /**
     * 设置职级
     *
     * @param positionGradeId 职级
     */
    public void setPositionGradeId(Long positionGradeId) {
        this.positionGradeId = positionGradeId;
    }

    /**
     * 获取归属企业id
     *
     * @return company_id - 归属企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置归属企业id
     *
     * @param companyId 归属企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取创建人
     *
     * @return user_id - 创建人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置创建人
     *
     * @param userId 创建人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(int judgeResult) {
        this.judgeResult = judgeResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", positionId=").append(positionId);
        sb.append(", positionName=").append(positionName);
        sb.append(", positionCode=").append(positionCode);
        sb.append(", parent=").append(parent);
        sb.append(", level=").append(level);
        sb.append(", positionGradeId=").append(positionGradeId);
        sb.append(", companyId=").append(companyId);
        sb.append(", userId=").append(userId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
