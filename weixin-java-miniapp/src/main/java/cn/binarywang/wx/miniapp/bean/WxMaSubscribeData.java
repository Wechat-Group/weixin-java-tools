package cn.binarywang.wx.miniapp.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 参考文档 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
 * </pre>
 */
@Data
@NoArgsConstructor
public class WxMaSubscribeData {
  private String name;
  private String value;
  private String color;

  public WxMaSubscribeData(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public WxMaSubscribeData(String name, String value, String color) {
    this.name = name;
    this.value = value;
    this.color = color;
  }


}
