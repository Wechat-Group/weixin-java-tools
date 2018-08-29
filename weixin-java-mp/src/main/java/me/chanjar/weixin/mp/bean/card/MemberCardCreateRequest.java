package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
public class MemberCardCreateRequest implements Serializable {
  @SerializedName("card_type")
  private String cardType = "MEMBER_CARD";

  @SerializedName("member_card")
  private MemberCard memberCard;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
