package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 刷卡功能.
 * @author yuanqixun
 * date:2018-08-25 00:33
 */
@Data
public class SwipeCard implements Serializable {

  /**
   * 是否设置该会员卡支持拉出微信支付刷卡界面
   */
  @SerializedName("is_swipe_card")
  private boolean isSwipeCard;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
