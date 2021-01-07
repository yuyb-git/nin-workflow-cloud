package cn.netinnet.cloudcommon.constant;

/**
 * @ClassName ParaConstant
 * @Description 分类常量配置
 * @Author yuyb
 * @Date 2020/5/20 11:54
 */
public interface ParaConstant {

    /************************************ 学校 start ****************************************/

    /**
     * 学校状态 - 正常
     */
    int SCHOOL_STATUS_ACTIVE = 0;
    /**
     * 学校状态 - 禁用
     */
    int SCHOOL_STATUS_FORBIDDEN = 1;

    /************************************ 学校 end ****************************************/

    /************************************ 组织 start ****************************************/
    /**
     * 组织类型 - 学院
     */
    int ORG_TYPE_COLLEGE = 1;
    /**
     * 组织类型 - 专业
     */
    int ORG_TYPE_MAJOR = 2;
    /**
     * 组织类型 - 班级
     */
    int ORG_TYPE_CLASS = 3;
    /************************************ 组织 end ****************************************/

    /************************************ 流程、企业归类 start ****************************************/
    /**
     * 答案类型
     */
    int CATEGORY_ANSWER = 0;
    /**
     * 学生类型
     */
    int CATEGORY_STUDENT = 1;

    /************************************ 流程归类 end ****************************************/

    /**
     * 数据类型-题目类型
     */
    int DATA_QUESTION_TYPE = 0;

    /**
     * 数据类型-行业
     */
    int DATA_INDUSTRY = 1;

    /***
     * 系统数据字典
     */
    int DICTIONARY_SYSTEM = 0;

    /***
     * 用户数据字典
     */
    int DICTIONARY_USER = 1;

    /**
     * 正常
     */
    int DEL_FLAG_ZERO = 0;
    /**
     * 已删
     */
    int DEL_FLAG_ONE = 1;

    /*************************************批次 start **********************************************/
    /**
     * 批次状态-新建
     */
    int BATCH_STATUS_NEW = 0;
    /**
     * 批次状态-已开始
     */
    int BATCH_STATUS_START = 1;
    /**
     * 批次状态-暂停中
     */
    int BATCH_STATUS_PAUSE = 2;
    /**
     * 批次状态-结束
     */
    int BATCH_STATUS_END = 3;
    /*************************************批次 end **********************************************/

    /**
     * 普通消息
     */
    int MESSAGE_NORMAL = 0;
    /**
     * 审核消息
     */
    int MESSAGE_APPROVAL = 1;
    /**
     * 抄送消息
     */
    int MESSAGE_COPY = 2;
    /**
     * 转填消息
     */
    int MESSAGE_TRANSFER = 3;

    //============================不满足条件时处理方式=========================
    /**
     * 结束流程
     */
    int MISMATCH_END = 1;
    /**
     * 退回起始
     */
    int MISMATCH_START = 2;

    //============================企业分类===================================
    /**
     * 系统企业
     */
    int COMPANY_SYSTEM = 0;
    /**
     * 自建企业-学生
     */
    int COMPANY_STUDENT = 1;
    /**
     * 自建企业-答案
     */
    int COMPANY_ANSWER = 2;
    /**
     * 教师企业
     */
    int COMPANY_TEACHER = 3;

    //========================发起人、执行人类型==============================
    /**
     * 指定执行人
     */
    int ASSIGNEE_STAFF = 1;
    /**
     * 指定执行岗位
     */
    int ASSIGNEE_POSITION = 2;

    //=================================回退方式==============================
    /**
     * 上一步
     */
    int BACK_TO_PREVIOUS = 1;
    /**
     * 发起人
     */
    int BACK_TO_START = 2;

    //=================================通知类型==============================
    /**
     * 通知上一步
     */
    String NOTICE_PREVIOUS = "1";
    /**
     * 通知下一步
     */
    String NOTICE_NEXT = "2";
    /**
     * 通知发起人
     */
    String NOTICE_START = "3";

    /*************************************考试 start **********************************************/
    /**
     * 考试状态-新建
     */
    int EXAM_STATUS_NEW = 0;
    /**
     * 考试状态-已开始
     */
    int EXAM_STATUS_START = 1;
    /**
     * 考试状态-暂停中
     */
    int EXAM_STATUS_PAUSE = 2;
    /**
     * 考试状态-结束
     */
    int EXAM_STATUS_END = 3;
    /*************************************考试 end **********************************************/

