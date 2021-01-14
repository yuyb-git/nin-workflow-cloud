package cn.netinnet.cloudcommon.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author pan.tao
 * @version V1.0
 * @Description: request工具类
 * @Date 2017-11-30
 **/
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        // HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        return request;
    }


    public static String getParameter(HttpServletRequest request, String key) {
        String parameter = request.getParameter(key);
        return StringUtils.isBlank(parameter) ? "" : parameter;
    }

    public static String getParameter(String key) {
        String parameter = getRequest().getParameter(key);
        return StringUtils.isBlank(parameter) ? "" : parameter;
    }

    public static String getParameter(HttpServletRequest request, String key, String defaultStr) {
        String parameter = request.getParameter(key);
        return StringUtils.isBlank(parameter) ? defaultStr : parameter;
    }


    public static String getParameter(String key, String defaultStr) {
        String parameter = getRequest().getParameter(key);
        return StringUtils.isBlank(parameter) ? defaultStr : parameter;
    }
    /**
     * 获取远程地址
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        //多次反向代理后会有多个ip值，第一个ip才是真实ip
        int index = ip.indexOf(",");
        if(index != -1){
            ip = ip.substring(0,index);
        }
        return ip;
    }

    /**
     * @author liyq liyq@netinnet.cn
     * @date 2017/12/15 13:28
     * @param ipAddress
     * @return
     * @Description 将ip地址转为long类型
     */
    public static long ipToLong(String ipAddress) {
        Long ipLong = 0L;
        String[] ipNumbers = ipAddress.split("\\.");
        for (String ipNumber : ipNumbers) {
            ipLong = ipLong << 8 | Integer.parseInt(ipNumber);
        }
        return ipLong;
    }

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name, String defaultValue) {
        Cookie cookie = getCookieObject(request, name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public static Integer getCookieToInt(HttpServletRequest request, String name, Integer defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public static Long getCookieToLong(HttpServletRequest request, String name) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public static Long getCookieToLong(HttpServletRequest request, String name, Long defaultValue) {
        String result = getCookie(request, name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public static Cookie getCookieObject(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    /**
     * Get all cookie objects.
     */
    public static Cookie[] getCookieObjects(HttpServletRequest request) {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

}
