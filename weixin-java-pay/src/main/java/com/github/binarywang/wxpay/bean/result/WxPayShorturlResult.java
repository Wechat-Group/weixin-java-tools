package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 * 转换短链接结果对象类
 * Created by Binary Wang on 2017-3-27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayShorturlResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = -2121902492357304418L;

  /**
   * <pre>
   * URL链接
   * short_url
   * 是
   * String(64)
   * weixin：//wxpay/s/XXXXXX
   * 转换后的URL
   * </pre>
   */
  @XStreamAlias("short_url")
  private String shortUrl;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXml(Document d) {
    shortUrl = readXmlString(d, "short_url");
  }

}
