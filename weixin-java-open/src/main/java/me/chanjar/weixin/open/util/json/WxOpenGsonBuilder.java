package me.chanjar.weixin.open.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizerInfo;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerOptionResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxOpenComponentAccessToken.class, new WxOpenComponentAccessTokenGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerAccessToken.class, new WxOpenAuthorizerAccessTokenGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizationInfo.class, new WxOpenAuthorizationInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerInfo.class, new WxOpenAuthorizerInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenQueryAuthResult.class, new WxOpenQueryAuthResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerInfoResult.class, new WxOpenAuthorizerInfoResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerOptionResult.class, new WxOpenAuthorizerOptionResultGsonAdapter());

  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
