package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * 发送企业红包
 *
 * @author wuyong
 * @date 2019-12-1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class EntPayRedpackRequest extends BaseWxPayRequest {

  private static final long serialVersionUID = 1L;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  /**
   * 商户订单号（每个订单号必须唯一）
   * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。  接口根据商户订单号支持重入，如出现超时可再调用。
   * 必填：是
   */
  @Required
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * 微信分配的公众账号ID（企业微信corpid即为此appId）
   * 必填：是
   */
  @Required
  @XStreamAlias("wxappid")
  private String wxAppId;

  /**
   * 发送者名称
   * 以个人名义发红包，红包发送者名称(需要utf-8格式)。与agentid互斥，二者只能填一个。
   * 必填：否
   */
  @XStreamAlias("sender_name")
  private String senderName;

  /**
   * 发送红包的应用id
   * 以企业应用的名义发红包，企业应用id，整型，可在企业微信管理端应用的设置页面查看。与sender_name互斥，二者只能填一个。
   * 必填：否
   */
  @XStreamAlias("agentid")
  private String agentId;

  /**
   * 发送者头像
   * 发送者头像素材id，通过企业微信开放上传素材接口获取
   * 必填：否
   */
  @XStreamAlias("sender_header_media_id")
  private String senderHeaderMediaId;

  /**
   * 用户openid
   * 接受红包的用户.用户在wxappid下的openid。
   * 必填：是
   */
  @Required
  @XStreamAlias("re_openid")
  private String reOpenid;

  /**
   * 金额
   * 单位分，单笔最小金额默认为1元
   * 必填：是
   */
  @Required
  @XStreamAlias("total_amount")
  private Integer totalAmount;

  /**
   * 红包祝福语
   * 必填：是
   */
  @Required
  @XStreamAlias("wishing")
  private String wishing;

  /**
   * 项目名称
   * 必填：是
   */
  @Required
  @XStreamAlias("act_name")
  private String actName;

  /**
   * 备注
   * 必填：是
   */
  @Required
  @XStreamAlias("remark")
  private String remark;

  /**
   * 场景
   * 发放红包使用场景，红包金额大于200时必传
   * PRODUCT_1:商品促销
   * PRODUCT_2:抽奖
   * PRODUCT_3:虚拟物品兑奖
   * PRODUCT_4:企业内部福利
   * PRODUCT_5:渠道分润
   * PRODUCT_6:保险回馈
   * PRODUCT_7:彩票派奖
   * PRODUCT_8:税务刮奖
   */
  @XStreamAlias("scene_id")
  private String sceneId;


  @Override
  protected boolean ignoreAppid() {
    return true;
  }

  @Override
  protected boolean ignoreSubAppId() {
    return true;
  }

  @Override
  protected boolean ignoreSubMchId() {
    return true;
  }

  @Override
  protected boolean isWxWorkSign() {
    return true;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("mch_billno", mchBillNo);
    map.put("wxappid", wxAppId);
    map.put("sender_name", senderName);
    map.put("agentid", agentId);
    map.put("sender_header_media_id", senderHeaderMediaId);
    map.put("re_openid", reOpenid);
    map.put("total_amount", totalAmount.toString());
    map.put("wishing", wishing);
    map.put("act_name", actName);
    map.put("remark", remark);
    map.put("scene_id", sceneId);
  }
}
