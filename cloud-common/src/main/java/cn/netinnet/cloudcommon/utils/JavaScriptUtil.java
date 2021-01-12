package cn.netinnet.cloudcommon.utils;

import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * 在Java中执行JavaScript脚本
 * */
public class JavaScriptUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaScriptUtil.class);

    private static final Pattern P = Pattern.compile("\\{([^\\{\\}]*)\\}");

    private static final Pattern NO_CONTAIN_P = Pattern.compile("(\\.indexOf\\(')(.*?)('\\)==-1)");

    private static final Pattern CONTAIN_P = Pattern.compile("(\\.indexOf\\(')(.*?)('\\)!=-1)");

    public static final String CONTAIN = "⊆";
    public static final String NO_CONTAIN = "⊄";

    private JavaScriptUtil() {}

    /**
     * @Description 正则表达式截取变量
     * @param       expression
     * @author      yuyb
     * @return      Map<String, String>
     * @date        2020/5/6 9:39
     **/
    public static Map<String, String> getVariableRgex(String expression){
        Map<String, String> columnsMap = new HashMap<>(3);
        // 匹配的模式
        Matcher m = P.matcher(expression);
        while (m.find()) {
            int i = 1;
            String fullVariable = m.group(i);
            String[] variableArr = fullVariable.split("\\.");
            String formId = variableArr[0];
            String variable = variableArr[1];
            if(columnsMap.containsKey(formId)){
                String variableJoin = columnsMap.get(formId);
                if(!variableJoin.contains(variable)){
                    variableJoin += "," + variable;
                    columnsMap.put(formId, variableJoin);
                }
            }else{
                columnsMap.put(formId, variable);
            }
            i++;
        }
        return columnsMap;
    }

    /**
     * 执行JavaScript脚本
     * */
    public static Object eval(String js) {
        Object result;
        try {
            result = ScriptEngineHolder.nashorn.eval(js);
            LOGGER.info("eval: {} = {}", js, result);
        } catch (ScriptException e) {
            e.printStackTrace();
            LOGGER.error("eval {} fail", js, e.getCause());
            throw new CustomException(e.toString(), ResultEnum.R_INTERNAL_SERVER_ERROR.getCode(), e.getCause());
        }
        return result;
    }

    static class ScriptEngineHolder {
        static ScriptEngine nashorn;
        static {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            nashorn = scriptEngineManager.getEngineByName("nashorn");
        }
    }

    /***
    * 将indexOf替换为⊆、⊄
    * @author ousp
    * @date 2020/9/18
    * @return java.lang.String
    */
    public static String indexOfToContain(String str, String containStr) {
        Matcher m = null;
        if (NO_CONTAIN.equals(containStr)) {
            m = NO_CONTAIN_P.matcher(str);
        } else if (CONTAIN.equals(containStr)){
            m = CONTAIN_P.matcher(str);
        } else {

        }
        StringBuffer sb = new StringBuffer();
        while(m.find()) {
            m.appendReplacement(sb, containStr + "(" + m.group(2) + ")");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
