package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Document;

/**
 * <pre>
 *  企业付款获取RSA加密公钥接口返回结果类
 *  Created by BinaryWang on 2017/12/20.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class GetPublicKeyResult extends BaseWxPayResult {
  /**
   * 商户号.
   */
  @XStreamAlias("mch_id")
  private String mchId;

  /**
   * 密钥
   */
  @XStreamAlias("pub_key")
  private String pubKey;

  @Override
  protected void loadXML(Document d) {
    mchId = readXMLString(d, "mch_id");
    pubKey = readXMLString(d, "pub_key");
  }
}
