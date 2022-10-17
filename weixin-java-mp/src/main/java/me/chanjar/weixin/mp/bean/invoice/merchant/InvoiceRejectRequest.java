package me.chanjar.weixin.mp.bean.invoice.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 拒绝开票请求参数
 *
 * @author Mario Luo
 */
@Data
public class InvoiceRejectRequest implements Serializable {
  private static final long serialVersionUID = -5303749544133451879L;

  /**
   * 开票平台标示
   */
  @SerializedName("s_pappid")
  private String sPappid;

  /**
   * 订单id
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 拒绝原因
   */
  private String reason;

  /**
   * 引导用户跳转url
   */
  private String url;

}
