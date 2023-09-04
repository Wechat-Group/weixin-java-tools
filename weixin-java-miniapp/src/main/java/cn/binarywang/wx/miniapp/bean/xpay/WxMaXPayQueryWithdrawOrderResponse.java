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
public class WxMaXPayQueryWithdrawOrderResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("withdraw_no")
  private String withdrawNo;
  @SerializedName("withdraw_amount")
  private String withdrawAmount; //提现的金额，单位元,不传的情况下表示全额提现

  @SerializedName("wx_withdraw_no")
  private String wxWithdrawNo;

  @SerializedName("status")
  private Integer status;
  @SerializedName("withdraw_success_timestamp")
  private String withdrawSuccessTimestamp;
  @SerializedName("create_time")
  private String createTime;
  @SerializedName("fail_reason")
  private String failReason;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
