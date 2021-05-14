package com.msy.plus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
  /** 常见文件头信息 */
  public static final Map<String, String> FILE_TYPE_MAP =
      new HashMap<String, String>() {
        private static final long serialVersionUID = 2413557023486330089L;

        {
          this.put("jpg", "FFD8FF"); // JPEG
          this.put("png", "89504E47"); // PNG
          this.put("gif", "47494638"); // GIF
          this.put("tif", "49492A00"); // TIFF
          this.put("bmp", "424D"); // Windows Bitmap
          this.put("dwg", "41433130"); // CAD
          this.put("html", "68746D6C3E"); // HTML
          this.put("rtf", "7B5C727466"); // Rich Text Format
          this.put("xml", "3C3F786D6C");
          this.put("zip", "504B0304"); // ZIP Archive
          this.put("rar", "52617221"); // RAR Archive
          this.put("7z", "377ABCAF271C"); // 7z Archive
          this.put("psd", "38425053"); // PhotoShop
          this.put("eml", "44656C69766572792D646174653A"); // Email [thorough only]
          this.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express
          this.put("pst", "2142444E"); // Outlook
          this.put("xls", "D0CF11E0"); // MS Word
          this.put("doc", "D0CF11E0"); // MS Excel 注意：word 和 excel的文件头一样
          this.put("mdb", "5374616E64617264204A"); // MS Access
          this.put("wpd", "FF575043"); // WordPerfect
          this.put("eps", "252150532D41646F6265");
          this.put("ps", "252150532D41646F6265");
          this.put("pdf", "255044462D312E"); // Adobe Acrobat
          this.put("qdf", "AC9EBD8F"); // Quicken
          this.put("pwl", "E3828596"); // Windows Password
          this.put("wav", "57415645"); // Wave
          this.put("avi", "41564920");
          this.put("ram", "2E7261FD"); // Real Audio
          this.put("rm", "2E524D46"); // Real Media
          this.put("mpg", "000001BA"); // Moving Pictures Experts Group
          this.put("mov", "6D6F6F76"); // QuickTime
          this.put("asf", "3026B2758E66CF11"); // Windows Media
          this.put("mid", "4D546864"); // MIDI
        }
      };

  private FileUtils() {}

  /**
   * 获取文件类型
   *
   * @param file 文件
   * @return 文件类型
   */
  public static String getFileType(final File file) {
    String fileType = null;
    final byte[] b = new byte[50];
    try {
      final InputStream is = new FileInputStream(file);
      is.read(b);
      fileType = FileUtils.getFileType(b);
      is.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return fileType;
  }

  /**
   * 获取文件类型
   *
   * @param fileBytes 文件二进制数据
   * @return 文件类型
   */
  public static String getFileType(final byte[] fileBytes) {
    final String fileTypeHex = String.valueOf(FileUtils.getFileHexString(fileBytes));
    for (final Map.Entry<String, String> entry : FileUtils.FILE_TYPE_MAP.entrySet()) {
      final String fileTypeHexValue = entry.getValue();
      if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static String getFileHexString(final byte[] b) {
    final StringBuilder stringBuilder = new StringBuilder();
    if (b == null || b.length <= 0) {
      return null;
    }
    for (final byte value : b) {
      final int v = value & 0xFF;
      final String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv);
    }
    return stringBuilder.toString();
  }
}
