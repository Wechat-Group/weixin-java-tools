package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * @author Binary Wang
 */
public class WxMpKfSession {
  /**
   * kf_account 正在接待的客服，为空表示没有人在接待
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * createtime 会话接入的时间，UNIX时间戳
   * 该返回值 存在于 获取客服会话列表接口
   */
  @SerializedName("createtime")
  private long createTime;

  /**
   * latest_time 粉丝的最后一条消息的时间，UNIX时间戳
   * 该返回值 存在于 获取未接入会话列表接口
   */
  @SerializedName("latest_time")
  private long latestTime;

  /**
   * openid 客户openid
   */
  @SerializedName("openid")
  private String openid;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getKfAccount() {
    return this.kfAccount;
  }

  public void setKfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public long getLatestTime() {
    return this.latestTime;
  }

  public void setLatestTime(long latestTime) {
    this.latestTime = latestTime;
  }
}
