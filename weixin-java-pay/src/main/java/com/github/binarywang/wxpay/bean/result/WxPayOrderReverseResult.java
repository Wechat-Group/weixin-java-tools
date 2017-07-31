package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 * 撤销订单响应结果类
 * Created by Binary Wang on 2017-3-23.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayOrderReverseResult extends WxPayBaseResult {

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

  public String getIsRecall() {
    return this.isRecall;
  }

  public void setIsRecall(String isRecall) {
    this.isRecall = isRecall;
  }
}
