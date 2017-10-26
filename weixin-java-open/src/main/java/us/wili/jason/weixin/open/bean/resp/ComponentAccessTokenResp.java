package us.wili.jason.weixin.open.bean.resp;

import us.wili.jason.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * Created by JasonY on 17/10/24.
 */
public class ComponentAccessTokenResp implements Serializable {
    private static final long serialVersionUID = -7342177058851839617L;

    private String componentAccessToken;
    private int expiresIn = -1;

    public static ComponentAccessTokenResp fromJson(String json) {
        return WxOpenGsonBuilder.create().fromJson(json, ComponentAccessTokenResp.class);
    }

    public String getComponentAccessToken() {
        return componentAccessToken;
    }

    public void setComponentAccessToken(String componentAccessToken) {
        this.componentAccessToken = componentAccessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
