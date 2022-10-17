package me.chanjar.weixin.cp.bean.school;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取订单详情.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpTrade extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  /**
   * 微信交易单号
   */
  @SerializedName("transaction_id")
  private String transactionId;

  /**
   * 交易时间
   */
  @SerializedName("pay_time")
  private Long payTime;

  /**
   * From json wx cp trade.
   *
   * @param json the json
   * @return the wx cp trade
   */
  public static WxCpTrade fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTrade.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
