package me.chanjar.weixin.mp.bean.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * @Date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class DiscountCard extends Card implements Serializable {
  private static final long serialVersionUID = 1704610082472315077L;

  /**
   * 折扣券专用，表示打折额度（百分比）。填30就是七折。
   */
  private int discount;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static DiscountCard fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, DiscountCard.class);
  }
}
