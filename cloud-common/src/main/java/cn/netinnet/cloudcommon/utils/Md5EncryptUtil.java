package cn.netinnet.cloudcommon.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin
 * @version V1.0
 * @Description: MD5加密类 —— 包括js的md5加密整理，主要是中文加密的处理
 * @Date 2017-11-30
 **/
public class Md5EncryptUtil {

    /**
     * edit by xianlaioy at 20171206
     * 设置自己项目的salt最好
     * 1.线上，线下最好不一样
     * 2.或者设置在配置文件
     */
    public static final String keySecret = "$[!nin99match^-^]$";

    /**
     * 使用 MessageDigest 类加密的md5
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) // 如果一个byte小于等于15，
            // Integer.toHexString转换成后前面不会添0，所以这里要判断
            {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 传递字节
     *
     * @param source
     * @return
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @param source
     * @return
     */
    public static String examMd5(String source) { // 方法3
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source.getBytes("UTF-8"));
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，

            StringBuffer sb = new StringBuffer();
            for (byte b : tmp) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 配合js的md5加密，包括中文的加密 位置：lib\md5js.js
     *
     * @param source
     * @return
     */
    public static String examMd54Js(String source) {
        if (source == null) {
            source = "";
        }
        try {
            String tempstr = source;
            String resultstr = "";
            for (int i = 0; i < tempstr.length(); i++) {
                resultstr += tempstr.codePointAt(i) + "";
            }

            return examMd5(resultstr);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 配合jquery md5 plus插件使用 位置：lib\jquery.md5.js
     *
     * @param source
     * @return
     */
    public static String examMd54JqueryJs(String source) {
        if (source == null) {
            source = "";
        }
        return examMd5(source);
    }


    public static void main(String[] args) {
        String str1 = "55237929f7606f9dd6ec550f00000001";
        String str2 = "55237929f7606f9dd6ec550f00000002";
    }
}
