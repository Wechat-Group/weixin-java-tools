package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author chenliang
 * @date 2021-08-02 5:39 下午
 *
 * <pre>
 *   委托扣款返回值
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxWithholdResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 临时字段
   */
  @XStreamAlias("temp")
  private String temp;


  @Override
  protected void loadXml(Document d) {
    temp = readXmlString(d, "temp");
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
