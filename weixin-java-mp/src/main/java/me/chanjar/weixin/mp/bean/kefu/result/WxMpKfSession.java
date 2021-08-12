package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author Binary Wang
 */
@Data
public class WxMpKfSession implements Serializable {
  private static final long serialVersionUID = 7804332813164994062L;

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
    return WxMpGsonBuilder.create().toJson(this);
  }

}
