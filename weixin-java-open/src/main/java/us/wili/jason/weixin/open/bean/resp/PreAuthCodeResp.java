package us.wili.jason.weixin.open.bean.resp;


import us.wili.jason.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * Created by JasonY on 17/10/24.
 */
public class PreAuthCodeResp implements Serializable {
    private static final long serialVersionUID = -6951701441284672550L;

    private String preAuthCode;
    private int expiresIn = -1;

    public static PreAuthCodeResp fromJson(String json) {
        return WxOpenGsonBuilder.create().fromJson(json, PreAuthCodeResp.class);
    }

    public String getPreAuthCode() {
        return preAuthCode;
    }

    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
