package me.chanjar.weixin.qidian.bean.result;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 换取二维码的Ticket
 *
 * @author chanjarster
 */
@Data
public class WxMpQrCodeTicket implements Serializable {
  private static final long serialVersionUID = 5777119669111011584L;

  protected String ticket;
  /**
   * 如果为-1，说明是永久
   */
  protected int expireSeconds = -1;
  protected String url;

  public static WxMpQrCodeTicket fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpQrCodeTicket.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
