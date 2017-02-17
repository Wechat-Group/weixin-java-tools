package me.chanjar.weixin.common.util.http;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class URIUtil {

  private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

  public static String encodeURIComponent(String input) {
    if (StringUtils.isEmpty(input)) {
      return input;
    }

    int l = input.length();
    StringBuilder o = new StringBuilder(l * 3);
    try {
      for (int i = 0; i < l; i++) {
        String e = input.substring(i, i + 1);
        if (ALLOWED_CHARS.indexOf(e) == -1) {
          byte[] b = e.getBytes("utf-8");
          o.append(getHex(b));
          continue;
        }
        o.append(e);
      }
      return o.toString();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return input;
  }

  private static String getHex(byte buf[]) {
    StringBuilder o = new StringBuilder(buf.length * 3);
    for (int i = 0; i < buf.length; i++) {
      int n = buf[i] & 0xff;
      o.append("%");
      if (n < 0x10) {
        o.append("0");
      }
      o.append(Long.toString(n, 16).toUpperCase());
    }
    return o.toString();
  }
}
