package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPaySandboxSignKeyResult extends BaseWxPayResult {

  /**
   * <pre>
   * 沙箱密钥
   * sandbox_signkey
   * 否
   * 013467007045764
   * String(32)
   * 返回的沙箱密钥
   * </pre>
   */
  @XStreamAlias("sandbox_signkey")
  private String sandboxSignKey;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXML(Document d) {
    sandboxSignKey = readXMLString(d, "sandbox_signkey");
  }

}
