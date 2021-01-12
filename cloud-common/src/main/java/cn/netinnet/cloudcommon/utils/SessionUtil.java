package cn.netinnet.cloudcommon.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Liyq [Liyq@netinnet.cn]
 * @Date 2017/12/18 9:58
 */
public class SessionUtil<T> {

    private static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request.getSession();
    }

    public static <T> void setSessionAttr(HttpSession session, String name, T t){
        session.setAttribute(name, t);
    }

    public static <T> T getSessionAttr(HttpSession session, String name, T t){
        Object val = session.getAttribute(name);
        if(val == null){
            return t;
        }
        return (T) val;
    }

    public static Integer getSessionAttr(String name, Integer defaultValue){
        HttpSession session = getSession();
        return Integer.valueOf(getSessionAttr(session, name, defaultValue));
    }
    public static Integer getSessionAttr(String name){
        return Integer.valueOf(getSessionAttr(name, 0));
    }

    public static Long getSessionAttrLong(HttpSession session, String name){
        return Long.valueOf(getSessionAttr(session, name, 0L));
    }

    public static Long getSessionAttrLong(HttpSession session, String name, Long defaultValue){
        return Long.valueOf(getSessionAttr(session, name, defaultValue));
    }

    public static Long getSessionAttr(String name, Long defaultValue){
        HttpSession session = getSession();
        return getSessionAttrLong(session, name, defaultValue);
    }

    public static Integer getSessionAttrInt(HttpSession session, String name, Integer defaultValue){
        return Integer.valueOf(getSessionAttr(session, name, defaultValue));
    }

    public static Integer getSessionAttr(HttpSession session, String name){
        return Integer.valueOf(getSessionAttr(session, name, 0));
    }

    public static String getSessionAttrStr(String name, String defaultValue){
        HttpSession session = getSession();
        return String.valueOf(getSessionAttr(session, name, defaultValue));
    }

    public static String getSessionAttrStr(HttpSession session, String name, String defaultValue){
        return String.valueOf(getSessionAttr(session, name, defaultValue));
    }

    public static String getSessionAttrStr(HttpSession session, String name){
        return String.valueOf(getSessionAttr(session, name, ""));
    }

    public  static  void  setSession(String name,Object val){
        HttpSession session = getSession();
        session.setAttribute(name,val);
    }


    /**
     * 设置session
     * @param session
     * @param name
     * @param val
     */
    public  static  void  setSession(HttpSession session, String name,Object val){
        session.setAttribute(name,val);
    }
}
