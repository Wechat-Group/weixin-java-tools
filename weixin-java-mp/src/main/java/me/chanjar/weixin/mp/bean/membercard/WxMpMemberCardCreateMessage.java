package me.chanjar.weixin.mp.bean.membercard;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
public class WxMpMemberCardCreateMessage implements Serializable {

  /**
   * 会员卡背景图
   */
  @SerializedName("background_pic_url")
  private String backgroundPicUrl;

  /**
   * 基本信息
   */
  @SerializedName("base_info")
  private BaseInfo baseInfo;

  /**
   * 特权说明
   */
  @NotNull
  private String prerogative;

  /**
   * 自动激活
   */
  @SerializedName("auto_activate")
  private boolean autoActivate;

  /**
   * 是否一键开卡
   */
  @SerializedName("wx_activate")
  private boolean wxActivate;

  /**
   * 显示积分
   */
  @SerializedName("supply_bonus")
  private boolean supplyBonus;

  /**
   * 查看积分外链,设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段。
   */
  @SerializedName("bonus_url")
  private String bonusUrl;

  /**
   * 支持储值
   */
  @NotNull
  @SerializedName("supply_balance")
  private boolean supplyBalance;

  /**
   * 余额外链,仅适用于余额无法通过激活接口同步的情况下使用该字段。
   */
  @SerializedName("balance_url")
  private String balanceUrl;

  /**
   * 自定义会员类目1,会员卡激活后显示
   */
  @SerializedName("custom_field1")
  private CustomField customField1;

  /**
   * 自定义会员类目2
   */
  @SerializedName("custom_field2")
  private CustomField customField2;

  /**
   * 自定义会员类目3
   */
  @SerializedName("custom_field3")
  private CustomField customField3;

  /**
   * 积分清零规则
   */
  @SerializedName("bonus_cleared")
  private String bonusCleared;

  /**
   * 积分规则
   */
  @SerializedName("bonus_rules")
  private String bonusRules;

  /**
   * 储值规则
   */
  @SerializedName("balance_rules")
  private String balanceRules;

  /**
   * 激活会员卡的url
   */
  @SerializedName("activate_url")
  private String activateUrl;

  /**
   * 激活会原卡url对应的小程序user_name，仅可跳转该公众号绑定的小程序
   */
  @SerializedName("activate_app_brand_user_name")
  private String activateAppBrandUserName;

  /**
   * 激活会原卡url对应的小程序path
   */
  @SerializedName("activate_app_brand_pass")
  private String activateAppBrandPass;

  /**
   * 自定义会员信息类目，会员卡激活后显示。
   */
  @SerializedName("custom_cell1")
  private CustomCell1 customCell1;

  /**
   * 积分规则,JSON结构积分规则 。
   */
  @SerializedName("bonus_rule")
  private BonusRule bonusRule;

  /**
   * 折扣,该会员卡享受的折扣优惠,填10就是九折。
   */
  private Integer discount;

  /**
   * 创建优惠券特有的高级字段
   */
  @SerializedName("advanced_info")
  private AdvancedInfo advancedInfo;

  public String getBackgroundPicUrl() {
    return backgroundPicUrl;
  }

  public void setBackgroundPicUrl(String backgroundPicUrl) {
    this.backgroundPicUrl = backgroundPicUrl;
  }

  public BaseInfo getBaseInfo() {
    return baseInfo;
  }

  public String getPrerogative() {
    return prerogative;
  }

  public void setPrerogative(String prerogative) {
    this.prerogative = prerogative;
  }


  public boolean getAutoActivate() {
    return autoActivate;
  }

  public void setAutoActivate(boolean autoActivate) {
    this.autoActivate = autoActivate;
  }


  public boolean getWxActivate() {
    return wxActivate;
  }

  public void setWxActivate(boolean wxActivate) {
    this.wxActivate = wxActivate;
  }


  public boolean getSupplyBonus() {
    return supplyBonus;
  }

  public void setSupplyBonus(boolean supplyBonus) {
    this.supplyBonus = supplyBonus;
  }


  public String getBonusUrl() {
    return bonusUrl;
  }

  public void setBonusUrl(String bonusUrl) {
    this.bonusUrl = bonusUrl;
  }


  public boolean getSupplyBalance() {
    return supplyBalance;
  }

  public void setSupplyBalance(boolean supplyBalance) {
    this.supplyBalance = supplyBalance;
  }


  public String getBalanceUrl() {
    return balanceUrl;
  }

  public void setBalanceUrl(String balanceUrl) {
    this.balanceUrl = balanceUrl;
  }


  public String getBonusCleared() {
    return bonusCleared;
  }

  public void setBonusCleared(String bonusCleared) {
    this.bonusCleared = bonusCleared;
  }


  public String getBonusRules() {
    return bonusRules;
  }

  public void setBonusRules(String bonusRules) {
    this.bonusRules = bonusRules;
  }


  public String getBalanceRules() {
    return balanceRules;
  }

  public void setBalanceRules(String balanceRules) {
    this.balanceRules = balanceRules;
  }


  public String getActivateUrl() {
    return activateUrl;
  }

  public void setActivateUrl(String activateUrl) {
    this.activateUrl = activateUrl;
  }


  public String getActivateAppBrandUserName() {
    return activateAppBrandUserName;
  }

  public void setActivateAppBrandUserName(String activateAppBrandUserName) {
    this.activateAppBrandUserName = activateAppBrandUserName;
  }


  public String getActivateAppBrandPass() {
    return activateAppBrandPass;
  }

  public void setActivateAppBrandPass(String activateAppBrandPass) {
    this.activateAppBrandPass = activateAppBrandPass;
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public CustomField getCustomField1() {
    return customField1;
  }

  public void setCustomField1(CustomField customField1) {
    this.customField1 = customField1;
  }

  public CustomField getCustomField2() {
    return customField2;
  }

  public void setCustomField2(CustomField customField2) {
    this.customField2 = customField2;
  }

  public CustomField getCustomField3() {
    return customField3;
  }

  public void setCustomField3(CustomField customField3) {
    this.customField3 = customField3;
  }

  public CustomCell1 getCustomCell1() {
    return customCell1;
  }

  public void setCustomCell1(CustomCell1 customCell1) {
    this.customCell1 = customCell1;
  }

  public BonusRule getBonusRule() {
    return bonusRule;
  }

  public void setBonusRule(BonusRule bonusRule) {
    this.bonusRule = bonusRule;
  }

  public AdvancedInfo getAdvancedInfo() {
    return advancedInfo;
  }

  public void setAdvancedInfo(AdvancedInfo advancedInfo) {
    this.advancedInfo = advancedInfo;
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
