package cn.binarywang.wx.miniapp.util.json;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaGsonBuilder {
  private static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMaTemplateMessage.class, new WxMaTemplateMessageGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
