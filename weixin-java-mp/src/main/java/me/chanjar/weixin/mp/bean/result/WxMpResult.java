package me.chanjar.weixin.mp.bean.result;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 基础的微信公众号平台请求结果.
 */
@Data
public class WxMpResult implements Serializable {
  private static final long serialVersionUID = 2101652152604850904L;
  protected String errcode;
  protected String errmsg;

  /**
   * 请求是否成功.
   */
  public boolean isSuccess() {
    return StringUtils.equalsIgnoreCase(errcode, "0");
  }

  public static WxMpResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
