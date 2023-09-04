package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayCreateWithdrawOrderRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("env")
  private Integer env;

  @SerializedName("withdraw_no")
  private String withdrawNo;
  @SerializedName("withdraw_amount")
  private String withdrawAmount; //提现的金额，单位元,不传的情况下表示全额提现

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
