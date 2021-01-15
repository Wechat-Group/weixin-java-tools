package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author : cofedream
 * @date : 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingMerchantRatioQueryRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 2773455587673225334L;

  public ProfitSharingMerchantRatioQueryRequest(String subMchId) {
    this.subMchId = subMchId;
  }

  @Override
  protected void checkConstraints() throws WxPayException {
    // 目前仅支持HMAC-SHA256.
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }

  @Override
  public boolean ignoreAppid() {
    return true;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
  }
}
