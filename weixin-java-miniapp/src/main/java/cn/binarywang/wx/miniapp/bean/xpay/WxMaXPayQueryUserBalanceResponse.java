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
public class WxMaXPayQueryUserBalanceResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("balance")
  private Long balance;

  @SerializedName("present_balance")
  private Long presentBalance;
  @SerializedName("sum_save")
  private Long sumSave;
  @SerializedName("sum_present")
  private Long sumPresent;
  @SerializedName("sum_balance")
  private Long sumBalance;
  @SerializedName("sum_cost")
  private Long sumCost;
  @SerializedName("first_save_flag")
  private Boolean firstSaveFlag;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
