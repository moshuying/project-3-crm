package com.yly.crm.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 线程安全的日期工具
 *
 * @author MoShuying
 * @date 2018/07/20
 */
public class DateUtils {
  private static final DateTimeFormatter DTF_YEAR = DateTimeFormatter.ofPattern("yyyy");
  private static final DateTimeFormatter DTF_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DTF_DAYS = DateTimeFormatter.ofPattern("yyyyMMdd");
  private static final DateTimeFormatter DTF_TIME =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final DateTimeFormatter DTF_TIMES = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private DateUtils() {}

  /**
   * 现在时间（年）
   *
   * @return DTF_YEAR
   */
  public static String getThisYear() {
    return DTF_YEAR.format(LocalDateTime.now());
  }

  /**
   * 校验时间（年）是否合法
   *
   * @param dateString 时间字符串
   * @return Boolean
   */
  public static Boolean validateYear(final String dateString) {
    return validate(dateString, DTF_YEAR);
  }

  /**
   * 现在时间（天）
   *
   * @return DTF_DAY
   */
  public static String getThisDay() {
    return DTF_DAY.format(LocalDateTime.now());
  }

  /**
   * 校验时间（天）是否合法
   *
   * @param dateString 时间字符串
   * @return Boolean
   */
  public static Boolean validateDay(final String dateString) {
    return validate(dateString, DTF_DAY);
  }

  /**
   * 现在时间（天）
   *
   * @return DTF_DAYS
   */
  public static String getThisDays() {
    return DTF_DAYS.format(LocalDateTime.now());
  }

  /**
   * 校验时间（天）是否合法
   *
   * @param dateString 时间字符串
   * @return Boolean
   */
  public static Boolean validateDays(final String dateString) {
    return validate(dateString, DTF_DAYS);
  }

  /**
   * 现在时间（秒）
   *
   * @return DTF_TIME
   */
  public static String getThisTime() {
    return DTF_TIME.format(LocalDateTime.now());
  }

  /**
   * 校验时间（秒）是否合法
   *
   * @param dateString 时间字符串
   * @return Boolean
   */
  public static Boolean validateTime(final String dateString) {
    return validate(dateString, DTF_TIME);
  }

  /**
   * 现在时间（秒）
   *
   * @return DTF_TIMES
   */
  public static String getThisTimes() {
    return DTF_TIMES.format(LocalDateTime.now());
  }

  /**
   * 校验时间（秒）是否合法
   *
   * @param dateString 时间字符串
   * @return Boolean
   */
  public static Boolean validateTimes(final String dateString) {
    return validate(dateString, DTF_TIMES);
  }

  /**
   * 校验日期是否合法
   *
   * @param dateString 时间字符串
   * @param dateTimeFormatString 时间格式字符串
   * @return Boolean
   */
  public static Boolean validate(final String dateString, final String dateTimeFormatString) {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatString);
    try {
      LocalDateTime.parse(dateString, dateTimeFormatter);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  /**
   * 校验日期是否合法
   *
   * @param dateString 时间字符串
   * @param dateTimeFormatter 时间格式器
   * @return Boolean
   */
  public static Boolean validate(
      final String dateString, final DateTimeFormatter dateTimeFormatter) {
    try {
      LocalDateTime.parse(dateString, dateTimeFormatter);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  /**
   * 比较两个时间的大小
   *
   * @param dateString1 时间字符串1
   * @param dateString2 时间字符串2
   * @param dateTimeFormatString 时间格式
   * @return -1:时间1小于时间2 | 0:时间1等于时间2 | 1:时间1大于时间2
   */
  public static Integer compare(
      final String dateString1, final String dateString2, final String dateTimeFormatString) {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatString);
    final LocalDateTime dateTime1 = LocalDateTime.parse(dateString1, dateTimeFormatter);
    final LocalDateTime dateTime2 = LocalDateTime.parse(dateString2, dateTimeFormatter);
    if (dateTime1.isBefore(dateTime2)) {
      return -1;
    } else if (dateTime1.equals(dateTime2)) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * 在原时间上增加x个时间单位
   *
   * @param dateString 时间字符串
   * @param x x个时间单位
   * @param chronoUnit 时间单位
   * @param dateTimeFormatString 时间格式
   * @return 增加后的时间
   */
  public static String add(
      final String dateString,
      final Long x,
      final ChronoUnit chronoUnit,
      final String dateTimeFormatString) {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatString);
    final LocalDateTime dateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
    final LocalDateTime newTime = dateTime.plus(x, chronoUnit);
    return dateTimeFormatter.format(newTime);
  }

  /**
   * 增加x小时
   *
   * @param dateString 时间字符串
   * @param hours 小时
   * @param dateTimeFormatString 时间格式
   * @return 增加后的时间
   */
  public static String addHours(
      final String dateString, final Long hours, final String dateTimeFormatString) {
    return add(dateString, hours, ChronoUnit.HOURS, dateTimeFormatString);
  }

  /**
   * 增加x分钟
   *
   * @param dateString 时间字符串
   * @param minutes 分钟
   * @param dateTimeFormatString 时间格式
   * @return 增加后的时间
   */
  public static String addMinutes(
      final String dateString, final Long minutes, final String dateTimeFormatString) {
    return add(dateString, minutes, ChronoUnit.MINUTES, dateTimeFormatString);
  }

  /**
   * 增加x秒
   *
   * @param dateString 时间字符串
   * @param seconds 秒
   * @param dateTimeFormatString 时间格式
   * @return 增加后的时间
   */
  public static String addSeconds(
      final String dateString, final Long seconds, final String dateTimeFormatString) {
    return add(dateString, seconds, ChronoUnit.SECONDS, dateTimeFormatString);
  }

  /**
   * 是否为闰年
   *
   * @param dateString 时间字符串
   * @param dateTimeFormatString 时间格式字符串
   * @return Boolean
   */
  public static Boolean isLeapYear(final String dateString, final String dateTimeFormatString) {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatString);
    final LocalDate dateTime = LocalDate.parse(dateString, dateTimeFormatter);
    return dateTime.isLeapYear();
  }
}
