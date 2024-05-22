package cn.binarywang.wx.miniapp.bean.delivery;


import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 传运单接口 follow_waybil
 *
 * 商户使用此接口向微信提供某交易单号对应的运单号。微信后台会跟踪运单的状态变化，在关键物流节点给下单用户推送消息通知。
 * </pre>
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FollowWaybillRequest implements Serializable {

  private static final long serialVersionUID = -7538739003766268386L;


  /**
   * 用户openid
   * <pre>
   * 是否必填： 是
   * 描述： 用户openid
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 寄件人手机号
   * <pre>
   * 是否必填： 否
   * 描述：
   * </pre>
   */
  @SerializedName("sender_phone")
  private String senderPhone;

  /**
   * 收件人手机号
   * <pre>
   * 是否必填： 否
   * 描述：部分运力需要用户手机号作为查单依据
   * </pre>
   */
  @SerializedName("receiver_phone")
  private String receiverPhone;

  /**
   * 运力id（运单号所属运力公司id），该字段从 <a href='https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/express_open_msg.html#_4-4%E8%8E%B7%E5%8F%96%E8%BF%90%E5%8A%9Bid%E5%88%97%E8%A1%A8get-delivery-list'>get_delivery_list</a> 获取。
   * <pre>
   * 是否必填： 否
   * 描述：该参数用于提高运单号识别的准确度；特别是对非主流快递公司，建议传入该参数，确保查询正确率。
   * </pre>
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 运单ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * 交易单号（微信支付生成的交易单号，一般以420开头）
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("trans_id")
  private String transId;


  /**
   * 点击落地页商品卡片跳转路径（建议为订单详情页path），不传默认跳转小程序首页。
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("order_detail_path")
  private String orderDetailPath;

  /**
   * 商品信息
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_info")
  private WaybillGoodsInfo goodsInfo;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
