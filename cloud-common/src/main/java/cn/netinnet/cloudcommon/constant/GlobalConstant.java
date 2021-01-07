package cn.netinnet.cloudcommon.constant;


/**
 * 全局常量
 * @author chen.wb
 * @version V1.0
 * @date 2017-11-30
 **/
public class GlobalConstant {

    public static final String PROJECT_CODE = "ssc";
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
     * 表单json数据的key
     */
    public static final String FORM_JSON_KEY = "formJson";

    public static final int INT_50 = 50;

    public static final int ONE = 1;

    public static final int ZERO = 0;

    /** 文件分隔符 */
    public static final String FILE_SEPARATOR = "/";

}
