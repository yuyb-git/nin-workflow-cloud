package cn.netinnet.cloudcommon.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chen.wb
 * @version V1.0
 * @Description: date工具类
 * @Date 2017-11-30
 **/
public class DateUtil {

    /**
     * 生成ISO-8601 规范的时间格式
     *
     * @param date
     * @return
     */
    public static String formatISO8601DateString(Date date) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 获取原时间戳
     *
     * @param reverseTime
     * @return
     */
    public static Long recoverReverseTime(Long reverseTime) {
        long longTime = Long.MAX_VALUE - reverseTime;
        return longTime / 1000000;
    }

    /**
     * 获取32位唯一id
     * @return
     */
    public static String getNID(){
        return  new ObjectId().toString();
    }


    public static long getUID(){
        return  SnowFlake.nId();
    }

    /**
     * 生成页面普通展示时间
     * pattern:yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatNormalDateString(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return DateFormatUtils.format(date, pattern);
    }

    public static String yyyymmddHHMMssSSS_23(Date rightNow) {
        return DateFormatUtils.format(rightNow, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static String yyyymmddHHMMssSSS_23() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static String yyyymmddHHMMss_14() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyyMMddHHmmss");
    }

    /**
     * pattern : yyyy年MM月dd日
     *
     * @return
     */
    public static String yyyy_mm_dd_chinese_14() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyy年MM月dd日");
    }

    public static String yyyy_mm_dd_10(Date rightNow) {
        return DateFormatUtils.format(rightNow, "yyyy-MM-dd");
    }

    public static String yyyy_mm_dd_10() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyy-MM-dd");
    }

    public static String yyyymmdd_8(Date rightNow) {
        return DateFormatUtils.format(rightNow, "yyyyMMdd");
    }

    public static String yyyymmdd_8() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyyMMdd");
    }

    public static String yyyy() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "yyyy");
    }

    public static String mm() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "MM");
    }

    public static String dd() {
        Date rightNow = new Date();
        return DateFormatUtils.format(rightNow, "dd");
    }

    /**
     * 判断当前日期是否在某个日期之后
     *
     * @param arg_date_yyyymmdd
     * @return
     */
    public static boolean isDateAfter_yyyymmdd8(String arg_date_yyyymmdd) {
        String v_today = DateUtil.yyyymmdd_8();
        if (v_today.compareTo(arg_date_yyyymmdd) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDateBefore_yyyymmdd8(String arg_date_yyyymmdd) {
        String v_today = DateUtil.yyyymmdd_8();
        if (v_today.compareTo(arg_date_yyyymmdd) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回 今天 日期 加 几天 的日期
     *
     * @param arg_date
     * @return
     */
    public static Date today_add(int arg_date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, arg_date);
        return cal.getTime();
    }

    /**
     * 返回 今天 日期 加 几天 的日期
     *
     * @param arg_date
     * @return
     */
    public static String yyyy_mm_dd_10_after_today(int arg_date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, arg_date);
        return DateUtil.yyyy_mm_dd_10(cal.getTime());
    }

    /**
     * 返回 今天 日期 加 几天 的日期
     *
     * @param arg_date
     * @return
     */
    public static String yyyymmdd_8_after_today(int arg_date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, arg_date);
        return DateUtil.yyyymmdd_8(cal.getTime());
    }

    public static Date firstDayofMonth(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month > 11 ? 11 : month < 0 ? 0 : month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date firstDayofMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month > 11 ? 11 : month < 0 ? 0 : month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date lastDayofMonth(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month > 11 ? 11 : month < 0 ? 0 : month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date lastDayofMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month > 11 ? 11 : month < 0 ? 0 : month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static long spaceDay(String date) {
        FastDateFormat formatter = FastDateFormat.getInstance("yyyy-MM-dd");
        Date d1;
        try {
            d1 = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("日期格式错误" + formatter);
            return 100;    //日期错误则默认间隔100
        }
        Date d2 = new Date();
        long diff = d2.getTime() - d1.getTime();

        return diff / (1000 * 60 * 60 * 24);
    }

    public static long spaceMinute(String date) {
        FastDateFormat formatter = FastDateFormat.getInstance("yyyyMMddhhmm");
        Date d1;
        try {
            d1 = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("日期格式错误" + formatter);
            return 100;    //日期错误则默认间隔100
        }
        Date d2 = new Date();
        long diff = d2.getTime() - d1.getTime();
        return diff / (1000 * 60);
    }

    /**
     * 获取指定年月的前后n个月是什么年月
     * @param year 年
     * @param month 月
     * @param afterMonth 经过的月
     * @return calendar
     */
    public static Calendar afterMonth(int year, int month, int afterMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1); // Calender 月份从0-11 表示1-12月
        // 设置天
        calendar.set(Calendar.DAY_OF_MONTH,1);

        calendar.add(Calendar.MONTH, afterMonth);
        return calendar;
    }

    /**
     * 获取某月的最后一天
     * @param year
     * @param month
     * @return int
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        cal.set(5,1);//修复获取平年二月份的最后一天的日期错误
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }
}
