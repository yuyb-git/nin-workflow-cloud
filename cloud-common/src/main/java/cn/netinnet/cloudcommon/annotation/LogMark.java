package cn.netinnet.cloudcommon.annotation;

import java.lang.annotation.*;

/**
 \* @Author: Linjj
 \* @Date: 2019/7/31 16:57
 \* @Description: 日志记录注解
 \*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogMark {

    String value() default "";

}