package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.w3c.dom.Document;

/**
 * @author chenliang
 * @date 2021-08-02 4:59 下午
 * <pre>
 *   微信签约/解约 回调结果
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxSignStatusNotifyResult extends BaseWxPayResult {

  private static final long serialVersionUID = 1L;

  /**
   * 签约协议号
   */
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * 模板ID
   */
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * 用户表示
   */
  @XStreamAlias("openid")
  private String openId;

  /**
   * 变更类型， ADD：签约，DELETE：解约
   */
  @XStreamAlias("change_type")
  private String changeType;

  /**
   * 操作时间
   */
  @XStreamAlias("operate_time")
  private String operateTime;

  /**
   * 委托代扣协议ID
   */
  @XStreamAlias("contract_id")
  private String contractId;

  /**
   * 协议到期时间，ADD会有，长期有效，忽略
   * 非必传
   */
  @XStreamAlias("contract_expired_time")
  private String contractExpiredTime;

  /**
   * DELETE时返回，0: 未解约，1：有效期过期自动解约，2：用户主动解约，3：商户api解约，4：商户平台解约，5：用户账号注销
   * 非必传
   */
  @XStreamAlias("contract_termination_mode")
  private Integer contractTerminationMode;

  /**
   * 请求序列号
   */
  @XStreamAlias("request_serial")
  private Long requestSerial;

  @Override
  public void checkResult(WxPayService wxPayService, String signType, boolean checkSuccess) throws WxPayException {
    //防止伪造成功通知
    if (WxPayConstants.ResultCode.SUCCESS.equals(getReturnCode()) && getSign() == null) {
      throw new WxPayException("伪造的通知！");
    }

    super.checkResult(wxPayService, signType, checkSuccess);
  }

  /**
   * From xml wx sign notify result.
   *
   * @param xmlString the xml string
   * @return the wx sign result
   */
  public static WxSignStatusNotifyResult fromXML(String xmlString) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxSignStatusNotifyResult.class);
    WxSignStatusNotifyResult result = (WxSignStatusNotifyResult) xstream.fromXML(xmlString);
    result.setXmlString(xmlString);
    return result;
  }

  @Override
  protected void loadXml(Document d) {
    contractCode = readXmlString(d, "contract_code");
    planId = readXmlString(d, "plan_id");
    openId = readXmlString(d, "openid");
    changeType = readXmlString(d, "change_type");
    operateTime = readXmlString(d, "operate_time");
    contractId = readXmlString(d, "contract_id");
    contractExpiredTime = readXmlString(d, "contract_expired_time");
    contractTerminationMode = readXmlInteger(d, "contract_termination_mode");
    requestSerial = readXmlLong(d, "request_serial");
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
