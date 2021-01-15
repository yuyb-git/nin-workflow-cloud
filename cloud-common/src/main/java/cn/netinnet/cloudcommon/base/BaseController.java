package cn.netinnet.cloudcommon.base;

import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.utils.RequestUtil;
import cn.netinnet.cloudcommon.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Author Liyq [Liyq@netinnet.cn]
 * @Date 2017/12/4 14:47
 */
public abstract class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * @Author yuyb
     * @Description 获取配置的bpmn资源路径
     * @Date 10:13 2020/4/10
     * @param request
     * @return java.lang.String
     **/
    public String generateBpmnPath(HttpServletRequest request){
        String bpmnPath = (String)request.getServletContext().getAttribute("bpmnPath");
        if(!bpmnPath.endsWith(GlobalConstant.FILE_SEPARATOR)) {
            bpmnPath += GlobalConstant.FILE_SEPARATOR;
        }
        return bpmnPath;
    }

    /**
     * @Author liyq liyq@netinnet.cn
     * @Date 2017/12/6 14:48
     * @param request
     * @return
     * @Description 获取所有的请求参数
     */
    public Map<String, String> getPara2Map(HttpServletRequest request){
        Enumeration<String> names = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            LOG.debug("get parameter : "+name+"="+value);
            params.put(name, value);
        }
        return params;
    }

    public String generateRequestUrl(HttpServletRequest request, String url){
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            url += name + "=" + value + "&";
            LOG.debug("get parameter : "+name+"="+value);
        }
        if(url.endsWith("&")){
            url = url.substring(0, url.length()-1);
        }
        return url;
    }

    public static PageInfo getPage(Supplier<List> supplier){
        String pageStr = RequestUtil.getParameter("page","0");
        Integer page = StringUtil.isBlank(pageStr)?0:Integer.valueOf(pageStr);
        String sizeStr = RequestUtil.getParameter("size");
        if(StringUtil.isNullOrEmpty(sizeStr)) {
            sizeStr =  RequestUtil.getParameter("limit","10");
        }
        Integer size = StringUtil.isBlank(pageStr)?10:Integer.valueOf(sizeStr);
        PageHelper.startPage(page, size);
        List list =  supplier.get();
        return new PageInfo(list);
    }

}
