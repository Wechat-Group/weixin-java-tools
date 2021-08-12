package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.*;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author yuanqixun
 */
@Data
public final class MemberCard implements Serializable {
  private static final long serialVersionUID = 2922028551810647622L;

  /**
   * 会员卡背景图.
   */
  @SerializedName("background_pic_url")
  private String backgroundPicUrl;

  /**
   * 基本信息.
   */
  @SerializedName("base_info")
  private BaseInfo baseInfo;

  /**
   * 特权说明.
   */
  @SerializedName("prerogative")
  private String prerogative;

  /**
   * 自动激活.
   */
  @SerializedName("auto_activate")
  private boolean autoActivate;

  /**
   * 显示积分.
   */
  @SerializedName("supply_bonus")
  private boolean supplyBonus;

  /**
   * 查看积分外链,设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段.
   */
  @SerializedName("bonus_url")
  private String bonusUrl;

  /**
   * 支持储值.
   */
  @SerializedName("supply_balance")
  private boolean supplyBalance;

  /**
   * 余额外链,仅适用于余额无法通过激活接口同步的情况下使用该字段.
   */
  @SerializedName("balance_url")
  private String balanceUrl;

  /**
   * 自定义会员类目1,会员卡激活后显示.
   */
  @SerializedName("custom_field1")
  private CustomField customField1;

  /**
   * 自定义会员类目2.
   */
  @SerializedName("custom_field2")
  private CustomField customField2;

  /**
   * 自定义会员类目3.
   */
  @SerializedName("custom_field3")
  private CustomField customField3;

  /**
   * 积分清零规则.
   */
  @SerializedName("bonus_cleared")
  private String bonusCleared;

  /**
   * 积分规则.
   */
  @SerializedName("bonus_rules")
  private String bonusRules;

  /**
   * 储值规则.
   */
  @SerializedName("balance_rules")
  private String balanceRules;

  /**
   * 激活会员卡的url.
   */
  @SerializedName("activate_url")
  private String activateUrl;

  /**
   * 激活会原卡url对应的小程序user_name，仅可跳转该公众号绑定的小程序.
   */
  @SerializedName("activate_app_brand_user_name")
  private String activateAppBrandUserName;

  /**
   * 激活会原卡url对应的小程序path.
   */
  @SerializedName("activate_app_brand_pass")
  private String activateAppBrandPass;

  /**
   * 自定义会员信息类目，会员卡激活后显示.
   */
  @SerializedName("custom_cell1")
  private CustomCell1 customCell1;

  /**
   * 自定义会员信息类目，会员卡激活后显示.
   */
  @SerializedName("custom_cell2")
  private CustomCell1 customCell2;


  /**
   * 自定义会员信息类目，会员卡激活后显示.
   */
  @SerializedName("custom_cell3")
  private CustomCell1 customCell3;

  /**
   * 积分规则,JSON结构积分规则.
   */
  @SerializedName("bonus_rule")
  private BonusRule bonusRule;

  /**
   * 折扣,该会员卡享受的折扣优惠,填10就是九折.
   */
  private Integer discount;

  /**
   * 创建优惠券特有的高级字段.
   */
  @SerializedName("advanced_info")
  private AdvancedInfo advancedInfo;

  /**
   * 是否支持一键激活 ，填true或false.
   */
  @SerializedName("wx_activate")
  private boolean wxActivate;

  /**
   * 是否支持跳转型一键激活，填true或false.
   */
  @SerializedName("wx_activate_after_submit")
  private boolean wxActivateAfterSubmit;

  /**
   * 跳转型一键激活跳转的地址链接，请填写http:// 或者https://开头的链接.
   */
  @SerializedName("wx_activate_after_submit_url")
  private String wxActivateAfterSubmitUrl;

  /**
   * 参照https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1499332673_Unm7V卡券内跳转小程序
   */
  /**
   * 积分信息类目对应的小程序 user_name，格式为原始id+@app
   */
  @SerializedName("bonus_app_brand_user_name")
  private String bonusAppBrandUserName;
  /**
   *积分入口小程序的页面路径
   */
  @SerializedName("bonus_app_brand_pass")
  private String bonusAppBrandPass;
  /**
   *余额信息类目对应的小程序 user_name，格式为原始id+@app
   */
  @SerializedName("balance_app_brand_user_name")
  private String balanceAppBrandUserName;
  /**
   *余额入口小程序的页面路径
   */
  @SerializedName("balance_app_brand_pass")
  private String balanceAppBrandPass;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static MemberCard fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, MemberCard.class);
  }
}
