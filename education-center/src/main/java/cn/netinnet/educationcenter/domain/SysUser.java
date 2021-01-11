/*
 * SysUser.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2020-05-12 Created
 */
package cn.netinnet.educationcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2020-05-12
 **/
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    private Integer userType;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 账号
     */
    private String userLogin;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 昵称
     */
    private String userNickname;

    /**
     * 性别,0-女;1-男;2-保密
     */
    private Integer userSex;

    /**
     * 生日
     */
    private String userBirthday;

    /**
     * 手机
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userMail;

    /**
     * 入学年份
     */
    private String userGrade;

    /**
     * 用户状态,0-正常;1-禁用
     */
    private Integer userStatus;

    /**
     * 学校id
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学院id
     */
    private Long collegeId;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 专业id
     */
    private Long majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 第三方id
     */
    private Long individualityId;

    /**
     * 创建行政班权限,0-无权限;1-有权限
     */
    private Integer createClassPermission;

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
     * 逻辑删除标识
     */
    private Integer delFlag;

    private static final long serialVersionUID = 2698096176573805568L;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     *
     * @return user_type - 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     *
     * @param userType 用户类别,0-后台管理员;1-学校管理员;2-教师;3-学生
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取角色编码
     *
     * @return role_code - 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     *
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取账号
     *
     * @return user_login - 账号
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * 设置账号
     *
     * @param userLogin 账号
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return user_pwd - 密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置密码
     *
     * @param userPwd 密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取昵称
     *
     * @return user_nickname - 昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置昵称
     *
     * @param userNickname 昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * 获取性别,0-女;1-男;2-保密
     *
     * @return user_sex - 性别,0-女;1-男;2-保密
     */
    public Integer getUserSex() {
        return userSex;
    }

    /**
     * 设置性别,0-女;1-男;2-保密
     *
     * @param userSex 性别,0-女;1-男;2-保密
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    /**
     * 获取生日
     *
     * @return user_birthday - 生日
     */
    public String getUserBirthday() {
        return userBirthday;
    }

    /**
     * 设置生日
     *
     * @param userBirthday 生日
     */
    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * 获取手机
     *
     * @return user_phone - 手机
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置手机
     *
     * @param userPhone 手机
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取邮箱
     *
     * @return user_mail - 邮箱
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * 设置邮箱
     *
     * @param userMail 邮箱
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * 获取入学年份
     *
     * @return user_grade - 入学年份
     */
    public String getUserGrade() {
        return userGrade;
    }

    /**
     * 设置入学年份
     *
     * @param userGrade 入学年份
     */
    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    /**
     * 获取用户状态,0-正常;1-禁用
     *
     * @return user_status - 用户状态,0-正常;1-禁用
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态,0-正常;1-禁用
     *
     * @param userStatus 用户状态,0-正常;1-禁用
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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
     * 获取学院id
     *
     * @return college_id - 学院id
     */
    public Long getCollegeId() {
        return collegeId;
    }

    /**
     * 设置学院id
     *
     * @param collegeId 学院id
     */
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * 获取学院名称
     *
     * @return college_name - 学院名称
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * 设置学院名称
     *
     * @param collegeName 学院名称
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * 获取专业id
     *
     * @return major_id - 专业id
     */
    public Long getMajorId() {
        return majorId;
    }

    /**
     * 设置专业id
     *
     * @param majorId 专业id
     */
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    /**
     * 获取专业名称
     *
     * @return major_name - 专业名称
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * 设置专业名称
     *
     * @param majorName 专业名称
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    /**
     * 获取班级id
     *
     * @return class_id - 班级id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取班级名称
     *
     * @return class_name - 班级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置班级名称
     *
     * @param className 班级名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取第三方id
     *
     * @return individuality - 第三方id
     */
    public Long getIndividualityId() {
        return individualityId;
    }

    /**
     * 设置第三方id
     *
     * @param individualityId 第三方id
     */
    public void setIndividuality(Long individualityId) {
        this.individualityId = individualityId;
    }

    /**
     * 获取创建行政班权限,0-无权限;1-有权限
     *
     * @return create_class_permission - 创建行政班权限,0-无权限;1-有权限
     */
    public Integer getCreateClassPermission() {
        return createClassPermission;
    }

    /**
     * 设置创建行政班权限,0-无权限;1-有权限
     *
     * @param createClassPermission 创建行政班权限,0-无权限;1-有权限
     */
    public void setCreateClassPermission(Integer createClassPermission) {
        this.createClassPermission = createClassPermission;
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
     * 获取逻辑删除标识
     *
     * @return del_flag - 逻辑删除标识
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除标识
     *
     * @param delFlag 逻辑删除标识
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
        sb.append(", userId=").append(userId);
        sb.append(", userType=").append(userType);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", userLogin=").append(userLogin);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", userNickname=").append(userNickname);
        sb.append(", userSex=").append(userSex);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userMail=").append(userMail);
        sb.append(", userGrade=").append(userGrade);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", collegeId=").append(collegeId);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", majorId=").append(majorId);
        sb.append(", majorName=").append(majorName);
        sb.append(", classId=").append(classId);
        sb.append(", className=").append(className);
        sb.append(", individualityId=").append(individualityId);
        sb.append(", createClassPermission=").append(createClassPermission);
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