    /**
     * 场次来源-系统自建
     */
    int SESSION_FROM_SYSTEM = 0;
    /**
     * 场次来源-考试平台
     */
    int SESSION_FROM_EXAM = 1;
    /**
     * 场次来源-正保云
     */
    int SESSION_FROM_ZBY = 2;

    /**
     * 场次类型-练习
     */
    int SESSION_TYPE_TRAIN = 0;
    /**
     * 场次类型-作业
     */
    int SESSION_TYPE_TASK = 1;
    /**
     * 场次类型-考试
     */
    int SESSION_TYPE_EXAM = 2;

    /*************************************场次 start **********************************************/
    /**
     * 场次状态-新建
     */
    int SESSION_STATUS_NEW = 0;
    /**
     * 场次状态-已开始
     */
    int SESSION_STATUS_START = 1;
    /**
     * 场次状态-结束
     */
    int SESSION_STATUS_END = 2;
    /*************************************场次 end **********************************************/

    /*************************************错误类型 start **********************************************/
    /** 错误类型：0企业名称，1部门，2岗位，3员工，4流程分类，5流程名称，6流程节点，7流程表达式，8传递字段，9表单错误 10流程发起人 11流程执行分支*/
    int ERROR_TYPE_DEPT = 1;
    int ERROR_TYPE_POSITION = 2;
    int ERROR_TYPE_STAFF = 3;
    int ERROR_TYPE_DEF_TYPE = 4;
    int ERROR_TYPE_DEF_NAME = 5;
    int ERROR_TYPE_DEF_NODE = 6;
    int ERROR_TYPE_DEF_EXPRESSION = 7;
    int ERROR_TYPE_DEF_REF = 8;
    int ERROR_TYPE_TABLE = 9;
    int ERROR_TYPE_FORM_JSON = 10;
    int ERROR_TYPE_INST_FLOW = 11;
    /*************************************错误类型 end **********************************************/

    /** 系统试题 */
    int QUESTION_CATEGORY_SYSTEM = 0;
    /** 教师试题 */
    int QUESTION_CATEGORY_TEACHER = 0;

    /*************************************是否允许查看答案**********************************************/
    /** 不允许 */
    int VIEW_ANSWER_NO = 0;
    /** 学生提交后 */
    int VIEW_ANSWER_SUBMIT = 1;
    /** 考试结束后 */
    int VIEW_ANSWER_END = 2;
    /*************************************是否允许查看答案 end **********************************************/

    /** 产品编码 */
    /** 友商A **/
    String PROJECT_CODE_A = "A";

    /** 友商B **/
    String PROJECT_CODE_B = "B";

    /** DIF 综合版 **/
    String PROJECT_CODE_DIF = "DIF";

    /**标准版**/
    String PROJECT_CODE_STANDARD = "STANDARD";

    /** 表单分类 0=系统，1=系统自建，2=用户自建 **/
    int FORM_SYSTEM = 0;
    int FORM_SYSTEM_ADD = 1;
    int FORM_USER_ADD = 2;

    /************************************* 自定义表单字段类型 **********************************************/
    /** 文本 ***/
    int FIELD_DATA_TYPE_TEXT = 0;
    /** 数字 **/
    int FIELD_DATA_TYPE_DECIMAL = 1;
    /************************************* 自定义表单字段类型end **********************************************/

    /************************************* 自定义表单组件属性 **********************************************/
    /** 能传递字段的组件：单行文本、多行文本、单选框、多选框、时间选择 、日期选择、部门、用户、岗位、级联**/
    String FORM_CAN_TRANSFER_COMPONENTS = "input,textarea,radio,checkbox,time,date,select,dep,user,job,cascader";
    /** 栅格 **/
    String FORM_COMPONENT_ROW = "row";
    /** 子表 **/
    String FORM_COMPONENT_CHILDTABLE = "table";
    /** 可以拥有静态数据的组件 **/
    String FORM_COMPONENT_HAS_STATIC_DATA = "radio,checkbox,select,cascader";
    /************************************* 自定义表单组件属性end **********************************************/
}
