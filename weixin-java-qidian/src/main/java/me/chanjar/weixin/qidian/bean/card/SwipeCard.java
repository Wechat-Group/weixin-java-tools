package me.chanjar.weixin.qidian.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

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
