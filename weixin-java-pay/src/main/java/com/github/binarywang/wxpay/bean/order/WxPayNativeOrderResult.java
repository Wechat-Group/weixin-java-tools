package com.github.binarywang.wxpay.bean.order;

import lombok.Builder;
import lombok.Data;

/**
 * <pre>
 * 微信扫码支付统一下单后发起支付拼接所需参数实现类
 * Created by Binary Wang on 2017-9-1.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
public class WxPayNativeOrderResult {
  private String codeUrl;
}
