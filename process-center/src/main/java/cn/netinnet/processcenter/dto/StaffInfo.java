package cn.netinnet.processcenter.dto;

import cn.netinnet.workflow.wf.domain.WfStaff;

import java.io.Serializable;

/**
 * @ClassName StaffInfo
 * @Description
 * @Author yuyb
 * @Date 2020/4/24 14:34
 */
public class StaffInfo implements Serializable {

    private static final long serialVersionUID = 8151201786281549330L;

    /**
     * 员工id
     */
    private Long staffId;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 所属企业id
     */
    private Long companyId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 岗位id
     */
    private Long positionId;

    /**
     * 岗位名称
     */
    private String positionName;

    public StaffInfo() {
        super();
    }

    public StaffInfo(WfStaff staff) {
        this.staffId = staff.getStaffId();
        this.staffName = staff.getStaffName();
        this.jobNumber = staff.getJobNumber();
        this.companyId = staff.getCompanyId();
        this.deptId = staff.getDeptId();
        this.positionId = staff.getPositionId();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public String toString() {
        return "StaffInfo{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", companyId=" + companyId +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                '}';
    }

}
