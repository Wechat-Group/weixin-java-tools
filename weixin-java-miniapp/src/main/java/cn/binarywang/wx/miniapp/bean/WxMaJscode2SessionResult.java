package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <pre>
 * code换取session_key接口的响应
 * 文档地址：https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject
 * 微信返回报文：{"session_key":"nzoqhc3OnwHzeTxJs+inbQ==","openid":"oVBkZ0aYgDMDIywRdgPW8-joxXc4"}
 * </pre>
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxMaJscode2SessionResult implements Serializable {
  private static final long serialVersionUID = -1060216618475607933L;

  @SerializedName("session_key")
  private String sessionKey;

  @SerializedName("openid")
  private String openid;

  @SerializedName("unionid")
  private String unionid;

  public static WxMaJscode2SessionResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaJscode2SessionResult.class);
  }

}
