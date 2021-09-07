package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;
import java.util.Objects;

/**
 * @author chenliang
 * @date 2021-08-02 5:17 下午
 *
 * <pre>
 *   公众号纯签约入参
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxMpEntrustRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 协议模板ID
   * plan_id
   * 是
   * String(28)
   * 12535
   * 协议模板ID，分为首次签约，支付中签约，重新签约
   * </pre>
   */
  @Required
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * <pre>
   * 签约协议号
   * contract_code
   * 是
   * String(32)
   * 100000
   * 商户侧签约协议号，由商户生成，只能是数字，大小写字母组成
   * </pre>
   */
  @Required
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * <pre>
   * 请求序列号
   * request_serial
   * 是
   * int(64)
   * 1000
   * 商户请求签约时的序列号，要求唯一性，禁止使用0开头的，用户排序，纯数字
   * </pre>
   */
  @Required
  @XStreamAlias("request_serial")
  private Long requestSerial;

  /**
   * <pre>
   * 用户账户展示名称
   * contract_display_account
   * 是
   * string(32)
   * 微信代扣
   * 签约用户的名称，用户页面展示，不支持符号表情
   * </pre>
   */
  @Required
  @XStreamAlias("contract_display_account")
  private String contractDisplayAccount;

  /**
   * <pre>
   * 回调通知URL
   * notify_url
   * 是
   * string(256)
   * https://weixin.qq.com
   * 用于接收签约成功消息的回调通知地址
   * </pre>
   */
  @Required
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 版本号
   * sign
   * 是
   * string(8)
   * 1.0
   * 固定值1.0
   * </pre>
   */
  @Required
  @XStreamAlias("version")
  private String version;

  /**
   * <pre>
   * 时间戳
   * timestamp
   * 是
   * string(10)
   * 1414488825
   * 系统当前时间，10位
   * </pre>
   */
  @Required
  @XStreamAlias("timestamp")
  private String timestamp;

  /**
   * <pre>
   * 返回web
   * return_web
   * 否
   * int
   * 1
   * 用来控制签约页面结束后的返回路径（不传签约后留在微信内），1 表示返回签约页面的referrer url,
   * 不填或获取不到referrer则不返回，跳转referrer url 时会自动带上from_wxpay=1
   * </pre>
   */
  @XStreamAlias("return_web")
  private Integer returnWeb;

  /**
   * <pre>
   * 商户测的用户标识
   * outerid
   * 否
   * String()
   * 陈*(101000203)
   * 用于多账户签约，同一个模板下要保持一致，取值和contractDisplayAccount取一样就行
   * </pre>
   */
  @XStreamAlias("outerid")
  private String outerId;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  /**
   * 是否需要nonce_str
   */
  @Override
  protected boolean needNonceStr() {
    return false;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("plan_id", planId);
    map.put("contract_code", contractCode);
    map.put("request_serial", String.valueOf(requestSerial));
    map.put("contract_display_account", contractDisplayAccount);
    map.put("notify_url", notifyUrl);
    map.put("version", version);
    map.put("timestamp", timestamp);
    if (Objects.nonNull(returnWeb)) {
      map.put("return_web", String.valueOf(returnWeb));
    }
    map.put("outerid", outerId);
  }
}
