package me.chanjar.weixin.mp.bean.membercard;

import com.google.gson.annotations.SerializedName;

/**
 * 会员卡激活接口的参数
 *
 * @author YuJian(mgcnrx11@hotmail.com)
 * @version 2017/7/8
 */
public class WxMpMemberCardActivatedMessage {

  // 会员卡编号，由开发者填入，作为序列号显示在用户的卡包里。可与Code码保持等值。
  @SerializedName("membership_number")
  private String membershipNumber;
  // 领取会员卡用户获得的code
  private String code;
  // 卡券ID,自定义code卡券必填
  @SerializedName("card_id")
  private String cardId;
  // 商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错。卡面设计请遵循微信会员卡自定义背景设计规范
  @SerializedName("background_pic_url")
  private String backgroundPicUrl;
  // 激活后的有效起始时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
  @SerializedName("activate_begin_time")
  private Integer activateBeginTime;
  // 激活后的有效截至时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
  @SerializedName("activate_end_time")
  private Integer activateEndTime;
  // 初始积分，不填为0。
  @SerializedName("init_bonus")
  private Integer initBonus;
  // 积分同步说明。
  @SerializedName("init_bonus_record")
  private String initBonusRecord;
  // 初始余额，不填为0。
  @SerializedName("init_balance")
  private Integer initBalance;
  // 创建时字段custom_field1定义类型的初始值，限制为4个汉字，12字节。
  @SerializedName("init_custom_field_value1")
  private String initCustomFieldValue1;
  // 创建时字段custom_field2定义类型的初始值，限制为4个汉字，12字节。
  @SerializedName("init_custom_field_value2")
  private String initCustomFieldValue2;
  // 创建时字段custom_field3定义类型的初始值，限制为4个汉字，12字节。
  @SerializedName("init_custom_field_value3")
  private String initCustomFieldValue3;

  public String getMembershipNumber() {
    return membershipNumber;
  }

  public void setMembershipNumber(String membershipNumber) {
    this.membershipNumber = membershipNumber;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public String getBackgroundPicUrl() {
    return backgroundPicUrl;
  }

  public void setBackgroundPicUrl(String backgroundPicUrl) {
    this.backgroundPicUrl = backgroundPicUrl;
  }

  public Integer getActivateBeginTime() {
    return activateBeginTime;
  }

  public void setActivateBeginTime(Integer activateBeginTime) {
    this.activateBeginTime = activateBeginTime;
  }

  public Integer getActivateEndTime() {
    return activateEndTime;
  }

  public void setActivateEndTime(Integer activateEndTime) {
    this.activateEndTime = activateEndTime;
  }

  public Integer getInitBonus() {
    return initBonus;
  }

  public void setInitBonus(Integer initBonus) {
    this.initBonus = initBonus;
  }

  public String getInitBonusRecord() {
    return initBonusRecord;
  }

  public void setInitBonusRecord(String initBonusRecord) {
    this.initBonusRecord = initBonusRecord;
  }

  public Integer getInitBalance() {
    return initBalance;
  }

  public void setInitBalance(Integer initBalance) {
    this.initBalance = initBalance;
  }

  public String getInitCustomFieldValue1() {
    return initCustomFieldValue1;
  }

  public void setInitCustomFieldValue1(String initCustomFieldValue1) {
    this.initCustomFieldValue1 = initCustomFieldValue1;
  }

  public String getInitCustomFieldValue2() {
    return initCustomFieldValue2;
  }

  public void setInitCustomFieldValue2(String initCustomFieldValue2) {
    this.initCustomFieldValue2 = initCustomFieldValue2;
  }

  public String getInitCustomFieldValue3() {
    return initCustomFieldValue3;
  }

  public void setInitCustomFieldValue3(String initCustomFieldValue3) {
    this.initCustomFieldValue3 = initCustomFieldValue3;
  }
}
