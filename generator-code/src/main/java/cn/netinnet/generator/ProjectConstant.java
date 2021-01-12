package cn.netinnet.generator;

/**
 * 项目常量
 * @author Administrator
 */
public final class ProjectConstant {
    public static final String MOUDLE = "process-center";
    public static final String MOUDLE_PACKAGE = "processcenter";

    public static final String COMMON_PACKAGE = "cn.netinnet.cloudcommon";


    /**
     * 项目基础包名称，根据自己公司的项目修改
     */
    public static final String BASE_PACKAGE = "cn.netinnet.common";
    public static final String PACKAGE = "cn.netinnet.generator";
    /**
     * 项目基础包名称，根据自己公司的项目修改
     */
    public static final String BASE_PACKAGE_MODULE = "cn.netinnet."+MOUDLE_PACKAGE;
    /**
     * Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE_MODULE + ".domain";
    //
    /**
     * Mapper所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE_MODULE + ".dao";
    /**
     * Service所在包
     */
    public static final String SERVICE_PACKAGE = BASE_PACKAGE_MODULE + ".service";
    /**
     * ServiceImpl所在包
     */
    public static final String SERVICE_IMPL_PACKAGE = BASE_PACKAGE_MODULE + ".service" + ".impl";
    /**
     * Controller所在包
     */
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE_MODULE + ".controller" +"" ;
    /**
     *Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE_MODULE + ".base";
}