package cn.netinnet.cloudcommon.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author chen.wb
 * @version V1.0
 * @Description: string工具类
 * @Date 2017-11-30
 **/
public class StringUtil {

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 字符串未找到时的索引
     */
    public static final int INDEX_NOT_FOUND = -1;

    public static final String SIGN_LETTER_NUM = "([0-9]|[a-z]|[A-Z]|[\\-_])";
    public static final char[] LETTER_ARRAY_WITHOUT_ZOIL = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};
    private static final Pattern SPECIAL_STR_PATTERN = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\]\\\\.<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");

    /**
     * 判断指定的字符串是否为空。（null或者空字符串，不包含多个空格或者制表符之类的字符串。）
     *
     * @param _$str 要检测的字符串。
     * @return
     */
    public static boolean isEmpty(String _$str) {
        return null == _$str || 0 == _$str.length();
    }

    /**
     * 判断指定的字符串是否不为空。（null或者空字符串，不包含多个空格或者制表符之类的字符串。）
     *
     * @param _$str 要检测的字符串。
     * @return
     */
    public static boolean isNotEmpty(String _$str) {
        return !isEmpty(_$str);
    }

    /**
     * 检测指定的字符串是否为空白字符。指的 是空格、制表符、回车符、换行符或换页符，则返回 true。
     *
     * @param _$str 要检测的字符串。
     * @return true / false
     */
    public static boolean isBlank(String _$str) {
        // return null == _$str || "".equals( _$str.trim() ) ? true : false ;
        int strLen;
        if (null == _$str || 0 == (strLen = _$str.length())) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((false == Character.isWhitespace(_$str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查是否所有元素都为空,如果有一个元素不为空则返回false.
     *
     * @param _strings
     * @return
     */
    public static boolean isAllBlank(String... _strings) {
        if (null == _strings || _strings.length < 1)
            return true;
        for (String str : _strings) {
            if (isNotBlank(str))
                return false;
        }
        return true;
    }

    /**
     * 检查给定元素是否有空值,如果所有元素都不为空返回true,如果有任意一个为空则返回false.
     *
     * @param _strings
     * @return
     */
    public static boolean hasBlank(String... _strings) {
        if (null == _strings || _strings.length < 1) {
            return true;
        }
        for (String str : _strings) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测指定的字符串是否不为空白字符。
     *
     * @param _$str 要检测的字符串。
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.isNotBlank(null)      = false
     * StringUtil.isNotBlank(&quot;&quot;)        = false
     * StringUtil.isNotBlank(&quot; &quot;)       = false
     * StringUtil.isNotBlank(&quot;bob&quot;)     = true
     * StringUtil.isNotBlank(&quot;  bob  &quot;) = true
     * </pre>
     */
    public static boolean isNotBlank(String _$str) {
        return !isBlank(_$str);
        // return null == _$str || "".equals( _$str.trim() ) ? false : true;
    }

    /**
     * 检测指定的字符串序列中是否全部不为空白字符串。指的 是空格、制表符、回车符、换行符或换页符，则返回 true。
     *
     * @param _$strs 要检查的字符串
     * @return true / false
     */
    public static boolean isNotBlank(String... _$strs) {
        if (null == _$strs) {
            return false;
        }
        for (String str : _$strs) {
            if (isBlank(str)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断字符串是否为null、“ ”、“null”
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(String obj) {
        if (obj == null) {
            return true;
        } else if (obj.toString().trim().equals("")) {
            return true;
        } else if (obj.toString().trim().toLowerCase().equals("null")) {
            return true;
        }

        return false;
    }

    /**
     * 正则验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
        Matcher match = pattern.matcher(str);

        return match.matches();
    }


    /**
     * 将指定字符串首字母变为小写。
     *
     * @param _$str 待操作的字符串对象
     * @return 首字母小写的字符串对象。
     */
    public static String firstCharToLowerCase(String _$str) {
        if (isBlank(_$str)) {
            return _$str;
        }
        Character firstChar = _$str.charAt(0);
        String tail = _$str.substring(1);
        _$str = Character.toLowerCase(firstChar) + tail;
        return _$str;
    }

    /**
     * 将指定字符串首字母变为大写。
     *
     * @param _$str 待操作的字符串对象
     * @return 首字母大写的字符串对象。
     */
    public static String firstCharToUpperCase(String _$str) {
        if (isBlank(_$str)) {
            return _$str;
        }
        Character firstChar = _$str.charAt(0);
        String tail = _$str.substring(1);
        _$str = Character.toUpperCase(firstChar) + tail;
        return _$str;
    }

    /**
     * 将指定字符串转换为大写
     *
     * @param _str 待操作的字符串对象
     * @return 大写字符串。
     */
    public static String upperCase(String _str) {
        return isBlank(_str) ? _str : _str.toUpperCase();
    }

    /**
     * 将指定字符串转换为小写
     *
     * @param _str 待操作的字符串对象
     * @return 小写字符串。
     */
    public static String lowerCase(String _str) {
        return isBlank(_str) ? _str : _str.toLowerCase();
    }

    /**
     * 将指定字符去除空格。
     *
     * @param _$str 待操作的字符串对象
     * @return null / 去除空格的字符串。
     * <p>
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim(&quot;&quot;)            = &quot;&quot;
     * StringUtil.trim(&quot;     &quot;)       = &quot;&quot;
     * StringUtil.trim(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.trim(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     */
    public static String trim(String _$str) {
        return null == _$str ? null : _$str.trim();
    }

    /**
     * 将指定字符去除所有空字符，包括空格，回车，换行，制表等。此方法不返回null值。
     *
     * @param _$str 待操作的字符串对象
     * @return 空字符 / 无空字符的字符串。
     * <p>
     * <pre>
     * StringUtil.clean(null)          = &quot;&quot;
     * StringUtil.clean(&quot;&quot;)            = &quot;&quot;
     * StringUtil.clean(&quot;abc&quot;)         = &quot;abc&quot;
     * StringUtil.clean(&quot;    abc    &quot;) = &quot;abc&quot;
     * StringUtil.clean(&quot;     &quot;)       = &quot;&quot;
     * </pre>
     */
    public static String clean(String _$str) {
        return null == _$str ? EMPTY : _$str.trim();
    }

    /**
     * 检查字符在指定字符串的位置。支持null对象。
     *
     * @param _$str        要检查的对象
     * @param _$searchChar 求索引位置的字符
     * @return 指定字符的索引位置
     * <p>
     * <pre>
     * StringUtil.indexOf(null, *)         = -1
     * StringUtil.indexOf(&quot;&quot;, *)           = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'a') = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b') = 2
     * </pre>
     */
    public static int indexOf(String _$str, char _$searchChar) {
        if (isEmpty(_$str)) {
            return INDEX_NOT_FOUND;
        }
        return _$str.indexOf(_$searchChar);
    }

    /**
     * 检查字符串在指定字符串的位置。支持null对象。
     *
     * @param _$str       要检查的对象
     * @param _$searchStr 求索引位置的字符串
     * @return 指定字符串的索引位置
     * <p>
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf(&quot;&quot;, &quot;&quot;)           = 0
     * StringUtil.indexOf(&quot;&quot;, *)            = -1 (except when * = &quot;&quot;)
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
     * </pre>
     */
    public static int indexOf(String _$str, String _$searchStr) {
        if (null == _$str || null == _$searchStr) {
            return INDEX_NOT_FOUND;
        }
        return _$str.indexOf(_$searchStr);
    }

    /**
     * 检查字符在指定字符串的最后出现位置。支持null对象。
     *
     * @param _$str        要检查的对象
     * @param _$searchChar 求索引位置的字符
     * @return 指定字符的索引位置
     * <p>
     * <pre>
     * StringUtil.lastIndexOf(null, *)         = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     */
    public static int lastIndexOf(String _$str, char _$searchChar) {
        if (isEmpty(_$str)) {
            return INDEX_NOT_FOUND;
        }
        return _$str.lastIndexOf(_$searchChar);
    }

    /**
     * 检查字符串在指定字符串的最后出现位置。支持null对象。
     *
     * @param _$str       要检查的对象
     * @param _$searchStr 求索引位置的字符串
     * @return 指定字符串的索引位置
     * <p>
     * <pre>
     * StringUtil.lastIndexOf(null, *)          = -1
     * StringUtil.lastIndexOf(*, null)          = -1
     * StringUtil.lastIndexOf(&quot;&quot;, &quot;&quot;)           = 0
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 5
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 4
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 8
     * </pre>
     */
    public static int lastIndexOf(String _$str, String _$searchStr) {
        if (null == _$str || null == _$searchStr) {
            return INDEX_NOT_FOUND;
        }
        return _$str.lastIndexOf(_$searchStr);
    }

    /**
     * 将指定字符串的首字母转换为大写。
     *
     * @param _$str 源字符串
     * @return 转换后的字符串
     * <p>
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtil.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
     * StringUtil.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
     * </pre>
     */
    public static String capitalize(String _$str) {
        int strLen;
        if (null == _$str || 0 == (strLen = _$str.length())) {
            return _$str;
        }
        return new StringBuilder(strLen).append(Character.toTitleCase(_$str.charAt(0))).append(_$str.substring(1)).toString();
    }

    /**
     * 将指定字符串的首字母转换为小写。
     *
     * @param _$str 源字符串
     * @return 转换后的字符串
     * <p>
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize(&quot;&quot;)    = &quot;&quot;
     * StringUtils.uncapitalize(&quot;Cat&quot;) = &quot;cat&quot;
     * StringUtils.uncapitalize(&quot;CAT&quot;) = &quot;cAT&quot;
     * </pre>
     */
    public static String uncapitalize(String _$str) {
        int strLen;
        if (null == _$str || 0 == (strLen = _$str.length())) {
            return _$str;
        }
        return new StringBuilder(strLen).append(Character.toLowerCase(_$str.charAt(0))).append(_$str.substring(1)).toString();
    }

    /**
     * 检查指定字符串是否具有指定的前缀。
     *
     * @param _$str    源字符串
     * @param _$prefix 前缀字符串
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.startsWith(null, null)      = true
     * StringUtil.startsWith(null, &quot;abc&quot;)     = false
     * StringUtil.startsWith(&quot;abcdef&quot;, null)  = false
     * StringUtil.startsWith(&quot;abcdef&quot;, &quot;abc&quot;) = true
     * StringUtil.startsWith(&quot;ABCDEF&quot;, &quot;abc&quot;) = false
     */
    public static boolean startsWith(String _$str, String _$prefix) {
        return startsWith(_$str, _$prefix, false);
    }

    /**
     * 忽略大小写检查指定字符串是否具有指定的前缀。
     *
     * @param _$str    源字符串
     * @param _$prefix 前缀字符串
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.startsWithIgnoreCase(null, null)      = true
     * StringUtil.startsWithIgnoreCase(null, &quot;abc&quot;)     = false
     * StringUtil.startsWithIgnoreCase(&quot;abcdef&quot;, null)  = false
     * StringUtil.startsWithIgnoreCase(&quot;abcdef&quot;, &quot;abc&quot;) = true
     * StringUtil.startsWithIgnoreCase(&quot;ABCDEF&quot;, &quot;abc&quot;) = true
     * </pre>
     */
    public static boolean startsWithIgnoreCase(String _$str, String _$prefix) {
        return startsWith(_$str, _$prefix, true);
    }

    /**
     * 检查指定字符串是否具有指定前缀数组中的任意一个前缀。
     *
     * @param _$str        源字符串
     * @param _$searchStrs 前缀字符串数组
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.startsWithAny(null, null)      = false
     * StringUtil.startsWithAny(null, new String[] {&quot;abc&quot;})  = false
     * StringUtil.startsWithAny(&quot;abcxyz&quot;, null)     = false
     * StringUtil.startsWithAny(&quot;abcxyz&quot;, new String[] {&quot;&quot;}) = false
     * StringUtil.startsWithAny(&quot;abcxyz&quot;, new String[] {&quot;abc&quot;}) = true
     * StringUtil.startsWithAny(&quot;abcxyz&quot;, new String[] {null, &quot;xyz&quot;, &quot;abc&quot;}) = true
     * </pre>
     */
    public static boolean startsWithAny(String _$str, String[] _$searchStrs) {
        if (isEmpty(_$str) || null == _$searchStrs || 0 == _$searchStrs.length) {
            return false;
        }
        for (int i = 0; i < _$searchStrs.length; i++) {
            String searchString = _$searchStrs[i];
            if (StringUtil.startsWith(_$str, searchString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查指定字符串是否具有指定的前缀。
     *
     * @param _$str        源字符串
     * @param _$prefix     前缀字符串
     * @param _$ignoreCase 是否忽略大小写
     * @return true / false
     */
    private static boolean startsWith(String _$str, String _$prefix, boolean _$ignoreCase) {
        if (null == _$str || null == _$prefix) {
            return (null == _$str && null == _$prefix);
        }
        if (_$prefix.length() > _$str.length()) {
            return false;
        }
        return _$str.regionMatches(_$ignoreCase, 0, _$prefix, 0, _$prefix.length());
    }

    /**
     * 检查指定字符串是否具有指定的后缀。
     *
     * @param _$str    源字符串
     * @param _$suffix 后缀字符串
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.endsWith(null, null)      = true
     * StringUtil.endsWith(null, &quot;def&quot;)     = false
     * StringUtil.endsWith(&quot;abcdef&quot;, null)  = false
     * StringUtil.endsWith(&quot;abcdef&quot;, &quot;def&quot;) = true
     * StringUtil.endsWith(&quot;ABCDEF&quot;, &quot;def&quot;) = false
     * StringUtil.endsWith(&quot;ABCDEF&quot;, &quot;cde&quot;) = false
     * </pre>
     */
    public static boolean endsWith(String _$str, String _$suffix) {
        return endsWith(_$str, _$suffix, false);
    }

    /**
     * 忽略大小写检查指定字符串是否具有指定的后缀。
     *
     * @param _$str    源字符串
     * @param _$suffix 后缀字符串
     * @return true / false
     * <p>
     * <pre>
     * StringUtil.endsWithIgnoreCase(null, null)      = true
     * StringUtil.endsWithIgnoreCase(null, &quot;def&quot;)     = false
     * StringUtil.endsWithIgnoreCase(&quot;abcdef&quot;, null)  = false
     * StringUtil.endsWithIgnoreCase(&quot;abcdef&quot;, &quot;def&quot;) = true
     * StringUtil.endsWithIgnoreCase(&quot;ABCDEF&quot;, &quot;def&quot;) = true
     * StringUtil.endsWithIgnoreCase(&quot;ABCDEF&quot;, &quot;cde&quot;) = false
     * </pre>
     */
    public static boolean endsWithIgnoreCase(String _$str, String _$suffix) {
        return endsWith(_$str, _$suffix, true);
    }

    /**
     * 检查指定字符串是否具有指定的后缀。
     *
     * @param _$str        源字符串
     * @param _$suffix     后缀字符串
     * @param _$ignoreCase 是否忽略大小写
     * @return true / false
     */
    private static boolean endsWith(String _$str, String _$suffix, boolean _$ignoreCase) {
        if (null == _$str || null == _$suffix) {
            return (null == _$str && null == _$suffix);
        }
        if (_$suffix.length() > _$str.length()) {
            return false;
        }
        int strOffset = _$str.length() - _$suffix.length();
        return _$str.regionMatches(_$ignoreCase, strOffset, _$suffix, 0, _$suffix.length());
    }

    /**
     * 返回不为null的字符串对象。如果为null则返回一个空字符串。
     *
     * @param _str 待操作的字符串对象。
     * @return 原字符串 / 空字符串。
     */
    public static String getValue(String _str) {
        return isBlank(_str) ? "" : _str;
    }

    /**
     * 得到指定对象的字符串形式。此方法不返回 null。
     *
     * @param _obj 待操作的对象。
     * @return 对象的字符串表示形式 / 空字符串。
     */
    public static String parseString(Object _obj) {
        if (_obj instanceof String) {
            return String.valueOf(_obj);
        }
        return "";
    }

    /**
     * 从一个字符串得到一个整形数字，如果字符串为空值或者空字符串，则返回指定的默认值。
     *
     * @param _$str          待操作字符串
     * @param _$defaultValue 为空或者空字符串时的默认值
     * @return 解析出的数字 / 默认值
     */
    public static int parseInt(String _$str, int _$defaultValue) {
        return isBlank(_$str) ? _$defaultValue : Integer.parseInt(_$str);
    }

    /**
     * @param _$obj
     * @param _$defaultValue
     * @return
     * @description 检查一个对象是否为null, 如果不为null, 则调用toString()方法并检查结果是否为空，如果为空，则返回指定的默认值，否则返回原字符串。
     * @author <a href="mailto:baihw@netinnet.cn">baihw</a>
     * @date 2014-3-9 下午05:18:54
     */
    public static String parseStr(Object _$obj, String _$defaultValue) {
        if (null == _$obj)
            return _$defaultValue;
        return parseStr(_$obj.toString(), _$defaultValue);
    }

    /**
     * 检查一个字符串是否为空，如果为空，则返回指定的默认值，否则返回原字符串。
     *
     * @param _$str          待操作字符串
     * @param _$defaultValue 为空或者空字符串时的默认值
     * @return 原字符串 / 默认值
     */
    public static String parseStr(String _$str, String _$defaultValue) {
        return isBlank(_$str) ? _$defaultValue : _$str;
    }

    /**
     * 将指定的字符串转换为boolean型，当前版本只有1和true(忽略大小写)返回真，否则返回假，null时返回默认值。
     *
     * @param _$value   待转换的字符串
     * @param _$default 如果为null时的默认值
     * @return boolean / 默认值
     */
    public static boolean parseBoolean(String _$value, boolean _$default) {
        if (null == _$value) {
            return _$default;
        }
        final String value = _$value.trim().toLowerCase();
        if ("1".equals(value) || "true".equals(value)) {
            return true;
        }
        // else if( "0".equals( value ) || "false".equals( value ) )
        // return false ;
        // return null ;
        return false;
    }

    /**
     * 字符串中间用*代替   例如： 1508****067
     *
     * @param str
     * @param starCount  **数量
     * @param startIndex 开始位置
     * @return
     */
    public static String insteadStart(String str, int starCount, int startIndex) {
        if (str == null) {
            return null;
        }
        String newStr = "";
        if (str.length() >= (starCount + startIndex)) {
            String star = "";
            for (int i = 0; i < starCount; i++) {
                star = star + "*";
            }
            newStr = str.substring(0, startIndex) + star + str.substring(starCount + startIndex);
        } else if (str.length() > 4) {
            newStr = str.substring(0, 1) + "****" + str.substring(4);
        } else {
            newStr = str;
        }
        return newStr;
    }

    public static String insteadStart(String str) {
        return insteadStart(str, 4, 3);
    }

    public static String insteadStartUserCode(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        if (str.length() < 3) {
            return str;
        }
        return insteadStart(str, str.length() - 3, 2);
    }

    /**
     * 将某个字符串的首字母 大写
     *
     * @param str
     * @return
     */
    public static String convertInitialToUpperCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (i == 0) {
                sb.append(String.valueOf(ch).toUpperCase());
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    /**
     * 过滤掉符号字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringSignFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        Matcher m = SPECIAL_STR_PATTERN.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 将非字母数字的转成两位随机字母
     *
     * @param str
     * @return
     * @Author pant
     * @Date 2017年6月28日 09点00分
     */
    public static String StrSignalSpecialRevalue(String str) {
        StringBuilder strNew = new StringBuilder();
        str = str.replace(" ", "");//去空格。
        char[] chars = str.toCharArray();
        if (chars.length > 0) {
            int arrayLength = LETTER_ARRAY_WITHOUT_ZOIL.length;
            Random rand = new Random();
            for (char subChar : chars) {
                String s = String.valueOf(subChar);
                if (s.matches(SIGN_LETTER_NUM)) {
                    strNew.append(s);
                } else {
                    int firstLetterIndex = rand.nextInt(arrayLength - 1);
                    int secondLetterIndex = rand.nextInt(arrayLength - 1);
                    strNew.append(String.valueOf(LETTER_ARRAY_WITHOUT_ZOIL[firstLetterIndex]));
                    strNew.append(String.valueOf(LETTER_ARRAY_WITHOUT_ZOIL[secondLetterIndex]));
                }
            }
        }
        return strNew.toString();
    }

    /**
     * 获取指定位数的随机字母
     *
     * @param length
     * @return
     */
    public static String getRandomLetter(int length) {
        if (length <= 0) {
            length = 6;
        }

        StringBuilder resultStr = new StringBuilder();
        int arrayLength = LETTER_ARRAY_WITHOUT_ZOIL.length;
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(arrayLength - 1);
            resultStr.append(String.valueOf(LETTER_ARRAY_WITHOUT_ZOIL[randomIndex]));
        }

        return resultStr.toString();
    }

    /**
     * 字符串全局替换（将arg_origin中所有的 arg_old(大小写敏感) 替换成 arg_new）
     * 注：不能重复替换，如将"  "换成" "可能结果中还有 "  "
     *
     * @param arg_origin
     * @param arg_old
     * @param arg_new
     * @return 替换后的新串
     * 例： StringGlobalReplace("ab''cd'efg","'","''");     结果： ab''''cd''efg
     */
    public static String StringGlobalReplace(String arg_origin, String arg_old, String arg_new) {//测试过
        String v_ret = arg_origin;
        if (arg_origin == null) return "";
        if (arg_old == null || arg_old.length() == 0) return arg_origin;
        int pos = v_ret.indexOf(arg_old);
        while (pos >= 0) {
            v_ret = v_ret.substring(0, pos) + arg_new + v_ret.substring(pos + arg_old.length());
            pos = v_ret.indexOf(arg_old, pos + arg_new.length());
        }
        return v_ret;
    }

    /**
     * 删除字串中所有空格(' ')
     *
     * @param arg_origin
     * @return
     */
    public static String RemoveAllBlank(String arg_origin) {
        if (arg_origin == null) return null;
        StringBuffer v_ret = new StringBuffer();
        char v_c;
        for (int i = 0; i < arg_origin.length(); i++) {
            v_c = arg_origin.charAt(i);
            if (v_c != ' ') v_ret.append(v_c);
        }
        return v_ret.toString();
    }

    /**
     * 将 字串转换成 int类型值， 格式不合法 返回 0
     *
     * @param arg_IntStr
     * @return
     */
    public static int parseInt(String arg_IntStr) {
        int v_temp;
        try {
            v_temp = Integer.parseInt(arg_IntStr);
            return v_temp;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 判断字符串是否为空格 或 null
     *
     * @param arg
     * @return
     */
    public static boolean isBlankOrNull(String arg) {
        if (arg == null) return true;
        if (arg.trim().equals("")) return true;
        return false;
    }
}
