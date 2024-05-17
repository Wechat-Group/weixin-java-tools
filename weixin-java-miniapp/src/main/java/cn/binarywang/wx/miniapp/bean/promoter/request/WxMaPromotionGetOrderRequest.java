package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionGetOrderRequest implements Serializable {

  private static final long serialVersionUID = 3773454747090805733L;

  /*
    {
      "openid": "xxxxx",
      "mch_id": "xxxxx",
      "trade_no": "xxxxx",
      "out_trade_no": "xxxxx",
      "status": 1,
      "start_id": "123",
      "need_unionid": 1
    }
   */

  /**
   * 推广员的openid或unionid
   * 必填
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 商户号
   * 非必填
   */
  @SerializedName("mch_id")
  private String mchId;

  /**
   * 微信支付订单号
   * 非必填
   */
  @SerializedName("trade_no")
  private String tradeNo;

  /**
   * 商户订单号
   * 非必填
   */
  @SerializedName("out_trade_no")
  private String outTradeNo;

  /**
   * 订单状态 1：支付完成 2：退款受理
   * 非必填
   */
  @SerializedName("status")
  private Long status;

  /**
   * 用于分页时透传，单次拉取上限为1000，超过1000须分页
   * 非必填
   */
  @SerializedName("start_id")
  private String startId;

  /**
   * 默认返回openid，填1：返回unionid
   * 非必填
   */
  @SerializedName("need_unionid")
  private Long needUnionid;

  /**
   * 订单支付日期，格式为yyyyMMdd，例如20230801
   * 非必填
   */
  @SerializedName("date")
  private Long date;
}
