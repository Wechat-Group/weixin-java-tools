package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * created on  2022/8/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopPayGetOrderResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -3329915987130142268L;

  @SerializedName("order")
  private OrderBean order;

  @Data
  public static class OrderBean implements Serializable {
    @SerializedName("trade_no")
    private String tradeNo;
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("combine_trade_no")
    private String combineTradeNo;
    @SerializedName("mchid")
    private String mchid;
    @SerializedName("create_time")
    private int createTime;
    @SerializedName("update_time")
    private int updateTime;
    @SerializedName("pay_time")
    private int payTime;
    @SerializedName("expire_time")
    private int expireTime;
    @SerializedName("amount")
    private int amount;
    @SerializedName("description")
    private String description;
    @SerializedName("profit_sharing_delay")
    private int profitSharingDelay;
    @SerializedName("profit_sharing_frozen")
    private int profitSharingFrozen;
    @SerializedName("refund_list")
    private List<RefundListBean> refundList;
    @SerializedName("profit_sharing_list")
    private List<ProfitSharingListBean> profitSharingList;

    @Data
    public static class RefundListBean implements Serializable {
      @SerializedName("amount")
      private int amount;
      @SerializedName("create_time")
      private int createTime;
      @SerializedName("finish_time")
      private int finishTime;
      @SerializedName("result")
      private String result;
      @SerializedName("refund_id")
      private String refundId;
      @SerializedName("refund_no")
      private String refundNo;
    }

    @Data
    public static class ProfitSharingListBean implements Serializable {
      /**
       * mchid : 1623426221
       * amount : 1
       * create_time : 1648880985
       * finish_time : 1648881016
       * result : SUCCESS
       * profit_sharing_id : 30002107912022040228952584675
       * profit_sharing_no : 512341
       */

      @SerializedName("mchid")
      private String mchid;
      @SerializedName("amount")
      private int amount;
      @SerializedName("create_time")
      private int createTime;
      @SerializedName("finish_time")
      private int finishTime;
      @SerializedName("result")
      private String result;
      @SerializedName("profit_sharing_id")
      private String profitSharingId;
      @SerializedName("profit_sharing_no")
      private String profitSharingNo;
    }
  }
}

