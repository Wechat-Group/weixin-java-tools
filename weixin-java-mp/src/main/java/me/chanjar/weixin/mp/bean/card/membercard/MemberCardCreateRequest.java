package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 创建会员卡请求对象.
 *
 * @author yuanqixun
 */
@Data
public class MemberCardCreateRequest implements Serializable {
  private static final long serialVersionUID = -1044836608401698097L;

  @SerializedName("card_type")
  private String cardType = "MEMBER_CARD";

  @SerializedName("member_card")
  private MemberCard memberCard;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
