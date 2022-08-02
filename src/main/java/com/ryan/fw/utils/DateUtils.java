package com.ryan.fw.utils;

import cn.hutool.core.convert.Convert;
import com.ryan.fw.constans.enums.UnitEnum;
import com.ryan.fw.constans.pattern.DatePattern;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Describe 日期
 * @Author smileGongJ
 * @Date 2022/7/30 20:47
 */
public class DateUtils {

    /*时间前缀*/
    private static final String TIME_PREFIX = "1970-01-01 ";

    /*时间格式*/
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD);

    /**
     * 根据【LocalDateTime】获取【LocalDateTime】
     *
     * @param ldt LocalDateTime
     * @return LocalDateTime
     */
    public static LocalDateTime ldt(LocalDateTime ldt) {
        return strToLdt(ldt.format(FORMATTER), DatePattern.YYYY_MM_DD);
    }

    /**
     * 根据【LocalDateTime】获取【Long】
     *
     * @param ldt LocalDateTime
     * @return Long
     */
    public static Long timestamp(LocalDateTime ldt) {
        return toTimestamp(ldt.format(FORMATTER));
    }

    /**
     * 根据【Date】获取【LocalDateTime】
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime ldt(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * 根据【LocalDateTime】获取【Date】
     *
     * @param ldt LocalDateTime
     * @return Date
     */
    public static Date d(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据【LocalDateTime】获取【String】
     *
     * @param ldt     LocalDateTime
     * @param pattern 模式
     * @return String
     */
    public static String ldtToStr(LocalDateTime ldt, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(ldt);
    }

    /**
     * 【常用】根据【LocalDateTime】获取【String，yyyy-MM-dd】
     *
     * @param ldt LocalDateTime
     * @return String
     */
    public static String ldtToStr(LocalDateTime ldt) {
        return DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD).format(ldt);
    }

    /**
     * 根据【String】获取【LocalDateTime】
     *
     * @param str     String
     * @param pattern 模式
     * @return LocalDateTime
     */
    public static LocalDateTime strToLdt(String str, String pattern) {
        return ldt(strToDate(str, pattern));
    }

    /**
     * 【常用】根据【String，yyyy-MM-dd】获取【LocalDateTime】
     *
     * @param str String
     * @return LocalDateTime
     */
    public static LocalDateTime strToLdt(String str) {
        return ldt(strToDate(str));
    }

    /**
     * 根据【String】获取【Date】
     *
     * @param str     String
     * @param pattern 模式
     * @return Date
     */
    public static Date strToDate(String str, String pattern) {
        Date date;
        try {
            date = new SimpleDateFormat(pattern).parse(str);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化失败：{}" + str + "模式：{}" + pattern, e);
        }
        return date;
    }

    /**
     * 【常用】根据【String】获取【Date】
     *
     * @param str String
     * @return Date
     */
    public static Date strToDate(String str) {
        Date date;
        try {
            date = new SimpleDateFormat(DatePattern.YYYY_MM_DD).parse(str);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化失败：{}" + str, e);
        }
        return date;
    }

    /**
     * 根据【Date】获取【String】
     *
     * @param d       Date
     * @param pattern 模式
     * @return String
     */
    public static String dateToStr(Date d, String pattern) {
        String format;
        try {
            format = new SimpleDateFormat(pattern).format(d);
        } catch (Exception e) {
            throw new RuntimeException("日期格式化失败：{}" + d + "模式：{}" + pattern, e);
        }
        return format;
    }

    /**
     * 【常用】根据【Date】获取【String，yyyy-MM-dd】
     *
     * @param date Date
     * @return String
     */
    public static String dateToStr(Date date) {
        String format;
        try {
            format = new SimpleDateFormat(DatePattern.YYYY_MM_DD).format(date);
        } catch (Exception e) {
            throw new RuntimeException("日期格式化失败：{}" + date, e);
        }
        return format;
    }

    /**
     * 由【2022-03-14、2022-03-14 12:10:30等】转【2022年03月、12时30分等】
     *
     * @param str      String
     * @param patternP 转换前模式
     * @param patternA 转换后模式
     * @return String
     */
    public static String sts(String str, String patternP, String patternA) {
        return ldtToStr(strToLdt(str, patternP), patternA);
    }

    /**
     * 根据【小时、分钟】转换为【时间戳】
     *
     * @param hours 小时数
     * @param minis 分钟数
     * @return Long 时间戳
     */
    public static Long toTimestamp(Integer hours, Integer minis) {
        hours = Objects.nonNull(hours) ? hours : 0;
        minis = Objects.nonNull(minis) ? minis : 0;
        return Convert.toLong(hours * 60 * 60 * 1000 + minis * 60 * 1000);
    }

    /**
     * 根据【时间戳】获取【小时数】
     *
     * @param timestamp 时间戳
     * @return Integer 小时数
     */
    public static Integer toHours(Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return 0;
        }
        return Convert.toInt(timestamp / Convert.toLong(60 * 60 * 1000));
    }

    /**
     * 根据【时间戳】获取【分钟数】
     *
     * @param timestamp 时间戳
     * @return Integer 分钟数
     */
    public static Integer toMinis(Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return 0;
        }
        long minis = timestamp % Convert.toLong(60 * 60 * 1000);
        return Convert.toInt(minis / Convert.toLong(60 * 1000));
    }

    /**
     * 根据【时间戳】获取【分钟数】2
     *
     * @param timestamp 时间戳
     * @return Integer 分钟数
     */
    public static Integer toMinis2(Long timestamp) {
        if (Objects.isNull(timestamp)) {
            return 0;
        }
        return Convert.toInt(timestamp / Convert.toLong(60 * 1000));
    }

    /**
     * 根据【Date】获取当前周的第一天的日期
     *
     * @return String 2022-03-14
     */
    public static String getStartOfWeek(Date d) {
        Date date = beginDayOfWeek(d);
        LocalDateTime ldt = DateUtils.ldt(date);
        return DateUtils.ldtToStr(ldt, DatePattern.YYYY_MM_DD);
    }

    /**
     * 根据【String】获取当前周的第一天的日期
     *
     * @return String 2022-03-14
     */
    public static String getStartOfWeek(String d) {
        Date date = beginDayOfWeek(strToDate(d, DatePattern.YYYY_MM_DD));
        return ldtToStr(ldt(date), DatePattern.YYYY_MM_DD);
    }

    /**
     * 根据【Date】获取当前周的第一天的日期 2
     *
     * @return String 2022-03-14
     */
    public static String getStartOfWeek2(Date d) {
        Date date = beginDayOfWeek(d);
        LocalDateTime ldt = DateUtils.ldt(date);
        return ldtToStr(ldt, DatePattern.MM_U_DD_U);
    }

    /**
     * 根据【Date】获取当前周的最后一天的日期
     *
     * @return String 2022-03-20
     */
    public static String getEndOfWeek(Date d) {
        Date date = endDayOfWeek(d);
        LocalDateTime ldt = DateUtils.ldt(date);
        return ldtToStr(ldt, DatePattern.YYYY_MM_DD);
    }

    /**
     * 根据【Date】获取当前周的最后一天的日期
     *
     * @return String 2022-03-20
     */
    public static String getEndOfWeek(String d) {
        Date date = endDayOfWeek(strToDate(d, DatePattern.YYYY_MM_DD));
        return ldtToStr(ldt(date), DatePattern.YYYY_MM_DD);
    }

    /**
     * 根据【Date】获取当前周的最后一天的日期 2
     *
     * @return String 03月20日
     */
    public static String getEndOfWeek2(Date d) {
        return ldtToStr(ldt(endDayOfWeek(d)), DatePattern.MM_U_DD_U);
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d Date
     * @return Timestamp
     */
    public static Timestamp dayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d Date
     * @return Timestamp
     */
    public static Timestamp dayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 根据【Date】获取【一周】的开始时间
     *
     * @return Date
     */
    public static Date beginDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return dayStartTime(cal.getTime());
    }

    /**
     * 根据【Date】获取【一周】的结束时间
     *
     * @return Date
     */
    public static Date endDayOfWeek(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDayOfWeek(d));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return dayEndTime(weekEndSta);
    }

    /**
     * 获取一周开始到结束的list集合
     *
     * @param dBegin 开始日期
     * @param dEnd   结束日期
     * @return List<String> 年月日list集合
     */
    public static List<String> strList(Date dBegin, Date dEnd) {
        List<String> lDate = new ArrayList();
        lDate.add(DateUtils.dateToStr(dBegin));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (dEnd.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(DateUtils.dateToStr(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 获取【指定月份】有【几个星期x】
     *
     * @param date 月份
     * @param day  1-7  周天-周六
     * @return 指定月指定星期x的数量
     */
    public static List<String> dayOfWeekCount(Date date, int day) {
        List<String> strL = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= actualMaximum; i++) {
            Calendar tp = Calendar.getInstance();
            tp.setTime(date);
            tp.set(Calendar.DAY_OF_MONTH, i);
            if (tp.get(Calendar.DAY_OF_WEEK) == day) {
                strL.add(dateToStr(tp.getTime()));
            }
        }
        return strL;
    }

    /**
     * 根据【日期】获取【星期几】
     *
     * @param datetime 日期
     * @return String 星期几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 根据【日期】获取【星期几】
     *
     * @param datetime 日期
     * @return Integer 星期几
     */
    public static Integer dateToWeekI(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return i == 0 ? 7 : i;
    }

    /**
     * 将时间带上单位
     *
     * @param hours 小时数
     * @param minis 分钟数
     * @return 时间
     */
    public static String withUnit(Integer hours, Integer minis) {
        if (hours == 0 && minis == 0) {
            return "0小时0分钟";
        }
        if (hours != 0 && minis == 0) {
            return hours + UnitEnum.HOUR.getValue();
        }
        if (hours == 0) {
            return minis + UnitEnum.MINIS.getValue();
        }
        return hours + UnitEnum.HOUR.getValue() + minis + UnitEnum.MINIS.getValue();
    }

    /**
     * 计算时间差的分钟数
     *
     * @param timeStrB 时间字符串，格式：HH:mm，示例：09:00
     * @param timeStrA 时间字符串，格式：HH:mm，示例：09:00
     * @return 分钟数
     */
    public static Integer timeDifference(String timeStrB, String timeStrA) {
        return Convert.toInt((timestamp(timeStrA) - timestamp(timeStrB)) / (1000 * 60));
    }

    /**
     * 获取时间戳
     *
     * @param timeStr 时间字符串，格式：HH:mm，示例：09:00
     * @return 时间戳
     */
    public static Long timestamp(String timeStr) {
        return strToDate(TIME_PREFIX + timeStr, DatePattern.YYYY_MM_DD_HH_MM).getTime();
    }

    /**
     * 获取某年某月的所有日期集合
     *
     * @param year  年
     * @param month 月
     * @return 日期集合
     */
    public static List<String> dayListOfMonth(String year, String month) {
        List<String> list = new ArrayList<>();
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.set(Calendar.YEAR, Convert.toInt(year));
        instance.set(Calendar.MONTH, Convert.toInt(month) - 1);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        int day = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= day; i++) {
            list.add(year + "-" + month + "-" + (i < 10 ? ("0" + i) : i));
        }
        return list;
    }

    /**
     * 获取某年某月的所有日期集合
     *
     * @param date 日期
     * @return 日期集合
     */
    public static List<String> dayListOfMonth(Date date) {
        LocalDateTime ldt = ldt(date);
        return dayListOfMonth(ldt.getYear() + "", ldt.getMonth().getValue() + "");
    }

    /**
     * 获取时间戳
     *
     * @param date 日期
     * @return Long 秒级时间戳
     */
    public static Long toTimestamp(String date) {
        return strToLdt(date).toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 日期转换cron表达式
     *
     * @param date 日期
     * @return String
     */
    public static String getCron(Date date) {
        return Objects.nonNull(date)
                ? new SimpleDateFormat("ss mm HH dd MM ?").format(date)
                : null;
    }

    /**
     * 获取前一天日期
     *
     * @param str
     * @return
     */
    public static String getLastDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取后一天日期
     *
     * @param str
     * @return
     */
    public static String getNextDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取后几天日期
     *
     * @param str
     * @param num
     * @return
     */
    public static String getNextDate(String str, Integer num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + num);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取时间间隔
     *
     * @param s1
     * @param s2
     * @return
     */
    public static Integer getDateInterval(String s1, String s2) {
        long c = DateUtils.getTimes(s2) - DateUtils.getTimes(s1);
        c = c / 1000 / 60 / 60 / 24;
        return Math.toIntExact(Math.abs(c));
    }

    /**
     * 获取时间戳
     *
     * @param str
     * @return
     */
    public static Long getTimes(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取当前日期-时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取日期路径(/2022/02/04)
     *
     * @return
     */
    public static String getDatePath() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return "/" + year + "/" + month + "/" + day;
    }

    /**
     * 格式化DateTime
     *
     * @param str
     * @return
     */
    public static String formatDateTime(String str) {
        if (Objects.nonNull(str)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.format(sdf.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
                return str;
            }
        } else {
            return str;
        }
    }

    /**
     * 格式化Date
     *
     * @param str
     * @return
     */
    public static String formatDate(String str) {
        if (Objects.nonNull(str)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.format(sdf.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
                return str;
            }
        } else {
            return str;
        }
    }

}
