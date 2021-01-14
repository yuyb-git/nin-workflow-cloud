package cn.netinnet.cloudcommon.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @Description: 权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequiresPermission {

    String value() default "";

}
