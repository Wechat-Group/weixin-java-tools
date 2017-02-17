package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * Created by Binary Wang on 2016/7/18.
 */
public class WxMpKfMsgRecord {
  /**
   * worker	完整客服帐号，格式为：帐号前缀@公众号微信号
   */
  @SerializedName("worker")
  private String worker;

  /**
   * openid	用户标识
   */
  @SerializedName("openid")
  private String openid;

  /**
   * opercode	操作码，2002（客服发送信息），2003（客服接收消息）
   */
  @SerializedName("opercode")
  private Integer operateCode;

  /**
   * text	聊天记录
   */
  @SerializedName("text")
  private String text;

   /**
   * time	操作时间，unix时间戳
   */
  @SerializedName("time")
  private Long time;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getWorker() {
    return this.worker;
  }

  public void setWorker(String worker) {
    this.worker = worker;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Long getTime() {
    return this.time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Integer getOperateCode() {
    return this.operateCode;
  }

  public void setOperateCode(Integer operateCode) {
    this.operateCode = operateCode;
  }
}
