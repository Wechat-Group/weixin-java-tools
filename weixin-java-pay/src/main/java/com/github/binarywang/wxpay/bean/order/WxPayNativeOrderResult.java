package com.github.binarywang.wxpay.bean.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 微信扫码支付统一下单后发起支付拼接所需参数实现类
 * Created by Binary Wang on 2017-9-1.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@AllArgsConstructor
public class WxPayNativeOrderResult implements Serializable {
  private String codeUrl;
}
