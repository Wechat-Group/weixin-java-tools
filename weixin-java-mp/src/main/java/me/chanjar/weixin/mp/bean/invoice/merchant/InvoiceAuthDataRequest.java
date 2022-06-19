package me.chanjar.weixin.mp.bean.invoice.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取电子开票用户授权数据
 *
 * @author Mario Luo
 */
@Data
public class InvoiceAuthDataRequest implements Serializable {
  private static final long serialVersionUID = -7423619297443219650L;

  /**
   * 开票平台在微信的标识号，商户需要找开票平台提供
   */
  @SerializedName("s_pappid")
  private String sPappid;

  /**
   * 订单id，在商户内单笔开票请求的唯一识别号
   */
  @SerializedName("order_id")
  private String orderId;

}
