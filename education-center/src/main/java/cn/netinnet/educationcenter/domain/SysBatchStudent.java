/*
 * SysBatchStudent.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-26 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-05-26
 **/
public class SysBatchStudent implements Serializable {
    /**
     * 批次学生id
     */
    private Long batchStudentId;

    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 学生id
     */
    private Long userId;

    /**
     * 学生名称
     */
    private String userName;

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

    private static final long serialVersionUID = 3644648865038431232L;

    /**
     * 获取批次学生id
     *
     * @return batch_student_id - 批次学生id
     */
    public Long getBatchStudentId() {
        return batchStudentId;
    }

    /**
     * 设置批次学生id
     *
     * @param batchStudentId 批次学生id
     */
    public void setBatchStudentId(Long batchStudentId) {
        this.batchStudentId = batchStudentId;
    }

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
     * 获取学生id
     *
     * @return user_id - 学生id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置学生id
     *
     * @param userId 学生id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取学生名称
     *
     * @return user_name - 学生名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置学生名称
     *
     * @param userName 学生名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
        sb.append(", batchStudentId=").append(batchStudentId);
        sb.append(", batchId=").append(batchId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
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
