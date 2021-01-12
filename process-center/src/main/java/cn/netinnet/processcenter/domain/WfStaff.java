/*
 * WfStaff.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-07-07 Created
 */
package cn.netinnet.processcenter.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-07-07
 **/
public class WfStaff implements Serializable {
    /**
     * 员工id
     */
    private Long staffId;

    /**
     * 姓名
     */
    @NotBlank(message = "员工姓名不能为空")
    @Length(max = 20, message = "姓名长度不能超过20")
    private String staffName;

    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空")
    private String jobNumber;

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属企业id
     */
    @NotNull(message = "所属企业不能为空")
    private Long companyId;

    /**
     * 部门id
     */
    @NotNull(message = "员工部门不能为空")
    private Long deptId;

    /**
     * 岗位id
     */
    @NotNull(message = "员工岗位不能为空")
    private Long positionId;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 启用状态
     */
    private Integer enable;

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

    private static final long serialVersionUID = 6143205380368347136L;

    /**
     * 获取员工id
     *
     * @return staff_id - 员工id
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * 设置员工id
     *
     * @param staffId 员工id
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    /**
     * 获取姓名
     *
     * @return staff_name - 姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置姓名
     *
     * @param staffName 姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * 获取工号
     *
     * @return job_number - 工号
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置工号
     *
     * @param jobNumber 工号
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取用户名
     *
     * @return account - 用户名
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户名
     *
     * @param account 用户名
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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
     * 获取电话
     *
     * @return tel - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取启用状态
     *
     * @return enable - 启用状态
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置启用状态
     *
     * @param enable 启用状态
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
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
        sb.append(", staffId=").append(staffId);
        sb.append(", staffName=").append(staffName);
        sb.append(", jobNumber=").append(jobNumber);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", companyId=").append(companyId);
        sb.append(", deptId=").append(deptId);
        sb.append(", positionId=").append(positionId);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", enable=").append(enable);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}