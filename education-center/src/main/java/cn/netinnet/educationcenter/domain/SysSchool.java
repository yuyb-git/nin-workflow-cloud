/*
 * SysSchool.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-12 Created
 */
package cn.netinnet.educationcenter.domain;

import cn.netinnet.cloudcommon.utils.RegUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-05-12
 **/
public class SysSchool implements Serializable {
    /**
     * 学校id
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    @NotBlank(message = "学校名称不可为空")
    private String schoolName;

    /**
     * 学校简称
     */
    @NotBlank(message = "学校简称不可为空")
    private String schoolNickname;

    /**
     * 学校编码
     */
    private String schoolCode;

    /**
     * 区域
     */
    private String schoolArea;

    /**
     * 省份
     */
    @NotBlank(message = "省份不可为空")
    private String schoolProvince;

    /**
     * 城市
     */
    @NotBlank(message = "城市不可为空")
    private String schoolCity;

    /**
     * 地址
     */
    private String schoolAddress;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不可为空")
    private String contactName;

    /**
     * 联系人电话
     */
    @NotBlank(message = "联系电话不可为空")
    private String contactNumber;

    /**
     * 0-正常;1-禁用
     */
    private Integer schoolStatus;

    /**
     * 备注
     */
    private String schoolDescr;

    /**
     * 管理员账号
     */
    @Size(min = 1, max = 16, message = "账号长度应在{min}-{max}位之间！")
    @Pattern(regexp = RegUtil.REGEX_LETTER_NUM, message = "账号只能有字符和数字")
    private String adminLogin;

    /**
     * 记录创建人ID
     */
    private Long createUserId;

    /**
     * 记录生成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 记录修改人ID
     */
    private Long modifyUserId;

    /**
     * 记录修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 逻辑删除：0表示正常； 1表示删除
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1394303962891110400L;

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
     * 获取学校名称
     *
     * @return school_name - 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置学校名称
     *
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 获取学校简称
     *
     * @return school_nickname - 学校简称
     */
    public String getSchoolNickname() {
        return schoolNickname;
    }

    /**
     * 设置学校简称
     *
     * @param schoolNickname 学校简称
     */
    public void setSchoolNickname(String schoolNickname) {
        this.schoolNickname = schoolNickname;
    }

    /**
     * 获取学校编码
     *
     * @return school_code - 学校编码
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * 设置学校编码
     *
     * @param schoolCode 学校编码
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * 获取区域
     *
     * @return school_area - 区域
     */
    public String getSchoolArea() {
        return schoolArea;
    }

    /**
     * 设置区域
     *
     * @param schoolArea 区域
     */
    public void setSchoolArea(String schoolArea) {
        this.schoolArea = schoolArea;
    }

    /**
     * 获取省份
     *
     * @return school_province - 省份
     */
    public String getSchoolProvince() {
        return schoolProvince;
    }

    /**
     * 设置省份
     *
     * @param schoolProvince 省份
     */
    public void setSchoolProvince(String schoolProvince) {
        this.schoolProvince = schoolProvince;
    }

    /**
     * 获取城市
     *
     * @return school_city - 城市
     */
    public String getSchoolCity() {
        return schoolCity;
    }

    /**
     * 设置城市
     *
     * @param schoolCity 城市
     */
    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    /**
     * 获取地址
     *
     * @return school_address - 地址
     */
    public String getSchoolAddress() {
        return schoolAddress;
    }

    /**
     * 设置地址
     *
     * @param schoolAddress 地址
     */
    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    /**
     * 获取联系人
     *
     * @return contact_name - 联系人
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系人
     *
     * @param contactName 联系人
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取联系人电话
     *
     * @return contact_number - 联系人电话
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 设置联系人电话
     *
     * @param contactNumber 联系人电话
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 获取0-正常;1-禁用
     *
     * @return school_status - 0-正常;1-禁用
     */
    public Integer getSchoolStatus() {
        return schoolStatus;
    }

    /**
     * 设置0-正常;1-禁用
     *
     * @param schoolStatus 0-正常;1-禁用
     */
    public void setSchoolStatus(Integer schoolStatus) {
        this.schoolStatus = schoolStatus;
    }

    /**
     * 获取备注
     *
     * @return school_descr - 备注
     */
    public String getSchoolDescr() {
        return schoolDescr;
    }

    /**
     * 设置备注
     *
     * @param schoolDescr 备注
     */
    public void setSchoolDescr(String schoolDescr) {
        this.schoolDescr = schoolDescr;
    }

    /**
     * 获取管理员账号
     *
     * @return admin_login - 管理员账号
     */
    public String getAdminLogin() {
        return adminLogin;
    }

    /**
     * 设置管理员账号
     *
     * @param adminLogin 管理员账号
     */
    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
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
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", schoolNickname=").append(schoolNickname);
        sb.append(", schoolCode=").append(schoolCode);
        sb.append(", schoolArea=").append(schoolArea);
        sb.append(", schoolProvince=").append(schoolProvince);
        sb.append(", schoolCity=").append(schoolCity);
        sb.append(", schoolAddress=").append(schoolAddress);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactNumber=").append(contactNumber);
        sb.append(", schoolStatus=").append(schoolStatus);
        sb.append(", schoolDescr=").append(schoolDescr);
        sb.append(", adminLogin=").append(adminLogin);
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
