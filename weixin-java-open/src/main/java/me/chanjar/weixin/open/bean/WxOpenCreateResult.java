package me.chanjar.weixin.open.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

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
public class WxOpenCreateResult implements Serializable {

  @SerializedName("open_appid")
  private String openAppid;

  @SerializedName("errcode")
  private String errcode;

  @SerializedName("errmsg")
  private String errmsg;

  public static WxOpenCreateResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenCreateResult.class);
  }

}
