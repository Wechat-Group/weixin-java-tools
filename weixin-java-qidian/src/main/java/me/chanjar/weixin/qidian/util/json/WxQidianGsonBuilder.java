package me.chanjar.weixin.qidian.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author someone
 */
public class WxQidianGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
