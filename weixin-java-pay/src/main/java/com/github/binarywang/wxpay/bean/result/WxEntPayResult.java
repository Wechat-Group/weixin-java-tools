package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 企业付款返回结果
 * 请使用{@link EntPayResult}
 */
@XStreamAlias("xml")
@Deprecated
public class WxEntPayResult extends EntPayResult {
  public static WxEntPayResult createFrom(EntPayResult entPayResult) {
    WxEntPayResult result = new WxEntPayResult();
    try {
      BeanUtils.copyProperties(result, entPayResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
