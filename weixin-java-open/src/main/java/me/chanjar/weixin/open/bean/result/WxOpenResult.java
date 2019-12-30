package me.chanjar.weixin.open.bean.result;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 基础的微信开放平台请求结果.
 *
 * @author yqx
 * @date 2018/10/1
 */
@Data
public class WxOpenResult implements Serializable {
  private static final long serialVersionUID = 2101652152604850904L;
  protected String errcode;
  protected String errmsg;

  /**
   * 请求是否成功.
   */
  public boolean isSuccess() {
    return StringUtils.equalsIgnoreCase(errcode, "0");
  }

  public static WxOpenResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenResult.class);
  }

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
