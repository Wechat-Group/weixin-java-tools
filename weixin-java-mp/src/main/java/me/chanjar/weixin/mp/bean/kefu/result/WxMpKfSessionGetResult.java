package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author Binary Wang
 */
@Data
public class WxMpKfSessionGetResult implements Serializable {
  private static final long serialVersionUID = 8474846575200033152L;

  /**
   * kf_account 正在接待的客服，为空表示没有人在接待
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * createtime 会话接入的时间 ，UNIX时间戳
   */
  @SerializedName("createtime")
  private long createTime;

  public static WxMpKfSessionGetResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpKfSessionGetResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
