package com.github.binarywang.wxpay.bean.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <pre>
 * 微信H5支付统一下单后发起支付拼接所需参数实现类.
 * Created by Binary Wang on 2018-4-21.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@AllArgsConstructor
public class WxPayMwebOrderResult {
  @XStreamAlias("mwebUrl")
  private String mwebUrl;
}
