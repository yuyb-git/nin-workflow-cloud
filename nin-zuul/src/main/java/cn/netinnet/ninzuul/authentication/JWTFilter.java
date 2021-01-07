package cn.netinnet.ninzuul.authentication;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Component
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private final static Logger LOG = LoggerFactory.getLogger(JWTFilter.class);
    /**
     * 后端免认证接口 url
     **/
    public static String anonUrl;
    /**
     * 静态变量通过set方法注入
     **/
    @Value("${apigateway.shiro.anonUrl}")
    public void setAnonUrl(String anonUrl) {
        JWTFilter.anonUrl = anonUrl;
    }
    /**
     * 匹配url路径
     **/
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * @Author Linjj
     * @Date 2019/8/14 14:32
     * @Description 校验url是否允许直接访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String[] anonUrls = anonUrl.replace(" ", "").split(",");
        // 对请求的路径进行校验
        String url = httpServletRequest.getServletPath();
        // 匹配是否为后端免认证接口
        for (String anonUrl : anonUrls) {
            if (pathMatcher.match(anonUrl, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 14:32
     * @Description 直接访问失败后，进行shiro认证处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //是否成功认证
        boolean loggedIn = false;
        if (isLoginAttempt(request, response)) {
            loggedIn = executeLogin(request, response);
        }
        if (!loggedIn) {
            sendChallenge(request, response);
        }
        return loggedIn;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 14:32
     * @Description 查看是否含有token，有则说明企图认证
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(JWTUtil.ACCESS_TOKEN_KEY);
        return StringUtils.isNotBlank(token);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 14:33
     * @Description 将token注入shiro，提交认证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(JWTUtil.ACCESS_TOKEN_KEY);
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/14 14:28
     * @Description 认证失败时的处理
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        throw new AuthenticationException("认证失败");
    }

    /**
     * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", httpServletRequest.getMethod());
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
