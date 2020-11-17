package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 * 撤销订单响应结果类
 * Created by Binary Wang on 2017-3-23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayOrderReverseResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 3615350465758009338L;
  /**
   * <pre>
   * 是否重调
   * recall
   * 是
   * String(1)
   * Y
   * 是否需要继续调用撤销，Y-需要，N-不需要
   * </pre>
   **/
  @XStreamAlias("recall")
  private String isRecall;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXml(Document d) {
    isRecall = readXmlString(d, "recall");
  }

}
