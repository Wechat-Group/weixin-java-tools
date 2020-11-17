package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 *  授权码查询openid接口请求结果类
 * Created by Binary Wang on 2017-3-27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayAuthcode2OpenidResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = -2409408725777108398L;
  /**
   * <pre>
   *   用户标识.
   *   openid
   *   是
   *   String(128)
   *   用户在商户appid下的唯一标识
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXml(Document d) {
    openid = readXmlString(d, "openid");
  }

}
