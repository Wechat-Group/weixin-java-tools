package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 分享场景
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ShareScene {
  /** 1 直播间 */
  LIVE_ROOM(1, "直播间"),
  /** 2 橱窗 */
  WINDOW(2, "橱窗"),
  /** 3 短视频 */
  SHORT_VIDEO(3, "短视频"),
  /** 4 视频号主页 */
  CHANNEL_HOME(4, "视频号主页"),
  /** 5 商品详情页 */
  PRODUCT_DETAIL(5, "商品详情页"),

  ;


  private final Integer key;
  private final String value;

  ShareScene(Integer key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
