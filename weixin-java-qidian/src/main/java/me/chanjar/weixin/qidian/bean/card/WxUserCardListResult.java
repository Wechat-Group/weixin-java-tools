package me.chanjar.weixin.qidian.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 用户已领卡券返回
 *
 * @author yang229
 * @date 2019/12/22
 */
@Data
public class WxUserCardListResult implements Serializable {
  private static final long serialVersionUID = 4348804828075982412L;

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
