package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;

/**
 * 客服基本信息以及客服在线状态信息
 *
 * @author Binary Wang
 */
public class WxMpKfInfo implements Serializable {
  private static final long serialVersionUID = -5877300750666022290L;

  /**
   * kf_account 完整客服账号，格式为：账号前缀@公众号微信号
   */
  @SerializedName("kf_account")
  private String account;

  /**
   * kf_headimgurl 客服头像地址
   */
  @SerializedName("kf_headimgurl")
  private String headImgUrl;

  /**
   * kf_id 客服工号
   */
  @SerializedName("kf_id")
  private String id;

  /**
   * kf_nick  客服昵称
   */
  @SerializedName("kf_nick")
  private String nick;

  /**
   * kf_wx 如果客服帐号已绑定了客服人员微信号，则此处显示微信号
   */
  @SerializedName("kf_wx")
  private String wxAccount;

  /**
   * invite_wx 如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请，则此处显示绑定邀请的微信号
   */
  @SerializedName("invite_wx")
  private String inviteWx;

  /**
   * invite_expire_time 如果客服帐号尚未绑定微信号，但是已经发起过一个绑定邀请，则此处显示为邀请的过期时间，为unix 时间戳
   */
  @SerializedName("invite_expire_time")
  private Long inviteExpireTime;

  /**
   * invite_status 邀请的状态，有等待确认“waiting”，被拒绝“rejected”，过期“expired”
   */
  @SerializedName("invite_status")
  private String inviteStatus;

  /**
   * status 客服在线状态，目前为：1、web 在线
   */
  @SerializedName("status")
  private Integer status;

  /**
   * accepted_case 客服当前正在接待的会话数
   */
  @Expose
  @SerializedName("accepted_case")
  private Integer acceptedCase;

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getAcceptedCase() {
    return this.acceptedCase;
  }

  public void setAcceptedCase(Integer acceptedCase) {
    this.acceptedCase = acceptedCase;
  }

  public String getAccount() {
    return this.account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getHeadImgUrl() {
    return this.headImgUrl;
  }

  public void setHeadImgUrl(String headImgUrl) {
    this.headImgUrl = headImgUrl;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNick() {
    return this.nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getWxAccount() {
    return this.wxAccount;
  }

  public void setWxAccount(String wxAccount) {
    this.wxAccount = wxAccount;
  }

  public String getInviteWx() {
    return this.inviteWx;
  }

  public void setInviteWx(String inviteWx) {
    this.inviteWx = inviteWx;
  }

  public Long getInviteExpireTime() {
    return this.inviteExpireTime;
  }

  public void setInviteExpireTime(Long inviteExpireTime) {
    this.inviteExpireTime = inviteExpireTime;
  }

  public String getInviteStatus() {
    return this.inviteStatus;
  }

  public void setInviteStatus(String inviteStatus) {
    this.inviteStatus = inviteStatus;
  }
}
