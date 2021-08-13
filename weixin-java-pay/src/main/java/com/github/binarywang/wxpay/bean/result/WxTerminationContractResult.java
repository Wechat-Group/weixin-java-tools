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
 * @date 2021-08-02 5:41 下午
 *
 * <pre>
 *   主动解约返回值
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxTerminationContractResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 委托代扣协议ID
   */
  @XStreamAlias("contractId")
  private String contractId;

  /**
   * 模板ID
   */
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * 签约协议号
   */
  @XStreamAlias("contract_code")
  private String contractCode;


  @Override
  protected void loadXml(Document d) {
    contractId = readXmlString(d, "contract_id");
    planId = readXmlString(d, "plan_id");
    contractCode = readXmlString(d, "contract_code");
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
