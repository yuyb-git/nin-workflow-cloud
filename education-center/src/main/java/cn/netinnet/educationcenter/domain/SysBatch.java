/*
 * SysBatch.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-26 Created
 */
package cn.netinnet.educationcenter.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-05-26
 **/
public class SysBatch implements Serializable {
    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 批次名称
     */
    @NotBlank(message = "批次名称不可为空")
    private String batchName;

    /**
     * 教师id
     */
    private Long userId;

    /**
     * 教师名称
     */
    private String userName;

    /**
     * 学校id
     */
    private Long schoolId;

    /**
     * 批次开始时间
     */
    private Date beginDate;

    /**
     * 批次结束时间
     */
    private Date endDate;

    /**
     * 0-未开始;1-已开始;2-暂停中
     */
    private Integer batchStatus;

    /**
     * 记录创建人ID
     */
    private Long createUserId;

    /**
     * 记录生成时间
     */
    private Date createTime;

    /**
     * 记录修改人ID
     */
    private Long modifyUserId;

    /**
     * 记录修改时间
     */
    private Date modifyTime;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

    private static final long serialVersionUID = 6153363207744215040L;

    /**
     * 获取批次id
     *
     * @return batch_id - 批次id
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * 设置批次id
     *
     * @param batchId 批次id
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取批次名称
     *
     * @return batch_name - 批次名称
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * 设置批次名称
     *
     * @param batchName 批次名称
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * 获取教师id
     *
     * @return user_id - 教师id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置教师id
     *
     * @param userId 教师id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取教师名称
     *
     * @return user_name - 教师名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置教师名称
     *
     * @param userName 教师名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取学校id
     *
     * @return school_id - 学校id
     */
    public Long getSchoolId() {
        return schoolId;
    }

    /**
     * 设置学校id
     *
     * @param schoolId 学校id
     */
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取批次开始时间
     *
     * @return begin_date - 批次开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置批次开始时间
     *
     * @param beginDate 批次开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取批次结束时间
     *
     * @return end_date - 批次结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置批次结束时间
     *
     * @param endDate 批次结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取0-新建;1-已开始;2-暂停中
     *
     * @return batch_status - 0-新建;1-已开始;2-暂停中
     */
    public Integer getBatchStatus() {
        return batchStatus;
    }

    /**
     * 设置0-新建;1-已开始;2-暂停中
     *
     * @param batchStatus 0-新建;1-已开始;2-暂停中
     */
    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    /**
     * 获取记录创建人ID
     *
     * @return create_user_id - 记录创建人ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置记录创建人ID
     *
     * @param createUserId 记录创建人ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取记录生成时间
     *
     * @return create_time - 记录生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录生成时间
     *
     * @param createTime 记录生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取记录修改人ID
     *
     * @return modify_user_id - 记录修改人ID
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置记录修改人ID
     *
     * @param modifyUserId 记录修改人ID
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 获取记录修改时间
     *
     * @return modify_time - 记录修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置记录修改时间
     *
     * @param modifyTime 记录修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", batchId=").append(batchId);
        sb.append(", batchName=").append(batchName);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", batchStatus=").append(batchStatus);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
