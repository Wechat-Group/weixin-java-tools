package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 *  关闭订单请求对象类
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WxPayOrderCloseV3Request implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：直连商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  直连商户的商户号，由微信支付生成并下发。
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;
  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string[6,32]
   * 描述：
   *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
   *  示例值：1217752501201407033233368018
   * </pre>
   */
  private transient String outTradeNo;
}
