package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 会员卡更新对象
 * @author yuanqixun
 */
@Data
public final class MemberCardUpdate implements Serializable {
  private static final long serialVersionUID = -4755309390784904858L;

  //以下字段顺序根据微信官方文档顺序相同，不能传入非文档之外的字段
  //https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1466494654_K9rNz

  /**
   * 基本信息.
   */
  @SerializedName("base_info")
  private BaseInfoUpdate baseInfo;

  /**
   * 会员卡背景图.
   */
  @SerializedName("background_pic_url")
  private String backgroundPicUrl;

  /**
   * 是否支持积分，仅支持从false变为true，默认为false
   */
  @SerializedName("supply_bonus")
  private Boolean supplyBonus;

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
   * 查看积分外链,设置跳转外链查看积分详情。仅适用于积分无法通过激活接口同步的情况下使用该字段.
   */
  @SerializedName("bonus_url")
  private String bonusUrl;

  /**
   * 余额外链,仅适用于余额无法通过激活接口同步的情况下使用该字段.
   */
  @SerializedName("balance_url")
  private String balanceUrl;

  /**
   * 是否支持储值，仅支持从false变为true，默认为fals e 该字段须开通储值功能后方可使用， 详情见： 获取特殊权限
   */
  @SerializedName("supply_balance")
  private Boolean supplyBalance;

  /**
   * 储值规则.
   */
  @SerializedName("balance_rules")
  private String balanceRules;

  /**
   * 特权说明.
   */
  @SerializedName("prerogative")
  private String prerogative;

  /**
   * 自动激活.
   */
  @SerializedName("auto_activate")
  private Boolean autoActivate;

  /**
   * 是否一键开卡.
   */
  @SerializedName("wx_activate")
  private Boolean wxActivate;

  /**
   * 激活会员卡的url.
   */
  @SerializedName("activate_url")
  private String activateUrl;

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


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static MemberCardUpdate fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, MemberCardUpdate.class);
  }
}
