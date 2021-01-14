package cn.netinnet.cloudcommon.globol;

import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.exception.ExpiredAccountException;
import cn.netinnet.cloudcommon.exception.RepeatSubmitException;
import cn.netinnet.cloudcommon.utils.RequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Description @description: 局部异常处理类 —— 用来捕获Controller层异常（包含被Controller层调用的接口抛出的异常）
 *  * （因ControllerAdvice只能通知Controller层，如果程序还未进入到Controller内抛出的异常，该异常处理类无法捕捉处理。
 *  *   如：filter过滤器,interceptor拦截器等方法都还未进入Controller，则无法拦截）
 * @Author yuyb
 * @Date 2020/4/27 14:11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理未被指定的异常
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResultEntry defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        JSONObject errorData = new JSONObject();
        errorData.put("message", (e.getMessage() == null) ? e : e.getMessage());
        errorData.put("stackTrace", e.getStackTrace());
        errorData.put("exception", e);
        errorData.put("url", req.getRequestURL());
        logger.error("请求人ip：{}", RequestUtil.getRemoteAddr(req));
        logger.error("错误信息：{}", e.getMessage());
        logger.error("错误地址：{}", req.getRequestURL());
        logger.error("错误日志：{}", (Object) e.getStackTrace());
        logger.error("错误详情：", e);
        return HttpResultEntry.customize(ResultEnum.R_INTERNAL_SERVER_ERROR, errorData);
    }

    /**
     * 自定义异常类，返回json格式
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {CustomException.class})
    public HttpResultEntry jsonErrorHandler(HttpServletRequest req, CustomException e) throws Exception {
        return HttpResultEntry.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * @Description 重复提交异常处理（不返回提示信息）
     */
    @ExceptionHandler(value = {RepeatSubmitException.class})
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void repeatSubmitExceptionHandler(HttpServletRequest request, RepeatSubmitException e) throws Exception{
        logger.info(e.getMessage() + "[url -> {}]", request.getServletPath());
    }

    /**
     * @Description 权限控制异常处理
     */
    @ExceptionHandler(value = {AuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object authorizationExceptionHandler(HttpServletRequest request, AuthorizationException e) throws Exception{
        logger.info("{}=>权限校验失败：" + request.getServletPath(), RequestUtil.getRemoteAddr(request));
        logger.debug(e.getMessage());
        return HttpResultEntry.customize(ResultEnum.R_FORBIDDEN);
    }


    /**
     * @Description 认证异常处理
     */
    @ExceptionHandler(value = {AuthenticationException.class, ServletException.class, IncorrectCredentialsException.class, ExpiredAccountException.class, ExpiredCredentialsException.class, ConcurrentAccessException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object authenticationExceptionHandler(HttpServletRequest request, Exception e) throws Exception{
        // 返回信息
        HttpResultEntry resultEntry = HttpResultEntry.customize(ResultEnum.R_AUTHENTICATION);
        // 获取异常的根源触发异常（因shiro会将异常置为ServletException,ErrorController再将其置为AuthenticationException）
        Throwable rootCause = e;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        // 过期的凭据异常(请求刷新token，除了这个异常，其他都要重新登录)
        if (rootCause instanceof ExpiredCredentialsException){
            resultEntry = HttpResultEntry.customize(ResultEnum.R_TOKEN_EXPIRED);
        }
        // 错误的凭据异常
        else if (rootCause instanceof IncorrectCredentialsException){
            resultEntry = HttpResultEntry.customize(ResultEnum.R_AUTHENTICATION);
        }
        // 一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许异地登录
        else if (rootCause instanceof ConcurrentAccessException){
            resultEntry = HttpResultEntry.customize(ResultEnum.R_REFRESH_TOKEN_FAILURE);
        }
        // 用户信息已失效或者已被踢出
        else if (rootCause instanceof ExpiredAccountException){
            resultEntry = HttpResultEntry.customize(ResultEnum.R_REFRESH_TOKEN_FAILURE);
        }
        // 其他的认证异常
        else if (rootCause instanceof AuthenticationException){
            resultEntry = HttpResultEntry.customize(ResultEnum.R_AUTHENTICATION);
        }
        // 非该异常处理方法可以处理的，将其抛出
        else {
            throw new Exception(e);
        }
        logger.info("{}认证失败：\n" + rootCause, RequestUtil.getRemoteAddr(request));
        return resultEntry;
    }

    /**
     * 缺少必要参数
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public HttpResultEntry requiredParamMissedHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        logger.error(ResultEnum.R_MISSED_REQUIRED_PARAM.getMsg(), e);
        return HttpResultEntry.error(ResultEnum.R_MISSED_REQUIRED_PARAM.getCode(), ResultEnum.R_MISSED_REQUIRED_PARAM.getMsg());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public HttpResultEntry paramMatchHandler(MethodArgumentTypeMismatchException e, HttpServletRequest req) {
        logger.error(ResultEnum.R_PARAM_NOT_MATCH.getMsg(), e);
        return HttpResultEntry.error(ResultEnum.R_PARAM_NOT_MATCH.getCode(), ResultEnum.R_PARAM_NOT_MATCH.getMsg());
    }

    /**
     * @Description 参数校验异常处理
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public HttpResultEntry validExceptionHandler(HttpServletRequest request, BindException e) throws Exception{
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        StringBuilder errMsg = new StringBuilder();
        for (FieldError error : errorList) {
            errMsg.append(error.getDefaultMessage()).append("\n");
        }
        String errMsgStr = errMsg.toString();
        logger.info("[url -> {}参数校验失败]:\n" + errMsgStr, request.getServletPath());
        return HttpResultEntry.error(ResultEnum.R_PARAM.getCode(), errMsgStr);
    }

}
