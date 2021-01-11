package cn.netinnet.cloudcommon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * \* @Author: Linjj
 * \* @Date: 2019/7/8 11:48
 * \* @Description: 正则表达式工具类
 * \
 */
public class RegUtil {
    /**
     * 整数
     */
    public static final String REGEX_INTEGER = "^-?[1-9]\\d*$";
    /**
     * 数字
     */
    public static final String REGEX_NUMBER = "^([+-]?)\\d*\\.?\\d+$";
    /**
     * 正整数
     */
    public static final String REGEX_INTEGER_POS = "^[1-9]\\d*$";
    /**
     * 浮点数
     */
    public static final String REGEX_FLOAT = "^([+-]?)\\d*\\.\\d+$";
    /**
     * 正浮点数
     */
    public static final String REGEX_FLOAT_POS = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
    /**
     * 字母
     */
    public static final String REGEX_LETTER = "^[A-Za-z]+$";
    /**
     * 字母数字
     */
    public static final String REGEX_LETTER_NUM = "^[a-zA-Z0-9]+$";

    /**
     * @Author Linjj
     * @Date 2019/7/8 13:41
     * @Description 匹配 - true , 不匹配 - false
     */
    public static final boolean isMatch(String regex, CharSequence content) {
        boolean flag = false;
        if (null != content) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * @Author Linjj
     * @Date 2019/7/8 13:41
     * @Description 不匹配 - true , 匹配 - false
     */
    public static final boolean isNotMatch(String regex, CharSequence content) {
        return !isMatch(regex, content);
    }

}
