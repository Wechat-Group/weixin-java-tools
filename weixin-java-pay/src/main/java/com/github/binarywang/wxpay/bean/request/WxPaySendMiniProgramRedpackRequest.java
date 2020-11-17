package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 发送小程序红包请求参数对象.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPaySendMiniProgramRedpackRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = -2035425086824987567L;

  @Override
  protected String[] getIgnoredParamsForSign() {
    return new String[]{"sign_type", "sub_appid"};
  }

  /**
   * mch_billno.
   * 商户订单号（每个订单号必须唯一）
   * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。  接口根据商户订单号支持重入，如出现超时可再调用。
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * send_name.
   * 商户名称
   * 红包发送者名称
   */
  @XStreamAlias("send_name")
  private String sendName;

  /**
   * re_openid.
   * 接受红包的用户   用户在wxappid下的openid
   */
  @XStreamAlias("re_openid")
  private String reOpenid;

  /**
   * total_amount.
   * 红包总额
   */
  @XStreamAlias("total_amount")
  private Integer totalAmount;

  /**
   * total_num
   * 红包发放总人数
   */
  @XStreamAlias("total_num")
  private Integer totalNum;

  /**
   * wishing.
   * 红包祝福语
   */
  @XStreamAlias("wishing")
  private String wishing;

  /**
   * act_name.
   * 活动名称
   */
  @XStreamAlias("act_name")
  private String actName;

  /**
   * remark.
   * 备注
   */
  @XStreamAlias("remark")
  private String remark;

  /**
   * 通知用户形式	.
   * 通过JSAPI方式领取红包,小程序红包固定传MINI_PROGRAM_JSAPI
   */
  @XStreamAlias("notify_way")
  private String notifyWay = "MINI_PROGRAM_JSAPI";

  /**
   * <pre>
   * 发放红包使用场景，红包金额大于200时必传
   * PRODUCT_1:商品促销
   * PRODUCT_2:抽奖
   * PRODUCT_3:虚拟物品兑奖
   * PRODUCT_4:企业内部福利
   * PRODUCT_5:渠道分润
   * PRODUCT_6:保险回馈
   * PRODUCT_7:彩票派奖
   * PRODUCT_8:税务刮奖
   * </pre>
   */
  @XStreamAlias("scene_id")
  private String sceneId;

  /**
   * wxappid.
   * 微信分配的公众账号ID（企业号corpid即为此appId）。
   * 接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），
   * 不能为APP的appid（在open.weixin.qq.com申请的）
   */
  @XStreamAlias("wxappid")
  private String wxAppid;

  @Override
  protected void checkConstraints() {

  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("mch_billno", mchBillNo);
    map.put("send_name", sendName);
    map.put("re_openid", reOpenid);
    map.put("total_amount", totalAmount.toString());
    map.put("total_num", totalNum.toString());
    map.put("wishing", wishing);
    map.put("act_name", actName);
    map.put("remark", remark);
    map.put("notify_way", notifyWay);
    map.put("scene_id", sceneId);
    map.put("wxappid", wxAppid);
  }

  @Override
  public String getAppid() {
    return this.wxAppid;
  }

  @Override
  public void setAppid(String appid) {
    this.wxAppid = appid;
  }

}
