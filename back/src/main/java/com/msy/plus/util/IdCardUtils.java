package com.msy.plus.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 二代身份证工具
 *
 * <p>【中华人民共和国国家标准GB11643-1999】关于公民身份号码的规定： 公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
 *
 * <p>排列顺序从左至右依次为：6位数字地址码，8位数字出生日期码，3位数字顺序码和1位数字校验码。
 *
 * <p>1-2位数字：所在省份的代码；
 *
 * <p>3-4位数字：所在城市的代码；
 *
 * <p>5-6位数字：所在区县的代码；
 *
 * <p>7-14位数字：出生xxxx年xx月xx日；
 *
 * <p>15-16位数字：所在地的派出所的代码；
 *
 * <p>17位数字表示性别：奇数表示男性，偶数表示女性； （在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。）
 *
 * <p>18位数字是校检码：检验身份证的正确性。校检码可以是数字0~9和字符X。
 *
 * <p>校验码的计算方法为：
 *
 * <p>1.将前面的身份证号码17位数分别乘以不同的系数。从第1位到第17位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2；
 *
 * <p>2.将这17位数字和系数相乘的结果相加；
 *
 * <p>3.用加出来和除以11，看余数是多少；
 *
 * <p>4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2；
 * （比如余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2）
 *
 * @author MoShuying
 * @date 2018/11/27
 */
@Slf4j
public class IdCardUtils {
  /** 省、直辖市代码表 */
  private static final String AREA_CODE[][] = {
    {"11", "北京"},
    {"12", "天津"},
    {"13", "河北"},
    {"14", "山西"},
    {"15", "内蒙古"},
    {"21", "辽宁"},
    {"22", "吉林"},
    {"23", "黑龙江"},
    {"31", "上海"},
    {"32", "江苏"},
    {"33", "浙江"},
    {"34", "安徽"},
    {"35", "福建"},
    {"36", "江西"},
    {"37", "山东"},
    {"41", "河南"},
    {"42", "湖北"},
    {"43", "湖南"},
    {"44", "广东"},
    {"45", "广西"},
    {"46", "海南"},
    {"50", "重庆"},
    {"51", "四川"},
    {"52", "贵州"},
    {"53", "云南"},
    {"54", "西藏"},
    {"61", "陕西"},
    {"62", "甘肃"},
    {"63", "青海"},
    {"64", "宁夏"},
    {"65", "新疆"},
    {"71", "台湾"},
    {"81", "香港"},
    {"82", "澳门"},
    {"91", "国外"}
  };

  /** 最后一位校验码 */
  private static final String[] LAST_CODE = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

  /** 每位加权因子 */
  private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

  private IdCardUtils() {}

  public static void main(final String[] args) {
    System.out.println(getProvince("44532319960516121a", false));
  }

  /**
   * 判断二代身份证合法性
   *
   * @param idCard 身份证
   * @return Boolean
   */
  public static Boolean validate(@NotBlank final String idCard) {
    // 18位长度
    if (idCard.length() != 18) {
      log.error("二代身份证长度错误");
      return false;
    }

    // 前17位全为数字
    final String idCard17 = idCard.substring(0, 17);
    if (!isDigital(idCard17)) {
      log.error("前17位不全为数字");
      return false;
    }

    // 校验地区
    if (getProvince(idCard, false) == null) {
      log.error("省份错误");
      return false;
    }

    // 校验生日
    if (getBirthday(idCard, false) == null) {
      log.error("生日错误");
      return false;
    }

    // 校验第18位
    final String idCard18Code = idCard.substring(17, 18);
    int powerSum = 0;
    for (int i = 0; i < 17; i++) {
      powerSum += Integer.parseInt(String.valueOf(idCard17.charAt(i))) * POWER[i];
    }
    // 将对权值和取11模得到余数
    final String lastCode = LAST_CODE[powerSum % 11];
    // 身份的第18位与算出来的校码进行匹配
    if (!idCard18Code.equalsIgnoreCase(lastCode)) {
      log.error("第18位错误");
      return false;
    }
    return true;
  }

  /**
   * 判断是否全为数字
   *
   * @param string 字符串
   * @return Boolean
   */
  private static Boolean isDigital(final String string) {
    final char[] cs = string.toCharArray();
    for (final char c : cs) {
      if (48 > c || c > 57) {
        return false;
      }
    }
    return true;
  }

  /**
   * 获取省份
   *
   * @param idCard 身份证
   * @param toValidate 是否校验
   * @return 省份
   */
  public static String getProvince(@NotBlank final String idCard, final boolean toValidate) {
    if (toValidate && !validate(idCard)) {
      return null;
    }
    final String provinceCode = getProvinceCode(idCard);
    for (final String[] area : AREA_CODE) {
      if (area[0].equals(provinceCode)) {
        return area[1];
      }
    }
    return null;
  }

  /**
   * 获取生日 生日格式：yyyy-mm-dd
   *
   * @param idCard 身份证
   * @param toValidate 是否校验
   * @return 生日
   */
  public static String getBirthday(@NotBlank final String idCard, final boolean toValidate) {
    if (toValidate && !validate(idCard)) {
      return null;
    }
    try {
      final String birthday = getBirthdayCode(idCard);
      final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
      return LocalDate.parse(birthday, dateTimeFormatter).toString();
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取性别
   *
   * @param idCard 身份证
   * @param toValidate 是否校验
   * @return 女 | 男
   */
  public static String getSex(@NotBlank final String idCard, final boolean toValidate) {
    if (toValidate && !validate(idCard)) {
      return null;
    }
    final String sex = getSexCode(idCard);
    return (Integer.valueOf(sex) & 1) == 0 ? "女" : "男";
  }

  private static String getProvinceCode(@NotBlank final String idCard) {
    return idCard.substring(0, 2);
  }

  private static String getBirthdayCode(@NotBlank final String idCard) {
    return idCard.substring(6, 14);
  }

  private static String getSexCode(@NotBlank final String idCard) {
    return idCard.substring(16, 17);
  }
}
