package me.chanjar.weixin.mp.bean.membercard;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 拉取会员信息返回的结果
 *
 * 字段格式参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283  6.2.1小节的步骤5
 *
 * @author YuJian
 * @version 2017/7/9
 */
public class WxMpMemberCardUserInfoResult implements Serializable {

  private static final long serialVersionUID = 9084777967442098311L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private String nickname;

  private String membershipNumber;

  private Integer bonus;

  private String sex;

  private MemberCardUserInfo userInfo;

  private String userCardStatus;

  private Boolean hasActive;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getMembershipNumber() {
    return membershipNumber;
  }

  public void setMembershipNumber(String membershipNumber) {
    this.membershipNumber = membershipNumber;
  }

  public Integer getBonus() {
    return bonus;
  }

  public void setBonus(Integer bonus) {
    this.bonus = bonus;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public MemberCardUserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(MemberCardUserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public String getUserCardStatus() {
    return userCardStatus;
  }

  public void setUserCardStatus(String userCardStatus) {
    this.userCardStatus = userCardStatus;
  }

  public Boolean getHasActive() {
    return hasActive;
  }

  public void setHasActive(Boolean hasActive) {
    this.hasActive = hasActive;
  }

  @Override
  public String toString() {
    return "WxMpMemberCardUserInfoResult{" +
      "errorCode='" + errorCode + '\'' +
      ", errorMsg='" + errorMsg + '\'' +
      ", openId='" + openId + '\'' +
      ", nickname='" + nickname + '\'' +
      ", membershipNumber='" + membershipNumber + '\'' +
      ", bonus=" + bonus +
      ", sex='" + sex + '\'' +
      ", userInfo=" + userInfo +
      ", userCardStatus='" + userCardStatus + '\'' +
      ", hasActive=" + hasActive +
      '}';
  }

  public static WxMpMemberCardUserInfoResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardUserInfoResult.class);
  }
}

