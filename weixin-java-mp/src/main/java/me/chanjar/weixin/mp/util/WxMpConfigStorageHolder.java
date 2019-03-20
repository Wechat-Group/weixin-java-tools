package me.chanjar.weixin.mp.util;


/**
 * @Author: yd
 * @Date: 2019-03-20 22:06
 */
public class WxMpConfigStorageHolder {

  private final static ThreadLocal<String> WX_MP_CONFIG_STORAGE_CHOSE = ThreadLocal.withInitial(() -> "default");

  public static String get() {
    return WX_MP_CONFIG_STORAGE_CHOSE.get();
  }

  public static void set(String label) {
    WX_MP_CONFIG_STORAGE_CHOSE.set(label);
  }

}
