/*
 * WfDept.java
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
public class WfDept implements Serializable {
    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门id
     */
    private Long parent;

    /**
     * 部门级别
     */
    private Integer level;

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

    private int judgeResult;

    private static final long serialVersionUID = 5770951318703130624L;

    /**
     * 获取部门id
     *
     * @return dept_id - 部门id
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id
     *
     * @param deptId 部门id
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取部门名称
     *
     * @return dept_name - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取父部门id
     *
     * @return parent - 父部门id
     */
    public Long getParent() {
        return parent;
    }

    /**
     * 设置父部门id
     *
     * @param parent 父部门id
     */
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * 获取部门级别
     *
     * @return level - 部门级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置部门级别
     *
     * @param level 部门级别
     */
    public void setLevel(Integer level) {
        this.level = level;
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
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", parent=").append(parent);
        sb.append(", level=").append(level);
        sb.append(", companyId=").append(companyId);
        sb.append(", userId=").append(userId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}