package cn.netinnet.cloudcommon.annotation;

import java.lang.annotation.*;

/**
 \* @Author: Linjj
 \* @Date: 2019/6/25 16:57
 \* @Description: 防止一次性提交多次的重复请求注解
 \*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PreventRepeatSubmit {
    /**
     * 设置请求锁定过期时间（分）
     * 默认10分钟，防止程序崩溃成死锁
     */
    long expireTime() default 10L;
}
