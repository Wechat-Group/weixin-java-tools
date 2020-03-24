package com.github.binarywang.wxpay.bean.profitsharing;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * @author Wang GuangXin 2019/10/22 11:07
 * @version 1.0
 */
public class Receiver implements Serializable {
  private String type;
  private String account;
  private Integer amount;
  private String description;
  private String name;
  private String relationType;
  private String customRelation;

  /**
   * 此构造函数用于单次分账
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
  public Receiver(String type, String account, Integer amount, String description) {
    this.type = type;
    this.account = account;
    this.amount = amount;
    this.description = description;
  }

  /**
   * 此构造用于添加分账方
   *
   * @param type           MERCHANT_ID：商户ID
   *                       PERSONAL_WECHATID：个人微信号PERSONAL_OPENID：个人openid（由父商户APPID转换得到）PERSONAL_SUB_OPENID: 个人sub_openid（由子商户APPID转换得到）
   * @param account        类型是MERCHANT_ID时，是商户ID
   *                       类型是PERSONAL_WECHATID时，是个人微信号
   *                       类型是PERSONAL_OPENID时，是个人openid
   *                       类型是PERSONAL_SUB_OPENID时，是个人sub_openid
   * @param name           分账接收方类型是MERCHANT_ID时，是商户全称（必传）
   *                       分账接收方类型是PERSONAL_NAME 时，是个人姓名（必传）
   *                       分账接收方类型是PERSONAL_OPENID时，是个人姓名（选传，传则校验）
   *                       分账接收方类型是PERSONAL_SUB_OPENID时，是个人姓名（选传，传则校验）
   * @param relationType   子商户与接收方的关系。
   *                       本字段值为枚举：
   *                       SERVICE_PROVIDER：服务商
   *                       STORE：门店
   *                       STAFF：员工
   *                       STORE_OWNER：店主
   *                       PARTNER：合作伙伴
   *                       HEADQUARTER：总部
   *                       BRAND：品牌方
   *                       DISTRIBUTOR：分销商
   *                       USER：用户
   *                       SUPPLIER：供应商
   *                       CUSTOM：自定义
   * @param customRelation 子商户与接收方具体的关系，本字段最多10个字。
   *                       当字段relation_type的值为CUSTOM时，本字段必填
   *                       当字段relation_type的值不为CUSTOM时，本字段无需填写
   */
  public Receiver(String type, String account, String name, String relationType, String customRelation) {
    this.type = type;
    this.account = account;
    this.name = name;
    this.relationType = relationType;
    this.customRelation = customRelation;
  }

  /**
   * 用于删除分账接受方
   *
   * @param type    MERCHANT_ID：商户ID
   *                PERSONAL_WECHATID：个人微信号PERSONAL_OPENID：个人openid（由父商户APPID转换得到）PERSONAL_SUB_OPENID: 个人sub_openid（由子商户APPID转换得到）
   * @param account 类型是MERCHANT_ID时，是商户ID
   *                类型是PERSONAL_WECHATID时，是个人微信号
   *                类型是PERSONAL_OPENID时，是个人openid
   *                类型是PERSONAL_SUB_OPENID时，是个人sub_openid
   */
  public Receiver(String type, String account) {
    this.type = type;
    this.account = account;
  }

  public String toJSONString() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    Gson gson = gsonBuilder.create();
    return gson.toJson(this);
  }

  public String getType() {
    return type;
  }

  public String getAccount() {
    return account;
  }

  public Integer getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getRelationType() {
    return relationType;
  }

  public String getCustomRelation() {
    return customRelation;
  }
}
