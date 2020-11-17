package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 *
 * @author leeis
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CashCardCreateRequest extends AbstractCardCreateRequest implements Serializable {
  private static final long serialVersionUID = 8251635683908302125L;

  @SerializedName("card_type")
  private String cardType = "CASH";

  private CashCard cash;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
