package me.chanjar.weixin.common.util.crypto;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Daniel Qian on 14/10/19.
 */
public class SHA1 {

  /**
   * 串接arr参数，生成sha1 digest
   */
  public static String gen(String... arr) {
    Arrays.sort(arr);
    StringBuilder sb = new StringBuilder();
    for (String a : arr) {
      sb.append(a);
    }
    return DigestUtils.sha1Hex(sb.toString());
  }

  /**
   * 用&串接arr参数，生成sha1 digest
   */
  public static String genWithAmple(String... arr) {
    Arrays.sort(arr);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      String a = arr[i];
      sb.append(a);
      if (i != arr.length - 1) {
        sb.append('&');
      }
    }
    return DigestUtils.sha1Hex(sb.toString());
  }
}
