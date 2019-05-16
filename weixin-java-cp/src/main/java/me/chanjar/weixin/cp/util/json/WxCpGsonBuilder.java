package me.chanjar.weixin.cp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.util.json.WxErrorAdapter;
import me.chanjar.weixin.cp.bean.*;

/**
 * @author Daniel Qian
 */
public class WxCpGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpChat.class, new WxCpChatGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxCpMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
