package me.chanjar.weixin.open.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.open.bean.resp.*;

/**
 * Created by JasonY on 17/10/24.
 */
public class WxOpenGsonBuilder {

    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(ComponentAccessTokenResp.class, new ComponentAccessTokenRespAdapter());
        INSTANCE.registerTypeAdapter(PreAuthCodeResp.class, new PreAuthCodeRespAdapter());
        INSTANCE.registerTypeAdapter(QueryAuthResp.class, new QueryAuthRespAdapter());
        INSTANCE.registerTypeAdapter(AuthorizerTokenResp.class, new AuthorizerTokenRespAdapter());
        INSTANCE.registerTypeAdapter(AuthorizerInfoResp.class, new AuthorizerInfoRespAdapter());
    }

    public static Gson create() {
        return INSTANCE.create();
    }

}
