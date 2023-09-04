package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
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
public class WxMaXPayCreateWithdrawOrderResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("withdraw_no")
  private String withdrawNo;
  @SerializedName("wx_withdraw_no")
  private String wxWithdrawNo;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
