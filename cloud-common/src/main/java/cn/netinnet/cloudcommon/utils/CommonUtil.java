package cn.netinnet.cloudcommon.utils;


import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description： 公共的工具类   
 * @company：netinnet  
 * @author：yuyb  
 * @createtime：2019年3月25日 下午2:57:31  
 * @version：V1.0
 */
public class CommonUtil {

	private static final char UNDERLINE = '_';
	private static final Pattern PATTERN = Pattern.compile("[A-Z]");

    public static boolean isIe(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        // IE7-10 || IE11
        return (userAgent.indexOf("msie") > 0 || (userAgent.indexOf("gecko") > 0 && userAgent.indexOf("rv:11") > 0));
    }

	/** 方法描述
	 * @description 对象序列化
	 * @param obj
	 * @return [obj]
	 * @author wanghy
	 * @date 2020/2/28 14:09
	 */
	public static byte[] objectToByte(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
			bo.close();
			oo.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/** 方法描述
	 * @description 反序列化
	 * @param bytes
	 * @return [bytes]
	 * @author wanghy
	 * @date 2020/2/28 14:10
	 */
	public static Object byteToObject(byte[] bytes) {
		Object object = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			object = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * @description  下划线转驼峰
	 * @param  val
	 * @author ousp
	 * @date 2020/4/15
	 * @return java.lang.String
	 */
	public static String underscoreToCamelCase(String val) {
		int index = val.indexOf(UNDERLINE);
		if (index == -1) {
			return val;
		}

		StringBuilder sb = new StringBuilder(val.substring(0, index));
		boolean flag = false;
		for (int i = index, len = val.length(); i < len; i ++) {
			if (val.charAt(i) == UNDERLINE) {
				flag = true;
				continue;
			} else {
				if (flag) {
					flag = false;
					sb.append(Character.toUpperCase(val.charAt(i)));
				} else {
					sb.append(val.charAt(i));
				}
			}
		}
		return sb.toString();
	}

	/**
	* 驼峰转下划线
	* @param val
	* @author ousp
	* @date 2020/5/8
	* @return java.lang.String
	*/
	public static String camelCaseToUnderscore(String val) {
		if (StringUtil.isBlankOrNull(val)) {return val;}
		Matcher matcher = PATTERN.matcher(val);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String getAbsolutePath(String path) {
		if (path.indexOf(":") > 0) {
			return path;
		}
		return ClassUtils.getDefaultClassLoader().getResource("").getPath() + path;
	}

	/**  方法描述
	 * @Description 判断是否重复
	 * @Author yuyb
	 * @Date 11:39 2020/6/8
	 * @return void
	 **/
	public static void judgeLoginRepeat(Map<String, String> dataMap, List<String> dataList,
										List<String> repeatList, String data, String rows){
		if (!dataMap.containsKey(data)) {
			dataMap.put(data, rows);
			if(dataList != null){
				dataList.add(data);
			}
		} else {
			if (!repeatList.contains(data)) {
				repeatList.add(data);
			}
			// 拼接重复的行次
			dataMap.put(data, dataMap.get(data) + "," + rows);
		}
	}

	/**  方法描述
	 * @Description 添加重复信息
	 * @Author yuyb
	 * @Date 11:39 2020/6/8
	 * @return void
	 **/
	public static void genErrInfo(Map<String, String> repeatMap, List<String> repeatList,
								  List<String> errList, String column, String errorMsg){
		StringBuilder err;
		for (String key : repeatList) {
			String[] rows = repeatMap.get(key).split(",");
			err = new StringBuilder();
			for (String row : rows) {
				err.append("第").append(row).append("行 第").append(column).append("列[").append(key).append("]，");
			}
			err.append(errorMsg);
			errList.add(err.toString());
		}
	}


}
