package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author chenliang
 * @date 2021-08-02 5:38 下午
 *
 * <pre>
 *   支付中签约返回结果
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxPayEntrustResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 预签约结果
   */
  @XStreamAlias("contract_result_code")
  private String contractResultCode;

  /**
   * 预约签约错误码
   */
  @XStreamAlias("contract_err_code")
  private String contractErrCode;

  /**
   * 预签约错误描述
   */
  @XStreamAlias("contract_err_code_des")
  private String contractErrCodeDes;

  /**
   * 预支付ID
   */
  @XStreamAlias("prepay_id")
  private String prepayId;

  /**
   * 交易类型
   */
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * 二维码链接
   * 非必传
   */
  @XStreamAlias("code_url")
  private String codeUrl;

  /**
   * 模板ID
   * 非必传
   */
  @XStreamAlias("plan_id")
  private Integer planId;

  /**
   * 请求序列号
   * 非必传
   */
  @XStreamAlias("request_serial")
  private Long requestSerial;

  /**
   * 签约协议号
   * 非必传
   */
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * 用户账户展示名称
   * 非必传
   */
  @XStreamAlias("contract_display_account")
  private String contractDisplayAccount;

  /**
   * 支付跳转链接
   * 非必传
   */
  @XStreamAlias("mweb_url")
  private String mwebUrl;

  /**
   * 商户订单号
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;


  @Override
  protected void loadXml(Document d) {
    contractResultCode = readXmlString(d, "contract_result_code");
    contractErrCode = readXmlString(d, "contract_err_code");
    contractErrCodeDes = readXmlString(d, "contract_err_code_des");
    prepayId = readXmlString(d, "prepay_id");
    tradeType = readXmlString(d, "trade_type");
    codeUrl = readXmlString(d, "code_url");
    planId = readXmlInteger(d, "plan_id");
    requestSerial = readXmlLong(d, "request_serial");
    contractCode = readXmlString(d, "contract_code");
    contractDisplayAccount = readXmlString(d, "contract_display_account");
    mwebUrl = readXmlString(d, "mweb_url");
    outTradeNo = readXmlString(d, "out_trade_no");
  }

  /**
   * 校验返回结果签名.
   *
   * @param wxPayService the wx pay service
   * @param signType     签名类型
   * @param checkSuccess 是否同时检查结果是否成功
   * @throws WxPayException the wx pay exception
   */

  @Override
  public void checkResult(WxPayService wxPayService, String signType, boolean checkSuccess) throws WxPayException {
    //校验返回结果签名
    Map<String, String> map = toMap();
    if (getSign() != null && !SignUtils.checkSign(map, signType, wxPayService.getConfig().getMchKey())) {
      this.getLogger().debug("校验结果签名失败，参数：{}", map);
      throw new WxPayException("参数格式校验错误！");
    }

    //校验结果是否成功
    if (checkSuccess) {
      List<String> successStrings = Lists.newArrayList(WxPayConstants.ResultCode.SUCCESS, "");
      if (!successStrings.contains(StringUtils.trimToEmpty(getReturnCode()).toUpperCase())
        || !successStrings.contains(StringUtils.trimToEmpty(getResultCode()).toUpperCase())) {
        StringBuilder errorMsg = new StringBuilder();
        if (getReturnCode() != null) {
          errorMsg.append("返回代码：").append(getReturnCode());
        }
        if (getReturnMsg() != null) {
          errorMsg.append("，返回信息：").append(getReturnMsg());
        }
        if (getResultCode() != null) {
          errorMsg.append("，结果代码：").append(getResultCode());
        }
        if (getErrCode() != null) {
          errorMsg.append("，错误代码：").append(getErrCode());
        }
        if (getErrCodeDes() != null) {
          errorMsg.append("，错误详情：").append(getErrCodeDes());
        }
        if (getContractErrCode() != null) {
          errorMsg.append(",预签约错误代码：").append(getContractErrCode());
        }
        if (getContractErrCodeDes() != null) {
          errorMsg.append(",预签约错误描述:").append(getContractErrCodeDes());
        }
        if (getContractResultCode() != null) {
          errorMsg.append(",预签约结果：").append(getContractResultCode());
        }


        this.getLogger().warn("\n结果业务代码异常，返回结果：{},\n{}", map, errorMsg.toString());
        throw WxPayException.from(this);
      }
    }
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
