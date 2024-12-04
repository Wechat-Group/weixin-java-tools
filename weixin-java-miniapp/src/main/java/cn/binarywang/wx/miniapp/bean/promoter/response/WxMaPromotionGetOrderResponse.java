package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetOrderResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 7815334184208585836L;

  /*
    {
      "order_list":
      [
        {
          "promoter_openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "openid": "xxxxx",
          "create_time": 1668667349,
          "path": "pages/xxxxx",
          "scene": 1077,
          "share_extra_info": "xxxxx",
          "mch_id": "xxxxx",
          "trade_no": "xxxxx",
          "out_trade_no": "xxxxx",
          "status": 1,
          "paid_amount": 150,
          "paid_time": 1668667360
        },
        {
          "promoter_openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "openid": "xxxxx",
          "create_time": 1668667349,
          "path": "pages/xxxxx",
          "scene": 1077,
          "share_extra_info": "xxxxx",
          "mch_id": "xxxxx",
          "trade_no": "xxxxx",
          "out_trade_no": "xxxxx",
          "status": 2,
          "paid_amount": 150,
          "paid_time": 1668667360,
          "paid_time": 1668668000
        }
      ],
      "total_cnt": 2,
      "start_id": "2",
      "errcode": 0,
      "errmsg": "OK"
    }
   */

  /**
   * 拉取的推广员总数
   */
  @SerializedName("order_list")
  private List<Order> orderList;

  /**
   * 拉取的推广员总数
   */
  @SerializedName("total_cnt")
  private Long totalCnt;

  /**
   * 用于分页时透传
   */
  @SerializedName("start_id")
  private Long startId;


  @Data
  public static class Order {

    /**
     * 推广员的openid或unionid
     */
    @SerializedName("promoter_openid")
    private String promoterOpenid;

    /**
     * 角色id
     */
    @SerializedName("role_id")
    private Long roleId;

    /**
     * 门店id
     */
    @SerializedName("retail_id")
    private String retailId;

    /**
     * 推广员参数
     */
    @SerializedName("extra_info")
    private String extraInfo;

    /**
     * 付款用户的openid或unionid
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 触达时间秒级时间戳
     */
    @SerializedName("create_time")
    private Long createTime;

    /**
     * 触达path
     */
    @SerializedName("path")
    private String path;

    /**
     * 触达场景值，枚举值参考场景值列表
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/reference/scene-list.html">场景值列表</a>
     */
    @SerializedName("scene")
    private Long scene;

    /**
     * 生成分享素材时的自定义参数
     */
    @SerializedName("share_extra_info")
    private String shareExtraInfo;

    /**
     * 商户号
     */
    @SerializedName("mch_id")
    private String mchId;

    /**
     * 微信支付订单号
     */
    @SerializedName("trade_no")
    private String tradeNo;

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 订单状态 1：支付完成 2：退款受理
     */
    @SerializedName("status")
    private Long status;

    /**
     * 用户支付金额，单位为分
     */
    @SerializedName("paid_amount")
    private Long paidAmount;

    /**
     * 支付完成时间
     */
    @SerializedName("paid_time")
    private Long paidTime;

    /**
     * 退款创建时间
     */
    @SerializedName("refunded_time")
    private Long refundedTime;
  }
}
