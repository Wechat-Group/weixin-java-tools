package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 合单支付订单只能使用此合单关单api完成关单。
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_12.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
public class CombineCloseRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：合单商户appid
   * 变量名：combine_appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  合单发起方的appid。
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "combine_appid")
  private String combineAppid;
  /**
   * <pre>
   * 字段名：合单商户订单号
   * 变量名：combine_out_trade_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   *  示例值：P20150806125346
   * </pre>
   */
  private transient String combineOutTradeNo;
  /**
   * <pre>
   * 字段名：子单信息
   * 变量名：sub_orders
   * 是否必填：是
   * 类型：array
   * 描述：
   *  最多支持子单条数：10
   * </pre>
   */
  @SerializedName(value = "sub_orders")
  private List<SubOrders> subOrders;

  @Data
  @NoArgsConstructor
  public static class SubOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：子单商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  子单发起方商户号，必须与发起方appid有绑定关系。
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "mchid")
    private String mchid;
    /**
     * <pre>
     * 字段名：子单商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[6,32]
     * 描述：
     *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     *  示例值：20150806125346
     * </pre>
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;
  }
}
