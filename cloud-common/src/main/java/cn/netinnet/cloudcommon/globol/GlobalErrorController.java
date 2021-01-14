package cn.netinnet.cloudcommon.globol;


import cn.netinnet.cloudcommon.exception.ExpiredAccountException;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chen.wb
 * @version V1.0
 * @description: 全局异常处理类
 * @date 2017-11-30
 **/
@RestController
public class GlobalErrorController extends AbstractErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorController.class);

    /**
     * @Author Linjj
     * @Date 2019/9/24 9:19
     * @Description 此为错误接口路径，不是视图路径（对应下方的error方法），
     * spring未被捕捉的意外错误将跳转至/error
     */
    private static final String ERROR_PATH = "/error";

    private final ErrorAttributes errorAttributes;

    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/19 17:50
     * @Description 全局error处理，非特殊错误则输出错误信息
     * 可对filter抛出的异常处理（controller统一异常处理捕捉不到，通过在此处再抛出异常，交由统一异常进行处理）
     */
    @RequestMapping(value = ERROR_PATH)
    public HttpResultEntry error(HttpServletRequest request) {
        // 获取抛出的error/exception
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Throwable throwable = this.errorAttributes.getError(servletWebRequest);
        if (throwable != null) {
            // 获取异常的根源触发异常
            Throwable cause = throwable;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            /*
                因shiro过滤器中身份认证或权限验证时发生的异常不在controller，无法通过@ControllerAdvice捕捉。
                所以触发异常为AuthenticationException或AuthorizationException异常，则抛出异常，
                交于controller统一异常处理。
             */
            if (cause instanceof AuthenticationException) {
                throw new AuthenticationException("认证失败", cause);
            }
            if (cause instanceof AuthorizationException) {
                throw new AuthorizationException(cause);
            }
            if (cause instanceof ConcurrentAccessException) {
                throw new ConcurrentAccessException(cause);
            }
            if (cause instanceof IncorrectCredentialsException) {
                throw new IncorrectCredentialsException(cause);
            }
            if (cause instanceof ExpiredAccountException) {
                throw new ExpiredAccountException(cause);
            }
            if (cause instanceof ExpiredCredentialsException) {
                throw new ExpiredCredentialsException(cause);
            }
        }
        // 其他异常则返回错误信息
        JSONObject errorData = new JSONObject();
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, true);
        errorData.put("message", errorAttributes.get("error"));
        errorData.put("stackTrace", errorAttributes.get("trace"));
        errorData.put("exception", errorAttributes.get("message"));
        errorData.put("url", errorAttributes.get("path"));
        LOG.error("未知错误：{}", errorAttributes.get("trace"));
        return HttpResultEntry.customize(ResultEnum.R_INTERNAL_SERVER_ERROR, errorData);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
