package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 支付功能.
 * @author yuanqixun
 * date:2018-08-25 00:33
 */
@Data
public class PayInfo implements Serializable {

  /**
   * 刷卡功能
   */
  @SerializedName("swipe_card")
  private SwipeCard swipeCard;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
