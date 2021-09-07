package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author chenliang
 * @date 2021-08-02 5:37 下午
 *
 * <pre>
 *   h5纯签约后结果
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxH5EntrustResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 业务结果描述
   */
  @XStreamAlias("result_msg")
  private String resultMsg;

  /**
   * 跳转url
   */
  @XStreamAlias("redirect_url")
  private String redirectUrl;

  @Override
  protected void loadXml(Document d) {
    resultMsg = readXmlString(d, "result_msg");
    redirectUrl = readXmlString(d, "redirect_url");
  }
}
