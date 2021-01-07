package cn.netinnet.cloudcommon.dto;

import java.io.Serializable;

/**
 \* @Author: Linjj
 \* @Date: 2019/6/18 11:33
 \* @Description: session用户信息包装类
 \*/
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -6559713976164555635L;

    /**
     * J用户id（学生/教师/管理员）
     */
    private Long userId;
    /**
     * 姓名（学生/教师/管理员）
     */
    private String userName;
    /**
     * 用户类别（学生/教师/管理员）
     */
    private int userType;
    /**
     * 账号（学生/教师/管理员）
     */
    private String userLogin;
    /**
     * 角色编码（学生/教师/管理员）
     */
    private String roleCode;

    /**
     * 学校id（学生/教师）
     */
    private Long schoolId;
    /**
     * 学校名（学生/教师）
     */
    private String schoolName;
    /**
     * 班级id（学生）
     */
    private Long classId;
    /**
     * 班级名（学生）
     */
    private String className;

    public UserInfo() {
    }

    public UserInfo(long userId, String userLogin, String userName, Integer userType, String roleCode) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userName = userName;
        this.userType = userType;
        this.roleCode = roleCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", userLogin='" + userLogin + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }

}
