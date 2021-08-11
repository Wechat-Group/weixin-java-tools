package com.github.binarywang.wxpay.bean.profitsharingV3;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 微信V3接口 分账接收方实体
 * @author pg
 * @date 2021-6-25
 *
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingReceiver implements Serializable {
  private static final long serialVersionUID = -4391888575149767840L;

  /**
   * <pre>
   * 字段名：应用ID
   * 是否必填：是
   * 描述：微信分配的商户appid
   * </pre>
   */
  @SerializedName("appid")
  private String appid;

  /**
   * <pre>
   * 字段名：分账接收方类型
   * 是否必填：是
   * 描述：
   * 1、MERCHANT_ID：商户号
   * 2、PERSONAL_OPENID：个人openid（由父商户APPID转换得到）
   * </pre>
   */
  @SerializedName("type")
  private String type;

  /**
   * <pre>
   * 字段名：分账接收方帐号
   * 是否必填：是
   * 描述：
   * 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号
   * 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
   * </pre>
   */
  @SerializedName("account")
  private String account;

  /**
   * <pre>
   * 字段名：分账个人接收方姓名
   * 是否必填：否
   * 描述：
   * 可选项，在接收方类型为个人的时可选填，若有值，会检查与 name 是否实名匹配，不匹配会拒绝分账请求
   * 1、分账接收方类型是PERSONAL_OPENID，是个人姓名的密文（选传，传则校验） 此字段的加密方法详见：敏感信息加密说明
   * 2、使用微信支付平台证书中的公钥
   * 3、使用RSAES-OAEP算法进行加密
   * 4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
   * </pre>
   */
  @SerializedName("name")
  @SpecEncrypt
  private String name;

  /**
   * <pre>
   * 字段名：与分账方的关系类型
   * 是否必填：是
   * 描述：子商户与接收方的关系。 本字段值为枚举：
   * STORE：门店
   * STAFF：员工
   * STORE_OWNER：店主
   * PARTNER：合作伙伴
   * HEADQUARTER：总部
   * BRAND：品牌方
   * DISTRIBUTOR：分销商
   * USER：用户
   * SUPPLIER： 供应商
   * CUSTOM：自定义
   * </pre>
   */
  @SerializedName("relation_type")
  private String relationType;

  /**
   * <pre>
   * 字段名：自定义的分账关系
   * 是否必填：是
   * 描述：子商户与接收方具体的关系，本字段最多10个字。
   * 当字段relationType的值为CUSTOM时，本字段必填;
   * 当字段relationType的值不为CUSTOM时，本字段无需填写。
   * </pre>
   */
  @SerializedName("custom_relation")
  private String customRelation;

  /**
   * <pre>
   * 字段名：分账描述
   * 是否必填：是
   * 描述： 分账的原因描述，分账账单中需要体现
   * </pre>
   */
  private String description;
  /**
   * <pre>
   * 字段名：分账金额
   * 是否必填：是
   * 描述： 分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
   * </pre>
   */
  private Long amount;

  /**
   * 此构造函数用于分账接口
   *
   * @param type        MERCHANT_ID：商户ID
   *                    PERSONAL_WECHATID：个人微信号PERSONAL_OPENID：个人openid（由父商户APPID转换得到）PERSONAL_SUB_OPENID: 个人sub_openid（由子商户APPID转换得到）
   * @param account     类型是MERCHANT_ID时，是商户ID
   *                    类型是PERSONAL_WECHATID时，是个人微信号
   *                    类型是PERSONAL_OPENID时，是个人openid
   *                    类型是PERSONAL_SUB_OPENID时，是个人sub_openid
   * @param amount      分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
   * @param description 分账的原因描述，分账账单中需要体现
   */
  public ProfitSharingReceiver(String type, String account, Long amount, String description) {
    this.type = type;
    this.account = account;
    this.amount = amount;
    this.description = description;
  }

}
