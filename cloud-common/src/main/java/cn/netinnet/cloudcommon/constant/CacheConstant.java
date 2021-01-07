package cn.netinnet.cloudcommon.constant;

/**
 * @author chen.wb
 * @description:缓存常量(定义为接口，其中的属性默认为常量)
 * @date 2018/1/17 15:12
 **/
public interface CacheConstant {
    /**
     * 全局缓存前缀
     */
    String PREFIX = "nin_workflow:";
    /**
     * 全局通用的redis缓存前缀
     */
    String GLOBAL = "global:";

    /************************************ redis缓存key start ************************************/

    /**
     * 用于验证当前userId对应的token，判断是否已被他人踢出； 0 -> userId； 值 -> token
     */
    String R_USERID_TOKEN = PREFIX + "login:{0}";
    /**
     * 存在refreshToken,key-value:token->refresh
     */
    String R_TOKEN_REFRSHTOKEN = PREFIX + "token:refreshToken:{0}";
    /**
     * 缓存学校状态key
     */
    String R_SCHOOL_STATUS=PREFIX+"schoolStatus:{0}";
    /**
     * 临时存放重新签发的新token。0 -> refreshToken; 值 -> newToken
     */
    String R_TOKEN_BACKLIST = PREFIX + "tokenBacklist:{0}";
    /**
     * shiro缓存key。0 -> userId
     */
    String R_SHIRO_CACHE = PREFIX + "shiro:{0}";
    /**
     * 表单信息缓存
     */
    String FORM_INFO_CACHE = PREFIX + "form_info:";
    /**
     * 转填缓存
     */
    String FORM_CONVERTER_CACHE = PREFIX + "form_converter:";
    /**
     * 表单模板数据
     */
    String FORM_TEMPLATE_CACHE = GLOBAL + "form_template";
    /**
     * 表单
     */
    String FORM_TABLE_STRUCTURE = PREFIX + "form_table_structure:";
    /**
     * 表单分类
     */
    String FORM_TYPE = PREFIX + "form_type:";
    /**
     * 表单考试判分字段
     */
    String FORM_EXAM_COLUMN = GLOBAL + "form_exam_column:";
    /**
     * 组织架构树形结构
     */
    String DEPT_TREE = PREFIX + "dept_tree:";

    /**
     * 系统数据类型
     */
    String SYS_DATA_TYPE = GLOBAL + "sys_data_type:";
    /**
     * 系统数据选项
     */
    String SYS_DATA_ITEM = GLOBAL + "sys_data_item:";

    /**
     * 表单模板子项
     */
    String FORM_TEMPLATE_ITEM = GLOBAL + "template_item";

     /**
    * 线条条件表达式
    */
    String FLOW_CONDITION = GLOBAL + "condition:";

    /**
     *  表单字段
     */
    String FORM_COLUMNS = GLOBAL + "column:";

    String FORM_COLUMNS_CMAL = GLOBAL + "column_cmal:";

    /**
     *  考试结束时间
     */
    String EXAM_END_TIME = PREFIX + "exam_end_time:";

    /**
    *  答案定义流程分类
    */
    String ANS_PROC_TYPE = PREFIX + "ans_proc_type:";

    /**
     *  答案定义流程
     */
    String ANS_PROC_DEF = PREFIX + "ans_proc_def:";

    /**
     *  答案企业
     */
    String ANS_COMPANY = PREFIX + "ans_company:";

    /**
     *  系统参数
     */
    String SYS_PARAMS = PREFIX + "sys_params";

    /**
     *  流程线条
     */
    String DEF_FLOW = PREFIX + "def_flow:";

    /**
     *  转填关系
     */
    String TRANSFER_REL = PREFIX + "transfer_flow:";

    /**
     *  流程节点
     */
    String DEF_NODE_MAP = PREFIX + "def_node_map:";

    /**
     *  企业自定义表单
     */
    String COMPANY_FORMS = PREFIX + "company_forms";

    /************************************ redis缓存key  end  ************************************/
}
