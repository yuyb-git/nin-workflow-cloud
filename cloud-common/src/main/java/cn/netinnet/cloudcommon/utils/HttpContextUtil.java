package cn.netinnet.cloudcommon.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 \* @Author: Linjj
 \* @Date: 2019/7/31 17:07
 \* @Description: 获取request/response/session
 \*/
public class HttpContextUtil {

    public HttpContextUtil() {
    }

    /**
     * @Author Linjj
     * @Date 2019/7/31 17:08
     * @Description 获取当前request
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request;
    }

    /**
     * @Author Linjj
     * @Date 2019/7/31 17:08
     * @Description 获取当前response
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        return response;
    }

    /**
     * @Author Linjj
     * @Date 2019/7/31 17:08
     * @Description 获取当前session
     */
    public static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpSession session = ((ServletRequestAttributes)requestAttributes).getRequest().getSession();
        return session;
    }

    /**
     * @Author Linjj
     * @Date 2019/8/23 9:09
     * @Description 获取request参数
     */
    public static Map<String, String> getPara2Map(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        HashMap params = new HashMap();
        // 获取所有参数
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String[] value = request.getParameterValues(name);
            if (value == null || value.length == 0) {
                params.put(name, "");
            } else if (value.length == 1) {
                params.put(name, value[0]);
            } else {
                params.put(name, value);
            }
        }
        return params;
    }
}