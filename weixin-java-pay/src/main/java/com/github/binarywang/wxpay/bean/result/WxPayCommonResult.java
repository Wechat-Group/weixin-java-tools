package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付结果仅包含有return 和result等相关信息的的属性类
 * Created by Binary Wang on 2017-01-09.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
public class WxPayCommonResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = -8051324891539367420L;

  @Override
  protected void loadXml(Document d) {
  }
}
