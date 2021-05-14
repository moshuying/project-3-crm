package com.msy.plus.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * ID工具
 *
 * @author MoShuying
 * @date 2018/05/27
 */
public class IdUtils {
  private static final DateTimeFormatter DTF_TIMES = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private IdUtils() {}

  public static String uuid16() {
    return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
  }

  public static String uuid64() {
    final UUID uuid = UUID.randomUUID();
    final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return Base64.encodeBase64URLSafeString(bb.array());
  }

  public static String timeId() {
    return DTF_TIMES.format(LocalDate.now()) + RandomStringUtils.randomNumeric(5);
  }
}
