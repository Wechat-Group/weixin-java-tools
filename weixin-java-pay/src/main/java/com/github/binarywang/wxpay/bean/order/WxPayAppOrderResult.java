package com.github.binarywang.wxpay.bean.order;

import lombok.Builder;
import lombok.Data;

/**
 * <pre>
 * APP支付调用统一下单接口后的组装所需参数的实现类
 * 参考 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12
 * Created by Binary Wang on 2017-9-1.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
public class WxPayAppOrderResult {
  private String sign;
  private String prepayId;
  private String partnerId;
  private String appId;
  private String packageValue;
  private String timeStamp;
  private String nonceStr;
}
