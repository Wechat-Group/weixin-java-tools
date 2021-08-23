package me.chanjar.weixin.common.util;

import cn.hutool.core.lang.Validator;
import me.chanjar.weixin.common.error.WxRuntimeException;

import java.nio.charset.StandardCharsets;

/**
 * 解决上传文件时候的一些问题
 *
 * @author caiqy
 */
public class ByteUtil {

  public static final int MAX_EXT_LENGTH = 10;

  /**
   * 组合新数组
   *
   * @param bytes the bytes
   * @param ext   文件后缀
   * @return byte [ ]
   */
  public static byte[] combine(byte[] bytes, String ext) {
    byte[] nb = new byte[bytes.length + MAX_EXT_LENGTH];
    if (ext != null && ext.length() > 0) {
      if (Validator.hasChinese(ext)) {
        throw new WxRuntimeException("文件后缀不允许存在中文");
      }
      if (ext.length() > MAX_EXT_LENGTH) {
        throw new WxRuntimeException("文件后缀不能超过{}位", MAX_EXT_LENGTH);
      }
      byte[] exts = ext.getBytes(StandardCharsets.UTF_8);
      System.arraycopy(exts, 0, nb, 0, exts.length);
    }
    System.arraycopy(bytes, 0, nb, MAX_EXT_LENGTH, bytes.length);
    return nb;
  }

  public static String getExt(byte[] bytes) {
    int i = 0;
    for (; i < MAX_EXT_LENGTH; i++) {
      if (bytes[i] == 0) {
        break;
      }
    }
    if (i == 0) {
      return "";
    }
    byte[] n = new byte[i];
    System.arraycopy(bytes, 0, n, 0, i);
    return new String(n);
  }

  public static byte[] getBody(byte[] bytes) {
    byte[] n = new byte[bytes.length - MAX_EXT_LENGTH];
    System.arraycopy(bytes, MAX_EXT_LENGTH, n, 0, n.length);
    return n;
  }
}
