package me.chanjar.weixin.mp.bean.kefu.request;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

@Data
public class WxMpKfSessionRequest implements Serializable {
  private static final long serialVersionUID = -5451863610674856927L;

  /**
   * kf_account 完整客服账号，格式为：账号前缀@公众号微信号
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * openid 客户openid
   */
  @SerializedName("openid")
  private String openid;

  public WxMpKfSessionRequest(String kfAccount, String openid) {
    this.kfAccount = kfAccount;
    this.openid = openid;
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
