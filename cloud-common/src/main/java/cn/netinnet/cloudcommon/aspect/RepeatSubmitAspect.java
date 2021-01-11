package cn.netinnet.cloudcommon.aspect;

import cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit;
import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.exception.RepeatSubmitException;
import cn.netinnet.cloudcommon.utils.HttpContextUtil;
import cn.netinnet.cloudcommon.utils.JWTUtil;
import cn.netinnet.cloudcommon.utils.RedisUtil;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * \* @Author: Linjj
 * \* @Date: 2019/6/25 16:24
 * \* @Description: 防止表单重复提交切面
 * \* @order 标记aop执行顺序，前置代码越小越先执行，后置代码越小越后执行。
 * \*         假定切点（目标方法）为 M，两个AOP对M进行切面编程
 * \*         最终结构将是 A B （前置代码）/ M / B A （后置代码）， 类似栈结构-先进后出
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class RepeatSubmitAspect {
    private final static Logger LOG = LoggerFactory.getLogger(RepeatSubmitAspect.class);
    // 缓存前缀
    private final static String KEY_PREFIX = CacheConstant.PREFIX + "repeat:";

    @Pointcut("@annotation(cn.netinnet.cloudcommon.annotation.PreventRepeatSubmit)")
    public void pointCut() {
    }

    /**
     * @Description 进入请求方法前调用，生成或校验key
     */
    @Before("pointCut() && @annotation(preventRepeatSubmit)")
    public void before(final JoinPoint joinPoint, PreventRepeatSubmit preventRepeatSubmit) {
        //TODO 压测先注释掉，之后放开
        if (preventRepeatSubmit != null) {
            long expireTime = preventRepeatSubmit.expireTime();
            String key = getRepeatKey(joinPoint);
            // 并发中只有第一个请求占有key
            boolean flag = RedisUtil.hasKey(key);
            if (!flag) {
                RedisUtil.set(key, System.currentTimeMillis(), expireTime);
                LOG.debug("生成防重复标记key:[{}]", key);
            } else {
                // 重复提交，则抛出异常；请求完成或发生异常会将key清空。
                throw new RepeatSubmitException();
            }
        }
    }

    /**
     * @Description 请求完成后回调，清除key
     */
    @AfterReturning("pointCut() && @annotation(preventRepeatSubmit)")
    public void doAfterReturning(JoinPoint joinPoint, PreventRepeatSubmit preventRepeatSubmit) {
        // 处理完请求，清除Key
        if (preventRepeatSubmit != null) {
            deleteRepeatKey(joinPoint);
        }
    }

    /**
     * @Description 发生异常后回调，清除key
     */
    @AfterThrowing(pointcut = "pointCut() && @annotation(preventRepeatSubmit)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e, PreventRepeatSubmit preventRepeatSubmit) {
        if (preventRepeatSubmit != null && e instanceof RepeatSubmitException == false) {
            //处理处理重复提交本身之外的异常
            deleteRepeatKey(joinPoint);
        }
    }

    /**
     * @Description 移除Key
     */
    private void deleteRepeatKey(JoinPoint joinPoint) {
        String key = getRepeatKey(joinPoint);
        // 并发中只有第一个请求占有key
        boolean flag = RedisUtil.hasKey(key);
        if (flag) {
            RedisUtil.del(key);
            LOG.debug("请求完成or发生异常，移除防重复标记key:[{}]", key);
        }
    }

    /**
     * @Description 获取Key（prefix + token + className$methodName）
     */
    private String getRepeatKey(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtil.getRequest();
        String token = request.getHeader(JWTUtil.ACCESS_TOKEN_KEY);
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key = new StringBuilder(KEY_PREFIX);
        key.append(token).append(":").append(className).append("$").append(methodName);
        return key.toString();
    }
}
