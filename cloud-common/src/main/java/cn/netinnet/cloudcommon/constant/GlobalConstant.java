package cn.netinnet.cloudcommon.constant;


/**
 * 全局常量
 * @author chen.wb
 * @version V1.0
 * @date 2017-11-30
 **/
public class GlobalConstant {

    public static final String PROJECT_CODE = "workflow";
    /** 请求成功 */
    public static final int SUCCESS = 200;
    /** 请求失败 */
    public static final int FAILURE = -1;

    public static final int SERVER_INTERNAL_ERROR = 500;
    /** session 会话过期 */
    public static final int SESSION_TIMEOUT = -501;
    public static final int SECRET_KEY_ERR_CODE = 201;
    public static final int REQUEST_PARAM_EXCEPTION = 202;

    public static final String SUCCESS_MSG = "操作成功！";
    public static final String FAILURE_MSG = "操作失败！";
    public static final String SESSION_TIMEOUT_MSG = "会话已过期，请重新登录";
    public static final String SECRET_KEY_ERR_MSG = "秘钥错误";
    public static final String REQUEST_PARAM_EXCEPTION_MSG = "请求参数异常";
    /** 响应的ContentType内容 */
    public static final String CONTENT_TYPE = "application/json";
    /** 响应编码 */
    public static final String CHARACTER_ENCODING = "utf-8";
    /** 图片类型 */
    public static final String IMAGE_TYPE = "png,jpeg,jpg,bmp";
    public static final String DEFAULT_PREVFIX = "thumb_";

    /**
     * 空列表JSON串
     */
    public static final String EMPTY_LIST = "[]";

    /**
     * 企业默认表单id
     */
    public static final long DEF_FORM_TEMPLATE = 100000000000000000L;
    /**
     * 指定执行角色为直接领导，约定id
     */
    public static final long DIRECT_LEADER_ID = 0;

    /**
     * 员工session key
     */
    public static final String SESSION_STAFF = "SESSION_STAFF";
    /**
     * 企业id session key
     */
    public static final String SESSION_COMPANYID = "SESSION_COMPANYID";
    /**
     * 考试信息session key
     */
    public static final String SESSION_EXAM = "SESSION_EXAM";

    /**
     * 表单所在的包路径
     */
    public static final String FORM_PACKAGE_MODULE = "cn.netinnet.workflow.form.domain.";

    /**
     * 表单json数据的key
     */
    public static final String FORM_JSON_KEY = "formJson";

    public static final int INT_50 = 50;

    public static final int ONE = 1;

    public static final int ZERO = 0;

    public static final String CONTENT_TYPE_JSON_WITH_CHARACTER_ENCODING = String.format("%s; charset=%s", CONTENT_TYPE, CHARACTER_ENCODING);

    public static final String EXAM_SUBMIT = "考试已提交！";
    public static final String EXAM_END = "考试已结束！";

    /** 文件分隔符 */
    public static final String FILE_SEPARATOR = "/";

    public final static String STU_QUERY_ANSWER_PERMISSION = "STU_QUERY_ANSWER_PERMISSION";

    public final static String IS_TEACHER_VIEW = "IS_TEACHER_VIEW";

    /** 正保云账号前缀 **/
    public final static String ZBY_USER_PREFIX = "zby_";

    /** 流程未完成 **/
    public final static String INST_NO_COMPLETE = "INST_NO_COMPLETE";
}
