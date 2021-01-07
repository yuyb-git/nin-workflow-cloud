package cn.netinnet.cloudcommon.constant;

/**
 * @Author Linjj
 * @Date 2019/6/28 17:42
 * @Description 角色编码常量，用于权限控制
 */
public interface RoleConstant {
    /** 学生 */
    String STUDENT = "student";
    /**
     * 普通教师
     */
    String TEACHER = "teacher";
    /**
     * 无行政班权限的教师
     */
    String TEACHER_NOCLASS = "teacher_noclass";
    /**
     * 出题备课教师
     */
    String TEACHER_PREPARE_LESSONS = "teacher_prepare_lessons";
    /** 学校管理员 */
    String MANAGER = "manager";
    /** 超级管理员 */
    String ADMIN = "admin";
}
