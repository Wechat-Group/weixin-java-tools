package me.chanjar.weixin.cp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.util.json.WxErrorAdapter;
import me.chanjar.weixin.cp.bean.WxCpChat;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpTag;
import me.chanjar.weixin.cp.bean.WxCpUser;
import java.util.Objects;

/**
 * @author Daniel Qian
 */
public class WxCpGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpChat.class, new WxCpChatGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxCpMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
  }

  public static Gson create() {
    if (Objects.isNull(GSON_INSTANCE)) {
      synchronized (GSON_INSTANCE) {
        if (Objects.isNull(GSON_INSTANCE)) {
          GSON_INSTANCE = INSTANCE.create();
        }
      }
    }
    return GSON_INSTANCE;
  }

}
