package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.result.WxMpResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * 用户已领卡券返回
 * @author yang229
 * @date 2019/12/22
 */
@Data
public class WxUserCardListResult extends WxMpResult implements java.io.Serializable {

  /**
   * 卡券列表
   */
  @SerializedName("card_list")
  private List<UserCard> cardList;

  /**
   * 是否有可用的朋友的券
   */
  @SerializedName("has_share_card")
  private Boolean hasShareCard;

  public static WxUserCardListResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxUserCardListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
