package cn.netinnet.cloudcommon.constant;

/**
 * @Author Linjj
 * @Date 2019/7/8 11:25
 * @Description 错误信息常量
 */
public interface ErrorMsgConstant {

    String COMPANY_TIMEOUT = "未选择企业或已过期，请重新选择企业！";
    /**
     * 用户名称为空
     */
    String USER_NAME_NULL = "用户名称不可为空！";
    /**
     *用户名称长度
     */
    String USER_NAME_LENGTH = "用户名称长度应在1-15位之间！";
    /**
     *用户账号为空
     */
    String USER_LOGIN_NULL = "用户账号不可为空！";
    /**
     *用户账号长度
     */
    String USER_LOGIN_LENGTH = "用户账号长度应在1-16位之间！";
    /**
     *用户密码为空
     */
    String USER_PWD_NULL = "用户密码不能为空！";
    /**
     *用户密码长度
     */
    String USER_PWD_LENGTH = "用户密码长度应在6-16位之间！";
    /**
     *用户手机号长度
     */
    String USER_PHONE_LENGTH = "用户手机号长度应为11位！";
    /**
     *用户账号字符组成
     */
    String USER_LOGIN_CHAR = "用户账号只能有字符和数字！";
    /**
     *用户组号为空
     */
    String GROUP_SEQ_NULL = "用户组号不能为空！";
    /**
     *用户组号正整数
     */
    String GROUP_SEQ_NUMBER = "用户组号只能是正整数！";
    /**
     *Excel格式
     */
    String EXCEL_FORMAT = "上传文件需为Excel格式";
    /**
     *zip格式
     */
    String ZIP_FORMAT = "上传文件需为zip格式";
    /**
     *Excel数量限制
     */
    String EXCEL_OUT_SIZE = "上传人数超过限制，不可超过一千条";
    /**
     *Excel模板错误
     */
    String EXCEL_TEMPLATE_ERR = "Excel模板错误";
    /**
     *JSON错误
     */
    String JSON_ERR = "JSON解析错误";
    /**
     *J备注长度
     */
    String SCHOOL_DESCR_ERR = "备注长度应在1-100位之间！";
    /**
     *学校ID不存在
     */
    String SCHOOL_SCHOOLID_NOTFOUND = "学校ID不存在";

    String NODE_NO_FOUNT = "找不到当前任务对应的节点数据！";

}
