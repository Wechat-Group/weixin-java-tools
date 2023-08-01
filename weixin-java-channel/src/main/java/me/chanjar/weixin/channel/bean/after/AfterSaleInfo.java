package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 售后单信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AfterSaleInfo implements Serializable {

  private static final long serialVersionUID = 6595670817781635247L;
  /** 售后单号 */
  @JsonProperty("after_sale_order_id")
  private String afterSaleOrderId;

  /** 售后状态 {@link me.chanjar.weixin.channel.enums.AfterSaleStatus} */
  @JsonProperty("status")
  private String status;

  /** 订单id */
  @JsonProperty("order_id")
  private String orderId;

  /** 买家身份标识 */
  @JsonProperty("openid")
  private String openid;

  /** 买家在开放平台的唯一标识符，若当前视频号小店已绑定到微信开放平台帐号下会返回 */
  @JsonProperty("unionid")
  private String unionid;

  /** 售后相关商品信息 */
  @JsonProperty("product_info")
  private AfterSaleProductInfo productInfo;

  /** 售后详情 */
  @JsonProperty("details")
  private AfterSaleDetail details;

  /** 退款详情 */
  @JsonProperty("refund_info")
  private RefundInfo refundInfo;

  /** 用户退货信息 */
  @JsonProperty("return_info")
  private ReturnInfo returnInfo;

  /** 商家上传的信息 */
  @JsonProperty("merchant_upload_info")
  private MerchantUploadInfo merchantUploadInfo;

  /** 创建时间 时间戳 秒 */
  @JsonProperty("create_time")
  private Long createTime;

  /** 更新时间 时间戳 秒 */
  @JsonProperty("update_time")
  private Long updateTime;

  /** 退款原因（后续新增的原因将不再有字面含义，请参考reason_text） */
  @JsonProperty("reason")
  private String reason;

  /** 退款原因解释 */
  @JsonProperty("reason_text")
  private String reasonText;

  /** 退款结果 */
  @JsonProperty("refund_resp")
  private RefundResp refundResp;

  /** 售后类型。REFUND:退款；RETURN:退货退款 */
  @JsonProperty("type")
  private String type;

  /** 纠纷id，该字段可用于获取纠纷信息 */
  @JsonProperty("complaint_id")
  private String complaintId;
}
